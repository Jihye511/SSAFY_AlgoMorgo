### AOP(Aspect Oriented Programming, 관점 지향 프로그래밍)

- 일반적으로 코드를 짤 때, 공통적으로 반복되는 기능(=관심사)들이 여러 클래스에 중복
- =횡단 관심 사항
    - 로그 출력
    - 보안 체크
    - 트랜잭션 처리
    - 성능 측정
- 횡단 관심의 모듈화 → 재사용 → 개발자는 비즈니스 로직에 전념

### ✅ 핵심 개념 (용어)

| 용어 | 설명 |
| --- | --- |
| **target** | 핵심 비지니스 로직을 포함한 빈 객체
AOP가 적용될 대상 |
| **Aspect** | 공통 기능(예: 로그 출력, 보안 체크 등)을 모듈화한 것 |
| **Advice** | 언제, 무엇을 할지 정한 공통 기능 (before, after 등) |
| **Join Point** | Advice가 적용될 수 있는 지점 (메서드 실행 등) |
| **Pointcut(어디서)** | 실제로 Advice를 적용할 **조건** (예: 특정 패키지의 모든 메서드) |
| **Weaving** | Pointcut에 맞는 Join Point에 Advice를 끼워 넣는 과정 |

Advice : target에는 무수히 많은 메소드 존재, 이들을join Point라고 한다. 그 중 적용시킬 메서드를 고르는데 이것을 Pointcut이라고 하고,Pointcut에 맞는 joint Point에 Advice를 끼어넣는 과정을 Weaving라고 한다.

이때 적용되는 메서드들은 보통 공통으로 가지고 있는 메서드이며 이를 Aspect라고 한다.

### Spring AOP의 동작 원리 : proxy

- proxy
    - 대리, 대신하는 것 : 특정 사람이나 조직을 대신해서 행동하는 대리인, 투표 대리인
    - 네트워크에서 클라이언트를 대신해서 요청을 수행하는 서버
    - 업무를 처리하는 입장에서는 일반적인 요청인지 Proxy를 통한 요청인지 알 수 없음
- Spring은 Proxy기반의 빈을 이용해서 AOP 구현
    - 기본 동작은 CGLib(Code Generator Library)를 이용해 Class기반 Proxy생성
    
    ![image](https://github.com/user-attachments/assets/8eeded54-04cc-4f25-9e60-28d3d59277b8)

    
- pointcut 작성법

![image](https://github.com/user-attachments/assets/03b5e330-5672-4e32-a657-1327aff6fb53)


- pointcut 실습

![image](https://github.com/user-attachments/assets/2aa60b6d-6790-43ef-bb3e-7c6185ef9ab4)


- advice 타입

![image](https://github.com/user-attachments/assets/016e4f90-0258-4c2f-9612-66aece78a385)


- 동작 과정
    - 클라이언트가 호출하면 프록시가 먼저 받고, before Advice에서 부가 로직 처리. 만약 Advice에서 예외가 발생하게 되면 타겟은 핵심 로직을 처리하지 못하고 실행되지 않는다.
- Around
    - advice내에서 target메서드를 직접 호출
        - 이전 advice와 달리 파라미터, 리턴값에 대한 완전한 대체 가능
    - 파라미터로 ProceedingJoinPoint를 받음
        - **proceed로 실제 target메서드 호출**

## `@Configuration`은 왜 특별한가?

`@Configuration` 클래스는 단순히 설정만 하는 클래스가 아냐.

Spring이 이 클래스를 **CGLIB 프록시로 감싸서** 싱글톤이 보장되도록 처리해줘.

### ✅ 예시

```java
java
복사편집
@Configuration
public class AppConfig {

    @Bean
    public A a() {
        return new A();
    }

    @Bean
    public B b() {
        return new B(a()); // 이때 a()는 또 호출되지만, 같은 객체 반환
    }
}

```

➡ `a()`가 두 번 호출되는 것처럼 보여도,

실제로는 Spring이 `@Configuration` 클래스에 **프록시를 씌워서**

`a()` 호출을 감지하고, **이미 등록된 Bean을 반환**함 (싱글톤 보장됨)

### Spring AOP의 한계

- proxy를 통하지 않으면 AOP가 적용되지 않는다.
    - 만약 동일한 클래스 내부에서 methodA()가 methodB()를 호출하면?
        - proxy 외부에서 봤을때는 methodB()만 호출 된 것이므로 methodA에 대한 advice가 동작하지 않음
- 즉 내부에서 호출되는 methodA()에서는 AOP가 적용될 수 없음
