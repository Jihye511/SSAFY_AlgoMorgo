## Security

- Security의 주요 용어와 concepty
    - Principal : 인증된 사용자 또는 이를 식별하는 정보로 사용자(계정), 디바이스, 시스템 등 행위의 주체
    - Secured Resource : 보안이 적용되는 리소스로 URL, API 엔드포인트, 파일 등
    - Authentication (인증): Principal이 믿을 수 있는지 파악하는 것으로 일반적으로 ID/PASS를 체크하는 것
    - Authorization (권한) : 인증이 완료된 Principal이 어떤 행위를 할 권한이 있는지 확인하는 것
        - ADMIN, MEMBER, GUEST 등 role에 기반하여 접근 가능한 자원 필터링
- Spring Security의 특징
    - 인증, 권한 부여, 일반적인 공격에 대해 보호 기능을 제공하는 프레임워크
    - 스프링 기반의 애플리케이션 보호를 위한 필수적 프레임 워크
        - 더 이상 자체적으로 세션을 이용한 인증 방식 구현을 하지 말자!!
    - 컨테이너에 무관하게 적용
        - WAR, EAR 형태로 배포될 수 있는 형태이며 standalone 형태로도 실행 가능
        - Servlet Application에서는 Filter 기반으로 동작
    - 관점의 분리
        - @Transactional 처럼 보안적인 이슈와 business logic의 분리
    - 유연성
        - 다양한 인증 방법 사용 가능: basic, form, x.509, cookies 등
        - 사용자 정보 저장을 위한 방식을 설정으로 제공 : properties file, RDBMS, LDAP
    
- Autentication과 Authrization
    
    ![image](https://github.com/user-attachments/assets/69186129-ec59-404b-aa8f-4f93e4e70b5c)

    
    ## 1. **사용자 요청 (request)**
    
    - 사용자가 로그인 같은 요청을 보낸다.
    - 요청에는 **username** (principal)과 **password** (credential)가 담겨 있다.
        
        ```
        ex) username: user01
            password: pass1234
        ```
        
    
    ---
    
    ## 2. **Servlet Filter Chain**
    
    - 요청은 먼저 **Servlet Filter Chain**으로 들어온다.
    - 이 체인에는 여러 개의 필터가 등록되어 있는데, 그 중 **DelegatingFilterProxy**가 Spring Security의 필터 체인으로 넘겨주는 역할을 한다.
    - **DelegatingFilterProxy**가 **Spring Security Filter Chain**을 호출한다.
    
    ---
    
    ## 3. **Security Filter Chain**
    
    - 이제 **Security Filter Chain**이 작동한다.
    - 여기엔 여러 보안 관련 필터들이 순서대로 존재하는데, 그중 중요한 두 개가 바로:
        - **AuthenticationFilter** (인증 담당)
        - **AuthorizationFilter** (권한 검사 담당)
    
    👉 여기서는 먼저 **AuthenticationFilter**가 작동한다.
    
    ---
    
    ## 4. **AuthenticationFilter 작동**
    
    - AuthenticationFilter가 사용자의 **principal(username)**과 **credential(password)**를 받아서 **Authentication 객체**를 생성한다.
    - 이 생성한 Authentication 객체를 **AuthenticationManager**에게 넘긴다.
    
    ---
    
    ## 5. **AuthenticationManager와 ProviderManager**
    
    - AuthenticationManager는 실질적인 구현체인 **ProviderManager**에게 이 인증 요청을 넘긴다.
    - ProviderManager는 내부에 여러 **AuthenticationProvider**를 가지고 있다.
    - 각각의 AuthenticationProvider는 특정 인증 방식을 처리할 수 있다 (ex: 폼 로그인, 소셜 로그인 등).
    
    ---
    
    ## 6. **AuthenticationProvider 동작**
    
    - AuthenticationProvider는 다음과 같은 과정을 거친다:
        1. **UserDetailsService**를 호출해서
        2. **username**을 기준으로 **UserDetails** 객체를 만든다.
            - UserDetails는 사용자의 비밀번호, 권한 같은 정보를 담고 있다.
        3. 사용자가 입력한 비밀번호(credential)와 UserDetails에 저장된 비밀번호를 비교한다.
    
    ✅ 만약 비밀번호가 일치하면 인증 **성공**
    
    ❌ 다르면 인증 **실패** → **AuthenticationException** 발생
    
    ---
    
    ## 7. **성공 시 SecurityContext 저장**
    
    - 인증이 성공하면 다음과 같은 일이 일어난다:
        - 인증된 사용자 정보를 담은 **Authentication 객체**를 생성한다.
        - 이 Authentication 객체는 **SecurityContext**에 저장된다.
        - SecurityContext는 **현재 사용자의 인증 정보와 권한 정보를 관리하는 곳**이다.
    
    ---
    
    ## 8. **Authorization (인가)**
    
    - 이제 **AuthorizationFilter**가 작동한다.
    - AuthorizationManager를 통해 사용자가 요청한 리소스에 **접근 권한이 있는지**를 검사한다.
        - 만약 권한이 **있다면** 요청을 계속 진행시킨다 (YES → continue).
        - 만약 권한이 **없다면** 예외를 발생시켜 요청을 막는다 (NO → AccessDeniedException).
    
    | 용어 | 설명 |
    | --- | --- |
    | Principal | 사용자 이름 같은 식별 정보 (ex: ID) |
    | Credential | 사용자 비밀번호 |
    | AuthenticationManager | 인증을 총괄하는 매니저 |
    | AuthenticationProvider | 실제 인증 로직을 수행하는 주체 |
    | UserDetailsService | 사용자 정보를 DB 등에서 가져오는 서비스 |
    | SecurityContext | 인증된 사용자 정보를 저장하는 공간 |
    | AuthorizationManager | 인가(권한 검사)를 담당하는 매니저 |
    
    ![image](https://github.com/user-attachments/assets/1ab9582b-bf84-4f63-99bb-55e78aeb9535)


    
- Servlet Application에서 Spring Security
    - Spring MVC는 물론 일반 Servlet Application을 지원하기 위해 Servlet의 Filter 레벌에서 동작
        - Filter는 Servlet 요청을 처리하기 전에 동작
        - Spring MVC는 Filter를 거친 후 DispatcherServlet에서 시작
            - 기본적으로는 @기반 설정들과 Fitler가 연결 될 수 없음
    - DelegatingFilterProxy : Spring에서 제공해주는 Filter
        - Servlet 컨테이너의 라이프 사이클과 Spring의 WebApplicationContext 사이의 브릿지 역할을 수행
        - ApplicationContext에서 선언된 Filter의 목록을 Lazy하게 가져와서 실행
    
    ![image](https://github.com/user-attachments/assets/725b830c-3eb6-49f4-a1ab-c5d785dc43c0)

    
- FilterChain Proxy와 SecurityFilterChain
    - FilterChainProxy : DelegatingFilterProxy로 부터 작업을 위임 받은 Filter
        - SecurityFilterChain을 목록으로 관리하며 Security 관련 작업을 위임
    - SecurityFilterChain
        - Security 와 관련된 Filter의 chain으로 인증 필터, CSRF 필터, 로그아웃 필터 등 기능 별로 filter들이 연결됨
    
- Authentication Archtecture
    - SecurityContext : 현재 인증도니 사용자의 인증 정보인 Authentication 객체를 가짐
        - SecurityContextHolder: SecurityContext를 저장하는 보관소
- Authentication interface
    - 대표적인 구현체 : UsernamePasswordAuthenticationToken
    - Principal : 사용자를 식별하는 정보로 인증시  UserDetails 타입의 객체 사용
    - Credentials : 자격증명으로 Password(또는 토큰, 인증서 등)에 해당하며 인증이 완료되면 보안을 위해 삭제
    - Authorities : ROLE_ADMIN 등 사용자가 부여받은 권한
- 인증 전 : 사용자가 인증을 위해 AutenticationManager에게 제공
    - Principal과 Credentials로 구성
- 인증 후 : 현재 인증된 사용자의 정보
    - Principal과 Authorities로 구성
    
- AutenticationManager: 인증을 수행하는 방법을 정의하는 인터페이스
    - ProviderManager : AutenticationManager의 구현체로 실제 인증 작업 수행
        - 여러 AuthenticationProvider들에게 인증 처리 위임
        - 순차적으로 인증 요청 → 마지막까지 인증 처리 실패 시 exception 발생
    - 반환된 인증 정보는 SecurityContextHolder에서 관리됨
    
- AuthenticationProvider
    - 특정 유형의 인증 처리
    - DaoAuthenticationProvider
        - username/password 기반 인증
    - JwtAutenticationProvider
        - JWT 기반 인증

![image](https://github.com/user-attachments/assets/6e0df669-2a8a-4da5-bdb5-0a3e5c5fd714)


![image](https://github.com/user-attachments/assets/7644743f-2e2b-4782-a98b-f1847553a853)
