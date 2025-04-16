### 프레임워크

- Frame를 가지고 하는 작업(Work) 또는 구조물의 뼈대나 툴
- **Spring + 비즈니스 로직 = 어플리케이션**
- 프레임워크는 비지니스 로직과 무관한 귀찮고 어려우며 모듈화 할  수 있는 일들 담당
- 개발자는 프레임워크에 적합한 비지니스 로직을 공급하는데 집중
- JDBC작업에서 스프링을 몰랐을 떄와 알게 된 후 개발자의 역할 변환

![image](https://github.com/user-attachments/assets/8ebd0e0c-b476-49a3-a16c-f08d84193e0e)


### 스프링부트

- 스프링 프레임 워크에서는 다양한 설정이 필요
- 또다른 스프링이 아니라 스프링 애플리케이션을 개발하는 일종의 템플릿(설정을 최소화)
- 설정 자동화를 통해 스프링 개발 속도 향상
- **단위 테스트 강화**로 플젝트 안정성 강화
- Boot는 자동화가 되어있기에 Boot만 사용하다보면 나중에 커스터마이즈 불가

### 스프링프레임워크 특징

- POJO 기반의 DI,AOP,PSA
- POJO(**Plain Old Java Object) :**  특별한 규격이나 제약이 없는 간단한 자바 객체를 의미(Person 객체)
    - 이제까지 하던 대로 그냥 평범한 객체를 만들면 된다
    - 특정한 인터페이스 구현, 상속 등이 필요 없다
    - FrameWork에서 다 해줄게~
- DI(Dependency Injection : 의존성 주입)
    - 필요한 것 주입
    - 의존성 삭제는 불가능
    - 의존성 = new 하는대신 주입받아 써라
    - 코드의 유지보수성 향상
- AOP(Aspect Oriented Programming : 관점 지향 프로그래밍)
    - 프로그램의 주요 비즈니스 로직과는 별개로 공통적인 관심사를 분리하여 처리할 수 있게 해주는 프로그래밍 패러다임
    - Aspect (관점)
        - 공통적인 관심사(로깅, 보안, 트랜잭션 관리 등)를 모듈화한 것
        - 로깅, 성능 측정, 보안 체크 등
- PSA(Portable Service Abstraction : 이식 가능한 서비스 추상화)
    - 쉽게 사용할 수 있게 추상화된 레이어 제공

### logging

- 디버깅
- 장기간 동작하는 시스템 상태에 대한 기록

### **Slf4j(Simple Logging Facade for Java)**

- java에서 logging를 위한 facade(정면)
- 심각도에 따라 trace<debug<info<warn<error
- 사용자 설정에 따라서 확인할 로그 레벨 결정
    - trace, info
- 스프링 부트는 로그백을 slf4j를 구현체로 사용

### Junit

- Java 코드의 단위 테스트 자동화를 위한 프레임워크
- 테스트와 자동화의 필요
- test 영역에서 작동
- Annotation
    - BeforeEach : 테스트 시작전 실행시킬 함수(객체 생성)
    - Test : 테스트 함수를 명시
    - DisplayName : 함수 이름을 명시
- assertion
    - 단정문 : 메서드 호출 결과가 예상한 값과 동일한지 판단하는 문장
    - org.junit.jupiter.pai.Assertions에 다양한 assertion 메서드들이 static 하게 제공됨
- 기본적인 단정문들
    - equals VS same(equals또는 ==에 대한 검증)
        - assertEquals(), assertNotSame()
        - assertArrayEquals(배열 비교)
- 예외에 대한 검증
- assertAll을 통한 모든 검증 실행
    - 최종결과 보고서에 사용됨(활용도 낮다)
- **단위 테스트 패턴**
    - BDD(Behavior Driven Development)에서 권장되는 패턴
        - given : 테스트를 위해 필요한 상황 준비
        - when : 테스트하는 메서드 실행
        - then : 테스트 결과 검증
        
        ![image](https://github.com/user-attachments/assets/b09a8dd6-1639-4c7f-bf6b-38c73fd3b3ad)


        
- F I R S T 원칙
    
    좋은 단위 테스트를 위한 기준
    
    1. **Fast (빠르다)**: 테스트는 빠르게 실행되어야 한다.
    2. **Independent (독립적이다)**: 테스트는 서로 독립적이어야 한다.
    3. **Repeatable (반복 가능하다)**: 테스트는 언제나 동일한 결과를 제공해야 한다.
    4. **Self-Validating (자체 검증이 가능하다)**: 테스트는 자동으로 성공/실패를 판단할 수 있어야 한다.
    5. **Timely (적시에 작성되다)**: 테스트는 코드와 함께 적시에 작성되어야 한다.
