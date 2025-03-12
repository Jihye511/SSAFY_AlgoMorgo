# 온라인

## 내장함수 - 집계함수

집계 함수 : 결과를 만족하는 전체 행을 하나 이상의 컬럼을 기준으로 그룹화 → 그룹별로 결과를 반환하는 함수, 통계 결과 제공

(↔ 단일 행 함수와 반대되는 개념)

집계함수 종류

- COUNT ( [ DISTINCT | ALL ] 컬럼 명 [, 컬럼 명, … ] ) : NOT NULL의 수
    - COUNT* : NULL을 포함한 수
- SUM ( [DISTINCT | ALL ] 컬럼명 )
- AVG ( [DISTINCT | ALL ] 컬럼명 ) = NOT NULL의 합 / (전체 수 - NOT NULL의 수 )
- MAX ( [DISTINCT | ALL ] 컬럼명 )
- MIN ( [DISTINCT | ALL ] 컬럼명 )

: NULL 값은 집계하지 않음 

: ALL이 기본 값

@ 실습

### GROUP BY 절

: 기술 컬럼이나 표현식을 기준으로 결과 집합 전체 행을 그룹별로 나눔

- select 절의 모든 요소는 group by 절의 표현식, 집계함수를 포함하는 표현식, 상수만 가능
- select 절에 기술한 컬럼 중 , 집계함수에 사용되지 않은 컬럼은 반드시 group by절에 명시
- where 절이 집계함수에서 쓰일 수 없음.
    - 이유 : 그룹핑을 나중에 하면 뭔 의미가 있냐
    - grouping 이전에 where 절을 사용해서 그룹 대상 집합을 먼저 선택
    - where 절에 집계함수를 쓴다? 집계 결과를 활용해서 필터링을 하고 싶다! > having 절 사용해
    
    - ename 때문에 안되는 예제
    
![image](https://github.com/user-attachments/assets/f2f3342f-641f-4691-aa84-7f1ed6a1698f)
    

### HAVING 절

: 집계된 결과를 필터링하니까, 집계함수를 쓸 수 밖에 없다.

```sql
SELECT MONTH(HIREDATE), COUNT(*), AVG(SAL)
FROM EMP
GROUP BY MONTH(HIREDATE)
HAVING COUNT(*) >= 2 AND AVG(SAL) >= 2000;
```

## 내장함수 - 집계함수 응용

### WITH ROLLUP

: 그룹 항목 총계나 각 그룹별 소계가 필요한 경우 사용

- QUERY를 두번 써야하던 것을 한번에 처리 가능

![image](https://github.com/user-attachments/assets/652475d8-4fca-4efa-a14b-3028ebde17de)

- LV 2

```sql
# 부서별 사원들의 급여합 조회하며 전사원들의 월급여합 총계 함께 생성
# ROLL UP
SELECT 	DEPTNO, SUM(SAL)
FROM 	EMP
GROUP BY DEPTNO WITH ROLLUP; #LV 2

# ROLL UP 없이
SELECT DEPTNO, SUM(SAL)
FROM EMP
GROUP BY DEPTNO
UNION ALL
SELECT NULL, SUM(SAL)        // NULL은 명시적으로 차수를 맞추기 위해
FROM EMP;
```

- LV 3

```sql
# 소계, 총계 rollup
# 부서별 직무별 사원들의 급여합 조회하며 부서별 소계, 전사원들의 월급여합 총계 함께 생성
SELECT DEPTNO, SUM(SAL)
FROM EMP
GROUP BY DEPTNO, JOB WITH ROLLUP;
```

### GROUPING

: 조회되는 컬럼의 NULL이 진짜 NULL인지 ROLLUP에 의한 NULL인지 판단 (소계)

- 실제 NULL OR NULL이 아님: 0
- ROLL UP에 의한 NULL : 1

![image](https://github.com/user-attachments/assets/2ce6ac8d-aba3-4db0-8889-932fa0a3a94c)

```sql
# grouping()
# 부서별 직무별 사원들의 급여합 조회하며 부서별 소계, 전사원들의 월급여합 총계 함께 생성
SELECT DEPTNO, MGR, SUM(SAL)
FROM EMP
GROUP BY DEPTNO, MGR WITH ROLLUP;
## 이렇게 작성하면 그냥 NULL 인 값들과 소계가 구분이 어려워짐

## -> 
SELECT DEPTNO, MGR, SUM(SAL),
		GROUPING(DEPTNO), GROUPING(MGR)
FROM EMP
GROUP BY DEPTNO, MGR WITH ROLLUP;
```

![image](https://github.com/user-attachments/assets/a3d1842c-a405-41ca-a5ef-9ff7c3bbcbdf)

숙제 →  GROUPING값에 따라 NULL 값을 각각 ① 관리자 없음 과 ② 관리자 소계로 나눠서 출력해보자 

### 피봇 테이블

 ( 내일 나간다. )

## 조인 (JOIN)

 :  두 개 이상의 테이블을 합쳐, 한번에 조회할 수 있도록

- 주로 PRIMARY KEY와  FOREIGN KEY의 관계를 가진 컬럼을 소유한 테이블을 통해 검색
- N개 테이블 조인 시 N-1개 이상의 조인 조건 필요

![image](https://github.com/user-attachments/assets/9d570c52-24be-4549-a87b-935ef557bb69)

- CROSS JOIN 형태가 되지 않도록 조심하자
- 자식 테이블이 꼭 외래키를 가져야 JOIN을 가져야 하는 것은 아님
    - 이건 참조 무결성을 위한 것이지, JOIN을 위한 것이 아니다.

### 조인의 종류 1

- 조건에 따라
    - EQUI-JOIN : 동등 비교 (= 등가),
    - NON-EQUI-JOIN : BETWEEN, AND 등 동등 조건이 아닌 연산자 사용
- 조인 처리 결과에 따라
    - INNER-JOIN : 조건에 부합하는 행동만 연결, 결과 생성
    - OUTER-JOIN : 조건에 부합하지 않는 기준 테이블의 행들도 모두 포함해 결과 생성
        - 항상 기준 테이블을 잘 식별해야함

### 조인의 종류 2

- 조인 조건 생략 시 구분
    - NATURAL JOIN : 대상 테이블의 모든 공통 칼럼을 조인 조건칼럼으로 사용해 연결
        - 두 테이블의 연결 역할을 하는 공통 컬럼의 **이름**이 같아야함
    - CROSS JOIN : 대상 테이블의 모든 행을 가능한 조합으로 연결
        - 냅다 곱해버림
- 이 외
    - SELF JOIN : 계층형의 성질을 갖는 데이터’s 테이블에서 상하 관계 행 연결 → 결과 생성

### EQUI-JOIN

: 동등비교 + 기본 값은 INNER JOIN (같은 이름을 가진 컬럼이 있어야함)

- USING (테이블 명 , 테이블 별칭 사용 불가)

![image](https://github.com/user-attachments/assets/bb8a531d-418e-47d4-87e7-d399819955ce)

- ON

![image](https://github.com/user-attachments/assets/9e8d5135-8744-412f-81ec-cd937bcee919)

- =

![image](https://github.com/user-attachments/assets/e94042ee-bff7-48d5-9f9d-fce2d65c2070)

### NON-EQUI-JOIN

: JOIN-USING 사용 불가

### INNER JOIN

### OUTER JOIN

: 조인 조건에 부합하지 않는 행도 포함

전체 행을 가져올 테이블을 기준으로 함

- LEFT OUTER   : 기준 테이블을 먼저 기술
- RIGHT OUTER : 기준 테이블을 나중에 기술
- FULL OUTER (mysql은 미지원 - union으로 해결)

# 오프라인

![image](https://github.com/user-attachments/assets/dfb9cef3-2352-4447-9727-3b78a5eefead)

- or . and 위치에 따라 결과 개수가 다르다.
- and 가 먼저 걸리냐 or가 먼저 걸리냐가 다름

[0311_2.sql](attachment:9f5d7edb-86b7-466d-bf86-31d806b64129:0311_2.sql)

[0311_offline.sql](attachment:8e555d67-2e1a-4604-9a4d-e29743e311d8:0311_offline.sql)

[0311.sql](attachment:83cb870c-9818-47fd-9041-a30e58452d99:0311.sql)
