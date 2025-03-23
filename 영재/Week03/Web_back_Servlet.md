# Servlet

**Servlet은 클라이언트의 요청을 처리하고 동적 웹 페이지를 생성하는 Java 기반 서버 사이드 프로그램**

- Web Server은 정적인 요청 처리
- WAS는 동적인 요소 처리

![image.png](attachment:f3a70fd9-990e-41ac-bbcc-783003b32533:image.png)

- Persistence Logic에서는 DAO를 사용하여 JDBC를 통해 DB Server와 통신

WAS에서 상주하는 Servlet에 대해 알아보자

### WAS, Container, Context

- 물리적인 서버는 network에서 접근하기 위해서 ip 주소를 갖음 - HTTP기반으로 통신
- 물리적인 서버에는 WAS가 설치되어 HTTP 기반의 웹 서비스 처리
- WAS는 웹 서비스를 위해 80 port를 사용하며 개발용으로는 8080을 주로 사용
    - WAS는 웹 애플리케이션이 필요한 실행환경 리소스를 담고 있으므로 container라고 불림
- WAS는 동시에 여러 가지 웹 애플리케이션 실행 가능
    - 각 애플리케이션의 실행 환경과 실행 정보 제공하는 것을 Context라고 함
- container : 아파트 / Context : 각 세대

## Servlet

- Servlet란 WAS에서 실행되는 Java Web Component - **was를 Servlet container라고도 한다**
- http://localhost:8080/BE_____/index.html
    
    프로토콜/컨테이너루트/context루트/url_mapping
    
- 장점
    - OOP기반으로 작성 : 유지보수성 및 재활용성 우수, 플랫폼 독립적
    - 확장성 : 필터를 통한 공통 모듈의 전/후 처리
- 단점
    - 비지니스 로직과 프레젠테이션 로직(HTML CODE)가 섞여서 나타남

### URL Mapping

- 클라이언트의 HTTP요청을 Servlet과 매핑하기 위한 방법
    - URL 매칭
    - 확장자 매칭

### 경로의 종류

- container root : servlet 배우기 전에 사용했던 ‘/’는 container root
    - 따라서 특정 context를 호출하기 위해서는 /context_name/ 처럼 개입시켜야함
    - <a>, <link>, <form>, <script>의 경로 등
- context root : servlet를 배우고 나서 배우는 경로는 ‘/’가 context root
    - servlet url mapping
    - web.xml의 경로

### Servlet Life Cycle 관리

- **개발자는Servlet을 만들지만 객체를 만들거나 호출하지 않음 - Container가 life cycle에 따라 관리**
    - 각각의 라이프 사이클 훅에서 할 일을 개발자가 적절히 작성 → Container가 호출
- init : 어떤 요청도 init이 종료되기 전에는 처리될 수 없음
    - Servlet에서 필요한 자원 초기화
- service()
- destroy() : 어떤 요청이라도 처리하고 있으면 destroy 는 동작하지 않음
    - init에서 초기화한 자원의 정리 작업

**HttpServletRequest**

- HTTP 요청을 추상화한 인터페이스로  헤더, 파라미터, 속성 및 요청 본문과 관련된 정보에 접근하는 메서드
    - set은 유일하게 void setAttribute 함수에서만 사용된다

**Http Status**

- 2XX (Success)
    - 200 : 요청의 정상 처리
    - 201 : 새로운 리소스 생성의 성공 상태
- 4XX (Client Error)
    - 403 : Forbidden
    - 404 : Not Found
- 5XX (Server Error)

Content-Type과 Character Encoding

- Content-Type
    - 서버가 전송하는 데이터의 MIME타입으로 **데이터의 형식과 인코딩 방식 포함**
- Character Encoding
    - 데이터를 컴퓨터가 이해하고 처리할 수 있는 형태로 변환하는 방법
    - “한”dlfksms rmfwkdp eogks dlszheld rufrhk

## Front Controller Pattern

- 사용자의 요청과 이를 처리하는 Servlet이 1:1로 매칭됨
    - 요청이 추가될 때마다 매번 새로운 Servlet 생성 필요
    - **동일한 루틴으로 서블릿 생성 필요**
- **따라서 전면에서 모든 요청을 받아들이는 Servlet ⇒ Front Controller 사용**
    - 전면에서 모든 요청을 받아들임
    - 단일 진입점 : 모든 요청을 Front Controller에서 접수
    - 공통 처리 : 모든 작업이 거침
    - 유연한 확장성 : 새로운 요청 처리할때 구조를 크게 변경하지 않아도 됨
    - 코드 간결성 : 여러 개의 servlet 안만들어도 됨
- main은 요청을 어떤게 구분?
    - URL에 언제나 “특정 작업”을 의미하는 파라미터 추가
    - ex) /main?action=gugudan&dan=3
