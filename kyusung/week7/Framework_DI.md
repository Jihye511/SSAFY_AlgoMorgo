# 의존성과 DI

의존성 : 우리가 어떤 활동을 하기위해 사용해야하는 도구

- 세탁기는 나의 의존성 !
- 기능을 모듈별로 분리시키고, 모듈 하나하나가 의존성

의존성을 보유하는 방법

- 전통적으로는 구매 .. 최근에는 렌탈!

빈의 컨테이너로써 스프링과 DI

- 스프링의 빈 : 스프링 프레임워크에 의해 생성, 관리되는 자바객체
- 스프링은 빈의 생성, 의존관계, 객체 관리 등 라이프 사이클을 관리한다.

의존 관계 : 어떤 객체가 비즈니스 로직 처리를 위해 다른 객체에 의존하는 관계

- 객체간의 has a 관계

의존 관계 관리를 위해 DI 개념을 사용, 이 과정에서 제어의 역전 발생

- 스프링에서 IoC 컨테이너라고 불림

(코드)

객체를 직접 만들면 구현체를 선택하므로 코드가 바뀌어야 하는 불편함이 있다.

팩토리 패턴 ? 

![image](https://github.com/user-attachments/assets/6023660f-18ae-452e-9cf1-f64abdbbcd7a)

- 이 패턴은 호출마다 새로운 객체를 생성 - 자원 낭비
- 싱글톤이면 좋겠다 → 스프링아 해줘~!

스프링은 WasherUser Swasher Lwasher 같은 객체를 빈이라는 개념으로 관리 : Spring은 빈의 Container 

![image](https://github.com/user-attachments/assets/9bd870bc-db39-445f-bb1e-1ad81dd63a95)

- 스프링은 설정에 따라 WasherUser의 의존성인 Washer에 가지고 있는 빈을 주입 : DI
- 개발자는 프레임워크가 원하는대로 만들어주면 알아서 처리됨 - 제어의 역전

결론적으로 DI란 ?

- 객체의 의존성,= 멤버 변수를 외부에서 주입하는 것
    - filed에서 할당
    - 생성자를 활용
    - setter 메서드 이용

![image](https://github.com/user-attachments/assets/889b7214-47c3-4f5e-987b-2867479748e9)

## Spring의 빈 관리 방법

![image](https://github.com/user-attachments/assets/44e84236-ab4a-4e56-ac28-8bf1d9e5b99f)

1. 개발자는 POJO로 빈 작성
2. 메타 정보 (빈의 생성 방법 및 관계 설정 정보)를 스프링 컨테이너에 전달
3. 런타임에 
    1. 스프링 : 메타 정보를 보고 빈 객체 생성 → 싱글톤 형태로 관리
    2. bean 관계에 따라 bean 주입 처리

# 명시적 DI

빈을 생성하고 의존성을 주입하는 코드를 별도 파일에 명시 : “ 이건 빈이다 “

애노테이션에서 반드시 확인 : Target ( 어디에 쓰냐 ) + 속성 

- @Configuration

: 자바 기반으로 설정 파일을 만들기 위한 어노테이션, class 레벨에 선언

![image](https://github.com/user-attachments/assets/85e45130-4d90-4380-afcd-ea754d0090fa)

- @Bean

: 빈을 선언하기 위한 어노테이션, method 레벨에 선언, method 명 == bean 명

![image](https://github.com/user-attachments/assets/3e0b680d-9098-4e5d-bd24-45e30201d54e)

# 묵시적 DI

- 명시적 DI 처럼 Configuration에서 Bean을 사용하지 않는 형태

Component

- 빈으로 사용할 각각의 클래스들에 Component 표시

기본 빈의 이름

- 클래스 이름이 Pascal case 인 경우 첫 글자를 소문자로 한 camel Case,  그렇지 않은 경우 클래스 이름 그대로 사용
- IronMan -> ironMan
- SWasher → SWasher

### Autowired

: 빈을 주입하는 어노테이션

- 타입 기반으로 빈 자동 주입 - 해당 빈은 반드시 하나만 존재
- 타입 충돌 발생 시 이름 기반의 조건을 추가 @Qualifier
- 생성자와 메서드에 사용 시 대상 파라미터는 모두 Spring Bean 또는 @Value에 의한 scalar 값
- 한 클래스에 @Autowired 적용된 생성자는 최대 하나만 가능, 메서드에서는 여러 번 사용 가능
- 생성자가 1개인 경우, 당연히 그 생성자 호출 → 생략 가능

### ComponentScan

: @Component 자체는 빈이 되는 것이 아니라, 될 수 있다 정도 → 실제 빈을 만들지는 않음

Scan을 통해 대상 빈을 스캔해야 빈으로 등록

= baesPackages : scan 대상 패키지 등록 : 생략 시 어노테이션 사용한 해당 클래스의 하위 패키지 scan

![image](https://github.com/user-attachments/assets/9aaadb5e-ccfd-4354-bcbd-29660d078aa2)

@Qualifier 

: 이름 기반으로 주입된 빈을 ㅁ한정짓기 위해 사용 , @Autowired와 함께 사용

- 생성자엔 사용 불가

## 스트레오타입 애너테이션

@Component 은 사실 특별한 의미 없음 (단순히 빈의 대상)

스테레오 타입 

- 용도에 따라 미리 여러 형태로 정형화
- 내부적으로 @Component 포함 - 빈으로 관리

![image](https://github.com/user-attachments/assets/d11ef6f5-2639-4835-89cf-9de30fb73320)

- 아무리 생각해도 없으면 Component

## DI 방법 비교

### 주입 방식에 따른 비교

생성자 주입 (가장 권장)

- 빈의 모든 의존성이 반드시 필요하다는 것을 명시적으로 표시
- 혹시나 발생할 빈의 순환 의존성 문제를 빈 생성 시점에 즉시 발견 가능
    - 다른 방식들은 빈 생성 후 주입 과정에서 발견
- 많은 경우 field를 blank final로 선언하고 생성자 주입 사용하는데
    - lombok의 @RequiredArgsConstructor를 활용 시 필요 생성자 자동 생성
- 순환 의존성 문제

![image](https://github.com/user-attachments/assets/91d9c9a5-548c-498b-a5db-a5c55a7fe852)

setter 주입 : 선택적 의존성을 가진 빈의 주입에 적합 (잘 안씀)

field 주입 : 비추 - 빈의 불변성을 보장하지 못함 (단위테스트 처럼 특수 목적으로만 . . .)

### 설정 방식에 따른 비교

명시적 DI : 로직간의 명확한 분리, 빈 설정 코드가 별도로 관리

묵시적 DI : 로직의 결합 - 구조 파악 어려움, 개발자가 의존성 주입 코드를 작성할 필요 없음

![image](https://github.com/user-attachments/assets/c9020a06-5ec1-48de-915d-3a57c00ae5a6)

명시적 DI는 사라질 수 없다. → 내가 만들지 않은 객체는 Component 가 달려있지 않다.

# appendix

@Bean에서 메서드 파라미터는 자동으로 @Autowired로 대체된다.

![image](https://github.com/user-attachments/assets/091387f2-e2aa-4e7f-bc89-7a74e77c1be6)

@Value 

- 객체가 아닌 스칼라값을 주입받는데 사용
- 주로 설정 파일에 선언된 property를 참조하는 경우 사용 : ${ property 이름 }

![image](https://github.com/user-attachments/assets/a8c615c5-6d32-4004-8863-57ae8d2ae87b)

### 빈의 생명주기

![image](https://github.com/user-attachments/assets/5de3c810-ed9a-492f-a2db-ea8c91b9096b)
