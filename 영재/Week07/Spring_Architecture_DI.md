### Dependency Injection(의존성 주입)

- 의존성 : 우리가 무언가 행동을 하기 위해 사용해야 하는 도구
- 의존성을 없애기는 불가능

![image](https://github.com/user-attachments/assets/5e67c248-19b2-478f-be43-fc3ce3837a95)


- Bean
    - 스프링 프레임워크에 의해 생성되고 관리되는 자바 객체
    - 스프링은 빈의 생성, 의존 관계 설정,  객체 관리 등 빈의 라이프 사이클을 관리
        - 스프링 =  빈의 컨테이너
- 의존 관계
    - has - a 관계
- **의존 관계 관리를 위해 DI개념을 사용하며 이 과정에서 “제어의 역전”이 발생**
    - **스프링을 IoC(Inversion Of Control) 컨테이너라고 불림**
- DI : 객체의 의존성 즉 멤버 변수를 외부에서 주입하는 것
- Spring의 빈 관리 과정
    1. 개발자는 POJO로 빈 박성
    2. 메타 정보(빈의 생성 방법 및 관계 설정 정보)를 스프링 컨테이너에게 전달
    3. 런타임에 스프링은 메타벙보를 보고 빈 객체 생성

### 명시적 DI

- 빈은 생성하고 의존성을 주입한 코드를 별도의 파일에 명시 “이것은 빈이다”
- @Configuration
    - java기반으로 설정 파일을 만들기 위한 annotation으로 class level에 사용
- @Bean
    - 빈을 선언하기 위한 annotation으로 method level에 선언하며 method의 이름은 빈의 이름

![image](https://github.com/user-attachments/assets/230fe253-4f8c-471b-b9e6-4177cb6e7839)


### 묵시적 DI

- 명시적 DI처럼 @Configuration에서  @Bean을 사용하지 않는 형태
- @Component
    - 빈으로 사용할 각각의 클래스들이 @Component 표시
- 기본 빈의 이름
    - 클래스 이름이 Pascal case인 경우는 첫 글자를 소문자로한 camel case, 그렇지 않은 경우는 그대로 사용
    - value속성으로 재정의 가
- @AutoWired
    - 빈을 주입하기 위해 사용되는 annotation
    - 타입 기반으로 빈 자동 주입 - 해당 빈은 반드시 하나만 존재 해야 함
        - 타입 충돌이 발생할 경우 @Qualifier를 사용해서 이름 부여
    - 생성자와 메서드에서 사용 시 대상 파라미터는 모두 Spring Bean또는 @Value에 의한 scalarrkqt
    - 한 클래스에 @AutoWired에 적용된 생성자는 최대 하나만 가능, 메서드는 여러번 사용 가능
    - 생성자가 1개일 경우는 당연히 그 생성자가 호출되므로 @Autowired 생략 가능
- @ComponentScan
    - “빈이 될 수 있다”는 의도로 **실제 빈을 만들지는 않음**
    - ComponentScan을 통해 대상 빈을 scan해야 빈으로 등록됨
        - basePackages로 scan대상 패키지 등록(생략시 현재 에너테이션이 사용된 클래스의 하위 패키지 scan)

### 스테레오타입 에너테이션

- 컴포넌트는 특별한 의미를 가지지 않은 단순히 ”빈의 대상”임을 나타냄

![image](https://github.com/user-attachments/assets/9ed0d15c-077c-432f-a442-288dad5f9d68)



### 주입 방식에 따른 DI

- 생성자 주입 : 가장 권장되는 방식
    - 빈의 모든 의존성이 반드시 필요하다는 것을 명시적으로 보여줌
    - 혹시나 발생할 수 있는 빈의 순환 의존성 문제를 빈 생성 시점에 즉시 발견 가능
    - 많은 field를 blank final로 선언하고, 생성자 주입을 사용하는데 lombok의 @RequiredArgsConstructor사

### 묵시적 DI vs 명시적 DI

![image](https://github.com/user-attachments/assets/73aa3dd9-f699-4241-bfa3-81f7389d6735)


- 묵시적 DI를 기본으로 하고 명시적 DI를 보조적인 방식으로 사용
- 내가 만들지 않은 객체는 묵시적 DI로 관리 불가능(컴포넌트 어노테이션이 달려있지 않음)
    - 외부 라이브러

### 빈의 생명 주기

![image](https://github.com/user-attachments/assets/3686e973-4aa4-42a9-baff-ed58f4a6ce24)
