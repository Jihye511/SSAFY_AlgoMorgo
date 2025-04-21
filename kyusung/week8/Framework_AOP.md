# AOP 기본 컨셉

### 횡단 관심사항

- 메서드 : 핵심 관심사항 (비즈니스 로직)과 횡단 관심사항 (부가 로직)

![image](https://github.com/user-attachments/assets/1d892153-fe3d-41c2-9c16-5dca355f1e10)

- 횡단 관심사항의 특징
    - 여러 메서드를 가로지르며 등장
    - 로깅, 성능, 보안, 암호화, 일괄적 예외처리, 트랜잭션 처리
    - 세부적으로 알아야할 기술 증가 → 유지보수의 어려움

AOP : Aspect Oriented Programming (관점 지향 프로그래밍)

- 기능을 비즈니스 로직이 아니라 필요한 개별 관점으로 본다.
- 횡단 관심의 모듈화 → 재사용 → 개발자는 비즈니스 로직에 전념

![image](https://github.com/user-attachments/assets/6eab102e-ebcc-4749-98a1-e16a4bf88e1b)

→ 횡단관심사는 기존에도 ctrl c + v 로 해결가능 하던 것들인데 이를 모듈화해서 처리가능하도록 한다.

![image](https://github.com/user-attachments/assets/8b8f1734-def6-486e-8a7e-dc9c934d91b4)

- advice : 부가적인 관심사들을 모듈화한 메서드 (어떤 상황에서 동작할 것 인지)
- pointcut : 어드바이스 받을 표현식 : 필터

? setter메서드 : 핵심 관심사 : 값 설정

- set 할 때마다 log를 남기고 싶다?  -> 부가 관심사
- 코드로 할 수 도 있음 (log.XXX) !! 하지만 이렇게 하지 않고 모듈화하는 것이 AOP

# AOP 작성과 동작

DAO가 호출될 때 전달되는 파라미터를 출력하라

- 핵심 관심사 : 각각 DAO에서 처리하는 메서드의 핵심 로직
- 횡단 관심사 :
    - 무엇을 - 파리미러를 포함한 로그 출력
    - 언제 - 메서드가 호출될 때
    - 어디서 - dAO메서드가 호출 될 때

Spring AOP 동작 원리 : Proxy

- proxy : 대리, 대신 → 네트워크에서 클라이언트를 대신해서 요청을 수행하는 서버
- 업무를 처리하는 입장에서는 일반적인 요청인지 Proxy를 통한 요청인지 알 수 없음

Spring의 Proxy 기반의 빈을 이용해서 AOP 구현

- 기본 동작은 CGLib (Code Generator Library)를 이용해서 Class 기반 Proxy 생성

![image](https://github.com/user-attachments/assets/a3bde740-e022-4727-8b1b-b4b65bf53aef)

### 동작원리

![image](https://github.com/user-attachments/assets/791dfd88-9f11-4758-99cf-365a1323f977)

- 타겟 : 우리가 만든 것 ↔ Proxy : 메서드 호출 전후로 설정한 해야할 일들
- 우리에게 노출된 것은 타겟이 아니라 Proxy
- 진짜 작업할 때는 target을 불러서 내부 동작 시행
- bean인줄 알고 썼던 것이 사실은 Proxy였다

# Pointcut 작성

- JoinPoint에서 Advice를 호출할 것인지 식별하는 필터
- advice를 선언하는 애너테이션의 value값으로 설정

![image](https://github.com/user-attachments/assets/eb85f824-f3f4-4ea4-a71a-84a49a0deca4)

- execution을 이용한 pointcut 작성

![image](https://github.com/user-attachments/assets/24f12a6e-29c6-4b01-841b-f30958df23ca)

parameter 

- * 없으면 생략 가능

특이사항 

- return타입과 parameter에 스칼라 타입을 제외한 레퍼런스 타입 사용 시 패키지이름까지 명시

execution ( public jvaa.util.List<com.ssafy.MyClass> * (..) 

- 여러개의 pointcut 연결 시 && || ! and or not 등을 사용

execution ( * com..*(..) || execution (* org .. * (..) ) 

- named pointcut 이용한 pointcut 재사용

![image](https://github.com/user-attachments/assets/ee1268b0-9483-4723-ac9f-81d79c825ae9)

연결되는 메서드 확인

![image](https://github.com/user-attachments/assets/40a32505-f69b-4bb5-8d90-cab1a91949e4)

// 1번 → 1번

// 2번 → 1, 2번 해당 (s* = s로 시작하는 메소드 명)

// 3번 →  3번 (long 반환하고 , 첫번째로 int 인자를 갖는 )

// 4번 →  X 

// 만약  * *( . . )로 썼다면? return 무관, 모든 메서드에 모든 파라미터 ? → 모든 메서드에 정의  ⇒ 대 참 사

# Advice 타입

- join point 에서 advice가 실행되는 상황에 따라 5가지 타입으로 규정

![image](https://github.com/user-attachments/assets/8984c1c7-7a65-471e-81ff-8c5ff5992ac4)

- After : 성공 여부와 무관하게 ..  ( ~= finalliy )
- AfterThrowing : 예외 시에 ( ~= catch ? )

### @Before

![image](https://github.com/user-attachments/assets/d34f1e59-37ba-4fe3-a1f0-8594a7d92d7d)

- target 전에 실행하므로 여기서 예외나면 target 동작 불가
- 전달받은 args를 조작할 수 있음 - 완전 대체는 불가, 객체의 경우 속성 변경 가능
- JoinPoint.getArgs () 대신 pointcut에 args( 변수명 ) 사용 시 쉽게 접근 가능

```java
@Before("execution(void *..PointcutTestBean.save(com.ssafy.live.model.dto.Member)) && args(member)")
```

### @AfterReturning

![image](https://github.com/user-attachments/assets/faf6b999-9486-4f8e-8e12-8a9fd59ffbf4)

- target 메서드가 정상 종류 후 동작
- returning : 타겟 반환 값을 저장할 변수명, 파라미터 이름과 일치해야함
- 반환 받은 return 값 조작 가능 : 완전 대체 불가, 객체의 경우 속성은 변경 가능

### @AfterThrowing

![image](https://github.com/user-attachments/assets/30e11722-2d3f-4c5e-aea7-12ae1f5b44b6)

- tartget이 예외를 던지는 경우 동작함
- throwing : 타겟이 던진 예외를 저장하는 변수 명, 파리미터 이름과 일치해야 함
- 던져진 것이 예외가 아님 - 참조가 전달 (try- catch X)

### @After

![image](https://github.com/user-attachments/assets/d3bc78b7-cdc6-443d-afeb-006a08cde9f9)

- finally

### @Around

![image](https://github.com/user-attachments/assets/bed82954-1c57-415e-8b38-f181a11fab92)

- advice 내에서 target 메서드를 직접 호출
    - 이전 advice와 달리 파라미터 리턴 값에 대한 완전 대체 가능
- 파라미터로 ProceedingJoinPoint를 받음
    - proceed로 실제 target메서드 호출

# Spring 내부의 AOP들

스프링의 마법같은 일들의 배후에는 AOP가 있다.

## 활용

@Configuration

→ 싱글톤 만드는데 proxy /  Around 사용

@Async

→ 내부적으로 스레드풀을 이용해서 비동기 작업 진행

## spring AOP의 한계

- 스프링 빈에 대한 Proxy를 기반으로 동작 → proxy를 통하지 않으면 AOP가 적용되지 않음
- 만약 동일 클래스 내 methodB가 methodA를 호출한다면?
    - 내부에서 호출되는 methodA에서 AOP가 적용되지 않음

![image](https://github.com/user-attachments/assets/977ff0e7-1db2-4594-a8dd-d9b1711497ec)
