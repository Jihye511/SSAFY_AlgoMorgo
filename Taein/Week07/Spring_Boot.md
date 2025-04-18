## Spring Boot

- 또 다른 스프링이 아니라 스프링 애플리케이션을 개발하는 일종의 템플릿
- 설정 자동화를 통해 스프링 개발 속도 향상
- 단위 테스트 강화로 프로젝트 안정성 강화

### application.properties

- application 동작에 필요한 property 설정
- 쓰는 이유는 그냥 주어져 있어서 쓴다 ???

@autowired는 type을 기반으로 bean을 주입

@value(”${property_name}”) → scalar 값을 주입받는다

→ scalar값을 어떻게 가져와라를 말할때 ${}로 말함 (내가 가져오고 싶은 property 값)

## 스코프 별 특징

- Singleton Scope : 비즈니스 로직을 재사용하기 위해 빈을 관리하는 scope
    - stateless 해서 개별적으로 구분될 필요가 없기 때문에 singleton 개념으로 하나만 만들어서 관리
    - 빈의 기본 scope로 @Scope를 생략하면 적용
- Prototype Scope : Singleton Scope와 달리 요청 할 때 마다 매번 새로운 빈 객체 생성
    - stateful 하게 각 빈이 독립적인 상태를 유지해야 하는 경우 적합
    - 재사용되지 않으므로 꼭 빈으로 만들어야 하는지 고민할 필요
    - 스프링 빈으로써의 장점을 얻을 수 있음
        - 다른 빈을 주입 받거나, 빈의 라이프 사이클에 따라 작업하거나 AOP를 적용하거나
- Request Scope, Session Scope
    - 각각 웹 환경에서 사용되는 scope로 request, session이 유지될 동안 존재해야 하는 빈 객체 생성
    - 만들려는 목적은 Prototype Scope와 유사

## Lombok

@Data는 많은 코드를 부지불식 간에 만들기 때문에 생각지 않았던 @ToString순환참조 될 수 있으니 주의하자

@Builder : 생성자의 파라미터를 바탕으로 builder 패턴 형식으로 객체 설정

- 클래스 레벨에 선언할 때는 전체 생성자가 필요
- 생성자가 레벨에 선언할 때는 생성자의 파라미터를 대상으로 builder 구성
- AllArgsConstructor의 Builder로 하면 모든 값을 default 즉 null로 적용
- 클래스 레벨에 선언할 때는 전체 생성자가 필요
- 생성자 레벨에 선언할 때는 생성자의 파라미터를 대상으로 builder 구성

1. annotation 쓰거나
2. annotation이 너무 많아서 힘들면 직접해라

# 강사님 정리

## 롬북

장점 :

코드가 간결해짐

단점 :

가독성 꽝, 롬북 깔려잇어야함

Service, Repository, Controller

Annotation Component에 상속받음 IOC 컨테이너에 있음

→ 구체화 된게 있으니 위에 3개 씀

스프링 MVC와 스프링 web을 햇갈리지 말라

스프링web은 dispatcher servelet, mapper, 이건 담주에 설명함

→ 스프링 컴포넌트다 즉 하나의 단락이다 (AOP, DI 같은 개념)

AOP : 

![image.png](attachment:44f62c9c-55a2-4f22-80a8-b015911534c1:image.png)

aspect : 관점에 대한것을 클래스로 만든것, 그 관점에 대한 기능을 메소드로

그 메소드가 advice

즉 aspect는 advice의 모임

로그찍겠어 → 메소드 → 각각을 advice, advice의 묶음이 aspect → 이름은 log aspect라 한다

관점지향은 코드상에 찍어야할 시점을 안적는다

그러면 어떻게 호출하나요?

코드를 일일히 짜는게 아니라 advice를 정의하면서 언제 실행되는지 결정

join point : 내가 정의한 advice가 끼어질 수 있는 지점

즉 모든 메소드는 join point가 될 수 있다 (static 메소드, 제외)

언제 적용해야 하는지 정의하는게 pointcut

그게 먼저 / 이후 / 앞과뒤 / 리턴 / exception 등 실행 시점을 정할 수 있다

weaving : 그러를 적용하는 행위 → 동사의 개념
