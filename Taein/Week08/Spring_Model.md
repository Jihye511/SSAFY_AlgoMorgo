### #{}

용도 : 값 바인딩

변경 가능성 : 값만 대체 가능

처리 방식 : PreparedStatement 사용

SQL 삽입공격 : 안전

### ${}

용도 : 문자열 치환

변경 가능성 : 테이블명, 컬럼명 등 구조까지 변경 가능

처리 방식 : Statement 사용

SQL 삽입 공격 : 취약

## 동적 SQL

- 상황에 따라 분기 처리를 통해 SQL을 동적으로 만드는 것
- JAVA였다면 기본 문법(조건문, 반복문)에 따라 진행하겠지만 XML에서는 별도의 표기법 필요
    - If, choose - when - otherwise, where, trim, foreach, set
- OGNL (Object Graph Navigation Language) 사용
    - 파라미터로 들어온 객체 또는 Map의 속성을 이용해서 값 비교

### IF

- 가장 많이 사용되는 태그로 주로 조건절에 따라 where 문장을 구성할 때 사용
- else 구문은 없다

### choose when when otherwise choose

- 모든 조건을 원하는 대신 단 한가지만 원하는 경우

### where trim

- where : 하위 엘리먼트에서 생성한 내용이 있을 경우 앞에 where를 붙여주고 없으면 무시
    - where 뒤에 오는 문장이 and, or 키워드로 시작하는 경우 키워드 삭제
- trim : 좀 더 다양한 상황에서 사용 가능
    - prefixOverrides / suffixOverrides : 처리 후 엘리먼트 내용 중 맨 앞/뒤에 해당 문자열이 있으면 삭제
    - prefix / suffix : 처리 후 엘리먼트에 내용이 있으면 가장 앞/뒤 에 붙임

### foreach

- 주로 in 절을 추가할 경우 사용
    - collection : 값 목록을 가진 객체로 배열 또는 list
    - item : collection 내의 item을 나타내는 변수 이름
    - open : 해당 블럭을 시작할 때 사용할 기호
    - close : 해당 블럭을 시작할 때 사용할 기호
    - separator : 각 item을 구분할 분리자 기호

### Set

- update에서 set 절을 생성하며 맨 마지막 column 표기에서 쉼표 제거

### 주의점

- 조건 절에 부등호 (<, ≤ ) 사용 시 주의
    - CDATA section 이용 → xml 파서가 파싱하지 않는 영역
    - 치환 문자 사용
- like 처리 : prepared statement는 파라미터를 통으로 대체
    - 파라미터 자체에 와일드카드 ($, __)를 추가해서 호출하거나 concat으로 문자열 결합 처리

### <sql> 와 <include>

- 자주 사용되는 sql의 모듈화와 재사용
    - <sql> : 재사용할 태그 선언
    - <include> : 참조 할 곳에서 사용

# Service Layer

service layer가 없을 때

commit과 rollback이 없을때는 connection이 가져가는 소관이라 여러 repository의 소관을 하나의 connection으로 관리할 필요가 있다

이에 service가 필요하다

기존 서비스

사용하는 기술에 따라 트랜잭션 처리 기술이 다르다

dao.delete

session.delete

이 둘과 같이

JDBC API에 완전 종속 → JDBC → myBatis 로 전환 시 코드 재사용 불가

즉 횡단 관심사 발생

cross cutting concern : 횡단 관심사

→ 여러 서비스에 걸쳐 동작해야 하는 코드

## T.X 처리

스프링에서 트렌젝션 관리르 위한 추상화 레벨로 DB 접근 기술에 따라 다양한 구현체 제공

서로 다른 기술을 하나의 구현체로 처리 → PSA

```
PSA(Portable Service Abstraction)PSA란환경의 변화와 관계없이 일관된 방식의 기술로의 접근 환경을 제공하는 추상화 구조를 말합니다.
```

@transaction

try catch finally → @transaction으로 처리

## AOP에 의한 선언적 Transcation 처리

- T.X 처리에 대한 코드가 없어지고 @Transactional 으로 TX 처리 선언
- @Transactional 의 역할
    - Target 객체에 대한 proxy 생성 → Around Advice 적용
- Proxy 객체의 동작
    - target 메서드 호출 이전에 트렌젝션 시작
    - target 메서드 정상 종료 후 commit 실행
    - RuntimeException 발생 시 rollback 실행

# @Transactional

RuntimeException 계열의 예외 발생 시 rollback

- noRollbackFor : RuntimeException 중 commit 대상 예외
- rollbackFor : Checked Exception 중 rollback 대상이 되는 예외 설정

## 전파속성

@Transaction 에서 다른 @Transaction을 호출하는 경우 기존 T.X 의 전파 속성

![image.png](attachment:d46492c6-eecb-49bd-88fb-5255b4f3158a:image.png)

Spring AOP 한계

Spring Bean에 대해서만 적용가능 → proxy기반으로 동작해서

- 동일한 클래스 내에서  다른 메소드 호출시 프록시 기반이라 호출불가
    - 명시적으로 트랜잭션 호출하기
