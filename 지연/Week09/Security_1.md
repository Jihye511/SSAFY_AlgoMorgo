# Framework(Spring) : Security 1

---

# ▶️ Security

## 핵심 개념

- **Principal**
    - 보호하고자 하는 리소스에 접근하는 "사용자" (혹은 시스템) 주체
- **Secured Resource**
    - 인증, 인가를 요구하는 보호된 리소스(API, 페이지 등)
- **Authentication(인증)**
    - "너는 누구냐?" → 사용자의 신원을 확인
- **Authorization(권한, 인가)**
    - "너 이거 할 수 있어?" → 사용자가 요청한 작업/리소스에 대한 권한 확인

---

## Spring Security 특징

- 인증, 인가, 일반적 보안 위협(CSRF, 세션 공격 등)에 대한 방어 기능 제공
- 서블릿 컨테이너(Tomcat 등)에 **직접 의존하지 않고** 동작 가능
- **관심사 분리(Separation of Concerns)**
    - 인증, 권한, 비즈니스 로직 분리
- **유연성**
    - 커스터마이징이 용이 (AuthenticationProvider, Filter, etc.)

---

## Authentication과 Authorization 절차

> 크게 3단계로 구성
> 

![image.png](attachment:f430dbc6-b316-4249-bbac-cf91bbd73781:image.png)

1. **Authentication(인증)**
    
    로그인 → 사용자 인증 정보(Authentication 객체) 생성
    
2. **Security Context에 저장**
    
    인증된 사용자 정보를 `SecurityContext`에 저장 (ThreadLocal 기반)
    
3. **Authorization(인가)**
    
    요청마다 사용자의 권한을 검사하여 리소스 접근 허용/거부
    

---

## Filter와 SecurityFilterChain

### Spring Security 필터 흐름

- Spring Security는 **Servlet Filter**로 동작
- **DelegatingFilterProxy**가 서블릿 컨테이너에 등록되며, 실제 로직은 Spring Bean으로 관리
- DelegatingFilterProxy → 내부적으로 **FilterChainProxy** 호출 → 그 안에 여러 **SecurityFilterChain**이 존재

---

## 1. FilterChainProxy와 SecurityFilterChain

### 주요 Security Filter 종류

| 필터명 | 역할 | 시점 |
| --- | --- | --- |
| **CsrfFilter** | CSRF 공격 방어 (POST, PUT 요청에 대한 토큰 검증) | 인증/인가 검사 **이전** |
| **LogoutFilter** | `/logout` URL 감지 → 세션 무효화, 로그아웃 처리 | 인증 검사 **이전** |
| **UsernamePasswordAuthenticationFilter** | `/login` 요청 처리 → ID/PW 인증 | 로그인 시점 |
| **RememberMeAuthenticationFilter** | 세션 만료 시 remember-me 쿠키로 인증 시도 | 인증 실패 후 보조 인증 시도 |
| **AuthorizationFilter** (또는 **FilterSecurityInterceptor**) | 리소스 접근 권한 체크 (인가) | 인증 완료 **후 최종 인가** |

### Multiple SecurityFilterChain

- **FilterChainProxy**는 여러 **SecurityFilterChain**을 가질 수 있다.
- 경로별로 다른 Security 설정 적용 가능 (예: `/admin/**`와 `/user/**` 따로 처리)
- 요청 경로를 기준으로 가장 먼저 매칭되는 SecurityFilterChain이 적용된다.

---

## 2. Servlet Authentication Architecture

![image.png](attachment:66d9577a-0a4d-4cd2-998a-833d905076b6:image.png)

### 인증 구조 핵심 컴포넌트

- **SecurityContext**
    - 현재 스레드에 인증 정보를 저장 (Authentication 객체 저장)
- **AuthenticationManager**
    - 인증 요청을 실제로 수행하는 인터페이스
- **ProviderManager**
    - AuthenticationManager 구현체 (여러 AuthenticationProvider를 순차적으로 위임 호출)
- **AuthenticationProvider**
    - 특정 방식(예: 폼 로그인, JWT 인증 등)에 맞게 인증 수행
        - 예) `DaoAuthenticationProvider` (DB 사용자 조회)
        - 예) `JwtAuthenticationProvider` (JWT 토큰 검증)

🔹 흐름 정리

1. 사용자가 로그인 요청 (ID/PW 제출)
2. `UsernamePasswordAuthenticationFilter`가 AuthenticationManager에게 인증 위임
3. `AuthenticationManager`가 등록된 `AuthenticationProvider` 중 적합한 걸 찾아 인증
4. 인증 성공 → Authentication 객체를 SecurityContext에 저장

---

## 3. Servlet Authorization Architecture

### 인가 구조 핵심 컴포넌트

- **SecurityContextHolder**
    - 현재 요청의 인증(Authentication) 객체를 조회
- **AccessDecisionManager**
    - 현재 사용자의 권한을 검사하여 리소스 접근 허용/거부 결정
- **AccessDecisionVoter**
    - AccessDecisionManager 안에서 실제 '찬성/반대'를 투표하는 구성요소
- **SecurityMetadataSource**
    - 요청 URL, 메서드 등과 필요한 권한 정보를 매핑하는 역할
        - ex) `/admin/**` 요청은 ROLE_ADMIN 필요

🔹 흐름 정리

1. 사용자가 보호된 리소스 요청
2. SecurityFilterChain에서 `FilterSecurityInterceptor`가 요청을 가로챔
3. SecurityMetadataSource로부터 해당 URL에 필요한 권한 정보 조회
4. 현재 사용자의 Authentication 객체로 권한 비교
5. AccessDecisionManager가 최종 인가 판단

---

# ✅ 정리 흐름 다이어그램

```
plaintext
복사편집
[요청]
  ↓
[DelegatingFilterProxy]
  ↓
[FilterChainProxy]
  ↓ (경로 기반)
[SecurityFilterChain 선택]
  ↓
[다양한 Security Filters 통과]
  ↓
  인증(Authentication)
    - 인증 성공: SecurityContext 저장
  ↓
  인가(Authorization)
    - 권한 체크
  ↓
[Controller 도착]

```

![image.png](attachment:812f70f2-8c43-4642-aafa-1a3656964e14:image.png)
