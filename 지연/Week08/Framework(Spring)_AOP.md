# Framework(Spring) AOP
---
## ▶️ AOP

### 횡단 관심 사항

- 메서드
    - 핵심 관심 사항(비지니스 로직)
    - 횡단 관심 사항(부가 로직)

### AOP(Asect Oriented Programming : 관점 지향 프로그램)

![image (15)](https://github.com/user-attachments/assets/37c4cf35-a04a-4e88-9f8f-3cf7da403e6e)

- advice
- 
    ![image (16)](https://github.com/user-attachments/assets/25e04bed-aa61-4db6-80b7-f188203a3e49)

- weaving : advice 적용하는 행위

## ▶️ AOP 작성  = 비지니스로직보다 부가기능을 위한

- 핵심 관심사
    - 각각의 DAO 에서 처리하는 메서드의 핵심 로직
- 횡단 관심사
    - 무엇을  파라미터를 포함한 로그 출력
    - 언제 - 메서드가 호출 될 때
    - 어디서  DAO 의 메서드에 대해

## ▶️ AOP 동작 테스트

### Spring AOP의 동작 원리 : proxy

- proxy
    - 대리, 대신하는 것  : 특정 사람이나 조직을 대신해서 행동하는 대리인, 투표 대리인
    - 네트워크에서 클라이언트를 대신해서 요청을 수행하는 서버
    - 업무를 처리하는 입장에서는 일반적 요청인지 proxy요청인지 구분 불

## ▶️ Pointcut작성

```java
    이부분
    @AfterReturning(value = "execution(* *..PointcutTestBean.select(String))", returning = "member")
    public void maskingUserPassword(JoinPoint jp, Member member) {
        log.debug("signature: {}, member: {}", jp.getSignature(), member);
        member.setPassword("*"); // return 값 조작!
    }
```

## ▶️ Advice의 타입

### Advice의 타입

- @Before
- @After  = finally(성공여부 상관없음)
- @AfterReturning   = 정상 종료한 경우 실행
- @AfterThrowing   = 예외가 던져졌을 때 실행
- @Around

### Before

### After

### AfterReturning

```java
   // TODO: 05-01. pointcut을 읽고 적용 대상을 파악한 후 advice 동작을 확인하세요.
    @AfterReturning(value = "execution(* *..PointcutTestBean.select(String))", returning = "member")
    public void maskingUserPassword(JoinPoint jp, Member member) {
        log.debug("signature: {}, member: {}", jp.getSignature(), member);
        member.setPassword("*"); // return 값 조작!
    }
    
    
    --> 메서드에서 사용자가 비밀번호를 사용할때 AOP를 사용함으로써 
    여기서 * 로 부가로직 대행
```

### AfterThrowing

```java
    @AfterThrowing(value = "execution(* *..PointcutTestBean.factorial(int))", throwing = "e")
    public void notifyError(JoinPoint jp, RuntimeException e) {
        log.debug("signature: {}, exception: {}", jp.getSignature(), e.getMessage());
        System.out.println("시스템 관리자에게 email 전송");
    }

```

### Around

- 직접 호출
- 유일하게 완전한 대체 가능
    - 다른 advice들은 대리 형태이지만 around만 유일하게 프록시에게 전권 이임 받아 행동

```java
	@Around("execution( * *.. PointcutTestBean.factorial(int) ) && args(n)")
	// TODO: 07-01. PointcutTestBean의 factorial이 호출될 때 값을 캐싱해보자.
	public Long casheFactorial(ProceedingJoinPoint pjp, int n) throws Throwable {
		if (!cache.containsKey(n)) {
			log.debug("없는 값 : target 조회");
			Long result = (Long) pjp.proceed(pjp.getArgs());
			cache. put(n,result);
		}
		return cache.get(n);
	}
	// END
```

## ▶️ singleton의 비밀

# AOP ≠ singleton

### Confinuration

## ▶️ Spring AOP의 한계

- Spring Bean 에 대한 proxy를 기반으로 동작
    
   ![image (17)](https://github.com/user-attachments/assets/d3d0f404-f7af-459b-863a-97487bd83140)
