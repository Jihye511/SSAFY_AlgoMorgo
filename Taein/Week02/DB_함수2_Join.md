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

# 강사님 정리

- as 빼도되지만 실수 한 경우 큰일난다 그래서 넣어라
- null이 들어가면 비교, 사칙연산 다 안된다
- 0은 데이터지만 null은 아무것도 없다
- IFNULL(COMM, 0) : NULL이면 0으로 바꿔서 계산
- where not comm is null == where comm is not null
- not이 두번 들어가면 null인 애들이 나온다 → 근데 이렇게 하면 선배들 한테 맞는다
    
    → 성능도 떨어진다 (index 아무리만들어도 full table scan 한다)
    
    → not 들어가면 table scan 실행하는데 notnot 두번쓰면 full table scan
    
- sql은 직사각형 형태의 매트릭스 형태를 가진다 → 집계함수는 일반 컬럼과 같이 쓸 수 없다
    
    → 단일행과도 같이 쓸 수 없다 (단일행 함수는 대문자로만 바꿀 뿐이다)
    
    → 집계함수 안에 집계함수 쓸 수 없다
    
    → 집계함수 안에 단일행 함수 쓸 수 있다 
    
    (오라클 안에는한단계 까진 된다 하지만 안쓰는 이유는 비싸서)
    
- 문자형 단일행, 숫자형, 날짜형 이렇게 분리
- or 대신 in 도 가능 deptno in(10,20) → 10 이거나 20
- or의 위치에 따라 조회문이 달라진다
    
    → 현장 팁 : and or 한 위치와 or and한 위치의 결과가 달라진다
    
    → 현장 팁 : 2번째 이상 조건 빼려면 - - 이렇게 넣지만 첫번째 빼기는 힘듬
    
    → 첫번째에 1 = 1을 넣으면 된다 더 나아가면 1만 적어놓는다 (1이 true라)
    
- 앞에 붙는 %는 성능에 문제 줌
- _ 와 % 차이는 자릿수 까지 비교
- not이 되는게 —→ ! =, <>, ^=
- from dual : 가상 테이블로 가져옴
- 단일행 함수 ADMIN@naver.com에서 id부분만 때놓기
    
    —> INSTR(’ADMIN@naver.com’, ‘@’) 이렇게 하면 자릿수 나옴
    
    —> SUBSTR(’ADMIN@naver.com’, 1, INSTR(’ADMIN@naver.com’,’@’)-1)
    

- 자릿수 미는거 LPAD(ENA<E,10,’*’), RPAD도 됨
- LENGTH가 있다 근데 띄워쓰기 포함한다
- LENGTH(RTRIM(’SSAFY HELLO   ‘) 오른쪽 공백사라짐, LTRIM도 됨, 양쪽은 TRIM, 가운데는 못날린다 → instr로 해서 짤라라
- 급여가 2000 미만은 에~~ 2000 이상은 우~~ 3000이상는 오~~~ , 4000이상은 와~~
    
    → 동등비교 : CASE 조건식 WHEN 비교 THEN 결과
    
    WHEN 비교2 THEN 결과2
    
    WHEN 비교3 THEN 결과3
    
    ELSE 결과4
    
    이거는 같은거 일때만 가능 == 만 가능
    
    비교는 when 비교식1 then 결과1 ~~
    
    → SELECT sal
    
- as 공백이 안되지만 공백 하려면 ‘문자 열’ 이렇게
- 다중행 : sum, count, avg, max, min,
    
    → 일반컬럼이나 단일행함수랑 같이 쓰는건 안된다 / 다중행 끼리는 된다
    
- group by : 소그룹 지정
- 순서 from, where, group by, select, order by
- as로 지정해준거 order by 전에 select가 있어서 된다
- 집계함수는 일반 컬럼과 같이 쓸 수 없지만 group by로 묶은건 된다
- union all 조심해야 할게 위 아래 컬럼 숫자가 같아야 한다 (근데 가상적으로 ‘ ‘ 이렇게 만들어줘도됨) ‘합계’ 이렇게 하는거도 되고
- rollup with는 union all 이 테이블 두개를 봐야 되지만 rollup with는 테이블 하나만 봐도 된다
    - 마지막에 총합이 붙는다
    - null 나오는거 싫다면 IFNULL(JOB, ‘소계’)
- group by 여러개는 첫번째 기준 그룹화 하고 같은 그룹에서 잡을 기준으로 그룹
    - 10번에서 잡이 다른애들끼리 그룹, 20번에서 잡이 다른 애들끼리 그루핑 2개를 기준으로 그룹한다
    - count나 avg는 null이 있으면 안된다

## JOIN

정의 : 모델링에 의해서 생성된 테이블 간의 데이터를 연결하는 방식

- FROM 에서 , 로 붙으면 N * M 형태로 나온다 → CROSS JOIN

### 연결기준

- EQUAL : 동등 조인
- NON-EQUAL : 비동등조인

- CROSS JOIN
    - FROM 에 , 만 찍기 → 다 지원하지만 DB마다 조인 방식이 다르다
    - FROM EMP JOIN DEPT → (ANSI 표준안) : 모든 DB가 다 지원함
- INNER JOIN
    - WHERE EMP.DEPTNO = DEPT.DEPTNO
    - FROM EMP JOIN DEPTNO ON(EMP.DEPTNO = DEPT.DEPTNO)
    - 테이블에 AS 줄 수 있다
    - FROM EMP A JOIN DEPTNO B ON(A.DEPTNO = B.DEPTNO)
    - FROM EMP A JOIN DEPTNO B USING(DEPTNO) // 연결하는 컬럼명이 같다면
- OUTER JOIN
    - LEFT OUTER JOIN
    - RIGHT OUTER JOIN
    - FULL OUTER JOIN
- NATUAL JOIN
- SELF JOIN
