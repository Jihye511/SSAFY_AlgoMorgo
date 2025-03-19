# JSP

**“JSB = Sevlet”**

## JavaServer Pages

- HTML 코드 안에 JAVA코드를 삽입할 수 있는 웹 기술로, 동적인 웹 페이지를 생성하는 데 사용
- JSP→ Sevlet 변환 후 실행

![image.png](attachment:2bb5aea6-31d5-4554-8f8b-0e9314d361c4:image.png)

## JSP 스크립트 요소 - Java 코드 작성

![image.png](attachment:0f3c1de6-d135-4f24-8802-cce9912e2c81:image.png)

- expression 안에는 세미콜론(;) 사용 X
- html 주석과 다르게 jsp주석은 사용자에게 보여지지 않는다

## 내장 객체

### .jsp 파일의 _jspService 메서드 내부에 로컬 변수로 선언 된 객체

![image.png](attachment:c3f0f2a8-b1a0-4001-b357-27ff771e3ad6:image.png)

일회용 컵 : page scope

주문서 : request scope

라면 : ssesion scope

식당 이벤트 : application scope

사용법 : set,get,remove Attribute

## EL & JSP

- JSP를 좀 더 JSP답게 만드는 요소
- JSP에서 최대한 프로그래밍 요소 제거
    - **태그 중심**
    - designer and publisher 등이 쉽게 접근 가능

## EL

- 4가지 웹 스코프에 저장된 attribute를 쉽게 출력하기 위함
- 기본 표현법 : ${attribute_name}
- page, request, session, application 순서대로 탐색하며 attribute 검색
- 갑이 없을경우 **공백(null 이 아님)**
- 자바스크립트와 같이 사용시 주의

- 객체 접근법
    - JavaBeans의 property에 사용할때 set/get을 제외하고 첫 글자를 소문자로 접근
    - 하나 이상의 Property, 기본 생성자 포함 camelCase

- 자바와 다른점
    - 문자열에 대한 + 연산은 지원하지 않고 자동 캐스팅
    - 나누기 : 정수간의 연산도 소수점 리턴
    - 비교 연산자 :  문자열에서 사전식 비교 처리 가능

## JSTL

- EL과 함께 사용할때 효과적
- JSP Standard Tag Library
    - 태그 라이브러리 중 자주 사용되는 기능을 모아서 제공하는 태그 모음
    - 주요 기능 : **코어 (변수 선언,조건문, 반복문)(가장 많이 사용),데이터 포멧팅,xml처리 등등**
- 작성 방법(위에껄 밑에꺼 방식으로)
    
    ![image.png](attachment:6ddcb274-16e8-4ee1-a7a6-f4c48771f298:image.png)
    
- JSP에서 `fmt`는 **JSTL (JavaServer Pages Standard Tag Library)** 의 **국제화(i18n) 및 포맷팅(Formatting)** 기능을 제공하는 라이브러리
