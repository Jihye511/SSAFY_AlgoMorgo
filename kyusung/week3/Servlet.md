# Backend와 Sevlet

### 웹 프로그램

: 네트워크 너머의 서버에서 존재하며 웹을 통해서 서비스되는 프로그램

- 비즈니스 로직을 위한 백엔드와 사용자 인터페이스를 위한 프론트엔드로 구성

![image](https://github.com/user-attachments/assets/6976342e-6695-4ac6-bb3b-2183e292bc3a)

![image](https://github.com/user-attachments/assets/a375bd70-61ea-4671-aedd-ddc563ae18c8)

- 동적인 요청을 서블릿이 하고 이것이 일어나는 곳이 WAS 이다 !
- 우리가 WAS로 사용하는 것이 tomcat

### WAS, Container, Context

- 물리적 서버는 network 접근을 위해 IP 주소 존재 : HTTP 기반으로  통신
- 물리적 서버에는 WAS가 설치되어 HTTP 기반의 웹 서비스 처리
- WAS : 웹 애플리케이션이 필요한 실행환경, 리소스를 담고 있음 → container라고 불림
    - 웹 서비스를 위해 일반적으로 80port 사용
    - 개발용으로 8080port 사용
    - 동시에 여러 웹 애플리케이션이 동작 가능
        - app의 실행환경, 실행정보를 제공하는 것 : context root
        - 엄밀히 기능 제공 app과 실행 환경 .. (사실상 동일)
    

![image](https://github.com/user-attachments/assets/f2445a80-c35e-4ec1-811f-95581c5a0b2a)

![image](https://github.com/user-attachments/assets/d76a77b3-1b95-4b6f-855d-c6f74414a706)

![image](https://github.com/user-attachments/assets/4e86d1f4-af34-452b-8d7a-e2520e6d0600)

- html css js는 webapp에 작성

# Sevlet

: WAS에서 실행되는 Java Web Component (was == servlet container)

![image](https://github.com/user-attachments/assets/87c3af36-2ed1-4fe4-bbef-3169a364ab20)

- http 요청을 톰캣이 받는다.

장점 : 

- java의 OOP 기반으로 작성 : 유지보수성 및 재활용성 우수, 플랫폼 독립적
- 높은 성능, 확장성 : 하나의 서블렛 객체만 생성, 멀티스레딩을 지원
- 확장성 : 필터를 통한 공통 모듈의 전/후처리, 리스너를 이용한 이벤트 기반 처리, 스프링같은 프레임워크와 통합 용이

단점 : 

- 비즈니스 로직과 프레젠테이션 로직(HTML)이 섞여서 나타남
    - →  Servlet + JSP의 Model2 방식으로 처리

### hello servlet

```jsx
// url 매핑 설정
@WebServlet(description = "Helllo라고 인사하는 서블렛", urlPatterns = {"/hello"})

// HttpServlet 상속
public class HelloServlet extends HttpServlet{
	// doGet 재정의
	protected void doGet(HttpServletRequest request, HttpServletRequest response) throws ServletException, IOExeption
	{
		response.getWriter().append("Served At : ").append(request.getContextPath());
	}

	// doPost 재정의
	protected void doPost(HttpServletRequest request, HttpServletRequest response) throws ServletException, IOExeption
	{
		doGet(request, response)
	}
}
```

![image](https://github.com/user-attachments/assets/ccfc5cc9-ef4b-4f3e-aecc-f5782e3b1951)
### URL Mapping

: 클라이언트의 HTTP 요청을 Servlet과 매핑하기 위한 방법

- url-mapping 작성 방법
    - URL 경로 지정 : /로 시작해서 경로 지정
    - 확장자 매칭 : ‘ *.확장자 ’ 형태로 지정, 경로 지정과 함께 사용 불가
- web.xml ( deployment descriptor )활용한  map
- @WebServlet 애너테이션 활용

### 경로의 종류

- 상대경로 : /로 시작하지 않고, 현재 파일의 위치 기반 계산
- 절대경로 :
    - container root : servlet 배우기전에 사용했던 / 는 container root
    - context root : servlet 배우고 나서 배우는 / 는 context root

### Servlet Life Cycle 관리

- 사용자는 서블렛을 만들지만 객체를 만들거나, 호출하지 않음
 : Container가 life cycle에 따라 관리
    - 각 라이프사이클 훅에서 할 일을 개발자가 작성 → Container가 호출
    - 이를 통해 Servlet이 효율적 자원 관리와 최적화된 성능 구현

![image](https://github.com/user-attachments/assets/f26d7d54-7d8a-4df8-b05b-c1798f914303)

- init (ServletConfig config) : 어떤 요청도 init이 종료되기 전에 처리될 수 없음
    - 서블렛에 필요한 자원 초기화
- destroy( ) : 요청이 하나라도 처리하고 있다면 destroy는 동작하지 않음
    - init에서 초기화한 자원 정리

### HTTPServletRequest, HTTPServletResponse

- 각각 http의 요청과 응답을 추상화하기 위한 JEE 인터페이스 / http 요청, 응답의 포멧
- 전송 전문 ( 아래 내용이 0101로 날아감 )

![image](https://github.com/user-attachments/assets/4304df18-d006-44e4-b136-960d0715c1d2)

### HTTPServletRequest

: HTTP 요청을 추상화한 인터페이스 - 헤더, 파라미터, 속성 및 요청 본문과 관련된 정보에 접근하는 메서드 제공

![image](https://github.com/user-attachments/assets/c327380a-8c84-472e-aac7-e8c25dcd8539)

### HTTP method

: form의 데이터를 서버로 전달하는 방식을 결정

![image](https://github.com/user-attachments/assets/f82f161b-3799-454f-97b6-4bf5e5e1803c)

### Request parameter

- <form> 또는 queryString을 통해 클라이언트가 전달한 값 : 항상 문자열
- 처음 클라이언트에서 설정 후 조작 불가

### HTTPServletResponse

: HTTP 응답을 추상화한 인터페이스 - 헤더 조회, 설정 / 응답 상태를 위한 작업 처리

![image](https://github.com/user-attachments/assets/d91ac8bf-bba0-4374-88b6-03e67da7838d)

### HTTP Status

![image](https://github.com/user-attachments/assets/789276b9-8690-407c-8578-01b9e10a16e3)

- get/post 중 재정의하지 않은 방식으로 요청 시

### Content-Type과 Character Encoding

- content-Type : **서버가 전송하는 데이터의 MIME 타입 (** 데이터의 형식과 인코딩 방식 포함 )

![image](https://github.com/user-attachments/assets/3e3c54f1-cd11-4bc3-bc50-bfc2c1d0fd47)

- character-Encoding : 데이터가 컴퓨터가 이해할 수 있는 형태로 변환
    - 응답의 기본 인코딩은 ISO-8859-01로 한글 전송 불가 : setContentType으로 변환 必

## 서블릿 작성

### AddServlet

: 두개의 숫자형 파라미터를 받아 더하기 결과를 반환

- 서블렛에서 할 일
    - request 파라미터 조회 : 파라미터에 대한 logging 및 validation 포함
    - 요청에 대한 비즈니스 로직 처리
    - 필요 시 persistence logic
    - 응답 준비 : content type, encoding 설정
    - 응답 위한 프레젠테이션 로직 처리

# Front Controller Pattern

# 오프라인 수업

## 1. GenericServlet 클래스

Servlet은 Java에서 웹 애플리케이션을 개발할 때 사용되는 핵심 개념으로, `GenericServlet`은 이를 위한 기본적인 기능을 제공하는 클래스이다.

### **GenericServlet의 주요 메서드**

- `init()`: 서블릿이 처음 생성될 때 한 번만 실행됨 (초기화 작업 수행)
- `service()`: 클라이언트의 요청을 처리하는 메서드 (주요 로직 구현)
- `destroy()`: 서블릿이 종료될 때 호출됨 (자원 정리 등)

## 2. 프레임워크와 서블릿

### **프레임워크의 특징**

자바에서 일반적으로 클래스를 생성하고, 객체를 만들고, 메서드를 호출하지만, 프레임워크에서는 **미리 정의된 클래스(템플릿)를 상속받아 기능을 확장해야 한다.**

- 개발자가 직접 호출하지 않아도 내부적으로 호출이 이루어짐 → "**누군가 대신 호출해준다!**"
- 이 호출을 수행하는 것이 **프레임워크**이다.
- 프레임워크에서는 미리 만들어진 클래스를 호출만 해야 하므로, 개발자가 특정 메서드를 오버라이드하면 **정해진 흐름에 맞게 동작**한다.
- 특정 상태에 따라 호출되므로 이러한 메서드를 **라이프사이클 메서드(Hook Method)** 라고 한다.

## 3. 서블릿은 어떻게 호출될까? (WAS 역할)

> WAS(Web Application Server) 가 서블릿을 호출한다!
> 

WAS는 HTTP 요청을 처리하기 위해 다양한 언어를 지원할 수 있는 구조를 가진다. 자바 기반의 WAS는 다음과 같은 방식으로 서블릿을 호출한다.

### **WAS가 서블릿을 호출하는 과정**

1. HTTP 요청이 들어오면, **요청에 맞는 서블릿 객체를 생성**한다.
2. 서블릿 클래스는 `HttpServlet`을 상속받으며, `HttpServlet`은 `GenericServlet`을 상속받는다.
3. `Servlet` 자체는 **인터페이스**이며, **실제 객체 생성은 `GenericServlet` 또는 `HttpServlet`에서 수행**된다.

### **HttpServlet의 서비스 로직**

`HttpServlet`은 `service()` 메서드를 오버라이드하여 **HTTP 요청 방식(GET, POST 등)에 따라 다른 메서드를 실행**하도록 설계되어 있다.

```
public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    super.service(req, res); // 이 메서드를 주석 처리하면 doGet과 doPost도 무효화됨

    if (req.getMethod().equals("GET")) {
        doGet(req, res);
    } else if (req.getMethod().equals("POST")) {
        doPost(req, res);
    }
}
```

> super.service()를 호출하면 GenericServlet의 service() 메서드가 실행됨. 이를 주석 처리하면 doGet()과 doPost()가 호출되지 않음.
> 

## 4. 서블릿 관련 설정 파일

웹 애플리케이션에서 서블릿을 실행하기 위해서는 다양한 설정 파일이 필요하다. **설정 파일이 없다면, 어노테이션을 사용하지 않는 한 XML 설정을 직접 확인해야 한다.**

### **주요 설정 파일**

- **`server.xml`**: 서버 전체에 대한 설정 (포트, 스레드 수, 로그 설정 등)
- **`context.xml`**: 특정 애플리케이션(컨텍스트)에 대한 설정 (데이터소스, 리소스 설정 등)
- **`web.xml`**: 웹 애플리케이션에 대한 설정 (서블릿 매핑, 필터 설정 등)

## 5. 서블릿, 필터, 리스너의 역할

- **Servlet**: 단순한 실행 클래스 (컨트롤러 역할이 아님, 요청을 처리하는 로직 수행)
- **Filter**: 요청이나 응답을 가로채어 특정 작업을 수행 (ex. 인코딩 처리, 로깅, 인증 등)
- **Listener**: 특정 이벤트가 발생했을 때 실행되는 클래스 (ex. 애플리케이션 시작/종료 감지, 세션 이벤트 처리 등)

## 6. 결론: 서블릿은 컨트롤러가 아니다

서블릿은 단순한 실행 클래스일 뿐이며, MVC 패턴에서 컨트롤러 역할을 수행하지 않는다. 대신 서블릿은 요청을 받고, 그에 맞는 작업을 수행한 후 **비즈니스 로직이나 뷰를 호출하는 역할**을 한다.

### **MVC 패턴에서 서블릿의 역할**

- **Model (M)**: 데이터 처리 (DAO, Service 등)
- **View (V)**: 화면을 담당 (JSP, HTML, JavaScript 등)
- **Controller (C)**: Model과 View를 연결하는 역할 (Spring MVC 등에서는 서블릿이 아닌 컨트롤러가 이 역할을 수행)

서블릿을 단순한 실행 클래스로 이해하고, **WAS의 역할과 프레임워크의 동작 방식**을 파악하는 것이 중요하다. 이를 통해 **어떤 메서드가 언제 호출되는지, 왜 필요한지**를 이해할 수 있다.

![image.png](attachment:17a1d605-e25e-4741-a1cf-a321e33059e4:image.png)
