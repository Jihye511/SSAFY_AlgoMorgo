DTO는 테이블에 매핑되는게 아니라 내가 필요한것만 처리 가능하다

autowired는 빈 주입

value는 스칼라 값 주입

# Handler Interceptor

- DispatcherServlet이 Handler Adapter를 호출하기 전/후 등에 거치는 일종의 필터
    - Servlet 필터와 유사한 개념 : 여러 Controller에서 공통적으로 사용되는 기능 정의
        - response header 설정 request 감사, 성능측정, 인증확인 등
    - Spring의 빈으로 사용 가능
        - 다른 빈 주입 등 다양한 잇점 제공

주요 메서드

HttpServletRequest, HttpServletResponse, @Controller 객체, ModelAndView, Exception 까지 모두 제공받을 수 있음

여러개 Handler Interceptor가 설정되었을 경우 동작

- 하나의 controller에 여러 개의 interceptor가 등록될 수 있음
- preHandler은 등록된 순서로 호출
- postHandler과 afterCompletion은 역순으로 호출

## Ant 경로 패턴

경로를 구분하는 특수만자

- ? : 1개의 글자
- * : 0개 이상의 글자
- ** : 0개 이상의 디렉터리

Servlet Filter : spring과 무관하며 DispatcherServlet 호출 이전에 동작

- 스프링과의 협업은 어렵고 단지 ServletRequest / Response 에 대한 전/후 처리

HandlerInterceptor : Spring에서 관리하며 DispatcherServlet 이후 동작

- spring의 빈으로 injection 등 모든 기능 사용 가능
- Controller에 대한 부가 작업이 필요한 경우

AOP : HTTP 관련 작업을 처리하기 위한 처리하기 어려움

- 일반적으로 Service, DAO에 적합

# 강사님 정리

webserver - WAS(tomcat)

톰캣은 웹어플리케이션서버를 가지고 객체를 생성

WAS가 web.xml을읽는데

서블렛, 리스너, 필터를 등록

스프링에서 서블렛 → dispatcher servlet

tomcat | filter | D.S

WAS가 DS에게 req, resp 던지고 받음

filter에서 dofilter 기준으로 처리

Spring에서 전체는 app context 그 안에 IOC 컨테이너를 가짐

IOC는 빈을 3종류로 나눔

- Controller
- Service
- Repository

스프링이 뜰 때 위 3개는 IOC에 담음 즉 로딩할때 담음

스프링 web은 dispatcher servlet을 자동으로 만들어 지는 역할을 함

3개의 빈을 호출하기 위해 Handler Mapping을 만듬

그래서 호출 전에 물어보고 응답받고 호출받음

모델은 repo가 처리한 결과값에 대한 값받음

그거를 처리하기 위해 viewresolver가 있음

model은 데이터, view는 그걸 보여주기 위한것

디스패처와 컨트롤러사이 들어가는거 interceptor

즉

                                 [ Appcontext → Spring core                                                                 ]

                                 [ IOC                                                                                                            ]

tomcat → filter → DS → interceptor → controller → AOP → service → AOP → Repo

DS 는 httpservlet를 상속받아서 http용이다

업로드 처리방식

1. 태그로처리하는방식 
    1. input의 타입을 파일로 줌 → 컨텐츠타입의 기본타입은? : x-**encode??
    2. 파일로 하면 얘기가 다름 → 파일은 두개의 파트로 나눠점
        - 기본전문 뒤에 바이너리 데이터를 보냄
        - 전문에 항상 시작전문, 엔드전문이 있는데 파일 사이즈도 같이 보내줌
        - 이게 끝나면 request만들어서 과정 처리함

다운로드 처리방식

스프링 초기 → 바이너리 파일을 쏘는 클래스를 빈으로 등록

→ 다운로드리졸버같은걸 만듬 → 정적리소스가 아니라 바이너리 파일을 보내는걸 씀

오늘 배운 방식

앵커에 다운로드 속성 처리하면 브라우저에서 자동으로 해줌

→ JS를 사용 즉 AJAX (form데이터에 담아 보냄)
