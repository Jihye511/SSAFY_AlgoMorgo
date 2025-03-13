# 3. DB : DML/DDL/Object(20250313)

## ▶️ DML

- Data Manipulation Language

### INSERT

- 새로운 행을 삽입
- 레코드 수 증가
- 단일 행 삽입
    
    ```sql
    INSERT INTO emp
    VALUES(9997,'손기민','MANAGER',NULL , default,
    (select max(hisal) from salgrade),1000,20);
    ```
    
- 다중 행 삽입
    
    ```sql
    INSERT INTP emp(empno, ename, sal)
    VALUES(9996,'박재완',2000),
    (9995,'정예성',3000),
    (9994,'오현석',4000),
    (9993,'박상찬',3000);
    ```
    
- NULL 입력
    - VALUES절에서 NULL 키워드나 ‘ ‘ 사용
    - VALUES절에서 DEFAULT 키워드 사

### UPDATE

- 기존 데이터를 수정
- 레코드 수 변화 없음
    
    ```sql
    UPDATE emp
    SET deptno = 30, sal = 3000 
    WHERE empno = 9996;
    ```
    

### UPDATE와 JOIN

### INSERT OR UPDATE

- 기존 데이터가 있으면 수정 없으면 삽입으로 처리

### DELETE

- 기존 데이터 삭제

## ▶️ 트랜잭션

### 트랜잭션(Transaction)

- 원자성을 가지는 하나의 논리적인 작업 단위
- ALL or Nothing

### 트랜잭션의 특징(ACID)

- Atomicity(원자성)
    - 모든 연산 성공 or 전체 실패
- Consistency(일관성)
    - 실행 전과 후의 데이터베이스 상태가 일관
- Isolation(고립성)
    - 하나의 트랜직션이 완료될 때까지 다른 트랜잭션 접근 못함
- Durability(지속성)
    - 성공적으로 완료 후 영구적 반영

![image.png](image.png)

### 격리수준

- READ UNCOMMITTED
- READ COMMITTED
- REPEATABLE READ
- SERIALIZABLE

### 트랜잭션 성능 최적화

- 트랙잭션 범위 최소화
- 적절한 격리 수준
- 인덱스 활

### TCL(Transaction Control Language)

| START TRANSACTION
(BEGIN TRANSACTION) | 명시적인 트랜잭션 시작암묵적 
자동 커밋 해제 |
| --- | --- |
| COMMIT | 성공적으로 완료하고 변경사항 저장                       |
| ROLLBACK [ TO SAVEPOINT 명] | 취소 후 변경사항을 되돌림 |
| SAVEPOINT SAVEPOINT 명 | 특정 지점을 저장,  부분 롤백 |

### 잠금 읽기

- 다른 트랜잭션의 읽기는 허용, 쓰기는 금지
    - SELECT ~ FOR SHARE;
- 다른 트랜잭션의 읽기, 쓰기 금지
    - SELECT ~ FOR UPDATE;

## ▶️ DDL

- Data Definition Language

---

# 보충

---

MySQL 트랜잭션 격리수준