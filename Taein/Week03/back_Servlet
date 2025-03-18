정적 요청 → 정적 리소스(static) : html, css, js

WAS : 동적 요청 처리 (Servlet)

- Business Logic(Java)
- Persistence Logic → JDBC ← DB Server
    - DAO 사용
- Presentation Logic

WAS에서 Servlet를 어떻게 만드냐?

톰캣 : 서버 컴에 설치된 어플인데 서비스 역할을 한다

서버 접속하려면 포트가 필요

WAS는 일반적 80, 개발용은 8080

- Container : 웹 어플이 필요한 실행환경, 리소스를 담고있는거
- http://192.168.0.1:8080 : container root
- Context : 어플의 실행 환경과 실행 정보를 제공
- http://192.168.0.1:8080/contextA : context root

container와 context 는 같진 않지만 동일한 개념

web.xml : 어플에 필요한 동작 리스트

## Servlet

Was에서 실행되는 Java Web Component → was를 servlet container라고도 함

장점:

- OOP 기반 작성 → 유지보수성 및 재활용성 우수, 플랫폼 독립정
- 높은 성능과 확장성 : 하나의 서블릿 객체만 생성하며 멀티스레딩을 지원하여 요청의 동시 처리가 가능
- 확장성 : 필터를 통한 공통 모듈의 전/후 처리, 리스너를 이용한 이븐트 기반 처리 가능 및 스프링 등 다양한 프레임워크와 통합 용이

단점 :

- 비즈니스 로직과 presentation logic가 섞여서 나타남 → Servlet + JSP의 Model 2 방식으로 처리

## URL Mapping

url 경로 지정 → / 로 시작해서 경로 지정 가능

확장자 매칭 : *.확장자 형태로 지정

web.xml를 활용한 mapping

@WebServlet 어노테이션 활용한 mapping

상대 경로 : 현재 파일 기반

절대공로 : / 로 시작

- container root : /context_name/
- context root : url mapping, web.xml 경로, include-directive의 경로 등

## Servlet Life Cycle

관련 메소드 : doget, dopost

Servlet 만들지만 객체 만들거나 호출 X 

→ Container가 life cycle 관리

- 할일을 개발자가 적절히 작성 → container 호출 → servlet이 효율적 자원관리, 최적화된 성능 구현

init → 어떤 요청도 init이 종료 전 까지 처리 X

→ servlet에서 필요한 자원 초기화

destroy() : 어떤 요청이라도 처리하고 있으면 destroy는 동작 X

→ inint에서 초기화한 자원의 정리 작업

httpServletRequest : 요청을 분석하는것

setAttribute 하나만 set이다

request parameter : 언제나 문자열, 첫 설정 이후 조작 불가

---

중요

### Content-Type

- text/plain : 일반 텍스트
- application/json : rest api 응답에 사용

### Character Encoding

- 컴이 이해하고 처리하는 형태로 변환

---

### AddServlet

요청을 받기 위해서는

url 매핑은 : /hello

서버에서 재정의 할 메서드 : doGet

- a default는 get방식

전달되는 파라미터 이름 : name 속성인 num1, num2

### Servlet 할일

- request 파라미터 조회 : logging 및 validation 포함
- 요청에 대한 business logic
- 필요 시 persistence logic 연동
- 응답 준비 : content type 및 encoding 설정
- 응답을 위한 presentation logic 처리

## Servlet 특징

단점 : bussiness 로직 + persentation logic(html)가 섞임

장점: 멀티스레딩을 지원한다

멀티스레드

servlet은 공유자원으로 thread safe하지 않다

servlet 멤버 변수를 통해 상태 관리 X

servlet할때 주의할점

멤버 변수 선언하고 쓰레드 쉬고 만들어보면

나중에 설정한게 된다

servlet은 공유된 자원이라서

안전하게 하려면 synchronized 사용

근데 이걸 이용하란 소리가 아니라 멤버 변수로 상태를 공유하면 안된다

혼자할때는 되는데 공유가 되면 문제가생긴다

기존 서블릿 : 요청과 처리하는 servlet이 1:1 매칭

개별 서블릿에서 로깅 예외 처리는 물론 인증, 비즈니스 로직 함께 처리

- 코드 중복성
- 복잡성 증가, 유지보수 어려움

## Front Controller

전면에서 모든 요청을 받아들이는 Servlet

장점

- 단일 진입점 : 요청 처리 일관성
- 공통 처리 : 필요한 전후 작업의 일괄 처리
- 유연한 확장성 : 쉽게 확장 가능
- 코드 간결성 : 가독성 확장

사용법

특정 작업 의미하는 파라미터 추가

와일드 카드

# 강사님 정리

브라으저에서 서버 접속 방법 : 주소창, a, form, JS

request를 통해 서버로 전송

request에서 text가 날라감

httpd와 was가 섞인게 tomcat

was는 요청에 걸맞는 servlet을 객체생성해준다

servlet ← generic 서블렛 상속 ← http servlet

generic 서블렛

- init
- service
- destroy

http servlet에는 service는 있긴한데 쓰진않는다

doget이랑 dopost를 오버라이드해서

http servlet에서

service() {

super.service() //gerneric service거다

// 이걸 없에면 → doget이랑 dopost 호출도 안된다

}

싸이클 :

init

service

doget / dopost

destroy

/ 없이

1. myservlet?name=둘리&age=8 이건 되는데
2. /myservlet?name=둘리&age=8 이건 안되는 이유 → 절대경로가 되서

IP:port/contextpath/ → /없으면 1번이 됨

앞으로 상대경로 금지

무조건 절대경로로 해라

/ 쓰려면 /HelloWeb/myservlet?name=둘리&age=8 이렇게 해라

근데맨날 contextpath 붙이는거 귀찮다? 나중가면 프로그래밍으로 해결함

```java
req.getRequestURI(); //  전체경로 http://ip:port/contextpath/servlet
req.getRequestURL(); //  contextpath 부터 ex) /contextpath/servlet/servlet....  
req.getServletPath(); // servlet 부터 ex) /servlet/servlet.... 
req.getContextPath(); // contextpath만
```
