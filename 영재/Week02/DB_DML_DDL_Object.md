## DML

- Data Manipulation Language
- 테이브르이 데이터를 조작하기 위한 명령어

### INSERT

- 새로운 행을 삽입
- 레코드 수 증가
- **단일 행 삽입, 다중행 삽입**

```sql
INSERT INTO EMP (ENAME, JOB, SAL, DEPTNO)
VALUES 
    ('Charlie', 'Clerk', 2500, 30),
    ('David', 'Analyst', 4000, 40),
    ('Eve', 'HR', 3500, 50);
```

```sql
INSERT INTO EMP_BACKUP (ENAME, JOB, SAL)
SELECT ENAME, JOB, SAL FROM EMP WHERE DEPTNO = 10;
```

- VALUES를 사용한 행 삽입, SUBQUERY를 사용한 행 삽입
- 생략 가능한 컬럼
    - NULL이 허용된 컬럼
    - DEFAULT가 설정된 컬럼
    - AUTO_INCREMENT가 설정된 컬럼

### UPDATE

- 기존 데이터를 수정
- 레코드 수 변화 없음

```sql
UPDATE EMP
SET SAL = 6000
WHERE ENAME = 'John';
```

- WHERE절 생략 시 전체 레코드가 수정 됨(MYSQL은 설정에서 막아둠)
- 수정하는 테이블을 조건문에 사용 불가
    - 조건을 FROM절에서 사용하기
        - JOIN 사용하기

INSERT OR UPDATE

```sql
INSERT INTO EMP(EMPNO, ENAME, SAL)
VALUES(8003,'MICKY',3500)
ON DUPLICATE KEY UPDATE JOB = 'CLERK', DEPTNO=30;
```

- ON DUPLICATE KEY 를 사용해서 있으면 문장 실행

### DELETE

- 기존 데이터 삭제
- 레코드 수 감소

```sql
DELETE FROM EMP
WHERE EMPNO>=8000;
```

## 트랜잭션

- 원자성을 가지는 하나의 논리적인 작업 단위
- ALL OR NOTHING
- 모든 작업이 성동적으로 완료되면 COMMIT, 하나라도 실패하면 ROLLBACK
- EX) 은행 계좌이체

### 트랜잭션 특징(ACID)

- 원자성(Atomicity) : 트랜잭션 내의 모든 연산이 성공하거나, 하나라도 실패하면 전체 취소
- 일관성(Consistency) : 트랜잭션이 실행되기 전과 후의 데이터베이스 상태가 일관성을 유지해야함(계좌이체 중 A의 계좌에서 출금이 일어나면 B의 계좌에서 입금이 일어나야함)
- 고립성(Isolation) : 하나의 트랜잭션이 완료될 때까지 다른 트랜잭션이 접근하지 못함
    - 잠금 : 동시성을 제어하기 위한 기능
    - 격리 수준 : 하나 또는 여러 트랜잭션간의 작업 내용을 어떻게 공유하고 차단할것인지를 결정하는 레벨
        - READ UNCOMMITTED : 커밋되지 않은 데이터를 읽을 수 있음
            - DIRTY READ
        - READ COMMITTED : 커밋된 데이터만 읽을 수 있음**(Default)**
            - NON-REPEATABLE READ
        - REPEATABLE READ : 트랜잭션 내에서 같은 데이터를 조회하면 항상 같은 데이터 조회 보장, **반복 읽기 가능(Default+α)**
            - PHANTOM READ
        - SERIALIZABLE : 가장 엄격한 수준으로 동시 실행을 막음
            - 성능 저하
- 지속성(Durability) : 트랜잭션이 성공적으로 완료되면 그 결과가 영구적으로 반영

### TCL

Transaction Control Language

- START TRANSACTION : 명시적 트랜잭션 시작
- COMMIT : 트랜잭션을 성공적으로 완료하고 저장
- ROLLBACK : 트랜잭션을 취소하고 변경 사항을 되돌림
- SAVEPOINT *SAVEPOINT*명

잠금 읽기

- FOR SHARE : **읽기 허용, 쓰기 금지**
- FOR UPDATE : **읽기, 쓰기 금지**

### 데이터 타입

- 숫자
    - UNSIGNED 지원
    - INT
    - DECIMAL : 고정소수점 표현
- 문자
    - 최대 크기가 Byte가 아니고 **글자의 수**
    - CHAR  : 고정 길이 / 나머지 칸은 공백
    - VARCHAR : 가변 길이 / 사용하는 만큼 공백 사용(실제 길이 저장하는 바이트 추가로 저장)
    - TEXT : 대량 텍스트
    - BLOL : 바이너리 데이터
    - ENUM
    - SET
- 날짜
    - DATE : YYYY-MM-DD
    - TIME : HH:MM:SS
    - DATETIME : YYYY-MM-DD HH:MM:SS
    - TIMESTAMP : UTC 시간대로 변환하여 저장
    - YEAR

### DDL

Data Definition Language

- CREATE
- ALTER
- DROP

## 강사님

- start transaction 이후 catch()에 걸리면 ROLLBACK
- 우리가 바꿀일은 거의 없다 (READ UNCOMMITTED,READ COMMITTED,….)
- 팬텀 리드(Phantom Read)**는 **트랜잭션이 같은 쿼리를 두 번 실행했을 때, 첫 번째 실행과 두 번째 실행 결과가 다른 경우**
    
    **다른 트랜잭션이 새로운 데이터를 추가하거나 삭제할 때 발생**
    
    - my sql에서는 일어나지 않는다
- **🔷 트랜잭션 A와 B**
    1. **트랜잭션 A**는 `테이블1`에 대한 **잠금(Lock)**을 취하고 `테이블2`에 접근하려고 한다.
    2. **트랜잭션 B**는 `테이블2`에 대한 **잠금(Lock)**을 취하고 `테이블1`에 접근하려고 한다.
    
    이때, **트랜잭션 A는 테이블2의 잠금을 기다리고**, **트랜잭션 B는 테이블1의 잠금을 기다리게** 되어 **두 트랜잭션은 서로 다른 리소스를 기다리며 교착상태에 빠지게 된다.**
    

## VIEW

- 가상테이블
- 보안 좋고 쿼리 단순화
- **가공되지 않은 raw data인 경우에만 데이터 조작 가능**
- VIEW에서는 웬만하면 데이터 건드리지 않는다
