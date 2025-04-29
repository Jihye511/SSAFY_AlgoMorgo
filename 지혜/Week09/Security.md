# Spring Security

![image](https://github.com/user-attachments/assets/90b715c3-85d6-4e92-b6da-0478e8966984)


- 인증, 인가 흐름

```yaml
사용자 입력 (ID/PW)
    ↓
UsernamePasswordAuthenticationFilter
    ↓
AuthenticationManager
    ↓
AuthenticationProvider
    ↓
UserDetailsService / PasswordEncoder
    ↓
[Authentication 완료]
    ↓
SecurityContext 저장
    ↓
---
요청 발생 (/admin/**)
    ↓
FilterSecurityInterceptor
    ↓
Authentication 가져옴
    ↓
AccessDecisionManager + Voter
    ↓
허용 → Controller 진입
거부 → 403 Forbidden

```

![image](https://github.com/user-attachments/assets/9137eefd-47b6-4353-8710-53c6f7952f2f)


---

# 🛡️ Authentication (인증) 절차

> "당신이 누구입니까?" 를 확인하는 절차
> 
> 
> 로그인(User ID + PW)을 처리하는 과정입니다.
> 

### 1. 사용자가 로그인 요청

- 사용자가 로그인 폼에 ID/PW를 입력 → 서버로 전송 (`POST /login`)

### 2. Security Filter Chain 진입

- 요청이 서버에 도착하면, 스프링 시큐리티의 **SecurityFilterChain**이 요청을 가로챔
- 그중 **UsernamePasswordAuthenticationFilter** 가 로그인 요청을 잡습니다.

### 3. Authentication 객체 생성

- UsernamePasswordAuthenticationFilter가 사용자의 입력으로 `Authentication` 객체를 하나 만듭니다.
    
    ```java
    java
    복사편집
    UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(username, password);
    
    ```
    
- **이 시점**의 Authentication은 "인증되지 않은 상태"입니다. (`isAuthenticated = false`)

### 4. AuthenticationManager에 전달

- 이 Authentication을 **AuthenticationManager**에게 넘겨서 진짜 인증을 맡깁니다.
    
    ```java
    java
    복사편집
    AuthenticationManager.authenticate(authenticationToken)
    
    ```
    

### 5. AuthenticationProvider가 인증 수행

- `AuthenticationManager`는 내부적으로 **AuthenticationProvider**를 호출합니다.
- 기본 설정이면 `DaoAuthenticationProvider`가 동작합니다.

> DaoAuthenticationProvider의 인증 과정
> 
> 1. **UserDetailsService**를 호출해, 입력된 username으로 사용자를 찾음
> 2. 사용자 객체(`UserDetails`)를 얻음
> 3. 입력된 비밀번호와 DB 비밀번호를 `PasswordEncoder`로 비교
> 4. 일치하면 인증 성공, 실패하면 예외(UsernameNotFoundException 등) 발생

---

✅ 인증 성공 시

- **"인증된" Authentication 객체**를 만듭니다. (`isAuthenticated = true`)
- **SecurityContext**에 Authentication을 저장합니다.
- 세션을 사용하면 `HttpSession`에 저장해서 이후 요청마다 꺼내 씁니다.

✅ 인증 실패 시

- 다시 로그인 페이지로 리다이렉트하거나, 에러를 반환합니다.

---

# 🛡️ Authorization (인가) 절차

> "당신은 이 행동을 해도 됩니까?" 를 확인하는 절차
> 
> 
> 접근 권한을 검사하는 과정입니다.
> 

### 1. 사용자가 보호된 리소스 요청

- 로그인 후, 예를 들어 `/admin/dashboard` 같은 URL에 접근을 시도합니다.

### 2. Security Filter Chain 진입

- 요청이 들어오면 또다시 SecurityFilterChain이 가로챔
- 이번엔 **FilterSecurityInterceptor**가 동작합니다.

### 3. 인증정보 조회

- FilterSecurityInterceptor는 현재 요청에 대해
    - `SecurityContext`에 저장된 Authentication(로그인 정보)을 확인합니다.

### 4. AccessDecisionManager 호출

- FilterSecurityInterceptor는 **AccessDecisionManager**를 호출해서
"이 요청을 허락해도 되는지" 결정합니다.

> AccessDecisionManager는 내부적으로 Voter(투표기)들을 호출해서 투표합니다.
예를 들어
> 
> - 이 URL은 ADMIN만 가능하다면
> - 내 Authentication에 ROLE_ADMIN 권한이 있는지 체크합니다.

### 5. 허용 or 거부

- 모든 Voter가 승인하거나 다수결로 OK → 요청을 처리 (Controller로 넘어감)
- 거부 → 403 Forbidden 에러 반환 (또는 예외 처리)
