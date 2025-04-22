# Spring @MVC

모델 2 기반으로 웹 어플리케이션을 만들기 위한 SpringFramework의 하위 모듈

- Front Controller Pattern : DispathcerServlet
    
    :  / 경로를 처리하므로 모든 요청을 받아들임
    
    - action parameter가 아니라 URL 기반으로 요청 분기

- 주요 특징
    - spring @MVC
    - 다양한 view 지원
    - restful 웹 서비스 지원
    - 테스트 용이

springMVC 구성요소

- DispatcherServlet
    - Spring@MVC의 중추 역할
    - Front Controller Pattern의 적용으로 클라이언트의 모든 요청 접수
    - 주로 다른 객체들에게 위임하여 처리
- Spring@MVC infra structure 컴포넌트 (제공됨)
    - HandlerMapping → 이런 요청을 받았는데 어떤 핸들러가 처리할 수 있는지?
    - HandlerAdapters → XX 핸들러에게 요청 좀 처리해달라고 해~
    - ViewResolvers → 이런 뷰가 필요한데 부탁합니다
- 내가 해야할 부분?
    - 모델 (서비스, DAO)
    - View 페이지
    - 핸들러

![image](https://github.com/user-attachments/assets/eba3a966-0eae-4ca0-8f57-ef580466c9ce)

- Spring@MVC 흐름

![image](https://github.com/user-attachments/assets/2e33b414-436a-4782-bdd6-400b53138093)

→ 복잡한 이유는 결합성 (View 기술등이 바뀔 때 대처)

템플릿 엔진 - 뷰 기술 (타임리프)

### 프로젝트 구조

![image](https://github.com/user-attachments/assets/8d10f11a-7717-494b-9f28-fa444bdb64a9)

- JSP 파일 위치 변경 이유 ? - 직접 접근을 막기 위해 : 무엇에 의한 ..?

지금까지 뭐 배웠니?

HTTPServlet Request Response 

Session Parameter Cookie get post Context

# @Controller

- Handler의 한 종류 MVC에서 Client 의 요청을 받아들이는 클래스
- Streotype(스테레오 타입) annotation인 @Controller로 표현
- 다수의 요청 처리 메서드 포함

@RequesMapping

- 요청 처리 메서드를 작성하기 위한 어노테이션
    - value (=path ) 속성을 이용해서 처리할 경로 지정
    - 클래스 레벨
        - 클래스에 소속된 모든 요청 처리 메서드 경로의 prefix
    - 메서드 레벨
        - 해당 메서드에만 적용되는 경로

![image](https://github.com/user-attachments/assets/92f23929-e049-47c4-95c5-190f8ea33327)

regist를 부르려면 ? → /member/regist

- 요청 방식 (Method 지정) → 메서드 속성을 이용해서 Get / Post 지정
- 축약형 - @GetMapping / @PostMapping

### 요청 처리 메서드

- URL에 기반해서 사용자의 요청을 처리하는 메서드
- @RequestMapping으로 요청(URL)과 메서드 연결

![image](https://github.com/user-attachments/assets/d536edad-712c-4fa9-8a68-f52c939d2cfe)

# @Controller 단위 테스트

Spring Test MVC

- 기존의 테스팅 : 직접 호출하는 구조로 웹 환경에 대한 설정(WAS)의 개입이 없음

![image](https://github.com/user-attachments/assets/228840d7-7048-4e4a-a266-13f064f538f1)

- MOC 환경 : 실제 WAS에서 Spring@MVC 와 소통하는 부분을 가상으로 제공

![image](https://github.com/user-attachments/assets/637b8bb6-1ae3-4380-818c-2be017e89b7a)

@WebMvcTest (Value = SimpleController.class)

근데 simpleController는 simple service가 필요하다. 이에 대한 Mock도 필요함

→ MockitoBean

MockMVC의 테스트 구조 : 요청 만들기 > perform 실행 > expect 검증 > do 확인

(요청 - 실행)

![image](https://github.com/user-attachments/assets/deef87ba-5966-4d6d-915a-c61b5a58bd6b)

(검증)

![image](https://github.com/user-attachments/assets/0615cadd-a13a-4bfe-b8b9-41ff91d49d18)

(확인)

![image](https://github.com/user-attachments/assets/e7c39d4d-e407-45e6-b9a2-e94fbf8e617e)

### Response Body Test

- content type이 application/json 인  경우 적용
- JSON 검증 위해 MockMvcResultMatchers.jsonPath() 메서드 사용

![image](https://github.com/user-attachments/assets/5cfb0f18-57f3-46eb-a6d2-933d4b5160fc)

# Handler 메서드의 파라미터

Request handling method의 parameter

- 다양한 타입의 파라미터들이 순서에 상관없이 필요한 경우 사용됨
- 파라미터 타입의 종류
    - HttpServletRequest, HttpServletResponse, HttpSession
    - Model ModelAttribute SessionStatus
    - Local ..

@RequestParam

- HttpServletRequest 객체의 parameter 조회에 사용
    - 요청 파라미터의 name과 동일한 변수 이름에 파라미터 매핑
        - name 속성에 파라미터 이름 지정
        - name, 변수명이 일치할 경우 name 생략가능 (버전에 따라 다를 수 있다)
    - 타입은 primitive 또는 wrapper 타입
    - 여러 값의 경우 배열 또는 List/Set 형태로 전달

```java
    @GetMapping("/calc")
    @ResponseBody
    // TODO: 06-1. 2개의 int num1, num2와 사칙 연산자 oper이 파라미터로 받아 연산 결과를 반환해보자.
    //  연산 결과는 Map타입으로 하고 key는 result로 한다.
     public Map<String, Object> calc(@RequestParam Integer num1 , @RequestParam Integer num2, @RequestParam String oper){
    	int result = switch(oper) {
    	case "+" -> num1 + num2;
    	
    	default -> num1 * num2;
    	};
    	
    	return Map.of("result", result);
    }
```

→ 이게 뭐라냐

@ModelAttribute

- 전달된 파라미터들을 DTO의 property 이름 기반으로 setter와 자동 연결해서 DTO 생성

![image](https://github.com/user-attachments/assets/3be31799-2500-48b5-ab13-3f8b45a8cde0)

- 내부적으로 일어나는 일
    - 기본 생성자를 통해 객체 생성
        - 없다면 유일 생성자 활용 - 해당 생성자에 선언한 파라미터 모두 필요
    - setter를 통해 property 설정
    - model 에 attribute 추가
        - 이름은 클래스 이름의 camel  case

![image](https://github.com/user-attachments/assets/e41d2876-ca21-480f-af46-7a6c42cd8ccc)
