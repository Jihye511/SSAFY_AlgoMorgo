> JSP 는 Servlet이다
> 

# JSP

### JSP Life Cycle

![image](https://github.com/user-attachments/assets/078403d1-300b-4500-a9de-aa19737957b6)


- servlet생성하는 과정만 하나 추가

## 1. **JSP의 개념**

- **서버 사이드 기술**: JSP는 서버에서 실행된 후 HTML로 변환되어 클라이언트(브라우저)에 전송됩니다.
- **Servlet 기반**: JSP는 내부적으로 **Servlet**으로 변환되어 실행됩니다.
- **동적 웹 페이지**: 데이터베이스와 연동하여 사용자에게 맞춤형 콘텐츠를 제공할 수 있습니다.

---

## 2. **JSP 동작 과정**

1. **클라이언트가 JSP 페이지 요청**
2. **JSP 컨테이너(Tomcat 등)가 JSP를 서블릿으로 변환**
3. **서블릿 클래스가 컴파일되어 실행됨**
4. **서버가 HTML 결과를 생성하고 클라이언트에 전송**
5. **클라이언트(브라우저)는 HTML을 렌더링하여 화면에 표시**

---

## 3. **JSP 문법**

JSP에서 사용하는 주요 문법을 살펴보겠습니다.

### ① **스크립트 요소**

JSP에서 Java 코드를 삽입할 수 있는 방법입니다.

| 구분 | 설명 | 예제 |
| --- | --- | --- |
| **<% ... %>** | Java 코드(선언 없이 실행) | `<% int a = 10; %>` |
| **<%= ... %>** | 출력(HTML에 값 표시) | `<%= "Hello JSP!" %>` |
| **<%! ... %>** | 변수 & 메서드 선언 | `<%! int count = 0; %>` |

```
jsp
복사편집
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head><title>JSP 예제</title></head>
<body>
    <% int a = 10; %>
    <h1>변수 값: <%= a %></h1>
</body>
</html>

```

---

### ② **디렉티브(Directive)**

JSP 페이지의 속성을 정의하는 태그입니다.

| 디렉티브 | 설명 | 예제 |
| --- | --- | --- |
| **`<%@ page %>`** | 페이지 속성 설정 | `<%@ page contentType="text/html; charset=UTF-8" %>` |
| **`<%@ include %>`** | 다른 JSP 파일 포함 | `<%@ include file="header.jsp" %>` |
| **`<%@ taglib %>`** | 커스텀 태그 라이브러리 사용 | `<%@ taglib uri="태그 URI" prefix="접두어" %>` |

---

### ③ **JSP 내장 객체**

JSP에서 기본적으로 제공하는 내장 객체들입니다.

| 객체 | 설명 |
| --- | --- |
| `request` | 클라이언트 요청 정보를 담고 있음 (`request.getParameter("name")`) |
| `response` | 클라이언트에게 응답을 보낼 때 사용 |
| `session` | 사용자 정보를 세션에 저장 |
| `application` | 웹 애플리케이션 전역에서 사용되는 정보 저장 |
| `out` | HTML 출력 스트림 (`out.println("Hello")`) |

---

## 4. **JSP & Servlet 비교**

| 특징 | JSP | Servlet |
| --- | --- | --- |
| 코드 스타일 | HTML 기반 (Java 코드 삽입) | Java 기반 (HTML 출력) |
| 실행 방식 | JSP → 서블릿 변환 → 실행 | Java 클래스 실행 |
| 유지보수 | 상대적으로 쉬움 | 어려움 (HTML이 Java 코드 내에 포함됨) |
| 속도 | 서블릿보다 약간 느림 | 빠름 |

---

## 5. **JSP 활용 예시**

### ✅ **폼 입력 데이터 처리**

```
jsp
복사편집
<form action="process.jsp" method="post">
    이름: <input type="text" name="username">
    <input type="submit" value="전송">
</form>

```

```
jsp
복사편집
<%-- process.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%
    String name = request.getParameter("username");
%>
<h2>환영합니다, <%= name %>님!</h2>

```

---

## 6. **JSP의 한계점**

- **비즈니스 로직과 UI가 혼합됨** → 유지보수 어려움
- **JSP 코드가 길어질 경우 복잡해짐**
- **Servlet + JSP 조합으로 MVC 패턴 활용** → **JSP는 View 역할 수행**

---

## 7. **JSP의 대체 기술**

- **Spring MVC (JSP 대신 Thymeleaf 사용 가능)**
- **React, Vue.js + Spring Boot 조합**
- **템플릿 엔진(Thymeleaf, FreeMarker) 활용**

---

# EL & JSTL

- JSP를 좀 더 JSP답게 만드는 요소
- 많은 부분을 태그 중심으로 변경

- `EL` : 표현 즉 출력을 위한 언어로 JSP의 expression (<%=…%>) 대체
    - 단순한 출력은 물론 특히 웹 스코프에 저장된 attribute를 사용하는 데 편리
- `JSTL` : 자주사용되는 기능들에 대해 정형화된 태그 제공
    - 별도의 라이브러리 추가 설치필

### EL 기본

![image](https://github.com/user-attachments/assets/9a3ff482-080e-4801-8b24-a9a3602ba245)


---
