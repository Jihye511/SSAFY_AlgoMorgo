# DB

: 저장장치에 저장한 데이터 집합

- DBMS에 의해 관리되는 구조화된, 상호 관련이 있는 데이터 집합
- 시스템이 종료되어도 유지되는 비휘발성 데이터

### 발전 과정

(초기) 파일 시스템 : 비휘발성이지만 관리가 어렵다.

- 동시 접속, 동시 수정 ❌
- 구조화된 형태로 저장 ❌
- 효율적인 데이터 검색 ❌

(이후) 계층형 DB - 네트워크형 - 관계형 - 객체지향형 - 객체관계형  - NoSQL - NewSQL (=RDB + NoSQL)

### 주요 특징

1. 실시간 접근 가능성
2. 데이터의 동시 공유
3. 데이터의 독립성 :  ( 트랜잭션 )
4. 데이터 무결성 : (명시제약 조건 표현)
5. 데이터 중복 최소화 : ( 정규화 )
6. 데이터 보안 : ( 권한 )
7. 데이터 일관성 : ( 트랜잭션 이후 정확한 값을 보장하는가 )
8. 데이터의 지속성

→ 이런 기능을 DBMS가 제공한다. DBMS 선택 기준은 이런 것들

## DBMS

: 응용 프로그램과 DB의 중개자로 데이터의 독립성을 제공

질의 처리, 동시성 제어, 보안, 복구, 계정 관리 등

- Oracle, mongo, maria DB ..  (* 우리는 MySQL )

아키텍쳐

![image](https://github.com/user-attachments/assets/4c69081c-0bad-4bef-a9b5-7a736d0fe4a3)

### RDBMS

: 관계형 모델을 지원하는 DBMS

| 구성 요소 | 세부 항목 | 설명 | 비고 |
| --- | --- | --- | --- |
| `개체` | 테이블 | 독립적이며 분별 가능한 특성을 가짐 | (~= 객체) |
| `관계` | 외래 키 | 두 개 이상의 개체(테이블) 간의 상호 작용을 정의 | 1:1, 1:N, N:N 관계를 가짐 |
| `속성` | 열 | 개체가 가지는 속성, 개체를 설명하는 세부 항목 |  |
| `행` | 행 | 데이터의 한 행(레코드), '인스턴스'라고도 함 |  |

## SQL

: 관계형 DBMS의 데이터를 관리하기 위해 설계한 특수 목적의 프로그래밍 언어

: ANSI SQL을 사용하면 이 표준을 따른 모든 DBMS에서 사용 가능

→ 논리적이 아니라 구조적으로 구현하는 프로그래밍 언어

+) 완벽히 DB 독립적인 SQL을 짜기는 어렵다.

![image](https://github.com/user-attachments/assets/d3faf779-279d-47a3-b101-78a23d6c0a53)

DML 中 select는 DQL로 분류되기도 함

DDL : 서비스 운영보단 준비 단계에서 많이 사용됨

# SQL - 데이터 검색

in ERD

- 점선 : 비식별 관계
- 실선 : 식별 관계

자식 엔티티 식별 간 부모의 식별자가 자식의 엔터티를 식별하는 필수적 관계에 있으면 식별 관계

![image](https://github.com/user-attachments/assets/d6f6b420-4078-4405-924b-f5437bd78081)

- order_items는  orders와 products가 없이는 식별될 수 없는 경우이다.
- order_items는 2개의 테이블의 외래키들로 구성된 복합키이기 때문이다.
- 자식이 부모의 키를 받으면서 키가 아니라 일반 속성으로 넣을 수도 있다. 
(설계상 비식별 관계로 설계가 가능하다.)

### SELECT

![image](https://github.com/user-attachments/assets/b6b60643-268b-495f-911f-c3fe5814ea35)

NULL 값 있을 때 처리 방법 : 

Select ename, sal*12, sal*12+comm, sal*12 + ifnull(comm, 0) “ 총 소득 ”

from emp;

# 내장 함수

데이터베이스에서 **반복적으로 사용하는 기능**을 편리하게 활용할 수 있도록 **미리 만들어진 함수**들을 의미한다. 기본적인 **QUERY 작성을 간편하게** 할 수 있도록 돕는다.

- 단일 행 함수 : ( 한 행 입력 → 한 행 출력 )
    
    숫자, 문자, 날짜 , 변환 함수 등
    
- 다중 행 함수  : ( 여러 행 입력 → 한 그룹당 하나의 결과 출력 )
    1. 집계 처리 (그룹 별 집계처리)
    2. 윈도우 함수 (개별 데이터 조회 + 윈도우 별 집계 결과)

+) 프로시저 : 리턴 값 거의 없고 내부적으로 일을 수행한다.

단일 행 함수 中

- 숫자 관련

![image](https://github.com/user-attachments/assets/1fd07239-f931-488d-b68a-6d13954cc5de)

** round에서 자릿수는 필수가 아니지만, truncate에서 자릿수는 필수.

123,-1 이면 120을 만들어준다.

0 기준으로 오른쪽이 양수, 왼쪽이 음수

- 문자 관련

![image](https://github.com/user-attachments/assets/7e153bc9-9373-45cf-a2e0-369a76723405)

![image](https://github.com/user-attachments/assets/5e9ae56c-45a4-4999-adc8-ce1663c68a00)

: trim의 기본 값은 both

: format 

: LENGTH - 바이트 크기

- 날짜 관련 함수

![image](https://github.com/user-attachments/assets/51dcda55-cfba-4113-893c-53f5bdbb218d)

![image](https://github.com/user-attachments/assets/79fe7c99-0539-49c6-8134-55b5184f0497)

① 날짜 

② 시간

③ 날짜 + 시간

날짜 함수는 꼭 실습을 해보자

![image](https://github.com/user-attachments/assets/33f28a30-8afb-4b74-8cd8-6390776de7a7)

![image](https://github.com/user-attachments/assets/ffdd2e01-4ffd-420b-ab48-43ba14748e75)

![image](https://github.com/user-attachments/assets/c207805d-4366-4c81-8e13-5962de4fc840)

![image](https://github.com/user-attachments/assets/ef5d77a1-c7c7-4192-a0c0-3bbcae1d17f5)

- 변환 함수

![image](https://github.com/user-attachments/assets/b683f0f3-ad25-4fcd-b786-27a7157f11bd)

- 기타함수

![image](https://github.com/user-attachments/assets/42455188-1ac5-40be-ad07-2c87aa116653)

# 오프라인

---

# **데이터베이스 언어 정리 (TCL, DDL, DML, DCL, SQL 함수)**

## **1. TCL (Transaction Control Language)**

- 트랜잭션의 처리 단위를 제어하는 명령어 집합
- `COMMIT` 또는 `ROLLBACK` 중 하나는 반드시 수행해야 함
- **SavePoint 사용 가능**
    - 일반적으로 `ROLLBACK`을 실행하면 트랜잭션 시작 지점까지 모든 변경 사항이 취소됨
    - 특정 지점(`SAVEPOINT a1`)을 설정하면 해당 지점 이후의 변경 사항만 취소 가능 (`ROLLBACK TO a1`)

---

## **2. DDL (Data Definition Language)**

- **데이터베이스 객체 (Table, Index, View, Trigger 등) 정의 및 관리**
- DDL을 이용해 생성된 객체는 **DB를 껐다 켜도 그대로 유지됨**
- 주요 명령어:
    - `CREATE` : 데이터베이스 객체 생성
    - `DROP` : 데이터베이스 객체 삭제
    - `ALTER` : 객체의 구조 변경
    - `RENAME` : 객체 이름 변경

---

## **3. DML (Data Manipulation Language)**

- **데이터를 조작하는 명령어 집합**
- 주요 명령어:
    - `INSERT` : 데이터 삽입
    - `UPDATE` : 데이터 수정
    - `DELETE` : 데이터 삭제
    - `SELECT` : 데이터 조회

---

## **4. DCL (Data Control Language)**

- **권한 부여 및 회수 관련 명령어**
- `ROLL` : 권한을 묶어서 관리 가능
- 주요 명령어:
    - `GRANT` : 권한 부여
    - `REVOKE` : 권한 회수

---

## **5. SQL 기본 개념**

### **5.1. SELECT 문 기본 구조**

```sql
SELECT [projection]
FROM [table]
WHERE [조건식]
ORDER BY [정렬기준];
```

- **`FROM` 절** : 데이터를 가져올 테이블 지정
- **`WHERE` 절** : 행(Row) 필터링
- **`SELECT` (Projection)** : 열(Column) 필터링
- **`ORDER BY` 절** : 정렬 (가장 마지막에 실행)

### **5.2. 데이터 필터링 순서**

1. **Row 필터링** : `WHERE` 절을 통해 조건에 맞는 행만 선택
2. **Column 필터링** : `SELECT` 문을 이용해 필요한 컬럼만 추출

### **5.3. 그룹화와 집계**

- **그룹화** : `GROUP BY` 사용
- **그룹 필터링** : `HAVING` 사용 (집계 함수 결과에 대한 조건)

### **5.4. 정렬**

- `ORDER BY` : 데이터 정렬 (오름차순/내림차순)

---

## **6. 데이터 타입과 내장 함수**

### **6.1. 데이터 타입**

- **숫자형** : 정수, 실수
- **문자형** : 문자열 데이터
- **날짜형** : 날짜 및 시간 데이터
- **NULL** : 값이 존재하지 않는 상태
- **ORDB** (객체-관계형 DB) : 객체 저장 가능

### **6.2. 내장 함수**

### **(1) 단일행 함수**

- 입력된 한 행에 대해 하나의 결과값을 반환
- 주요 함수:
    - **숫자 함수** : `ROUND()`, `CEIL()`, `FLOOR()`
    - **문자 함수** : `UPPER()`, `LOWER()`
    - **날짜 함수** : `DATE_ADD()`, `DATE_SUB()`, `TIMEDIFF()`

### **(2) 다중행 함수 (집계 함수)**

- 여러 행을 대상으로 하나의 결과값을 반환
- 주요 함수:
    - `COUNT()` : 행 개수 반환
    - `MAX()` : 최댓값 반환
    - `MIN()` : 최솟값 반환
    - `SUM()` : 합계 반환
    - `AVG()` : 평균 반환

### **6.3. 데이터 처리 특징**

- SQL은 **대소문자 구분 없음** (단, 데이터 자체는 대소문자 구분)
- `NULL` 값은 비교 연산 불가능 → `IS NULL` 사용
- **다중행 함수와 단일행 함수 혼용 불가능**
    - `SUM()`, `MAX()` 같은 다중행 함수는 함께 사용 가능하지만, 단일행 함수와 혼용 불가
    - 예제 (가능한 경우):
        
        ```sql
        SELECT SUM(sal), MAX(sal) FROM emp;
        
        ```
        
    - 예제 (불가능한 경우):
        
        ```sql
        SELECT ename, SUM(sal) FROM emp;  -- 단일행 + 다중행 함수 혼용 불가
        
        ```
        

---

## **7. SQL 실행 성능 고려**

- `WHERE` 절에서도 함수 사용 가능하지만, **주의 필요**
- 예제:
    
    ```sql
    SELECT ename, sal
    FROM emp
    WHERE UPPER(ename) = 'SMITH';
    
    ```
    
    - `UPPER(ename)`을 사용하면 **Full Table Scan**이 발생 → 성능 저하 우려

---

## **8. SQL 주석 작성법**

- **한 줄 주석** : `-` (마이너스 두 개)
    
    ```sql
    -- 사원의 급여를 조회하는 쿼리
    SELECT ename, sal FROM emp;
    
    ```
    
- **여러 줄 주석** (DBMS에 따라 지원 여부 다름)
    
    ```sql
    /* 여러 줄 주석 예제
       사원의 이름과 급여 조회 */
    SELECT ename, sal FROM emp;
    
    ```
    

---

### **📌 요약**

| 구분 | 설명 | 주요 명령어 |
| --- | --- | --- |
| **TCL** | 트랜잭션 제어 | `COMMIT`, `ROLLBACK`, `SAVEPOINT` |
| **DDL** | 데이터 정의 | `CREATE`, `DROP`, `ALTER`, `RENAME` |
| **DML** | 데이터 조작 | `INSERT`, `UPDATE`, `DELETE`, `SELECT` |
| **DCL** | 권한 관리 | `GRANT`, `REVOKE` |
| **단일행 함수** | 행마다 결과 반환 | `ROUND()`, `UPPER()`, `DATE_ADD()` |
| **다중행 함수** | 여러 행의 결과를 하나로 반환 | `SUM()`, `AVG()`, `COUNT()` |
| **정렬** | 데이터 정렬 | `ORDER BY` |
| **그룹화** | 그룹별 데이터 집계 | `GROUP BY`, `HAVING` |
