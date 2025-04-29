## Security

- principal : 인증된 사용자 또는 이를 식별하는 정보로 사용자(계정), 디바이스, 시스템 등 행위의 주체
- Secured Resource : 보안이 적용되는 리소스로 URL, API 엔드포인트, 파일 등
- Authentication (인증) : Principal이 믿을 수 있는지 파악하는 것으로 일반적으로는 ID/PASS를 체크하는 것
- Authorization(권한), 인증이 완료된 Principal이 어떤 행위를 할 권한이 있는지 확인하는 것
    - ADMIN, MEMBER, GUEST 등 role에 기반하여 접근 가능한 자원 필터링

## Spring Security의 특징

- 인증, 권한부여, 일반적인 공격에 대해 보호 기능을 제공하는 프레임워크
- 스프링 기반의 애플리케이션 보호를 위한 필수적 프레임워크
    - 더 이상 자체적으로 세션을 이용한 인증 방식 구현을 하지 말자
- 컨테이너에 무관하게 적용
    - WAR, EAR 형태로 배포될 수 있는 형태이며 standalone 형태로도 실행 가능
    - Servlet Application에서는 Filter 기반으로 동작
- 관점의 분리
    - @Transactional 처럼 보안적인 이슈와 business logic의 분리
- 유연성
    - 다양한 인증 방법 사용 가능 : basic, form, x.509, cookies 등
    - 사용자 정보 저장을 위한 방식을 설정으로 제공 : properties file, RDBMS, LDAP

## Authentication과 Authorization 절차

- SecurityContext
    - 현재 사용자에 대한 Authentication을 세션에 보관하는 객체로 SecurityContextHolder를 통해 접근
    - ThreadLocal에 저장되어 동일 스레드 내에서 언제든 현재 사용자 정보에 접근 가능
- AuthenticationManager : 인증을 담당하는 객체
    - 인증 성공 시 인증 정보를 담고있는 Authentication 객체 리턴
    - 인증 실패 시 AuthenticationException 발생
    - 기본 구현체는 ProviderManager
        - 인증 방식에 따라 하위 구현체 작성
- AuthorizationManager : 권한 처리를 담당하는 객체
    - 인증된 사용자가 Secured Resource에 접근할 수 있는 권한이 있는지 확인하는 객체

## Servlet Application에서 Spring Security

- Spring MVC는 물론 일반 Servlet Application을 지원하기 위해 Servlet의 Filter 레벨에서 동작
    - Filter는 Servlet 요청을 처리하기 전에 동작
    - Spring MVC는 Filter를 거친 후 DispatcherServlet에서 시작
        - 기본적으로는 @기반의 설정들과 Filter가 연결될 수 없음
- DelegationFilterProxy : Spring에서 제공해주는 Filter
    - Servlet 컨테이너의 라이프사이클과 Spring의 WebApplicationContext 사이의 브릿지 ㅕㄱ할을 수행
    - ApplicationContext에서 선언된 Filter 의 목록을 Lazy하게 가져와서 실행

## FilterChainProxy와 SecurityFilterChain

- FiltherChainProxy : DelegatingFilterProxy로부터 작업을 위임 받은 Filter
    - SecurityFilterChain을 목록으로 관리하며 Security 관련 작업을 위임
- SecurityFilterChain
    - Security와 관련된 Filter의 chain으로 인증 필터, CSRF 필터, 로그아웃 필터 등 기능별로 filter 들이 연결됨

## Multiple SecurityFilterChain

- FilterChainProxy는 경로 기반으로 다양한 SecurityFilterChain 보유가능
    - 경로 기반으로 순서대로 filter 확인 → 최초 적용 chain에서 처리
    - /api/message/ 요청 → SecurityFilterChain 0
    - /messages/ 요청 → SecurityFilterChain n

## Authentication Architecture

- SecurityContext : 현재 인증된 사용자의 인증 정보인 Authentication 객체를 가짐
    - SecurityContextHolder : SecurityContext를 저장하는 보관소
- Authentication interface
    - 대표적 구현체 : UsernamePasswordAuthenticationToken
    - Principal, Credentials, Authorities (권한)으로 구상
        - Principal : 사용자를 식별하는 정보(username)로 인증 시 UserDetails 타입의 객체 사용
        - Credentials : 자격증명으로 password(또는 토큰, 인증서 등)에 해당하며 인증이 완료되면 보안을 위해 삭제
        - Authorities : ROLE_ADMIN, ROLE_USER 등 사용자가 부여 받은 권한
        - 인증 전 : 사용자가 인증을 위해 AuthenticationManager에게 제공
            - principal과 Credentials로 구성
        - 인증 후 : 현재 인증된 사용자의 정보
            - Principal과 Authorities로 구성

# 인증 관리

- AuthenticationManager : 인증을 수행하는 방법을 정의한 인터페이스
    - ProviderManager : AuthenticationManager의 구현체로 실제 인증 작업 수행
        - 여러  AuthenticationProvider 들에게 인증 처리 위임
        - 순차적으로 인증 요청 → 마지막까지 인증 처리 실패 시 ProviderNotFoundException 발생
    - 반환된 인증 정보는 SecurityContextHolder에서 관리됨
        - Session Scope에서 SPRING_SECURITY_CONTEXT로 등록
- aUTHENTICATIONpROVIDER
    - 특정 유형의 인증 처리
    - DaoAuthenticationProvider
        - username/password 기반 인증
    - JwtAuthenticatiopnProvider
        - JWT 기반 인증 → 세션 미사용 방법
            - 매 요청마다 토큰을 전달하고, 인증 객체를 직접 생성

## 인증 관련 처리

- AuthenticationEntryPoint
    - 인증이 필요한 리소스에 인증되지 않은 사용자가 접근할 때 인증을 요청하는 역할 수행
- AuthenticationFailureHandler
    - 로그인을 시도했으나 잘못된 비밀번호, 계정 잠금 등의 이유로 인증에 실패했을 때 동작

## 인가 관련 처리

- Authorization
    - Authentication 객체에 포함된 GrantedAuthority 목록을 사용하여 수행되는 프로세스
        - GrantedAuthority : 사용자에게 부여된 권한으로 특정 작업을 수행할 수 있는지에 대한 정보
    - 권한은 일반적으로 ROLE_를 접미사로 관리
- AuthorizationManager
    - GrantedAuthority를 이용해 Secured resource에 대해 호출을 진행할 수 있는지에 대한 권한 점검 → @PreAuthorize
    - GrantedAuthority를 이용해 Secured resource에서 값은 반환할 수 있는지에 대한 결정 → @PostAuthorize

# 강사님 정리

SecurityHolder가 SecurityContext를 가짐

필터를 크게 두개

인증, 인가로 

SecurityHolder로 부터 getContext하면 static이라 접근 가능

static이든 싱글톤이든 한개 만들어지는데 static 쓰는 이유?

어짜피 끌고와서 유저마다 쓰레드가 만들어짐

그래서 인증할때 로컬쓰레드쓴다 → 쓰레드 영역으로 돌림

Holder로 부터 SecurityContext가져와서 presfell? getAuthorities하면 쭉 나옴

@EnableWebSecurity(debug = true)

하면 관련 로그가 다 나온다

그 필터들을 통해 어떤게 나오는지 다 뜸

프록시패턴인데 그중 원격프록시

프록시 패턴 3가지

**원격 객체 접근 제어, 보안 및 접근 제어, 그리고 객체 상호 간 최적화**

젼략패턴 : 폰안에 스피커 안에 쏘니스피커 메소드
