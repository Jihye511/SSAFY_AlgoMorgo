- 동작구조

![image](https://github.com/user-attachments/assets/fe2d834c-aa64-4b4c-91d9-94307ec96a9a)

## Container와 Context

### WAS, Container, Context

- 물리적인 서버는 network에서 접근하기 위해서 ip 주소를 갖음 - HTTP 기반으로 통신
- 물리적인 서버에는 WAS가 설치되어 HTTP 기반의 웹 서비스 처리
- WAS는 웹 서비스를 위해 일반적으로 80port를 사용하며 개발용으로는 8080을 주로 사용
    - WAS ⇒ Cotainer(실행환경 리소스 담고 있기 떄문)
- WAS에는 여러가지 웹 어플리케이션 동작 가능
    - `Context` : 각 어플리케이션의 실행 환경
- 톰캣은 server computer에 설치된 어플리케이션
- 컨테이너 루트 , 컨텍스트 루트 구별하기

![image](https://github.com/user-attachments/assets/fdfc319b-d4e9-4b19-985c-f36ea4fd342d)


## Servlet

- `Servlet` : WAS(=Servlet Container)에서 실행되는 Java Web Component

### 특징과 요청 처리 과정

![image](https://github.com/user-attachments/assets/ce63b521-efc9-408a-a05d-44c175a615a2)


- 장점
    - java의 OOP 기반으로 작성 : 유지보수 재활용성 GOOD
    - 높은 성능과 확장성 : 하나으 ㅣ서블릿 객체에서 멀티스레딩 지원⇒ 요청 동시처리
    - 확장성 : 필터를 통한 공통 모듈의 전후처리, 리스너를 이용한 이벤트기반 처리가능

### URL Mapping

- url - mapping 작성법
    - URL 경로 지정 : / 로 시작해서 경로 지정
    - 확장자 매칭 : ‘*’ 확장자 형태로 지정, 경로 지정과 함께 사용 불가
    - @ WebServlet 애너테이션 활용
        
        ```sql
        @WebServlet(urlPatterns ={”/hello”})
        ```
        
- 절대 경로로 시작하는 경로
    - 특정 context를 호출하기 위해선 / Context_name을 개입시켜야함
    - Context root: sevlet을 배우고 나서 배우는 경로는 ‘/’ 가 context root

### Servlet Life Cycle 관리

- 효율적인 자원 관리와 최적화 된 성능 구현도움
- `init` : 어떤 요청도 init이 종료되기 전에 처리될 수 X
    - 서블렛에서 필요한 자원초기화
- `destroy()` : 어떤 요청이라도 처리하고 있으면 destroy는 동작 X
    - init에서 초기화한 자원의 정리 작업

## HttpServletRequest, HttpServletResponse

- 추상화하기 위한 JEE 인터페이스

### Content-Type 과 Character Encoding

- Content-Type
    - 서버가 전송하는 데이터의 MIME타입으로 데이터의 형식과 인코딩 방식 포함
- Character Encoding
    - 데이터를 컴퓨터가 이해하고 처리할 수 있는 형태로 변환하는 방법

## Front Controller Pattern

- 기본 서블릿 작성 방식의 문제점

![image](https://github.com/user-attachments/assets/7c068e8d-131e-400f-9973-03408b994d0f)


- 해결법?

### Front Controller

- 전면에서 모든 요청을 받아들이는 Servlet

![image](https://github.com/user-attachments/assets/b28b6545-6280-4716-ab5d-21ef742c443a)


---

보충

### **Servlet 정리 -로컬**

### 1. **Servlet의 기본 개념**

- **Servlet**은 Java 기반의 웹 애플리케이션에서 클라이언트의 요청을 처리하고 응답을 생성하는 서버 측 프로그램이다.
- 클라이언트(웹 브라우저 등)가 요청을 보내면, 서버에서 해당 요청을 처리하고 HTML, JSON 등의 응답을 반환한다.
- Servlet은 **javax.servlet.http.HttpServlet**을 상속하여 구현된다.

### 2. **Service() 메서드와 HTTP 요청 처리**

- `service()` 메서드는 클라이언트 요청을 받아서 적절한 HTTP 메서드(`doGet()`, `doPost()` 등)로 전달하는 역할을 한다.
- `service()` 내부에서 **`super.service()`를 호출해야** `doGet()`, `doPost()` 등의 메서드가 정상적으로 동작한다.
- 만약 `super.service()`를 호출하지 않으면 `doGet()`, `doPost()` 등의 메서드가 실행되지 않고, `service()` 내에서 직접 요청을 처리해야 한다.

```java
@Override
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    super.service(request, response); // HTTP 요청에 따라 적절한 메서드 호출
    // 추가적인 기능을 여기에 구현 가능
}
```

### 3. **클라이언트와 서버의 통신 구조**

- **클라이언트**는 서버에 요청을 보내는 주체이다. (ex. 웹 브라우저, 모바일 앱)
- **서버**는 클라이언트 요청을 받아서 처리하고 응답을 반환한다.

### 4. **서버-클라이언트 통신과 스레드**

- 웹 서버는 **클라이언트 1명당 3개의 스레드가 필요**하다.
- 스레드를 사용하지 않고 서*버-클라이언트 통신을 설명할 수 없음. (즉, **통신에서 스레드는 필수적**)*
- *웹 서버는 요청이 들어올 때마다 **스레드를 생성하여 처리**하며, 이를 통해 동시 요청을 효율적으로 처리할 수 있다.*

### *5. **Servlet 동작 과정***

1. *클라이언트가 서버에 HTTP 요청을 보냄.*
2. *서버(서블릿 컨테이너)가 요청을 `servic*e()` 메서드로 전달.
3. `service()`가 `doGet()`, `doPost()` 등의 적절한 메서드 호출.
4. 요청을 처리한 후 응답을 클라이언트에 반환.
5. 요청이 끝나면 해당 요청을 담당한 스레드는 반환되거나 풀(Pool)로 돌아감.

### 6. **추가적인 기능 구현**

- `service()` 메서드에서 `super.service()`를 호출한 후 추가적인 기능을 구현 가능.
- 예를 들어, 요청을 로깅하거나, 공통적인 인증 처리를 수행할 수 있다.

```java
@Override
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("요청 수신: " + request.getMethod());
    super.service(request, response);
}
```
