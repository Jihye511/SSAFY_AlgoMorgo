### 주요 용어

- Principal : 인증된 사용자
- Secured Resource : 보안이 적용되는 리소스 : URL, API 엔드포인트, 파일 등
- Authentication(인증) : Principal이 믿을 수 있는지 파악하는 것으로 일반적으로 ID/PW 체크
- Authorization(권한) : 인증이 완료된 Principal이 어떤 행위를 할 권한이 있는지 확인
    - role에 기반하여 접근 가능한 자원 필터링

### 특징

- 인증,권한부여, 일반적인 공격(CSRF)에 대해 보호 기능을 제공
- 컨테이너와 무관하게 적용
    - WAR, EAR 형태로 배포 가능
    - Servlet Application에서는 Filter기반으로 동작
    - **Spring Security는 Spring만을 위해 만들어진게 아니다**
- 관점의 분리
    - @Transactional 처럼 보안적인 이슈와 business login의 분리
- 유연성
    - 다양한 인증 방법 사용 가능 : basic, form. x.509, cookies 등

![image](https://github.com/user-attachments/assets/666dd84f-5c9d-4081-badb-062d68d7ddc9)


### 1. 요청(Request) 입력

- 사용자가 `username` (principal)과 `password` (credential)를 입력하여 서버로 요청을 보냅니다.

---

### 2. Servlet Filter Chain

- 요청은 **Servlet Filter Chain**을 거칩니다.
- `DelegatingFilterProxy`가 Spring Security의 필터 체인(Security Filter Chain)으로 위임합니다.

---

### 3. Security Filter Chain

- 다양한 **Security Filter** 들이 순차적으로 요청을 처리합니다.
- 여기서 중요한 필터:
    - **AuthenticationFilter**: 인증을 담당
    - **AuthorizationFilter**: 권한을 담당

---

### 4. 인증(Authentication) 과정

1. **Authentication Filter**는 사용자의 `principal`(username)과 `credential`(userpassword)을 묶어서 `Authentication` 객체를 생성합니다.
    1. 인증이 완료되면 보안을 위해 credential 삭제
2. 이를 **AuthenticationManager** (주로 `ProviderManager`)에 전달합니다.
3. **AuthenticationProvider**는 실제 인증을 수행합니다.
    1. 순차적으로 인증 요청
    2. 인증 처리 유형 : Dao Authenticaion Provider, JwtAuthenticationProvider 
4. 인증 과정:
    - **UserDetailsService**가 사용자 정보를 데이터베이스 등에서 조회합니다.
    - 조회된 사용자 정보(`UserDetails`)와 입력받은 비밀번호를 비교합니다.
    - 비밀번호가 일치하면 인증 성공, 아니면 **AuthenticationException** 발생.
5. 인증이 성공하면, 인증된 **Authentication** 객체(사용자 정보, 권한 포함)를 반환합니다.

---

### 5. 인증 정보 저장

- 성공한 인증 정보는 **SecurityContext**에 저장되어 세션이나 ThreadLocal 등에 보관됩니다.
    - (SecurityContext: 인증 및 권한 정보를 관리하는 곳)

---

### 6. 인가(Authorization) 과정

- 요청된 리소스에 대해 **AuthorizationFilter**와 **AuthorizationManager**가 사용자의 권한을 검사합니다.
- 사용자가 필요한 권한을 가지고 있으면 계속 진행(`continue`)하고,
- 권한이 없으면 **AccessDeniedException**을 발생시켜 접근을 차단합니다.
- 권한(role)은 계층적으로 관리된다

---

### 요약

- **filter**
- **Authentication**: 사용자가 누구인지 확인하는 과정 (로그인)
- **Authorization**: 사용자가 요청한 자원에 접근할 수 있는 권한이 있는지 확인하는 과정 (권한 체크)

---

### 정리

- SecurityContext : 현재 사용자에 대한 Authentication을 세션에 보관
- AuthenticaionManager : 인증 담당 객체
- AuthorizationManager:  권한 담당 객체
- **Spring Security는 필터를 거친후 DispatcherServlet에서 동작**
    - 요청을 모두 처리하기 위해
- DelegatingFilterProxy : Spring에서 제공해주는 Filter
    - 서블렛 컨테이너와 Spring WebApplicationContext 사이의 브릿지 역할

Multiple SecurityFilterChain

- 필터 체인 프록시는 경로 기반으로 다양한 Security Filter Chain보유 가능
- 경로 기반으로 순서대로 filter 확인
- @Order(1) 을 통해 필터의 순서를 지정
