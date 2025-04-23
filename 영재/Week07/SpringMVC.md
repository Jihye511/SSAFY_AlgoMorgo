### SpringMVC

- DispatcherServlet(spring-webmvc의 org.springframework.web.servlet.DispathServlet)
    - Spring@MVC의 가장 중추적인 역할
    - Front Controller Pattern의 적용으로 client의 모든 request 접수
    - 주로 다른 객체들(Infrastructure Component)에게 위임하여 처리
- Spring@MVC infrastructure Components
    - 핸들러 : HTTP 요청을 처리할 수 있는 객체(DispatcherServlet의 친구들 3명)
        - 요청은 DispatcherServlet에서 받기만함
    - **Handler Mapping**→”이런 request를 받았는데 어떤 Handler가 처리할 수 있나요? said dispatcher
    - **Handler Adapters**→”XX Handler에게 request 좀 처리해달라고해~”
    - **ViewResolvers**→”이런 뷰가 필요한데 부탁해요~”
- 개발자 영역
    - **model(Service/Dao) ⇒ 비지니스 로직**
    - **View page**
    - **Handler**

![image](https://github.com/user-attachments/assets/146ce47b-1755-40ed-bac5-55190394cf78)


1. 브라우저에서 요청이 들어오면 디스패쳐 서블렛이 요청을 받음
2. 핸들러 매핑을 통해 어떤 핸들러가 있는지 알게된다
3. 디스패쳐 서블렛이 핸들러 어댑터에게 요청
4. 핸들러 어택터가 알맞은 핸들러에게 요청
5. 그 핸들러에서 서비스 호출해서 비지니스 로직 처리
6. View에 전달할 자료를 모델에 저장
7. 핸들러에서 핸들러 어댑터에게 View의 이름을 전달
8. 핸들러 어댑터에서 디스패쳐서블렛에게 View이름을 전달
9. View에게 화면 표시 요철
10. View는 모델에서 정보를 참조하고 페이지를 구성
11. View에서 브라우저에게 response

### @Controller

- Handler의 한 종류로 MVC에서 Client의 요청을 ‘받아들이는’ 역할을 하는 클래스
- 다수의 요청 처리 메서드를 포함

### @RequestMapping

- 요청 처리 메서드를 작성하기 위한 annotation
- value(=path) 속성을 이용해서 처리할 경로 지정
- 클래스레벨
    - 클래스에 소속된 “모든” 요청 처리 메서드 경로의 prefix
- 메서드 레벨
    - 해당 메서드에만 적용되는 경로

### 다양한 응답 형태

- redirect, forward, json 교안 확인

### Spring Test MVC

- 기존의 테스팅 : 직접 호출하는 구조로 웹 환경에 대한 설정(WAS의 개입)이 없음
- **Mock환경 : 실제 WAS에서 Spring@MVC와 소통하는 부분을 가상으로 제공**
    
    ![image](https://github.com/user-attachments/assets/8c3d0082-cf66-40c7-84d2-076c9c38d0fa)

    
- 스프링 테스트는 단위테스트가 아닌 통합테스트
- WebMvcTest 사용

## 강사님

- WEB-INF파일 안에 있는 것들은 주소로 호출 불가능
- 스프링부트에선 자동으로 dispatcherservlet 등록
- requestMapping 요청 매핑 (WebServlet())랑 비슷
    - 핸들러에 등록하는 과정
- model에 addAttribute만 해주면 된다
    - 기본에는 하나하나 변수명 지정해서 넘겨줌
