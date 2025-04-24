Service Layer

- high-level 의 기능을 외부(클라이언트)에게 노출시킴
- use-case, 비즈니스 로직이 구현되는 곳
- 업무의 단위로 Transaction 처리 필요

Data Access Layer(Repository)

- 애플리케이션에서 데이터 저장소 (RDB 등)에 접근하기 위한 계층
- 단일 쿼리 단위(ex: A 계좌에서 출금 : update, B 계좌로 입금 : update)

일반적인 Model의 문제점

- 기술 별로 단계적인 작업 필요 → cross cuttion concern 발생

cross cutting concern : 횡단 관심사

→ 여러 서비스에 걸쳐 동작해야 하는 코드

@Transactional : DB 접근 기술과 무관한 선언적 Transaction 처리

→ 리소스 자동 관리 : 사용 후 자동 close

범용적인 예외 처리 : DB 마다의 차이를 Spring이 흡수, 별도로 분석할 필요가 없음

# MyBatis

persistence framework : 어떤 데이터를 함구적으로 저장하기 위한 프레임워크

SQL과 Object를 편리하게 매핑하는 framework

특징

- 내부적으로 PreparedStatement 사용 → 파라미터 설정하기 위한 코드가 없음
- ResultSet 내용을 DTO에 저장하기 위한 코드가 필요 없음 → 1회 매핑 룰 작성 필요
- 전통적으로 XML에서 SQL을 관리 → @사용기능 추가

mapper.xml

- SQL 쿼리를 정의해서 등록하며 파라미터 및 결과에 대한 매핑 처리
- 설정 파일 : DB접속설정, Transaction 등 MyBatis 동작 규칙 정의
- Spring Boot에서는 application.properties로 통합

### DAO Interface

- Connection에 대한 관리는 Spring Framework가 담당 → 코드적으로 Connection을 전달하지 X
    - Dao 의 Connection Parameter 삭제
- DB 관련 예외는 RuntimeException 계열로 전달
    - throws SQLException 제거
- DAO는 mapper로써 runtime에 구현체에 대한 proxy 생성
    - 기존의 DAO 구현체들은 불필요

### Mapper 작성

- parameterType은 메서드에서 추정 가능하므로 생략 가능
- SQL 내에서 파라미터 표기법
    - #{parameterName} → 결과적으로 PreparedStatement의 ? 로 대체
    - 전달할 파라미터가 단일값일 경우 → parameterName에 아무런 값 사용
    - 전달할 파라미터가 DTO 일 경우
        - Setter / Getter 에서 set/get을 제거하고 사용
        - Setter / Getter가 없는 경우 runtime에 reflection 수행 → performance 이슈 발생
    - 전달할 파라미터가 MAP 계열일 경우
        - map의 key 값이 parameterName으로 사용 됨

### Has-one 관계 처리

<association> : resultMap의 하위 태그

- property : 연동할 객체의 속성 명
- column : join 관계에서 사용된 컬럼 명
- resultMap : 다른 resultMap을 재사용할 때
- columnPrefix : 두 테이블의 컬럼 이름이 동일해서 구별해야 할 경우 prefix 적용
- 기존 resultMap 활용 : extends 속성 활용

### Has-Many 관계 처리

<collection> : resultmap의 하위 ㅌ그

- property : 연동할 객체의 속성 명
- column : join 관계에서 사용된 컬럼 명
- column : join 관계에서 사용된 컬럼 명
- ofType : List에 담길 N에 해당하는 객체의 타입
- resultMap : 다른 resultMap을 재사용할 때 (다른 mapper를 사용할 경우 namespace 포함)
- columnPrefix : 두 테이블의 컬럼 이름이 동일해서 구별해야 할 경우 prefix 적용
- 기존 resultMap 활용 : extends 속성 활용

# 강사님 정리

jdbc → connection 리턴

마이바티스 → sqlsession 리턴

데이타소스 이용해서

팩토리빈으로 부터 데이타소스 넣고

메이바티스 속성설정하고

sqlsessionfactory만들고

그걸 가지고 sqlsession 만듬

→ 장점 : ?

DAO에서 sqlsession을 autowired로 바꿔도됨

매퍼파일 만들어서 바로 실행하면 404 에러 뜬다

클래스 명이나 sql문이 잘못되면 404를 띄움

스프링은 전체가 뜨면서 bean을 생성하지만

mybatis도 똑같이 뜨고

config, mapper의 설정을 처음 뜰 때 같이 로딩

spring과 mybatis 둘이 동시에 할 수 있어

id가 빈으로 등록 전에 mybatis가 먼저 찾아가서 404뜰 수 있다

mybatis가 최종으로 던지는건 session이다
