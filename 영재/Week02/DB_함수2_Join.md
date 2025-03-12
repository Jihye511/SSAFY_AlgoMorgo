## 집계함수

- 다중행 함수
- 결과를 만족하는 전체 행을 하나 이상의 컬럼을 기준으로 그룹화하여 그룹별로 결과를 반환하는 함수
- NULL값은 집계하지 않음
    - COUNT * 는 예외
- GROUP BY
    - 결과 집합의 하위 데이터 그룹을 만든다
    - 각 그룹별로 SELECT절에 기술한 집계 함수가 적용
    - WHERE절에는 집계 함수를 사용할 수 없다
        - 집계결과를 활용해서 필터링 하기 때문
- HAVING
    - GROUB BY 절에 의해 성성된 그룹을 대상으로 조건을 적용
- WITH ROLLUP
    - 그룹 항목 총계나 각 그룹별 소계가 필요한 경우 사용
- GROUPING
    - 조회되는 컬럼의 NULL값이 실제 NULL값인지 ROLLUP에 의해 성성된 NULL값인지 판단
    - ROLLUP에  의한 NULL값
        - RETURN 1
    - 그 이외
        - RETURN 0

## JOIN

- 두 테이블의 연결
- 하나의 SQL 명령문으로 여러 테이블에 저장된 데이터를 한번에 조회할 수 있는 기능
- 주로 Primary-Key와 Foreign-Key의 관계를 가진 컬럼을 소유하고 있는 테이블
- N개의 테이블 조인 시 N-1개 이상의 조인 조건 필요
- 외래키가 있어야만 조인이 가능하다(X)
    - 외래키는 참조 무결성을 위한 것
- EQUI JOIN
    - 조인 조건의 컬럼을 비교(EQUAL)를 통해 같은 값을 가지는 행을 연결하여 결과 생성
    - 반드시 공통 컬럼이 있어야한다
- NON-EQUI JOIN
    - ‘=’조건이 아닌 연산자를 사용하여 조인
- **INNER JOIN (Default)**
    - 조인 조건에 **부합하는 행들만 연결**
- **OUTER JOIN**
    - 조인 조건에 **부합하지 않는 모든 행들을 포함하여 연결**
    - **LEFT/RIGHT 반드시 명시**
    - LEFT-OUTER JOIN
        - 기준 테이블을 먼저 기술
    - RIGHT-OUTER JOIN
        - 기준 테이블을 나중에 기술
    - FULL OUTER JOIN
        - 모두 기준 MYSQL 지원 X
- 조인 조건 생략시 구분
    - NATURAL JOIN
        - 대상 테이블의 모든 **공통 컬럼**을 조인 조건 컬럼으로 사용
    - CROSS JOIN
        - 조합 가능한 모든 연결
- 기타
    - SELF JOIN
        - 게층형의 성질 | 상/하 관계의 행을 열결하여 결과를 생성
            - ex) 사원, MGR

## 순서

- 필터링 조건이 있는 경우 필터링 후 JOIN 동작

## 강사님

- YEAR 다른 방법으로 추출
    
    ```sql
    SELECT ENAME, SAL, HIREDATE
    FROM EMP
    WHERE DEPTNO IN(10,20)
    AND EXTRACT(YEAR FROM HIREDATE) <=2024;
    ```
    
- 다중 조건인 경우 앞에서부터 연산
- INSTR(문자열 탐색)
- SUBSTR(OG, IDX,IDX)
- LPAD, RPAD(OG, LEN, REPLACE CHAR)
- 공백 제거 RTRIM LTRIM
- **OUTER JOIN**
    - **“모든”** 이라는 단어에 유의하기
    - **이너 조인으로 나와야 하는 데이터는 모두 나오고**
    - **주가 되는 테이블의 데이터는 연결되지 않더라고 모두 나와야 한다**
- NATURAL JOIN
    - COLUMN명이 추후 바뀔 수 있으므로 사용 지양
- SELF JOIN
    - 같은 테이블 조인하는 기법
