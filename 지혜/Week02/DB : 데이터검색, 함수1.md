## DBMS

- 응용프로그램과 데이터베이스의 중개자로 데이터의 독립성 제공

## 관계형 데이처 베이스( RDBMS)

- 구성요소
    - 개체 - 테이블
        - LIKE Object(객체)와 유사
    - 관계 - 외래키
    - 속성 - 열

![image](https://github.com/user-attachments/assets/710dcd19-ae56-4463-9b88-09e295bfcd51)


### SQL 구문의 종류

- DML, DDL, TCL, DCL

### 데이터 조회(select)

```sql
# 모든 사원의 이름, 1년급여,  커미션, 1년급여+커미션 조회
SELECT ename, sal *12, comm, sal*12+comm
from emp;
# null(정의x 값)값 연산은 null로 나옴
```
![image](https://github.com/user-attachments/assets/7b3f995c-6107-44cb-88c3-2c4279851733)

- 중복행 제거
    - DISTINCT
    
    ```jsx
    # 모든 사원들이 속해 있는 부서 번호 조회
    SELECT DISTINCT deptno
    from emp;
    ```
    
- 선택적 데이터 조회(WHERE 절)
    - 문자와 날짜 타입의 상수 값은 작은 따옴표(’’)로 묶어서 표현하고 숫자는 그대로 사용

  |연산자|설명|
  |---|---|
  | = | 같다|
  |!=, <>|같지 않다|
  |< , <=, >, >=|작다, 작거나 같다, 크다, 크거나 같다|
  |IS NULL|NULL인지 비교|
  |BETWEEN a AND b|a이상 b이하의 범위 비교(**경계값 포함**)|
  |IN(value1, value2,...)|목록 안에 일치하는 값이 있는 지 비교|
  |LIKE 패턴|특정 패턴을 만족하는 지 비교 </br>파일 표현을 위해 와일드 카드 사용 </br>% ->0개 이상의 임의의 문자 </br> _ -> 1개의 임의의 문자를 의미|
     
    
    ```sql
    # 이름에 _가 포함된 사원번호, 이름 조회
    SELECT ename,sal
    FROM emp
    WHERE ename like '%\_%';
    ```
    

- CASE 연산자

![image](https://github.com/user-attachments/assets/f4ee6c0c-8522-4b38-8479-5f8d8e456cab)

- ORDER BY절 -정렬
    - 정렬조건
        - ASC : 오름차순(기본값)
        - DESC
    - 컬럼이름이나 컬럼 별치이나 컬럼 기술순서(1,2..)로 표현 가능
- LIMIT 절 - 행 결과 제한
    - OFFSET n 생략 시 OFFSET 0으로 처리
        - 첫번째 레코드부터 개수만큼의 의미
    - Top N 쿼리 또는 페이징 처리 시 활용
    - LIMIT 5, OFFSET 10 = LIMIT 10,5
    -  10개이후부터 5개 가져와라?

```sql
# 가장 많은 월급여를 받는 상위 5명 사원이름, 월급여 조회

SELECT empno,sal
from emp
order by sal desc
limit 0,5;
-- limit 5 OFFSET 0; 
```

## 내장 함수

- 주로 많이 쓰는 기능 들을 데베 내부에 저장해두고 가져다 쓰는 거
- 단일 행 함수
- 다중 행 함수
    - 여러 IINPUT → 하나 OUTPUT
    - 그륩당 1개 결과 집계
    - 집계 함수 : 그룹별 집계처리
    - 윈도우 함수 : 개별 데이터 조회 + WINDOW별 집계 결과

### 단일 행 함수

- 많이쓰는것만 외워도.,
- 숫자 관련 함수

---

로컬강의

- DATA TYPE
    - 숫자, 문자, 날짜,  null etc
    - 객체를 집어넣을 수 있는 타입도 있음

```sql
SELECT 
	date_add(sysdate(), interval -100 day)
    from dual;
```

- null은 비교가 x- > null 찾겠다고 하면 hiredate is null 이렇게 작성( hiredate =null 안됨!!)
- 
- 다중행 함수 안에 다중행 함수 못 넣음(오라클은 가능)
- 
- where절에서는 단일행 함수만 사용 가능(다중행 x)
    -
