Controller : 사용자 요청을 받고 model과 view간의 상호 작용을 조정하는 모듈

Model : 비즈니스 로직 즉 업무(Service)를 담당하는 모듈로 필요 시 DB 연동(DAO)

View : 사용자와의 상호작용을 위한 UI를 담당하는 모듈

- 장점 : 분리된 관심사로 독립적 개발 및 유지보수가 용이
- 코드의 가독성, 재사용성 향상

1. request(URL 기반 요청)
2. Controller
    1. HTTP 요청 데이터 검증
    2. 모델에 정보 처리 요청
    3. 응답을 위한 뷰 선택
3. Controller → 요청 전달 → Model → 결과 전달 → Controller
    1. 애플리 케이션의 주요 기능 제공
4. Controller → 뷰 선택 → View
    1. 응답을 위한 화면 작성
5. response (HTML 기반 응답)

## Model 1 : Controller + View, Model - 2개 분리

장점 :

- 단순성
- 빠른 개발
- 직관적

단점 :

- 유지보수 어려움
- 확장성 제한

## Model 2 : Controller, View, Model - 3개 분리

장점 :

- 명확한 구조
- 유지보수 용이
- 확장성

단점 :

- 복잡성
- 개발 속도
- 학습 곡선

### forward

내부에 호출

서블릿, JSP는 메서드 호출 처럼 직접 호출 X → container가 호출

### redirect

현재 실행중인 페이지의 실행을 중단하고 다른 웹 자원이 대신 호출되도록 함

서버 내부 리소스 뿐만 아니라 외부의 리소스까지 사용 가능

![image.png](attachment:e397dbf9-f76a-4e7e-b25e-0bc5b77b88ff:image.png)

session은 서버의 메모리를 써서 관리를 잘해줘야함

# Maven

java 기반의 프로젝트 관리 도구로 주로 빌드 자동화와 의존성 관리에 사용

주요 기능

- 프로젝트 구조 표준화
- 의존성 관리
- 빌드 자동화

archetype : 미리 만들어 놓은 maven 템플릿

maven에서 pom.xml이 프로젝트에 대한 거의 모든 정보를 가지고 있다

잘 안되면 update 해봐라

싱글톤 : 객체 여러개 만들 필요가 없다.

외부 스코프 :

페이지 리퀘스트 세션 어플리케이션

forward:

forward는 잘 전달 되지만

redirect:

최초의 request와 response가 전달되지 않는다

클라이언트로 데이터를 전달하려면 forward로 전달해야 한다

sendRedirect : request 레벨은 텍스트 데이터만 파라미터로 전달 가능

- queryString으로 redirect url에 추가
    
    필요지 session에 추가
    

# 강사님 정리

호출 방법

forward

include

차이는?

![image.png](attachment:e21a3275-aae5-40de-b04e-21c2f1966c3c:image.png)

forward로 던지면 실제 응답이 result.jsp가 하지만 브라우저는 모른다

http://localhost:8080/MemberBackendLocal/member?action=memberinsert

forward하지 말고 send redirect로 해라

근데 send redirect는 req 못던짐

그래서 session에서 한다

근데 session도 f5 누르면 했던거 반복하는 문제가 생김

sendredirect

→ 이게 필요하면 니가 쟤 불러

그래서 req → sevlet → resp → 다른 sevlet

그래면 통신을 2번하는거다

문제점 : 통신이 한번 끝났으니 request, session, application 중 request 가 끝난다

그래서 session에 저장한다 → 갔다 와도 session이 남아았음

→ session에 데이터가 있는데 있으니까 f5시 또 터진다

request를 다 넣어서 저장하는 방법도 있다

다른 servlet에 넣어야 하는 값을 resp 할때 같이 넣어 보냄

그래서 쿼리스트링에 넣어서 전달하고 파라미터로 받는 방법

단점 : 보안이 꽝

sendredirect는 contextpath 앞이 경로다

sendredirect 는 f5 하면 다시 하라는거 안뜸

forward 차이

isErrorpage=”true”시

exception 내장 객체 추가

el 은 jsp가 servlet으로 바뀔 때 실행

자바스크립트는 html로 바뀌고 나서 브라우저 업뎃 될때 실행됨

자바스크립트 안에 $ 이스케이프 \ 를 붙여야됨

안그러면 jsp가 $를 바꿔버림
