# JSP

→ 서블렛 라는 클래스

(jakarta server pages) : 

서블렛이 자바 코드 사이에 HTML을 작성했다면 JSP는 HTML 태그 기반에 Java코드를 작성하는 방법

### 동작원리 :

- 서블렛과의 관계 : JSP는 중간 단계, 최종은 서블렛
- 생성된 자바 파일의 위치
    - 서블렛 life cycle에 해당하는 메서드
    - service 메서드의 local 변수들
    

## JSP 작성

### JSP 구성요소

![image](https://github.com/user-attachments/assets/78c0783f-e8c6-455e-8f63-36acdde4b198)

### directive - Jsp 지시자

: jsp 페이지가 실행될 때 필요한 정보를 지정하는 역할

- 필요한 정보를 컨테이너에게 알려서 서블릿 생성에 활용
- <%@ [directive]  속성 = “값” 속성 = “값” … %> 의 형태
- 종류

![image](https://github.com/user-attachments/assets/892e830a-29ae-4a3c-84ee-9839c62d3535)

### page directive

: JSP page에 대한 기본 정보 지정

![image](https://github.com/user-attachments/assets/103802fb-e3fc-4eba-ba7f-ac92570b9923)

### JSP 스크립트 요소 (자바코드)

- Script-let : <% [자바 실행문] %>
    - jspService  메서드 내부에 삽입
    - local영역에 선언된 JSP내장객체를 자유롭게 사용 (HttpServletReqeust, HttpServletResponse)
- declation : <%!  [멤버변수 or 메소드]  %>
    - 선언 위치에 상관없이 멤버변수, 메서드 선언
- expression :  <>>
- comment

## JSP 내장 객체

- 내장 객체 : .jsp 파일의 _jspService 메서드 내부에 로컬변수로 선언된 객체
    - 스크립트릿 영역에서 동일한 이름으로 로컬 변수를 선언할 수 없음
    
![image](https://github.com/user-attachments/assets/87e3dcdb-250f-41be-a1ef-8e84739b1c32)
    

- 추가적인 속성 저장을 위한 웹의 영역

![image](https://github.com/user-attachments/assets/441b1065-c360-49b1-957c-6854a9b720fe)

- 웹의 영역과 사용방법

![image](https://github.com/user-attachments/assets/b0004a8a-d48d-489e-bb6e-e2158c362124)

# EL & JSTL

: JSP를 조금 더 JSP 답게

최대한 프로그래밍 요소 제거, 많은 부분을 태그 중심으로 변경

- EL (expression language)
    - 출력을 위한 언어
    - 단순 출력 + 웹스프에 저장된 속성을 사용하기 편리
- JSTL (jsp standard tag lib.)
    - 자주 사용되는 기능들에 대해 정형화된 태그 제공
    - 별도 라이브러리 추가 설치

## EL

: 4가지 웹 스코프에 저장된 attribute를 쉽게 출력하기 위함

- 기본 표현 : ${attribute_name}
- 작은 범위에서 큰 범위로 확장하며 attribute 의 검색 → 처음 발견된 값 활용

![image](https://github.com/user-attachments/assets/249d749c-3528-49e7-9735-d26c8a976c70)

- ${sum} 은 request scope의 값
- 세션 스코프의 값을 사용하는 경우 명시적 스코프 지정 : ${session Scope}
- 값이 없을 경우 null이 아닌 공백을 표시

+) js의 템플릿 문자의 혼용 주의

- el  :  서버 측 표현식으로 서버에서 attribute로 변경 후 client로 전송
- js  :  서버와 무관하며 클라이언트에 있어야 의미 존재, - escape 처리 필요

```jsx
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>el 기본 표현</h1>
    <%-- attribute 설정 --%>
    <%
    request.setAttribute("sum", 100);
    session.setAttribute("sum", 200);
    request.setAttribute("name", "hong");
    %>
    <!-- TODO: 05. request scope와 session scope에 있는 sum과 total을 각각 출력해보자. -->
	<%= request.getAttribute("sum") %>
	${sum }
	${sessionScope.sum }
    <!-- END -->
</body>
    <!-- TODO: 06. javaScript에서 sum attribute를 사용해보자. console에 출력 시는 ``을 사용한다.-->
	<script type = "text/javascript">
		let userName = "${name}";
		let scoreSum = ${sum};
		
		let info = ` \${userName} 님의 점수는 \${scoreSum} `;
		console.log(info);
		
	</script>
	<!-- END -->
</html>

```

### EL 내장 객체

: JSP 처럼 EL이 기본적으로 가지고 있는 객체들

![image](https://github.com/user-attachments/assets/d9c95b5c-3543-4bda-aca2-c18599d922d4)

### 객체 접근법

- JavaBeans의 property에 사용할때 set/get을 제외하고 첫 글자를 소문자로 접근
    - JavaBeans : 자바 클래스 작성 규약
        - 하나 이상의 property, 이름은 CamelCase 이용
        - setter/ getter 제공
        - 기본 생성자 포함
        - 직렬화 가능
- Record 사용 시 그냥 property 사용
- Map 계열은 key 이름으로 접근
- js와 마찬가지러  ‘ . ‘ or [ ] 으로 접근

![image](https://github.com/user-attachments/assets/8902460b-2058-4880-b094-5b8c653d70a5)

```jsx
<body>
    <%
    String name = request.getParameter("name");
    int age = Integer.parseInt(request.getParameter("age"));
    User user = new User(name, age);

    request.setAttribute("user", user);

    List<String> friends = List.of("hong", "jang", "lim");
    request.setAttribute("friends", friends);
    %>

    <ul>
        <!-- TODO: 07. param 정보와 user의 정보를 출력해보자. -->
        <!--  param을 통해 name, age를 사용해보자. -->
        ${ param.name}, ${param.age}
        <hr/>
        <!--  request scope의 user 정보가 가진 name, age 속성을 확인해보자. -->
        ${user.getName()}, ${user.name }, ${user.age}
        <hr/>        
        <!--  header 정보 중 accept-language를 출력해보자. -->
        ${header["sec-fetch-mode"] }
        <hr/>        
        <!--  friends가 몇 명인지 출력해보자. -->
		${friends.size() }
        <hr/>

        <!-- END -->
    </ul>
```

### EL의 연산자

![image](https://github.com/user-attachments/assets/6a937b98-0ce9-4410-86a0-cdc6a1641460)

- empty 연산자 : 비어있거나 null 이거나 길이가 0인 배열 true
- 자바의 차이
    - 문자열 결합 ❌, 자동 캐스팅
    - 나누기 : 정수간 연산도 소수점 결과 리턴
    - 비교 연산자 : 문자 데이터에 대해 사전식 비교처리
    ${gender  == “female” } → 속성의 값과 리터럴 비교

## JSTL

: 태그 라이브러리 , 사용자 정의 태그

- 태그 라이브러리 중 ㄴ자주 쓰는 기능을 모아 제공
- 주요기능 : 코어( 변수선언, 조건문, 반복문) , 데이터포멧팅, XML처리, DB활용, 국제화
- 주로 EL과 함께 사용

### 사용선언

: <%@ taglib prefix = “C” uri = “jakarta.tags.core”>

- prefix : 페이지 내 해당 태그를 사용할 때
- uri : 해당 태그 라이브러리를 식별하는 고유 문자열

![image](https://github.com/user-attachments/assets/0589e8fe-aa59-4173-9383-584fe04a731e)

### 변수 지원

- **<c:set> <c:remove>**
    - 웹 스코프에 el 변수 관리

![image](https://github.com/user-attachments/assets/89e6ebc3-720c-4e23-a2cb-8c1e5004b07a)

- **<c:if>**

![image](https://github.com/user-attachments/assets/8da6eff6-2b31-4f6b-8b8c-562375cdc1d4)

- 조건문 : **<c:choose> <c:when> <c:otherwise>**

![image](https://github.com/user-attachments/assets/07097f58-1f3c-4de4-9686-b7b0520b9b90)

- 반복문 **<c:forEach>**

![image](https://github.com/user-attachments/assets/3d9fb929-16b9-4576-bb3c-b7e1ee761ee0)

# Include Directive

# 수업

![image](https://github.com/user-attachments/assets/5681f3b8-c22b-4fb9-9830-1f343fba0482)

- 디렉티브 선언과 인크루드 선언의 차이

디렉티브 : 두개 의 jsp파일을 하나의 클래스로 만들어서 컴파일 (동일 변수명 불가)

인크루드 : 두개 의 jsp 파일을 두개의 클래스로 만들어서 컴파일, 따로따로 실행됨. (동일 변수명 가능) 

- <jsp : ~~~ > : 액션태그
- <c: ~~~>  :  JSTL

- 서블렛에서 JSP 파일 실행 가능
