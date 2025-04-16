# Framework

frame 틀을 가지고 하는 작업 또는 구조물의 뼈대

SW적 정의 : 비즈니스 로직이 빠진 뼈대만 갖춰진 반제품 형태의 어플리케이션

## Spring Framework

- 자바 애플리케이션 개발을 위한 경향 framework
- 비즈니스 로직과 무관, 귀찮은 모듈화할 수 있는 일 담당
- 개발자는 프레임워크에 적합한 비즈니스 로직을 공급하는데 집중

![image](https://github.com/user-attachments/assets/2f81c13e-8c77-4532-b20f-cff2c031bbf3)

- 사용법 위주의 접근을 권장한다.

## Spring Boot

- 스프링 애플리케이션을 개발하는 일종의 템플릿 (= 스타터 팩)
- 설정 자동화를 통해 스프링 개발 속도 향상
- 단위 테스트 강화로 프로젝트 안정성 강화

: 너무 쓰면 바보된다.

## Spring의 주요 특징

- POJO 기반의 DI, AOP, PSA
- DI (dependency Injection) : 의존성 주입
    - 의존성을 주입받아 사용하므로 의존성이 변경되더라도 의존하는 객체는 변경될 필요없다
    - 코드의 유지보수성
- AOP (Aspect Oriented Programming) : 관점 지향 프로그래밍
    - 비즈니스 로직에서 종단 관심사 코드를 분리/모듈화하고 필요한 곳에 적용
    - 핵심 관심사에 집중
- PSA (Portable Service Abstraction) : 이식 가능한 서비스 추상화
    - 어렵고 복잡한 개념을 특정 환경에 종속되지 않고 쉽게 사용할 수 있도록 추상화된  레이어 제공
    - JPA를 쓰던 MyBatis를 쓰던 스프링에서 TX 처리 방법은 동일

- POJO (Plain Old Java Object)
    - DI AOP PSA를 하기 위해 이제까지 하던대로 그냥 평범한 객체를 만들면 된다.
        - 특정 인터페이스 구현, 상속 불필요한 일반적 자바 클래스
        - 특정 프레임워크, 라이브러리에 종속되지 않음
    - POJO로 만들면 가독성과 유지보수성이 뛰어나고 단위테스트 용이

# SLF4J와 logging

## Logging

### 필요성

- 디버깅
    - 기본 상황에서 출력과 예외 발생 시 출력에 대한 계층화
- 장기간 동작하는 시스템 상태에 대한 기록
    - 콘솔도 메모리를 사용하는 것이기 때문에 고려

### Slf4J : simple logging facade for Java

: 자바에서 로깅하기위한 facade

- facade 패턴 : 라이브러리의 복잡한 구조를 단순화시켜 인터페이스로 제공 (ex JDBC)
- Slf4j의 구현체 : log4j , logback

### Slf4j의 특징

- 심각도에 따라 로그 분류
    - trace < debug < info < warn < error
- 사용자 설정에 따라 확인할 로그 레벨 결정
    - trace : trace - debug - info - warn - error 모두 표시 - 개발 과정
    - info : info - warn - error - 운영 과정
- 로그 레이아웃 설정을 통해 다양한 정보 제공

![image](https://github.com/user-attachments/assets/e1dbe526-431f-466d-839a-a180cfc51a14)

- 다양한 Appender 제공

![image](https://github.com/user-attachments/assets/c476413f-0496-4479-a1fe-f7d1c76742df)

- Calculator

```java
package com.ssafy.live;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator {

	private final static Logger log = LoggerFactory.getLogger(Calculator.class);
	
	public static int add (int a, int b) {
		int result = a + b;
		log.trace("a : {} , b : {}, result {}", a, b, result);
		return result;
	}
	
}
```

- Calculator_test

```java
package com.ssafy.live;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator_test {

	private static final Logger log = LoggerFactory.getLogger(Calculator.class);
	
	public static void main(String[] args) {

//		log.trace("level : {}" , "trace level");
//		log.debug("level : {}" , "debug level");
//		log.info("level : {}" , "info level");
//		log.warn("level : {}" , "warn level");
//		log.error("level : {}" , "error level");
//		
		Calculator c = new Calculator();
		int result = c.add(10, 20);
		System.out.println(result);
	}
}
```

- logback.xml

```java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration>

<configuration>
  
  <import class="ch.qos.logback.classic.encoder.PatternLayoutEncoder"/>
  <import class="ch.qos.logback.core.ConsoleAppender"/>
   <import class="ch.qos.logback.classic.encoder.PatternLayoutEncoder"/>
  <import class="ch.qos.logback.core.rolling.RollingFileAppender"/>
  <import class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy"/>

  <appender name="STDOUT" class="ConsoleAppender">
    <encoder class="PatternLayoutEncoder">
      <pattern>%d{HH:mm:ss} %-5level %logger{1} .%M .%L %msg%n</pattern>
    </encoder>
  </appender>
  
  <appender name="FILE" class="RollingFileAppender">
    <file>logFile.log</file>
    <rollingPolicy class="TimeBasedRollingPolicy">
      <fileNamePattern>logFile.%d{yyyy-MM-dd}.log</fileNamePattern>
      <maxHistory>30</maxHistory>
      <totalSizeCap>3GB</totalSizeCap>
    </rollingPolicy>
    <encoder class="PatternLayoutEncoder">
      <pattern>%-4relative [%thread] %-5level %logger{35} -%kvp- %msg%n</pattern>
    </encoder>
  </appender>

  <root level = "trace">
    <appender-ref ref="STDOUT"/>
  	<appender-ref ref="FILE"/>
  </root>
  
</configuration>
```

# Junit

: 자바 코드의 단위 테스트 자동화를 위한 프레임워크

![image](https://github.com/user-attachments/assets/8277cdfb-1b2c-47ec-9687-d9a6027c5d2b)

### Assertion

단정문 : 메서드 호출 결과가 예상 결과와 동일한지 판단

- standard assertion
- [assertEquals : assertNotEquals] [assertNull : assertNotNull]

![image](https://github.com/user-attachments/assets/39a31dbe-97ab-43ca-8bb0-a7f02c807154)

- equals vs same

![image](https://github.com/user-attachments/assets/c6d9ba51-5807-4dfb-a1f6-349bdce3a806)

→ 둘 다 true

### 기본적인 작성법

- Given - When - Then 패턴
    - Given : 테스트를 위한 필요 상황 준비
    - When : 테스트 메서드 실행
    - Then : 테스트 결과 검증
- BDD에서 권장됨

![image](https://github.com/user-attachments/assets/13fcb088-f340-4ec8-9872-3574e88abd57)
