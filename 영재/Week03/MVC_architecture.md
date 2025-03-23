# MVC

애플리케이션을 역할에 따라 Model, View, Controller로 모듈화 하는 패턴

- Controller : 사용자의 요청을 받고 Model과 View 간의 상호 작용을 조정하는 모듈
- Model : 비지니스 로직 즉 업부(Service)를 담당하는 모듈로 필요 시 DB 연동(DAO)
- View : 사용자와의 상호작용을 위한 UI를 담당하는 모듈

![image.png](attachment:a17d8792-3f8e-47ae-aad4-4b76719ac7fb:image.png)

## Model 1

- JSP가 Controller를 맡아 View 로직과 함께 수행
    
    ![image.png](attachment:4535ebb5-7ffe-44a5-864b-d6cdc8f504f6:image.png)
    
- 구조가 간단, 소규모 애플리케이션 적합, 빠른 개발, 직관적
- 유지보수 어려움, 확장성 제한

## Model 2

- 엄격히 MVC패턴을 따르며 Servlet이 Controller를 맡아 클라이언트 요청 접수, 전체적인 조절, JSP는 화면만 관리
    
    ![image.png](attachment:735bc853-4834-43e9-bf22-6a0f2d8152fc:image.png)
    
- 명확한 구조, 유지보수 용이, 확장성
- 복잡성, 개발속도, 학습 곡선

![image.png](attachment:5cb82eba-31d8-4de8-a34e-6e7c20314f35:image.png)

### 웹 컴포넌트 호출 : foward

- 1번
- 서버 내부에서 요청을 다른 컴포넌트(예: JSP, 서블릿)로 전달하는 방식
- 서블렛, JSP는 메서드 호출처럼 직접 호출하지 않음 : container가 호출
- 웹 컴포넌트 간 호출에는 RequesDispatcher**#foward**, HttpServletPesponse**#sendRedirect**
- 내부 자원만 호출
- 같은 서버 내에서 데이터를 유지하면서 다른 페이지로 이동할 때
    - 로그인 후 `request` 객체를 유지하면서 `welcome.jsp`로 이동
- / 는 context root

![image.png](attachment:866e5208-0d15-4567-adca-331e2da5f7a7:image.png)

### 웹 컴포넌트 호출 : redirect

- 2번
- 클라이언트에게 새로운 URL로 이동할 것을 요청하는 방식
- 클라이언트가 새로운 URL로 다시 요청을 보냄
- **현재 실행중인 페이지의 실행을 중단**하고 다른 웹 자원이 대신 호출되도록 함
- 서버 내부 리로스 뿐 아니라 외부의 리소스까지 사용 가능
- 외부 자원 호출 가능
- 새로운 요청을 발생시켜 URL을 변경하거나, 외부 사이트로 이동할 때
    - 로그인 후 메인 페이지로 이동, 특정 게시글 조회 후 목록 페이지로 이동
- /는 container root

![image.png](attachment:b8ab62e6-4c5c-444c-9787-c32688cb6f50:image.png)

## Maven

- Java 기반의 프로젝트 관리 도구로 주로 **프로젝트 구조 표준화**와  **의존성 관리, 빌드 자동화**에 사용
- POM (Project Object Model) 파일
    - `pom.xml`에 라이브러리를 추가하면 자동으로 다운로드됨
- Maven vs Gradle
    - Maven은 표준, Gradle 속도 및 커스텀 빌드

### 새로고침

- 홈페이지를 새롭게 랜더링하는 것이 아닌, 이전에 했던 요청을 다시 진행

## PRG

### 🎯 **PRG 패턴을 활용한 회원가입 예시 (Post/Redirect/Get)**

**PRG (Post/Redirect/Get) 패턴이란?**

웹에서 **폼을 제출한 후 새로고침(F5)을 했을 때 데이터가 중복 제출되는 문제**를 해결하기 위한 패턴

✔ **P (Post)** → 클라이언트가 데이터를 서버로 전송

✔ **R (Redirect)** → 서버가 클라이언트를 새로운 URL로 리다이렉트

✔ **G (Get)** → 클라이언트가 새로운 URL에서 데이터를 조회

---

## **💡 PRG 패턴을 적용한 회원가입 예시**

### ✅ **1. 일반적인 회원가입 흐름 (PRG 적용 전)**

1. 사용자가 `register.jsp`에서 회원가입 정보를 입력하고 제출 (`POST`)
2. 서버가 회원 정보를 저장한 후 `welcome.jsp`로 이동 (`forward`)
3. 사용자가 **새로고침(F5)** 하면 `POST` 요청이 다시 전송되어 **회원가입이 중복 처리됨** ❌

### ✅ **2. PRG 패턴 적용 흐름**

1. 사용자가 `register.jsp`에서 회원가입 정보를 입력하고 제출 (`POST`)
2. 서버가 회원 정보를 저장한 후 **리다이렉트 (`redirect`)** → `welcome.jsp` (`GET` 요청)
3. 사용자가 **새로고침(F5)** 해도 `GET` 요청만 발생하여 **중복 회원가입 문제 없음** ⭕
