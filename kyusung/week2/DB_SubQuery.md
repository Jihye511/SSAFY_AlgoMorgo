# 서브쿼리

: 하나의 쿼리가 다른 sql문에 포함된 구조

하나의 쿼리 결과를 다른 sql문에 전달하기 위해 두개 이상의 sql문을 하나의 sql문으로 처리하는 방법

다른 sql문에 포함된 서브쿼리는 메인 sql문에 사용될 값을 반환

<서브쿼리의 결과 → main sql에서 사용>

🗯️ 주의 : 

- ( ) 괄호로 감싸서 표현 - from 절 서브쿼리는 필수
- 쿼리 끝에 세미콜론 생략
- order by절 사용에 제한이 있거나 의미 없는 경우가 있음
    - 단독 사용은 불가하고, limit 사용 시 가능한 경우가 있음.
    - 정렬은 데이터 자체의 변화엔 없기 때문인가

(from 절의 서브 쿼리 = inline view )

## 유형

- 단일 행 서브쿼리 : 하나의 행만을 조회
    - 비교 조건에 사용될 경우 단일 행 비교 연산자만 사용 가능
    - 행 1  * 열 1 : 스칼라 서브쿼리

select 절에는 스칼라서브쿼리가 올 수 밖에 없다.

- 다중 행 서브 쿼리

: 여러 행 검색, 메인 sql에 반환

비교 조건에 쓰일 경우 다중 행 비교 연산자 사용 IN, ANY ALL, EXISTS

※ CTRL + SHIFT + ENTER : 드래그한 서브쿼리만 실행시킬 수 잇다.

# 다중 행 서브 쿼리 연산자

- IN : 메인 SQL문의 비교 조건이 서브쿼리 결과중 하나라도 만족하면 참
- ANY : 메인문의 비교 조건이 서브쿼리 결과 중 하나라도 만족하면 참 (: 적어도 1행 보다 ~ 하면)

```sql
# SALESMAN의 최저월급여보다 월급여가 많은 사원이름, 월급여 조회
SELECT ENAME, SAL
FROM EMP
WHERE SAL > ANY (	SELECT SAL
					FROM EMP
					WHERE JOB = 'SALESMAN' )
;
```
![image](https://github.com/user-attachments/assets/8a084706-737d-4ec2-98fa-6e3b86eef9b8)


- ALL : 서브쿼리 결과 중 모든 값이 만족하면 참
- EXISTS : 서브쿼리 실행 결과가 존재하면 참.
    - 서브쿼리에서 검색 결과가 하나라도 존재하면 메인 쿼리 조건절이 참
    - 조건에 맞는 행이 있는지 판단결과만 이용하고 SELECT 절에서 따로 컬럼데이터를 가져오지 않는 형태로 작성
    - 만족하는 행이 존재하면 바로 TRUE 반환
    - SEMI JOIN이라고도 불림

# 서브쿼리 활용

### 상호 연관 서브쿼리 ( = 상관관계 서브쿼리)

```sql
 SELECT EMPNO, ENAME, "관리자"
 FROM EMP E
 WHERE EXISTS (
									SELECT 1
									FROM EMP E2
									WHERE E.EMPNO = E2.MGR
								 );
```

**(서브쿼리에서 메인 쿼리의 E 를 사용한다.)**

→ 서브쿼리가 단독적으로 실행될 수 없다.

→ 바깥 쿼리가 먼저 도는 경우이다. 

### 다중 열 서브 쿼리

- 여러 열을 조회하는 서브쿼리 : 각 부서의 최대 급여를 받는 사원 조회

### 스칼라 서브쿼리

- 단 하나의 값만 조회하는 서브쿼리
- 단일 행 단일 열 서브쿼리
- 일치 값이 없을 경우 NULL 리턴

![image](https://github.com/user-attachments/assets/9199bc39-2d0f-49ac-9a92-89f0b7f766f5)

### 인라인 뷰

: FROM 이나 JOIN 절 뒤에 위치하는 서브쿼리

- 서브쿼리의 실행 결과 집합을 마치 테이블처럼 사용하기 위한 방법
    - RAW 데이터를 가공한 결과를 이용할때 사용
    - 복잡한 쿼리 가독성 ↑
- 반드시 ALIAS *(별칭)사용
- 일회성 VIEW
    - 객체로 저장 ❌

![image](https://github.com/user-attachments/assets/7cd0ec58-e55a-4289-8912-121a09aa6e3d)

```sql
# 각 부서에서 최대급여를 받는 사원번호, 월급여, 사원이름 조회
SELECT E.ENAME, E.SAL, E.DEPTNO
FROM (
	SELECT DEPTNO, MAX(SAL) MAXSAL
    FROM EMP
    GROUP BY DEPTNO
	) D
JOIN EMP E ON (E.DEPTNO = D.DEPTNO AND E.SAL = D.MAXSAL);
```

- 피봇 테이블

![image](https://github.com/user-attachments/assets/0acc6016-e3a1-41da-bb7f-6268be5f2bca)

① 기존 데이터를 컬럼화 한다.

② 이후에 그룹핑 및 집계를 한다.

![image](https://github.com/user-attachments/assets/92655d55-8c3f-4d06-af41-11a5cef96b63)

- 1번 과정(데이터 컬럼화)을 인라인뷰로 대체한 것

# 집합연산

: 쿼리 실행 결과를 하나의 집합으로 보고 집합간의 연산을 수행할 수 있음

- 두 쿼리에서 조회하는 컬럼 수는 같아야 한다.
- 컬럼 헤더는 앞쪽에 기술된 쿼리 기준으로 결정 : 컬럼 개수

![image](https://github.com/user-attachments/assets/396b0821-618f-480e-b497-4bb61c131f84)

# 오프라인

서브쿼리 : 쿼리 안에 들어가는 쿼리

- 위치
    1. projection 절
    2. from 절 (인라인 뷰)
    3. where 절 
        1. 단일 row : < > = !=
        2. 다중 row : in >all <all >any <any
        3. 다중 column : 
        
        ```sql
        select *
        from emp
        where (deptno, sal) > all (
        												select deptno, sal
        												from emp
        												where job
        											); 
        ```
        
- insert, update, delete, create, drop
