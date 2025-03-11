## 내장함수 - 집계함

- 집계함수
    - 결과를 만족하는 전체 행을 하나 이상의 컬럼을 기준으로 그룹화하여 그룹별로 결과를 반환하는 함수
    - NULL값을 집계하지 않음에 주의
        - count(*) 제외!~

| 함수 | 설명 |
| --- | --- |
| COUNT([DISTINCT | ALL] 컬럼명) |  |
|  |  |

- group by 절
    - group by  컬럼명 | 표현식
    - 각 그룹별로 select 절에 기술한 집계 함수가 적용
    - where절에는 집계 함수를 사용할 수 없음 ⇒ 집계결과 활용해서 filtering ⇒ having 절
        - select절의 모든 요소는 group by 저릐 표현식, 집계 함수를 포함하는 표현식 또는 상수만 가능!!

```sql
#비집계컬럼은 select에 오면x
select deptno, max(sal), ename
from emp
group by deptno;
```

- having 절
    - 그룹핑된 결과를 필터링
- with rollup
    - 그룹 항복 총계나 각 그룹별 소계가 필요한 경우 사용
    
    ![image.png](attachment:c90c87a0-6121-4e3e-8cec-0efc3ed3c00b:image.png)
    

```sql

# 총계 roll up
# 부서별 사원들의 급여합 조회하며 전사원들의 월급여합 총계 함께 생성
select deptno, sum(sal)
from emp
group by DEPTNO with rollup;

#다른 방식
select deptno, sum(sal)
from emp
group by DEPTNO
union all
select null, sum(sal)
from emp
```

- grouping 함수
    - 조회되는 컬럼의 null 값이 실제 null 값인지 rollup에 의해 생성된 null 값인지 판단
    - 실제 null 값 또는 null 값이 아닌 값
        - 0리턴
    - rollup에 의한 null 값
        - 1리턴

## join

- 하나의 sql 명령문으로 여러 테이블에 저장된 데이터를 한번에 조회할 수 있는 기능
- primary- key와 foreign-key의 관계를 가진 컬럼을 소유하고 있는 테이블

![image.png](attachment:43728bea-d6e5-4431-80fa-feb4a295370c:image.png)

- 종류
    - 조건에 따른 구분
        - EQUI JOIN
            - 조인 조건의 컬럼을 =(equal) 비교를 통해 같은 값을 가지는 행을 연결하여 결과를 생성하는 조인 방법
        - NON-EQUI JOIN
            - <, BETWEEN a AND b 와 같이 = 조건이 아닌 연산자를 사용하여조인하는 방법
    - 조인 연산에 따른 구분
        - INNER JOIN
            - 조인 조건에 부합하는 행들만 연결하여 결과를 생성하는 조인 방법
        - OUTER JOIN
            - 조인 조건에 부합하지 않는 기준 테이블의 행들도 모두 포함하여 결과를 생성하는 조인 방법
    - 조인 조건 생략 시 구분
        - NATURAL JOIN
            - 대상 테이블의 모든 공통 컬럼을 조인 조건 컬럼으로 사용하여 연결하는 조인 방법
            - 두 테이블 연결역할, 공통 컬럼(이름 같아야함), 양쪽 존재
        - CROSS JOIN
            - 대상 테이블의 모든 행들을가능한 조합으로 연결하는 조인 방법
            
    - 그 밖의 조인
        - SELF JOIN
            - 계층형의 성질을 갖는 데이터를 갖는 테이블에서의 상하 관계의 행을 연결하여 결과를 생성하는 조인 방법
            - EX. EMP, 카테고리, 게시판 게시글, 부서 상하위 조직
        

| 종류 | 구분 | 설명 |
| --- | --- | --- |
| **조건에 따른 구분** | **EQUI JOIN** | 조인 조건의 컬럼을 `=`(equal) 비교를 통해 같은 값을 가지는 행을 연결하여 결과를 생성하는 조인 방법 |
|  | **NON-EQUI JOIN** | `<`, `BETWEEN a AND b` 와 같이 `=` 조건이 아닌 연산자를 사용하여 조인하는 방법 |
| **조인 연산에 따른 구분** | **INNER JOIN** | 조인 조건에 부합하는 행들만 연결하여 결과를 생성하는 조인 방법 |
|  | **OUTER JOIN** | 조인 조건에 부합하지 않는 기준 테이블의 행들도 모두 포함하여 결과를 생성하는 조인 방법 |
| **조인 조건 생략 시 구분** | **NATURAL JOIN** | 대상 테이블의 모든 공통 컬럼을 조인 조건 컬럼으로 사용하여 연결하는 조인 방법 <br> - 두 테이블 연결 역할 <br> - 공통 컬럼(이름이 같아야 함) <br> - 양쪽 테이블에 존재해야 함 |
|  | **CROSS JOIN** | 대상 테이블의 모든 행들을 가능한 조합으로 연결하는 조인 방법 |
| **그 밖의 조인** | **SELF JOIN** | 계층형의 성질을 갖는 데이터를 갖는 테이블에서의 상하 관계의 행을 연결하여 결과를 생성하는 조인 방법 <br> **예시**: EMP 테이블(직원-관리자 관계), 카테고리, 게시판 게시글, 부서 상하위 조직 |

### EQUI JOIN

- 컬럼명이 같아야함

![image.png](attachment:1ff790bb-ce9c-411f-9885-fd09cf5ee6da:image.png)

### OUTER JOIN

- 조인 조건에 부합하지 않는 행도 모두 포함하는 조인 방식
    - LEFT OUTER JOIN : 기준 테이블을 먼저 기술
    - RIGHT OUTER JOIN : 기준 테이블을 나중에 기술
    - FULL OUTER JOIN : 양쪽 테이블 모두 기준 테이블

![image.png](attachment:99b5c347-be8b-49af-8515-b6888f211b99:image.png)

![image.png](attachment:5d57240e-e15a-4a5d-b66f-02ae1a51ecd2:image.png)

- UNION쓸때는 컬럼개수 맞춰줘야 하기 때문에 이렇게 하면 안되고 SUM앞에 NULL이나 ‘ ‘ 이런 걸로 채워줘야 함
