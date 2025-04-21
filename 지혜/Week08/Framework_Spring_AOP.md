# AOP

## 횡단 관심 사항

- 메서드 = 핵심 관심사항(비즈니스 로직)과 횡단 관심 사항(부가 로직)

![image](https://github.com/user-attachments/assets/f91fa344-ff96-4145-8b7f-6c238f6c1347)


- 횡단 관심사의 특징
    - 여러 메서드를 가로지르며 등장하는 부가 로직들
    - 로깅, 성능, 보안, 암호화, 일괄적인 예외 처리, 트랜잭션 처리 등
    - 세부적으로 알아야 할 기술들이 증가 - 유지보수 어려움

### AOP(관점 지향 프로그래밍)

- 기능을 비즈니스 로직이 아니라 필요한 개별 관점으로 바라 본다
    - 횡단 관심의 모듈화 → 재사용 → 개발자는 비즈니스 로직에 전념!!

![image](https://github.com/user-attachments/assets/a2bb46fb-f968-4959-b17e-65fd7f849a90)


## Pointcut 작성

- 특이 사항
    - return_type과  parameter에 스칼라 타입을 제외한 레퍼런스 타입 사용시 패키지 이름까지 사용
    - 여러 개의 pointcut을 연결할 때는 &&,||,!,and,or,not 등을 사용
    - named pointcut을 이용한 pointcut 재사용
    - 

## Advice의 타입

![image](https://github.com/user-attachments/assets/0ffc7d3d-a0bf-4e21-80a7-49fa182c4afa)

![image](https://github.com/user-attachments/assets/4006bea1-8f17-42dc-b9f4-272e63210f68)


### 🧩 AOP의 구성 요소

| 구성요소 | 설명 |
| --- | --- |
| **Aspect** | 공통 기능(로깅, 트랜잭션 등)을 모듈화한 단위 |
| **Join Point** | Advice가 적용될 수 있는 지점 (ex. 메서드 호출) |
| **Advice** | 실제 실행될 코드 (Before, After, Around 등) |
| **Pointcut** | 어떤 Join Point에 Advice를 적용할지 정의 |
| **Weaving** | 실제 코드에 Aspect를 적용하는 과정 |

## 🔧 `execution()` 표현식 구조

```
scss
복사편집
execution(수식어? 반환타입 클래스이름 메서드이름(파라미터) throws 예외?)

```

### ✅ 예시별 설명

| 표현식 | 설명 |
| --- | --- |
| `execution(* *(..))` | 모든 메서드 |
| `execution(* save(..))` | 이름이 `save`인 모든 메서드 |
| `execution(* com.example.service.*.*(..))` | `com.example.service` 패키지 내 모든 클래스의 모든 메서드 |
| `execution(* com.example..*.*(..))` | `com.example` 패키지와 그 하위의 모든 클래스 메서드 |
| `execution(public void com.example.service.UserService.save(..))` | `UserService`의 `save` 메서드 (정확히 지정) |

---

## 💡 추가로 알아두면 좋은 표현식

| 표현식 종류 | 설명 | 예시 |
| --- | --- | --- |
| `within()` | 특정 클래스/패키지 내부 전체 적용 | `within(com.example.service..*)` |
| `this()` / `target()` | 프록시 / 실제 객체 타입 기반 적용 | `target(com.example.MyService)` |
| `args()` | 전달되는 인자 타입 기반 | `args(String, ..)` |

---

## 🧠 실전 팁

- **공통 로직이 필요한 모든 서비스 로직에 적용하고 싶다** →
    
    `execution(* com.example.service..*(..))`
    
- **특정 어노테이션이 붙은 메서드만 적용하고 싶다** →
    
    `@annotation(YourCustomAnnotation)`
    

---

## ✅ 스프링 AOP 개요

| 특징 | 설명 |
| --- | --- |
| 기반 기술 | **프록시(Proxy)** |
| 지원 범위 | **메서드 실행(join point)** 에만 적용 가능 |
| 대상 | **Spring Bean** 에만 AOP 적용 가능 |
| 구현 방식 | **JDK 동적 프록시** 또는 **CGLIB 바이트코드 생성** 방식 |

---

## 🔁 AOP 적용 흐름 (Spring 내부 동작)

### 1. **빈 등록 시 Aspect 클래스 감지**

- `@Aspect` 어노테이션이 붙은 클래스를 스프링이 감지함
- `@EnableAspectJAutoProxy` 로 AOP 자동 설정

### 2. **Pointcut + Advice 분석**

- `@Before`, `@After`, `@Around` 등 메서드를 읽고
- 어디에(=pointcut), 어떤 로직을(=advice) 적용할지 매핑함

### 3. **프록시 객체 생성**

- 대상 빈을 프록시로 감쌈
- 인터페이스가 있으면 JDK 동적 프록시
- 클래스만 있다면 CGLIB 사용해서 하위 클래스로 프록시 생성

### 4. **빈 주입**

- DI 시, 원래 객체가 아닌 프록시 객체가 주입됨
    
    (ex. `UserService` → `UserServiceProxy`)
    

### 5. **메서드 호출 시 프록시가 가로챔**

- 실제 메서드 호출 전/후에 Advice 로직이 실행됨
- 핵심 로직은 그대로, 공통 기능만 삽입됨

---

## 📦 예제 흐름 요약

```java
java
복사편집
@Component
@Aspect
public class LogAspect {
    @Before("execution(* com.example.service..*(..))")
    public void beforeLog(JoinPoint joinPoint) {
        System.out.println("메서드 실행 전 로그");
    }
}

```

- 이 클래스가 등록되면
- `com.example.service` 아래의 메서드를 호출할 때마다
- Spring AOP 프록시가 `beforeLog()` 먼저 실행시킴

---

## ⚙️ JDK vs CGLIB 프록시 차이

| 항목 | JDK 동적 프록시 | CGLIB |
| --- | --- | --- |
| 조건 | 인터페이스 존재 시 | 클래스만 있을 때 |
| 방식 | 인터페이스 기반 | 하위 클래스를 동적으로 생성 |
| 단점 | 인터페이스만 프록시 대상 | `final` 클래스/메서드는 프록시 불가 |

---

## 💡 Spring AOP 한계 vs 대안

| 스프링 AOP | AspectJ (컴파일/로드타임 위빙) |
| --- | --- |
| 메서드 실행만 지원 | 필드 접근, 생성자 호출 등도 가능 |
| 프록시 기반 (런타임) | 바이트코드 조작 기반 (더 강력함) |
| **일반적인 애플리케이션엔 충분** | 복잡한 경우에만 사용 |
