**일반적인 SQL 실행 순서**

1. **FROM** – 첫 번째 테이블을 가져옴
2. **JOIN** – 두 번째 테이블과 조인
3. **ON** – 조인 조건을 적용
4. **WHERE** – 행을 필터링
5. **GROUP BY** – 그룹핑을 수행
6. **HAVING** – 그룹핑된 결과에 대한 필터링
7. **SELECT** – 필요한 컬럼을 선택
8. **DISTINCT** – 중복 제거
9. **ORDER BY** – 정렬
10. **LIMIT / OFFSET** – 결과 제한

**일반적인 SQL 쓰기 순서**

```sql
SELECT DISTINCT u.name, u.age, d.department_name
FROM users u
JOIN departments d ON u.department_id = d.id
WHERE u.age > 20
GROUP BY u.age, d.department_name
HAVING COUNT(*) > 1
ORDER BY u.age DESC
LIMIT 5;

```

# 서브쿼리

정의 : 다른 SQL문 내부에 포함된 SQL문

- 하나의 SQL문 안에서 또 다른 SQL문을 실행
- 메인쿼리(Outer Query) 실행 전, 서브쿼리가 먼저 실행됨
- 보통 `WHERE`, `FROM`, `SELECT` 절에서 사용됨
- 단일 값 또는 여러 값을 반환할 수 있음
- 쿼리 끝에 세미콜론(;)을 붙이지 않음
- ORDER BY절 사용에 제한이 있거나 의미 없는 경우가 있을 수 있음

### 단일 행 서브 쿼리

- 서브 쿼리에서 하나의 행만을 조회 하여 메인 SQL문에 반환
- 비교 조건에 쓰일 경우 단일 행 비교 연산자(=,<,>,<>)만 사용 가능

### 다중 행 서브 쿼리

- 서브 쿼리에서 여러 행을 검색하여 메인 SQL문에 반환
- 비교 조건에 쓰일 경우 다중 행 비교 연산자(IN, ANY, ALL, EXISTS)사용 해야함
- IN : 결과 중에서 하나라도 만족
- ANY : 비교조건이 결과중에서 하나라도 만족
    - IN은 =만 연산, ANY는 = 및 <, >, <>등등..
- ALL : 비교조건이 모두 만족
- EXISTS : 서브 쿼리의 실행 결과가 존재하면 참
    - 검색된 결과가 **하나라도 존재하면 참**
    - 조건에 맞는 행이 있는지만 판단하고, 결과값을 가져오지 않는 형태로 작성
    - **‘SEMI JOIN’**
    - 그냥 비교하기 때문에 SELECT 1 처럼 대충 처리하기

### 비교불가

```sql
SELECT EMPNO, ENAME
FROM EMP
WHERE EMPNO NOT IN(
	SELECT MGR
    FROM EMP
  //WHERE MGR IS NOT NULL
);
```

- 이런 경우 NULL과도 비교가 진행되기 때문에 WHERE문 안에 IS NOT NULL 필요

### 상호 연관(Correlative) 서브 쿼리

- Main 쿼리와 별도로 독립적인 실행이 불가능한 서브 쿼리 (참조 관계)

### 스칼라(Scalar) 서브 쿼리

- 단 하나의 값만을 조회하는 서브 쿼리
- 단일 행 단일 열 서브쿼리
- 하나의 값만이 요구되는 위치에 사용 됨
- 일치하는 값이 없을 경우 NULL 리턴

### 인라인 뷰

- **FROM**절 또는 **JOIN**절 뒤에 위치하는 서브쿼리(이전에는 **WHERE 안에서 사용)**
- **서브 쿼리의 실행 결과 집합을 마치 테이블처럼 사용**하기 위한 방법
- 일회성 VIEW

### 집합 연산

- 쿼리 실행 결과를 하나의 집합으로 보고 집합간의 연산을 수행 할 수 있음
- 두 쿼리에서 조회하는 컬럼의 개수는 같아야함
- UNION ALL(합집합)
    - 두 결과를 합치기만 함
- UNION(합집합)
    - 두 결과를 합치고 중복행 제거
- INTERSECT(교집합)
    - 두 결과 집합들의 공통 행 추출
- EXCEPT(차집합)
    - 두 결과 집합 중 기준이 되는 집합 어느 한쪽에만 존재하는 행 추출
