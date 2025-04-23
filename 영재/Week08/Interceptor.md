### Controller

- setupCookie만 필요
    - 스프링에서 쿠키까지는 따로 처리 X
- extends HttpServlet 제거
- request 사용 지양
    - model과 @RequestParam, @ModelAttribute 지향
- action값을 경로로 사용

Handler Interceptor

- DispatcherServlet이 HandlerAdapter를 호출하기 전/후 등에 거치는 필터
- 여러 컨트롤러에서 공통적으로 사용되는 기능 정의
    - response header 설정/request 감사/성능 측정/인증 확인 등
- Spring의 빈으로 사용 가능
    - 다른 빈 주입 등 다양한 이점 제공
- 주요 메서드(3개)
    - 프리핸들러 : 인터셉터가 등록 된 순서
    - 포스트핸들러 : 역순
    - 에프터컴플리션 : 역순

### Servlet Filter vs AOP  vs Interceptor

![image](https://github.com/user-attachments/assets/9c0a025f-b1c4-49cd-ac99-99489b796e95)


- Servlet Filter : spring과 무관하며 DispatcherServlet 호출 이전에 동작
    - 스프링과의 협업은 어렵다
- HandlerInterceptor : Spring에서 관리하며 DispatcherServlet 이후 동작
    - Spring의 빈으로 injection등 모든 기능 사용 가능
    - controller에 대한 부가 작업이 필요한 경우
- AOP : HTTP 관련 작업을 처리하기 위한 처리하기 어려움
    - 일반적으로 Service, Dao에 적

### ERROR

- 에러페이지는 기본적으로 error.jsp에 매핑
- 특정 에러코드도 자동 매핑
- 만약 특정 에러코드에 대한 jsp파일이 없다면 내장 에러페이지 로드
-
