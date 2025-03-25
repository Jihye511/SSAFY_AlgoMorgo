## Filter

- 웹 컴포넌트가 실행되기 전/후에 요청과 응답을 가로채서 부가적인 기능을 수행하기 위한 웹 컴포넌트
- Filter Chain : 여러 개의 필터가 모여 필터 체인 구성 가능
- 요청 및 응답 과정에서 조건에 따라 필터링 거침

![image](https://github.com/user-attachments/assets/8978ab76-25ac-4717-aff8-951df3a669fb)


### Filter 주요 용도

- 인증 및 권한 확인
- 로깅 : 어떤 요청이 있었는지, 어떤 응답이 있었는지 추적
- 보안 : 요청을 검사하여 악의적인 코드나 공격 차단

### Filter 작성

- 주로 URL Pattern : url 기반으로 filter적용 대상 설정
- request : 클라이언트 직접 요청인 경우 적용(Default)
- forward
- include
- error

### Paging

### Listener

- 웹 어플리케이션에서 발생하는 이벤트에 대한 모니터링 객체
- 역할
    - 애플리케이션 시작 및 종료 이벤트 감지
    - 세션 생성 및 소멸 감지
    - 서블릿 컨텍스트 초기화 및 정리 작업 수행

Connection Pool

- 기존 JDBC Connection의 문제점과 Connection Pool
- Connection은 Network를 통해서 얻어오는 비싸고 소중한 자원

### Exception

- 어떠한 경우도 예외에 대한 정보가 클라이언트에게 직접 전달되지 않도록 처리 필요
- 사용자에게 보여지는 페이지와 문제 해결을 위한 로깅이 병행 되어야 함
- 404
    - front controller까지 진입 후 처리할 sub controller를 발견하지 못한 경우
    - front controller까지 진입하지 못한 경우
- 500
    - front controller에서 try-catch로 Checked Exception 처리 후 관련 페이지에 에러 메시지 전달
    - WAS로 예외 전파
        - web.xml에 WAS가 받은 오류를 처리할 에러 페이지 설정

요약

![image](https://github.com/user-attachments/assets/560bda05-cb5b-4d56-9943-633799339fa8)


httpd가 있고 was를 호출 was에서 servlet 컨테이너에 request 와 response를 응답 및 호출

servlet에서 service로 service는 DAO를 호출 DAO에선 DB를 호출

tomcat안에 was가 있는거다

이때 Was와 servlet사이에서 작동하는게 filter

filter은 request, forward, include 타입으로 동작

필터는 여러개를 만들 수 있다 → filter chain

filter은 3개의 메소드

init, dofilter, destroy

가장 중요한 메소드는 dofilter

paging

데이터를 잘라서 읽어오는 것

요즘은 paging보단 infinite scroll
