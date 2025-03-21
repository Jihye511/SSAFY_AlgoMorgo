# MVC 디자인 패턴

- 어플리케이션을 역할에 따라 Model, View, Controller 로 모듈화하는 패턴
1. Controller : 사용자의 요청을 받고 모델과 뷰 간의 상호작용을 조정하는 모듈
2. Model : 비즈니스 로직 즉 업무를 담당하는 모듈로 필요시 DB 연동 (DAO 활용)
3. View : 사용자와의 상호작용을 위한 UI 담당 모듈

![image](https://github.com/user-attachments/assets/843132f6-72a8-4298-a6c4-16776540601a)

controller가 모든 일을 다 해도 상관없지만 , 유지보수 (대체 가능성)를 고려하기 위해 설계

- 장점 : 분리된 관심사로 독립적 개발 , 유지보수 용이 , 코드의 가독성, 재사용성 향상

### Model 1 방식

: JSP가 Controller를 맡아 View 로직과 함께 수행

![image](https://github.com/user-attachments/assets/9b4a37b7-d7bb-4025-b5f9-d95a3e64c6ec)

장점 : 단순성, 빠른 개발, 직관적

단점 : 유지보수 어려움, 확장성 제한

### Model 2 방식

: 엄격한 MVC 패턴, 서블릿이 컨트롤러로서 클라이언트 요청 접수, 전체적인 조절, JSP는 화면만 관리

![image](https://github.com/user-attachments/assets/66303d69-c85d-4903-a4fd-158ca1c55573)

장점 : 명확한 구조, 유지보수 용이, 확장성

단점 : 복잡성, 개발 속도, 학습 곡선

### Model 2 deep dive

![image](https://github.com/user-attachments/assets/591cb6cf-b989-4707-b16d-e2099e25a71e)

### 웹 컴포넌트 호출

지금까지는  서블릿, JSP는 메서드처럼 직접 호출하지 않았음, 컨테이너가 메서드를 호출했었음

- 웹 컴포넌트 호출에는
- RequestDispatcher#forward
    - 컨트롤러가 받은 요청과 응답을 JSP에 전달, request에 저장한 정보는 JSP에서 확인 가능
    
![image](https://github.com/user-attachments/assets/cb40f041-5ca1-4659-af69-af6fda2c82d6)
    

- HttpServletResponse#sendRedirect
    - 현재 실행중인 페이지의 실행을 중단하고 다른 웹 자원이 대신 호출되도록 함
    - 서버 내부 리소스 뿐만 아니라 외부 리소스까지 사용 가능

![image](https://github.com/user-attachments/assets/f2ca7f0f-1e49-4ae4-bd19-6a3334926b61)

- 비교

![image](https://github.com/user-attachments/assets/ba4db42a-cb26-4792-89a2-c97c9271b037)

/ 의 역할은 외부로 나갈 수 있기 때문에 container root

# Maven

:  Java 기반의 프로젝트 관리 도구로 주로 빌드 자동화와 의존성 관리에 사용

- 라이브러리 들이 다양해지면서 버전 동기화 문제 발생
- 기능
    - 프로젝트 구조 표준화
    - 의존성 관리
    - 빌드 자동화

프로젝트 구조가 IDE에 종속적인 것도 해결이 가능하다.

XML 파일로 해결 ? → Project Object Model

- mysql 추가해보기

```jsx
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.ssafy</groupId>
  <artifactId>BE_03</artifactId>
  <version>0.0.1-SNAPSHOT</version>

<!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->

<dependencies>
	<dependency>
	    <groupId>com.mysql</groupId>
	    <artifactId>mysql-connector-j</artifactId>
	    <version>9.2.0</version>
	</dependency>
</dependencies>

</project>
```

- Maven 기반 프로젝트 생성
    - property : 버전 정보를 property로 선언해서 재사용성 강화

## MVC 패턴의 흐름

![image](https://github.com/user-attachments/assets/09482ba8-923b-4d99-baac-5d4472590ff4)

클라이언트로 정보를 보내줄때 일단 forward를 써보자

### PRG : Post Redirect Get pattern

Post로 받은 요청을 다시 redirect 해서 get으로 돌려줘라 !

→ 새로고침 행위는 이전에 했던 요청을 다시 진행

- 멱등한 GET, PUT, DELETE는 문제 없지만
- post 요청은 멱등하지 않으므로 여러번 수행시 중복 제출문제  발생

# 오프라인

JSP : 저장소가 4개

서블릿 : 저장소가 3개 

서블릿에는 page가 없다. JSP에서는 include 때문에 필요함!
