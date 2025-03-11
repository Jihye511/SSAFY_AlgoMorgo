# 집계함수 (통계 결과 제공)

- 결과를 만족하는 전체 행을 하나 이상의 컬럼을 기준으로 그룹화하여 그룹별로 결과 반환 함수

NULL값은 집계에 포함하지 않는다

![image.png](attachment:faf2e6cc-f5fe-4a0a-9cb5-48e12393614b:image.png)

1. from
2. where
3. GROUP BY 컬럼명 | 표현식 [, 컬렴명 | 표현식, …]
4. HAVING

select절의 모든 요소는 group by 절의 표현식, 집계함수를 포함하는 표현식 또는 상수만 가능

select절에 기술한 컬럼 중, 집계함수에 사용되지 않은 컬럼은 GROUP BY절에 반드시 기술

—> SELECT절의 비집계컬럼, 표현식

select deptno, avg(sal), ename —> group by절에 기술되지도 않아서 문법적으로 어긋

from emp

group by deptno;

→ 비 집계 컬럼이 왔다

select @@sql_mode; → 여기서 옵션 확인 가능

—> only full group by → group by절에 있는 컬럼이 아닌건 집계가 되야된다

→ 예전엔 가능했지만 의미가 없었다

## WITH ROLLUP

- 그룹 항목 총계나 각 그룹별 소계가 필요한 경우

다음에 배울 union all은 합집합을 구하는거다

집합 연산은 선행되는 쿼리와 후행되는 쿼리에서 가져오는 속성이 일치해야한다

그래서 with rollup이 더 편해서 사용

![image.png](attachment:10f37bd4-24fa-49c1-a2b6-403893fe0e56:image.png)

사용시 group by deptno, job가 level 1 (빨강)

groupb by deptno level 2 (파랑)

groupb by job level 3 (초록)

## GROUPING

- 값이 null이 올 수 있으니 구분해야 할 필요가 있따
    
    =그걸 구별하는 역할의 함수가 grouping이다
    
    즉  rollup에 의한 null값은 1, 그렇지 않으면 0을 반환
    

grouping 숙제

![image.png](attachment:6c751237-433d-4431-ba4a-6c726809249e:image.png)

if함수 쓸 줄 알아라

피봇 테이블 → 서브쿼리 하면서 내일한다

# JOIN

관계형 DB의 핵심

- 두 테이블의 연결 : 하나의 SQL로 여러 테이블 한번에 조희 / 두개 이상 테이블 결함
- primary-key와 foreign-key의 관계를 가진 테이블 join

후보키 : 부서 이름이 unique라면 가능

→ 그래서 부서 이름을 외래키로 갖고 연결이 가능

→ 근데 번호를 키로 해서 하는게 일반적

- N개 테이블 조인 시 N-1개 이상 조인 조건 필요
- 자식 테이블에는 반드시 부모의 primary-key나 unique 조건을 가지지 않아도 가능

→ 외래키는 참조무결성 위함이 있어 굳이 없어도 된다

## 조건에 따른 구분

### EQUI JOIN

- 조인 조건 컬럼을 = 비교로 같은값
- 기본은 INNER JOIN이다
- USING절, ON절이 온다
- USING : 두 테이블의 각 컬럼을 이용해 연결 (이름이 같은 공통 컬럼이 있어야함, 별칭, 명칭 안됨 )(EX) TABLE.이름)
- ON 직접 조건을 기술 AND, OR 도 가능
- FROM에 원하는 테이블 열거하고, WHERE에 조건 명시 (함축형)

### NON-EQUI JOIN

- = 조건이 아닌 연산자
- ON에 ≥, < , BETWEEN 등

## 조인 처리 결과에 따른 구분

### INNER JOIN

- 조인 조건에 부합하는 행들만 연결하여 결과 생성

### OUTER JOIN

- 조건에 안맞는 기준 테이블의 행도 모두 포함
- 기준 테이블이 뭔지 식별하는게 중요하다
- RIGHT, LEFT : 조인데이터에 맞는게 없어도 다 가져온다
- FULL OUTER JOIN : MYSQL은 지원 x → UNION으로 해결

## 조인 조건 생략 시

### NATURAL JOIN

- 자연스럽게 조인
- 조인 조건을 주지 않아도 대상 컬럼 양쪽에 있는 모든 공통 컬럼을 조인 조건으로
- 두 테이블의 연결역할을 하는 공통컬럼(이름이 같아야함) 양쪽 존재할 결우

### CROSS JOIN

- 대상 테이블의 모든 행들을 가능한 조합을 다 만들어냄
- EX) 8, 4 행이 각 있으면 총 32개 결과
- 의도적으로 만들 순 있지만 일부로 만드는 경우는 드물다

## 그 밖 조인

### SELF JOIN

- 우리가 설계한 엔티티가 계층형(상위, 하위)에서 상하 관계 행을 연결
