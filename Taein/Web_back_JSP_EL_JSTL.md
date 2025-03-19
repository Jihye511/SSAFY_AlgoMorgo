비즈니스 로직상에서 프로젝트 로직 쓰는게 힘들어서 고치기 위한것

## JSP

Jakarta Server Pages

- 서블릿이 자바코드에 html 썻다면
- jsp는 html 코드에 자바 코드를 쓰는거

Servlet과의 관계 : JSP는 중간단계, 최종은 Servlet

### jsp → servlet로 변환 → .java → servlet 컴파일 → class

## 스크립트 요소 자바 코드

<% [자바 실행문] %> → script-let

- 메서드 내부에 삽입 → local영역
    - local 영역에 선언된 JSP를 자유롭게 사용

<%! [멤버 변수 또는 메서드 %> → declaration

- 선언 위치에 상관 없이 멤버 변수, 메서드 선언

<%= [출력 할 내용] %> → expression (출력할 내용)

- Servlet에서 _jspService() 내부에서 out.print() 형태로 변형

<%— [주석 내용] —%> → comment

- JSP에만 존재, servlet에는 전달 X
- 민감한 내용 화면에 전달되지 않도록 주의

![image.png](attachment:3cdc4455-0c8d-4065-aa71-5c26c2b2063d:image.png)

# EL

expression language

- jsp에서 프로그래밍 요소 제거
    - 태그 중심으로 변경

출력 전용의 언어

→ <%= %> 를 대체

→ 웹 스코프에 저장된 attribute를 사용하는데 매우 편리

- 4가지 웹 스코프에 저장된 attribute 쉽게 출력
- 기본 표현 : ${attribute_name}
- 작은 범위 → 큰 범위 확장하며 attriute 검색 → 처음 발견된 값 활용
- 값이 없으면 null이 아닌 공백으로
- JS 템플릿 문자열에 사용된 $와 혼용 주의

JavaBeans의 property 사용시 set get 제외 첫글자 소문자

- 하나 이상 property 가짐
- 이름은 cammelCase
- setter, getter 제공
- 기본 생성자 포함
- 직렬화 가능

자바와 다른 점

- 문자열에 대한 +(결합) 연산은 지원 X, 자동 캐스팅
- 나누기 (/, div) : 장수 간의 연산에도 소수점 리턴
- 비교 연산자 → 수치 비교 뿐 아니라 문자 데이터에 대한 사전식 비교
- empty 값이 true → null, 빈 문자열, 길이가 0인 배열, 빈 Collection 객체

# JSTL

tag library : 사용자 정의 태그

- 주요 기능 : 코어(변수 선언, 조건문, 반복문), 데이터 포메팅, XML 처리, DB활용, 국제화
- 주로 EL과 함께 사용되며 Script-let 대비 간결한 페이지 작성에 유리

c:if는 else 안쓴다

그래서 choose when otherwise 쓴다

# 강사님 정리

디렉티브 : 설정 정보들을 저장한거

디클리어레이션 : 선언부 → jsp는 servlet는 자바는 클래스 → 클래스안에서 선언하는거 변수/함수

→ 함수 호출은 안됨

스크릿트릿 : 자바코드 적는건데 함수 선언은 안된다

→ 함수 호출은 됨

익스프레션 : 출력, 함수호출 출력 됨

→ 변수, 함수 선언은 안됨

jsp는 내가 바꾼다고 바뀌는게 아니라 처음 호출할 때 바뀐다

servlet끼리 교환 : attribute

jsp 끼리 교환하는거 attribute

servlet → jsp에게 하고 싶어도 attribute

el { } 안에서 사칙연산 가능

## javaBean

자바 빈은 클래스로 만들어진다

bean1 에서 다른 bean으로 전달하는 방법 (즉 jsp에서 jsp로 전달)

→ attribute

## jstl

fmt : 표현방식 모음

number, date 두개 있음

fmt:format~~

```jsx
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<% request.setAttribute("value", 123456789); %> // 이거를
<c:set var="value" value="987654321" scope="request" />  // 이거로
```

<%@ include file=*"header.jsp"* %>

이걸로 선언하면

중복되는 변수 선언할 수 없다

## include

<%@ inclue : 디렉티브 → 이걸로 선언하면 같은 변수명 사용 X

<jsp: —> action 태그

action태그는 컴파일해서 서블렛으로 만들어서 걔를 합침

실제로는 클래스 파일이 달라서 변수도 다르다

각 자바
