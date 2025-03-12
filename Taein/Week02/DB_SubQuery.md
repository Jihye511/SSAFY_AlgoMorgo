# 서브쿼리
- 하나의 쿼리가 다른 SQL 문에 포함하는 문이다
- 두 개 이상의 sql 문을 하나의 SQL문으로 처리하는 방법
- 안에서 쓰인 쿼리의 결과를 바깥 SQL문에 사용될 값을 반환

## 서브 쿼리 유형

- 단일 행 서브 쿼리 : 행 한 개
    - 하나의 행만 조회
    - 단일 행 비교 연산자=, >, ≥, <, ≤, <> 만 사용 가능
    
- 다중 행 서브 쿼리
    - 여러 행을 검색
    - 다중 행 비교 연산자 IN, ANY, ALL, EXISTS 사용
        - IN : 결과 중 하나라도 만족하면 참
        - ANY : 결과 중 하나라도 만족하면 참
            - IN = ANY, > ANY, ≥ ANY, < ANY, ≤ ANY
            - 목록의 값들 중 적어도 한 개보다 큰 상황이면 됨 : 최소치 OR 최대치 가 아닌 상황에서 사
    - ALL : 모든 값이 만족하면 참
    - EXISTS : 실형 결과가 존재하면 참

### 상호 연관 서브쿼리

- MAIN 쿼리와 별도로 독립적인 실행이 불가능한 쿼리
- MAIN 쿼리가 먼저 실행되어 조회된 값을 서브쿼리에서 참조하는 연관 관계가 있는 서브퀄

### 다중 열 서브 쿼리

- 여러 열을 조회하는 서브쿼리

### SCALA

- 쪼갤래야 쪼갤 수 없는 단 하나의 값만 조회하는 서브 쿼리
    - 단일행 안일 열만 서브쿼리
    - 하나의 값만 요구되는 위치에 사용 → SELECT 절에 오는 경우가 많다
    - 일치하는 값이 없는 경우 NULL 리턴

### 인라인 뷰

- 테이블을 명시하는 자리에 올 수 있는 서브쿼리
    - 데이터를 조회하는 목적의 테이블을 명시하는 모든 곳에 올 수 있다
    - 즉 FROM절에 온다 또 JOIN에 올 수 있다
    - 반드시 별칭 사용해야함
- RAW 데이터 가공 결과 이용
- 복잡한 쿼리의 가독성 높이기 위해

피봇테이블 : 특정 컬럼이 갖고 있는 값을 컬럼화

## 집합연산

- 쿼리 실행 결과를 하나의 집합으로 보고 집합 간의 연산을 수행 할 수 있음
- UNION ALL : 합집합 (중복 행 포함)
- UNION         : 합집합 (중복 행 제거)
- INTERSECT  : 교집합
- EXCEPT         : 차집합 (먼저 기술된 앞쪽 쿼리가 기준 집합)

# 강사님 정리

## 서브쿼리 : 쿼리 안에 들어가는 쿼리

위치 : projection, from, where, insert, update, delete, create, drop

from에 들어가는 서브쿼리 : inline view

- SELECT A.ENAME, A.SAL, B.SUM
- FROM EMP A JOIN( SELECT SUM(SAL) SUM FROM EMP) B;

→ SELECT에 들어가게

- SELECT A.ENAME, A.SAL, (SELECT SUM(SAL)FROM EMP) SUM
- FROM EMP A;
- 단일로우, 단일 컬럼을 리턴하는 쿼리를 해야함 → 스칼라 서브쿼리

where에 들어가는 서브쿼리 : 조건 서브쿼리

- 단일로우 : >, <, =, ≠
- 다중로우 : IN(=), <ALL, >ALL, >ANY, < ANY, SOME도 있지만 공부안해도된다
- 다중컬럼 : EX) SELECT * FROM EMP WHERE (DEPTNO, SAL) > ALL (SELECT DEPTNOM, SAL FROM EMP WHERE JOB) → 두개가 동시에 일치 해야 참

서브쿼리 먼저 실행되고 메인쿼리 실행

서브쿼리 결과를 가지고 메인쿼리 실행

### 상호 연관 서브쿼리

이름, 급여, 부서별급여합 을 프로젝션절(컬럼절)의 서브쿼리로 구현하세요

SELECT ENAME, SAL, (SELECT SUM(SAL) FROM EMP B WHERE A.DEPTNO = B.DEPTNO)

FROM EMP A;

이게 그룹바이보다 빠르다

### insert 문 서브쿼리

insert into aaa(b, bb, bbb) values((select),(select),(select))

insert into table(column1, column2, column3)

select column1, column2, column3 from table;

### create문 서브쿼리

create table emp1 ()

as

select * from emp

where sal > 2000;

insert into emp1(empno, ename, job, deptno)

select empno, ename, job, deptno

from emp

where sal < 2000;

### update문 서브쿼리

update table set column1 = (select ), column2 = data2

where  column4 = (select)

### delete문 서브쿼리

delete from table where column1 = (select …)

## 쿼리 분석 시 select만나면 from부터 찾아라 진짜 from절의 데이터를 찾아라
