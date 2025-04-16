# Framework(Spring) : Framework소개
---

사전적 정의 : Frame을 가지고 하는 작업(work) 또는 구조물의 뼈대나 틀

SW 적 정의 : 비즈니스 로직이 빠진 뼈대만 갖춰진 반제품 형태의 애플리케이션

ex) 발표를 준비할때 PowerPoint라는 Framework안에 발표내용을 채워넣어 준비

## ▶️ Spring  Framework

### Spring

- 자바 애플리케이션 개발을 위한 경량 framework

### Spring Boot

- 스프링 애플리케이션을 개발하는 일종의 템플릿
- 설정 자동화로 개발속도 향상

- 단위테스트 강화로 프로그램 안정성 강화

---

### 주요 특징

- PoJo 기반의 DI, AOP, PSA
- 
![image (2)](https://github.com/user-attachments/assets/de4a0ab6-455c-4009-91e6-0e22dcacd202)

- DI(Dependency Injection : 의존성 주입)
    - 싸피에서 기본 키보드를 제공해주지만 사용하면서 불편하면
        
         내가 새로운 키보드를 구입하여 사용할 수도 있음
        
        의존성을 주입 받아 사용하므로 의존성이 변경되더라도 의존하는 객체는 변경 x 
        ⇒ 코드 유지보수성 향상
        
- AOP(Aspect Oriented Programming : 관점 지향 프로그래밍)
    
    ![image (3)](https://github.com/user-attachments/assets/7a725337-ba66-4462-b441-e7815c89eeb7)
    
    - 요리사- casher은 객체지향 형식, 요리사가 casher가 하는일을 알고 일을 시킴
    - AOP는 우렁각시처럼 몰래 , 나무꾼은 우렁각시가 일하는 것을 모름
    - 비즈니스 로직에서 종단 관심사 코드를 분리 모듈화하고 필요한 곳에 적용
    ⇒ 핵심 관심사에 집중
- PSA(Portable Service Abstraction : 이식 가능한 서비스 추상화)
    - 자전거의 기어, 자동차의 기어
    - 어렵고 복잡한 개념을 특정 환경에 종속되지 않고 쉽게 사용할 수 있게 추상화된 레이어 제공
    - JPA, MyBatis 등등 Spring에서 사용할 때 T.X처리하는 방법은 동일!
- POJO(Plain Old Java Object)
    - 마트갈때 자외선도 차단하고, 친구와 통신 장비, 장바구니 들 힘 ⇒ 우주복? = 뇌절
    - 특정 인터페이스 구현, 상속 필요없는 일반적인 자바 클래스 만들기 ⇒  FrameWork가 자외선도 차단, 통신 장비, 장바구니 들 힘 다 해줌
    ⇒ 가독성, 유지보수성, 단위테스트 용

## ▶️ Logging

### Slf4j(Simple Logging Facade for java)

- java에서 logging을 위한 facade

![image (4)](https://github.com/user-attachments/assets/258b25c7-c2f3-4373-aefe-eedd205c0069)

### 주요 특징

- 심각도에 따라 trace < debug < info < warn < error 다섯 단계로 로그 구분

```java
log.trace("trace level: {}", "trace level");
		log.debug("trace level: {}", "debug level");
		log.info("trace level: {}", "info level");
		log.warn("trace level: {}", "warn level");
		log.error("trace level: {}", "error level");
```

- trace : trace,debug,info,warn, error 표시 - 개발과정
- info : info, warn, error , - 운영 과정

```java
  <root level="trace">                                 logback.xml 에서 수정
    <appender-ref ref="STDOUT"/>
     <appender-ref ref="FILE"/>
  </root>
```

## ▶️ JUnit

### jUnit

- java 코드의 단위 테스트 자동화를 위한 프레임워크
⇒ 코드가 길어지고 시간이 지나면 테스트 결과 파악이 어려워

### 의존성 추가

```java
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.12.2</version>
    <scope>test</scope>                                     Test영역에서만 사용된다.
</dependency>
```

### 기본테스트

```java
	@BeforeEach
	public void setup() {
		calc = new Calculator();
	}

	@Test
	@DisplayName("계산 결과 확인 : ex)10+20=30")
	public void 계산기의_더하기결과가_나오는지_검증하는_테스트() {
		int a = 10;
		int b = 20;

		int result = calc.add(a, b);
		Assertions.assertEquals(30, result);
	}
```

- 결과
    
    ![image (5)](https://github.com/user-attachments/assets/098be3bf-e032-4d29-89e8-ac46acce6d84)


## ▶️ 기본적인 작성 방법

### F.I.R.S.T 원칙

| 원칙 | 의미 |
| --- | --- |
| **Fast** | 빠르게 실행돼야 함 |
| **Independent** | 다른 테스트와 독립적이어야 함 |
| **Repeatable** | 반복 실행이 가능해야 함 |
| **Self-Validating** | 결과는 자동으로 판별돼야 함 |
| **Timely** | 개발과 동시에 작성돼야 함 |
