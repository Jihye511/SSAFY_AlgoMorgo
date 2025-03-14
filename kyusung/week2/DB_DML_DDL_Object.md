# DML

: 테이블의 데이터 조작

- INSERT : 새로운 행 삽입
    - 단일 행 삽입 : insert into 테이블 명 [컬럼 명] VALUES (값 | 서브쿼리);
    - into절 컬럼 리스트를 명시하지 않으면 모든 컬럼 값을 생성 순서대로 입력해야함
    - 다중 행 삽입 : insert into 테이블 명 [컬럼 명] VALUES (값 | 서브쿼리), (값 | 서브쿼리), (값 | 서브쿼리)
    - 서브쿼리 결과가 복수개인 경우 반복문 없이도 다중삽입
    - 생략 가능 컬럼 : NULL 허용 컬럼, default가 있는 컬럼, auto_increment 설정 컬럼
- UPDATE : 기존 데이터 수정
    
    Update 테이블 명
    
    SET 컬럼명 = { 값  | 서브쿼리}
    
    WHERE 조건 ;
    
    - where절 생략 시 모든 레코드 수정에 유의
    
    ```sql
    # 사번이 *9995번인 사원의 부서번호와 직무를 *7934번 사원과 같게 수정
    update emp
    set deptno = (select deptno from emp where empno = 7934), 
    	job = (select job from emp where empno = 7934)
    where empno = 9995;
    # 왜 안됨 ? => 보장이 안됨
    
    update emp
    set deptno = (select deptno from (select deptno from emp where empno = 7934) E), 
    	job = (select job from (select job from emp where empno = 7934) A)
    where empno = 9995;
    # from 절로 오면 서브-서브쿼리를 먼저 처리한다 
    
    ```
    

- DELETE

# 트랜잭션

- 원자성을 가지는 하나의 논리적 작업 단위, 분할 실행 불가
- 여러 개의 SQL문을 하나의 작업 단위로 묶어 처리
- ALL or NOTHING
- 데이터의 무결성을 보장하는 핵심개념

### 트랜잭션 특징 : ACID

- Atomicity : 원자성
- Consistency : 일관성
- Isolation : 고립성
- Durability : 지속성

### **Lock**

- 잠금 : 동시성을 제어하기 위한 기능
- 격리 수준 : 하나 또는 여러 트랜잭션 간의 작업 내용을 어떻게 공유하고 차단할 것인지 결정하는 레벨

### 격리 수준

| `격리 수준 (Isolation Level)`  | `설명`  | `발생할 수 있는 문제` |
| --- | --- | --- |
| READ UNCOMMITTED | 커밋되지 않은 데이터를 읽을 수 있음 | Dirty Read |
| READ COMMITTED | 커밋된 데이터만 읽을 수 있음 | Non-Repeatable Read |
| REPEATABLE READ | 트랜잭션 내에서 같은 데이터를 조회하면 항상 같은 데이터 조회 보장 | Phantom Read |
| SERIALIZABLE | 가장 엄격한 수준으로 동시 실행을 막음 | 성능 저하 |

## TCL

- start transaction : 트랙잭션 시작 (암묵적 자동 커밋 해제)
- commit : 현재까지의 트랜잭션 완료
- rollback [to sp1]: 트랜잭션 취소, 변경사항 되돌리기
- savepoint sp1 : 특정 지점 저장, 롤백이 가능하도록

## 잠금 읽기

- 다른 트랜잭션의 읽기 허용, 쓰기 금지

```sql
start transaction;
select * for share;
~
```

- 다른 트랜잭션의 읽, 쓰 금지

```sql
start transaction;
select * for update;
~
```

# 데이터 타입

- 숫자 : int, decimal, unsigned,
- 문자 : char(고정길이 문자), varchar(가변길이 문자형)
enum(단일 선택), set(다중 선택)
- 날짜 : datetime (고정, 지역화된  날짜정보), timestamp (UTC  기준으로 시간 저장)
- 기타

# DDL

데이터 정의

create DB객체 ~ : 생성

alter DB객체 ~ : 수정

drop DB객체 ~ : 삭제

# VIEW

# INDEX

# 오프라인

- select : (읽기)
- insert / update / delete : (쓰기)

요즘은 테이블 단위 락이 아니라 로우 단위의 락을 건다.

→ lock의 mode

- unlock
- shared lock : 다른 트랜잭션도 lock 획득 가능
- exclusive lock  :  배타적 lock

초기 : unlock

만약의 select를 날리면 → shared lock 

insert / update / delete → 배타적 lock 

insert 하면 실제로 테이블이 바뀌지 않고 캐시 (유저와 실제 DB 사이의 임시 마킹)에 마킹 해놓음

→ 트랜잭션이 캐시를 통해 돌게 되므로 insert 된 것처럼 보이게 됨.

다른 세션에서는 commit을 할때까지 그 변경사항이 보이지 않음.

ROLLBACK : 캐시를 다 지워라

SAVEPOINT : 캐시에 표시함

COMMIT :  캐시 내용을 실 DB에 반영

- Java에서

```java
try{
	1. start transaction
	2. delete / select / update … 
	3. commit
catch{
	4. rollback;
}
```
