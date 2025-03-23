# Web(back) : Survlet

## ▶️ 웹 프로그램

### 웹 프로그램

비지니스 로직을 위한 backend와 사용자 인터페이스를 위한  frontend로 구성

- Business Logic(Java)
- Persistence Logic
- Presentation Logic

## ▶️ Container와 Context

### WAS, Container, Context

- 물리적인 서버는 HTTP기반으로 통신하며 IP주소를 갖음
- 물리적인 서버에는 WAS 가 설치되어 HTTP기반의 웹 서비스 처리
    - 일반적으로 80port사용
    - 개발용으로 8080 주로 사용
    - container라고 불림
    - 각 애플리케이션의 실행 환경과 실행 정보 제공하는 것 (웹 애플리케이션)= Context

```sql
http://192.168.0.1:8080/contextA   = context root

http://192.168.0.1:8080            = container root
```

## ▶️Servlet

WAS에서 실행되는 Java Web Component 

- 장점
    - java의 OOP기반으로 작성 : 유지보수성 및 재활용성 우수, 플랫폼 독립적
    - 높은 성능과 확장성
- 단점
    - business logic 과 presentation logic(HTML code)가 섞여서 나옴

### Hello Servlet

- URL 매핑 설정
    
    ```sql
    @WebServlet(description = " HEllo", urlPatterns = {"/hello"})
    ```
    
- HttpServlet 상속
    
    ```sql
    public class HelloServlet extends HttpServlet {
    ```
    
- doGet 재정의
    
    ```sql
    Protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
    IOException { response.getWriter().append("Served at: ").append(request.getContextPath());
    ```
    
- doPost 재정의
    
    ```sql
    Protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
    IOException { doGet(request,response); }
    ```
    

### URL Mapping

- 클라이언트와 HTTP 요청을 Servlet과 매핑하기 위한 방법

### 경로의 종류

- 상대 경로
    - / 로 시작하지 않는 것
- 절대 경로
    - /로 시작하는 경로
    - container root:  servlet을 배우기 전의 경로는 /가 container root
        - <a>,<link> , 에서는 /context name/을 사용해야 가능
    - context root : servlet을 배우고 나서 경로는 /가 context root
        - servlet url mapping
        - web.xml 의 경로
        - include directive의 경로 등

### Servlet Life Cycle 관리

- 개발자는 Servlet을 만들지만 객체를 만들거나 호출하지 않음 - Container가 life cycle에 따라 관리
    - 각각의 라이프 사이클 훅에서 할 일을 개발자가 적절히 작성 → Container가 호출
    - 이를 통해  Servlet이 효율적인 자원 관리와 최적화 된 성능을 구현할 수 있도록 함
- Init (ServletConfig config)
    - 어떤 요청도 init이 종료되기 전에는 처리될 수 없음
- Destroy()
    - 어떤 요청이라도 처리하고 있으면 동작 안함

## ▶️HttpServletRequest

### HttpServletRequest, HttpServletResponse

- 각각 Http 의 Request, Response를 추상화하기 위한 JEE 인터페이스

### HttpServletRequest

- HTTP 요청을 추상화한 인터페이스로 헤더, 파라미터, 속성 및 요청 본문과 관련된 정보에 접근하는 메서드 제공
- attribute를 제외하고 다 get

### Request parameter

- <form> 또는 queryString을 통해서 클라이언트가 전달한 값( 언제나 문자열)
    - 처음 설정 이후 조작 불가

### Http Status

![image.png](attachment:a20bd464-7368-40ae-92e7-e503a9c13613:image.png)

## ▶️HttpServletResponse

- content - Type
- Character Encoding
    - 데이터를 컴퓨터가 이해하고 처리할 수 있는 형태로 변환하는 방법

## ▶️Servlet 작성 1

### Add Servlet

- 두 개의 숫자형 파라미터를 받아서 더하기 결과를 반환하는  Servlet
    
    ```sql
    <form action ="/BE_01/add">
    <input type="number" name="num1" />+<input type="number" name="num2" />=?
    <button type = "submit"> 조회</button>
    </form>
    ```
    

### Servlet의 특징 돌아보기

- 장점
    - java의 OOP기반으로 작성 : 유지보수성 및 재활용성 우수, 플랫폼 독립적
    - 높은 성능과 확장성
- 단점
    - business logic 과 presentation logic(HTML code)가 섞여서 나옴

## ▶️Servlet 작성 2

### GuguServlet

## ▶️Servlet 작성 3

### LoginServlet

공통으로

- request파라미터 조회 : 파라미터에 대한 logging및 validation포함
- 요청에 대한 business logic처리
- 응답 준비: content type 및 encoding처리
- 응답을 위한 presentation logic 처리

→  각각 계속 만들어야 함

## ▶️Front Controller Pattern

### Front Controller

- 전면에서 모든 요청을 받아들이는 Servlet
    
    ![image.png](attachment:591ac389-a3e4-41fe-96ab-8f925e09b2eb:image.png)
    

### 요청의 구분

- URL에 언제나 ‘특정작업’을 의미하는 파라미터 추가
    - /main?action=gugudan&dan=3
    - /main?action=hello
- 와일드 카드를 이용한 URL 매핑
