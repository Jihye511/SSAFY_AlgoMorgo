# Spring Security

principal : 인증된 사용자

Secured Resource : 보안 적용 리소스 URL, API 엔드포인트 등 (지키는 대상)

Authentication : 인증 (로그인) 

Authorization : 권한 / 인증 완료된 principal이 권한이 있는지 확인

- Admin, member guest 등 role에 기반하여 자원 필터링

![image](https://github.com/user-attachments/assets/a027331d-3634-4bc5-a634-3e3a97c6baf7)

spring security의 특징 

- 인증, 권한부여, 일반적 공격에 대해 보호기능 제공 (하나의 프레임워크)
- 스프링 기반의 애플리케이션 보호를 위한 필수적 프레임워크
    - 더 이상 세션 이용 로그인 방식을 구현하지 말자
- 컨테이너에 무관하게 적용
    - 스프링을 위해 만들어진 것이 X, WAR, EAR 형태로 배포,  standalone
- 관점의 분리
    - @Transactional 처럼 보안적 이슈와 비즈니스 로직 분리
- 유연성
    - 다양한 인증 방법 제공 → basic, form 등
    - 사용자 정보 저장을 위한 방식을 설정으로 제공 : properties file

![image](https://github.com/user-attachments/assets/f684d43e-5fe4-4050-aae1-72062c12d83a)

인증 필터 : Authentication 객체 사용 - p, c를 만든다. - 매니저가 그걸 가져감 

- 매니저의 구현체 : providerManager
- 사용자 정보를 가짐 : UserDetailsService
- 확인 후 p, c 가 userdetails, authorities 로 바뀌어서 내려옴

### 필터로 동작하며, 인증으로 시작하고, 인가로 확인한다.

- Security Context

: 현재 사용자에 대한 인증을 세션에 보관하는 객체 SecurityContextHolder를 통해  접근

ThreadLocal에 저장되어 동일 스레드 내에서 언제든 현재 사용자 정보에 접근 가능

- Authentication  Manager : 인증 담당 객체

: 인증 정보를 담고 있는 Authentic 객체 리턴

인증 실패  시 예외 발생 ****(AuthentticationException) 

기본 구현체는 ProviderManager

- Authorization Manager : 권한 처리 담당 객체

: 인증된 사용자의 권한을 확인하는 객체

## 필터와 SecurityFilterChain

- 스프링 MVC 는 물론 일반 서블렛 앱을 지원하기 위해 서블렛의 filter 레벨에서 동작
    - Filter 는 Servlet 요청을 처리하기 전에 동작
    - Spring MVC는 filter를 거친 후 dispatcher servlet에서 시작
    - 기본적으로 @ 기반의 설정들과 Filter가 연결될 수 없음
    
- DelegationFilterProxy : Spring에서 제공하는 FIlter
    - 서블렛 컨테이너 라이프 사이클과 Spring의 WebApplicationContext 사이의 브릿지 역할 수행
    - Application Context에서 선언된 Filter의 목록을 Lazy하게 가져와서 실행

![image](https://github.com/user-attachments/assets/c94e778c-4118-46d8-b3c5-441b0c6f673a)

- FilterChainProxy 와 SecurityFilterChain
    - FilterChainProxy  : DelegationFilterProxy로 부터 작업을 위임받은 필터 (하청의 하청)
    - SecurityFilterChain을 목록으로 관리하며 Security 관련 작업을 위임
    - SecurityFilterChain : 인증필터, CSRF 필터, 로그아웃 필터 등 기능별로 연결됨

![image](https://github.com/user-attachments/assets/d3bcc2a4-74e5-456f-8e46-6bee4a37e37d)

- Multiple SecurityFilterChain
    - filterChainProxy는 경로 기반으로 다양한 SecurityFilterChain 보유 가능
    - 경로 기반으로 순서대로 필터 확인, 최초 적용 chain에서 처리
    - /api/message/ 요청 → SecurityFilterChain 0
    - /messages/ 요청 → SecurityFilterChain n

![image](https://github.com/user-attachments/assets/5b3d8839-c98b-41cd-b52c-800696173a00)

- Authentication Architecture

SecurityContext : 현재 인증된 사용자의 인증 정보인 Authentication 객체를 가짐

![image](https://github.com/user-attachments/assets/43536a65-6528-4b58-b2b8-10aaa5c382ec)

- 매니저

![image](https://github.com/user-attachments/assets/3327da1d-81b7-4f93-bdcb-2d47bafad339)

프로바이더 여러개를 가지고 있음 - 특정한 유형의 인증 처리를 담당한다. (방식에 따라)

순차적으로 인증을 요청 → 인증 실패 시 예외 발생

### DAO authentication provider

![image](https://github.com/user-attachments/assets/d5b8fa41-9cc3-49de-b72d-5e3dfc508a79)

인메모리 저장이 더 쉬운 구조?

DB 를 만들어서 사용하는 경우가 더 많이 쓰는 구조이다..

→ db에 평문으로 비밀번호를 저장할 수 없다 .

### 인증  관련 처리

AuthenticationEntryPoint : 인증이 필요한 리소스에 인증되지 않은 사용자가 접근할 때 인증을 요청하는 역할

AuthenticationFailureHandler : 로그인 시도 시 비밀번호 틀리거나, 계정 잠금 등의 이유로 인증 실패 시 동작

![image](https://github.com/user-attachments/assets/03c366f1-61f5-47a9-814b-48f20def651e)

### 인가 관련 처리

- Authorization
- AuthorizationManager

![image](https://github.com/user-attachments/assets/43c3eb8b-6ace-4ded-9899-5eecfb1933c7)

# Authentication

# Authorization
