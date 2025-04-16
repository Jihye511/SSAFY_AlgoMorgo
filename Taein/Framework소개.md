# SPRING BOOT : Spring + 설정자동화, …, 단위테스트 강화

- 설정 자동화를 통해 개발속도 향상
- 단위 테스트 강화로 프로젝트 안정성 강화

얘가 왜 되는지 생각하면서 boot를 봐라

이해하면서 보는게 중요하다

자바쓰는 이유 : 객체지향 ~~ 가비지컬렉션 ~~

## Spring Framework

주요 특징 :

- POJO 기반의 DI, AOP, PSA
- DI : 의존성 주입
    - 의존성 변경되더라도 의존하는 객체 변경될 필요가 없음
    - 코드 유지보수성 향상
- AOP : 관점 지향 프로그래밍
    - 비즈니스 로직에서 종단 관심사 코드를 분리/모듈화하고 필요한 곳에 적용
- PSA : 이식 가능한 서비스 추상화
    - 특정 환경에 종속 x → 쉽게 사용할 수 있게 추상화된 레이어 제공
    - EX) JPA, MYBATIS 등 스프링에서 T.X 처리 방법은 동일
- POJO (PLAIN OLD JAVA OBJECT)
    - 가독성 유지보수성이 뛰어나고 단위테스트가 용이
    - 특정 인터페이스 구현, 상속 등이 필요없는 일반적인 자바 클래스
    - 특정 프레임워크 라이브러리에 종속되지 않음

# Slf4j

- java에서 loggin을 위한 facade
    - facade 패턴은 라이브러리의 복잡한 구조를 단순화시켜 인터페잇로 제공하는 패턴
    - log4j, logback 같은게 있다

- trace < debug < info < warn < error
- 사용자 설정에 따라 확인할 로그 레벨 결정
- 로그 레이아웃 설정을 통해 다양한 정보 제공

# Junit

- java 코드의 단위 테스트 자동화를 위한 프레임워크
- 테스트와 자동화의 필요성
- BeforeAll, AfterAll은 static이다

# Given-When-Then 패턴

- given : 테스트를 위해 필요한 상황 준비
- when : 테스트하는 메서드 실행
- then : 테스트 결과 검증

# F I R S T 원칙

- Fast : 단위테스트는 빠르게 동작해야함, 목적을 단순히 설정하고 외부 환경과 무관하게 작성하는 것이 좋다
- Independent L 하나의 테스트는 이전 테스트에 의한 상태에 의존해서는 안된다
- Repeatable : 여러 번 반복해서 테스트를 진행하더라도 동일하게 동작해야 한다
- Self-Validation : 테스트 자체만으로 검증이 완료되어야 한다.
- Timely : TDD를 한다면 테스트는 production code 개발 전에 진행해야 한다

# 강사님 정리

로그는 성능을 잡아먹는다

로그백이 속도가 더 빠르고 최신거다

로그백은 l4fj → sl4j의 인터페이스 구현체

요즘 트렌드는 로그백이다

@slf4j 쓸때는 롬북 없는 IDE에선 조심해야 한다

애플리케이션 컨텍스트는 만들어진 객체를 저장하는 IOC 컨테이너

처음 프레임워크 뜰때 annotation component 읽어서 IOC 컨테이너에 저장

필요하다 하면 의존성에 따라 IOC에서 주입함

(주입)제어를 IOC 컨테이너가 한다 → Inverse of Controller

 

DI, IOC
