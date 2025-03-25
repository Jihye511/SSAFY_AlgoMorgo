# 필터

필터 : 웹 컴포넌트가 실행되기 전 후에 요청과 응답을 가로채서 부가적인 기능을 수행하기 위한 컴포넌트

필터체인 : 여러개의 필터가 모인 필터 체인 구성 가능

주요용도

- 인증 및 권한 확인 : 사용자가 특정 페이지에 접근하기 전에 로그인 했는지(인증) 권한 있는지 확인
    - 비 로그인 또는 권한 부족 시 접근 차단, 로그인 페이지로 redirect
- 로깅 : 어떤 요청이 있었는지, 어떤 응답이 있었는지 추적
    - 문제 해결 및 사용자의 행동 분석
- 보안 → 요청을 검사하여 악의적인 코드나 공격 차단
    - 크로스 사이트 스크립팅 공격 등을 방지하기 위한 입력 값 검증

- 서블릿 적용 : 개별로 다 해줘야함
- url : 경로 기반, 확장자 기반 등
- request 가 기본값

# 페이징

1. 처음링크를통한 화면 호출(a활용)과 검색 조건을 이용한 호출(form 전송), ㅔ이지링크를 이용한 호출로 구분
2. 처음 링크를 이용한 조회
    1. form의 submit을 이용한 조회 - 검색 조건을 이용한 새로운 조회 시작
    2. 페이지 링크를 이용한 호출을 위해 현재의 검색 조건 재 설정
3. 페이지링크를 이용한 호출
4. 페이지네이션에 있는 a들은 js에서 결정해서 form을 이용한 호출로 변경

Connection pool : connection들의 모음

network를 통해서 얻어오는 자원 아끼기

- 데이터 베이스와 연결되는 커넥션을 잔뜩 미리 만들어 둔다
- 커넥션 다시 쓰면 서비스 얻기, 반납하기를 통한 재사용

# Listener

웹 애플리케이션에서 발생하는 이벤트에 대한 모니터링 객체

ServletContextListener : 웹 애플리케이션 생성에서 소멸까지의 주요 사항에 대해서 모니터링

- 개별 서블릿 동작 전에 초기화하는데 비용이 많이 드는 공유 자원의 초기화에 주로 사용

# Exception 처리

- 어떠한 경우도 예외에 대한 정보가 클라이언트에게 직접 전달되지 않도록 처리 필요
- 사용자에게 보여지는 페이지와 문제 해결을 위한 로깅이 병행 되어야 함
- 예외발생정보를 직접 전달하면 보기 안좋다

## 404 오류

1. front controller 까지 진입 후 처리할 subcontroller를 발견 못한 경우
    - 존재하지 않는 경로 → forward 처리 → 404페이지로 이동처리 가능→ respose에서 404 에러 발생 →  WAS가 기본 404 페이지로 처리

1. front controller 까지 진입하지 못한 요청
    - 존재하지 않는 경로
    - Servlet에 도달하지 않았으므로 처리 불가 → WAS가 기본 404 페이지로 처리

## 505 오류 처리 과정

- Front controller에서 try-catch로 Checked Exception 처리 후 관련 페이지에 에러 메시지 전달
    - 시스템에서 예상치 못한 Unchecked Exception이 발생?
    - 관련 페이지가 아니라 공통의 예외 페이지를 사용?
- WAS로 예외 전파
    - Unchecked Exception - 언제나 전달
    - checked Exception - 꼭 보내고 ㅅㅍ다면 ServletException으로 wrapping해서 전달

# 강사님 정리

![image.png](attachment:72965e0a-d17d-4b29-8be7-2f7a23836b09:image.png)

![image.png](attachment:7ec4f5e4-4b8a-4f81-b434-90932da7137a:image.png)

### filter 동작 방식

1. request방식
2. forward방식
3. include방식

forward, include가 서블렛이 서블렛 호출하는게 아니냐?

가 아니라

WAS에서 servlet으로하는거라 둘다 WAS에서 호출

filter는 servlet 호출 전에

filter method

1. init
2. dofilter → 이게 중요 (req, resp)를 받음
    1. servlet의 req, resp 타입은 http 타입
    2. req와 resp 클래스 타입이 먼지 아는게 좋다 → dofilter의 req, resp타입은? servlet 타
    3. filter에서 관리 할 수 없는거 session,cookie 이런게 안됨
3. destroy

dofilter 전은 req, 이후는 resp

 > 2 > 1 > 1 > 2

WebFilter에 달때는 순서 어떻게 해야 하나?

Priority 속성을 주면 된다 

loggin2 에 2, loggin1 에 1을 주면?

순서에 대한것 잘 봐라

### 페이징

데이터를 잘라서 하는거

요즘은 인피니트스크롤 많이 씀

### listener

커넥션하고 디스커넥 속도는 조회보다 오래걸리는 경우가 많다

이걸 해결하는 방법

Connection Poll → 미리 모든 커넥션을 맺어 놓는다 → 필요할때마다 연결된 커넥션을 주고 반납하는 방식

장점 :

- connection overflow가 터지지 않는다 (db가 요구하는 것보다 더 많은커넥션이 일어나는 경우)
- max connection pool size를 지정 가능하기 때문
- jdbc 커넥션 리턴타입은? → 커넥션 → 풀 관리를 안함
- 커넥션 풀 리턴타입은? → 히카리데이터소스 → 데이터 소스를 상속받음

히카리디비 mvm에서 hikaricp 찾아라

### 에러처리

1. 서블릿에서 발생 시 직접 에러페이지 호출
2. WAS 즉 톰켓에서 에러 처리하는 방식
    1. 에러페이지를 pom.xml 에서 설정해서 jsp 설정하고 forward방식으로 호출

web.xml 파일이 없을 경우?

서버프로젝트의 web.xml로 감

서버.web.xml 파일은 우리 프로젝트만 관리하는게 아니라 여기에 에러 페이지 넣기는 힘들다

프로젝트 안에 web.xml을 새로 만들어라

java ee 설정하고 dd파일 가서 generate dd stub 클릭하면 web-inf자동으로 만들어짐
