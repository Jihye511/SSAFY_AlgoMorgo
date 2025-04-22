# Spring MVC

- MVC 기반(model2)의 web application을 작성하기 위한 Spring Framework의 하위 모듈
- front controller pattern : dispatcher Servlet : / 경로를 처리하므로 모든 요청을 받아들임
    - action parameter방식이 아닌 url 기반으로 요청 분기
- 주요 특징
    - 어노테이션 기반 → @MVC
    - 다양한 view 지원
    - restful 웹 서비스 지원
    - 테스트 용이

- DispatcherServlet
    - Spring@MVC의 가장 중추적인 역할
    - Front Controller Pattern의 적용으로 client의 모든 request 접수
    - 주로 다른 객체들에게 위임하여 처리
- Spring@mvc infrasstructure Components
    - HandlerMapping
    - HandlerAdapters
    - ViewResolvers

## @Controller

- Handler의 한 종류로 MVC에서 Client의 요청을 받아들이는 역할을 하는 클래스
- 다수의 요청 처리 메서드를 포함

## @RequestMapping

- 요청 처리 메서드를 작성하기 위한 annotation
- value(=path) 속성을 이용해서 처리할 경로 지정
- 클래스 레벨 : 클래스에 소속된 모든 요청 처리 메서드 경로의 prefix
- 메서드 레벨 : 해당 메서드에만 적용되는 경로

GET처리하는 메소드를 post로 요청하면 405에러가 뜬다

## Mock : 실제 WAS에서 Spring@MVC와 소통하는 부분을 가상으로 제공

구조 :

요청만들기 > 실행 > 검증 > 확인

# 강사님 정리

HandlerMapping : 어떠한 요청이 오면 어떠한 메소드를 적용하는지 어떻게 아냐?

컨트롤러를 만들어서 annotation requestmapper사용해서 ~~요청오면 내가 호출될거야 설정

그 어노테이션이 핸들러매퍼에게 등록

그 다음 요청이 오면 디스패처 서블렛이 핸들러매퍼에게 요청하고 코드를 반환

스프링도 하나의 자바 프로그램이다

스프링이 백그라운드에서 실행되면 계속 재실행해도 괜찮다

스프링 부트에서 톰캣은 하나인데 자바를 2개 실행하려 하니 안된다

그래서 

1. 예전에 한걸 끄고 다시 실행하거나
2. 스프링 dev tool은 수정하면 자동으로 반영된다

스프링부트는 web.xml의 디스패처 서블릿을 자동으로 등록해준다

컨트롤러는

리퀘스트매퍼에 등록할 수 있는 어노테이션 사용 가능

서비스 + DAO 등등 이걸 모델이라함

스프링은 기본적으로 뷰를 뭐로 처리하는지 정해져 있지 않다

그 중 jsp, html, json 등이 있는데

이 화면처리기가 뷰리졸버다

즉 뷰리졸버가 인터페이스다

기본 톰캣은 jsp 처리를 못하고 웹서버 역할만 한다
