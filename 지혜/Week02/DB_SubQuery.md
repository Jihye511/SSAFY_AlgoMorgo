# 서브쿼리

- 하나의 쿼리가 다른 SQL문에 포함되는 구조
- 하나의 쿼리의 결과를 다른 SQL무네 전달하기 위해 두 개 이상의 SQL문을 하나의 SQL문으로 처리하는 방법
- 다른 SQL문에 포함된 서브 쿼리(내부 쿼리)는 메인 SQL문(외부 SQL문)에 사용될 값을 반환하는 역할
- 주의!
    - 괄호 감싸서 표현 ( 모든 위치는 아님)
        - FROM 절 서브쿼리는 괄호 필수!
        - 세미콜론 X
        - ORDER BY절 사용에 제한이 있거나 의미 없는 경우가 있을 수 있음(서브쿼리에 ORDER BY 오면안됨? X!)

- 단일행 서브 쿼리
    - 비교 조건에 쓰일 경우 단일 행 비교 연산자만 사용가능
- 다중행 서브 쿼리
    - EXISTS 연산자 활용
        - 서브쿼리에서 검색된 결과가 하나라도 존재하면 메인 쿼리 조건절이 참
        - 조건에 맞는 행이 있는지의 ㄴ
        - 관리자(상사)인 사원조회 with EXISTS연산자

       ![image](https://github.com/user-attachments/assets/a85a20ea-8f6c-4d44-952a-fd2e589d0464)

        
- 다중 열 서브 쿼리
    - 여러 열을 조회하는 서브쿼리
        - 각 부서의 최대급여를 받는 사원 조회
        
      ![image](https://github.com/user-attachments/assets/5d46e33f-f494-4a21-b8ca-6628a50bc0c0)

        
- 스칼라 서브 쿼리
    - 단 하나의 값만을 조회하느 서브쿼리
    - 단일 행 단일 열 서브 쿼리
    - 하나의 값만이 요구되는 위치에 사용 됨
    - 일치하는 값이 없을 경우 NULL 리턴
    

## 집합연산

- 쿼리 실행 결과를 하나의 집합으로 보고 집합 간의 연산을 수행할 수 있음
- 두쿼에서 조회하는 컬럼의 개수는 같아야함
- 컬럼헤더는앞쪽에 기술된 쿼리 기준으로 결정

| 연산자 | 설명 | 의미 |
| --- | --- | --- |
| **UNION ALL** | 합집합 <br> 두 결과 집합들을 합한 결과(**중복 행 포함**) | 합집합 (중복 포함) |
| **UNION** | 합집합 <br> 두 결과 집합들을 합한 결과(**중복 행 제거**) | 합집합 (중복 제거) |
| **INTERSECT** | 교집합 <br> 두 결과 집합들의 **공통 행** 추출 <br> *MySQL 8.0.31부터 지원* | 교집합 |
| **EXCEPT** | 차집합 <br> 두 결과 집합 중 기준이 되는 집합 어느 한쪽에만 존재하는 행 추출 <br> *MySQL 8.0.31부터 지원* <br> **먼저 기술된 앞쪽 쿼리가 기준 집합** | 차집합) |

---

서브커리

- 정의 : 커리안에 들어가는 커리
- 위치
    - projection 절
    - from 절 ( Inline View)
    
    ```sql
    -- 이름 , 급여, 급여합을 구하세요
    SELECT A.ENAME,A.SAL, B.SUM
    
    FROM EMP A JOIN (SELECT SUM(SAL) SUM FROM EMP) B;
    
    -- FROM절 서브쿼리말고 PROJECTION 서브쿼리로 바꾸면 ( 대신 단일 컬럼 단일 로우 리턴해야함 => 스칼라 서브쿼리)
    SELECT A.ENAME,A.SAL, SELECT(SUM(SAL) FROM EMP) SUM
    
    FROM EMP A;
    ```
    
    - where 절
        - 단일로우 > < = ≠
        - 다중로우 IN(=) , > ALL <ALL >ANY <ANY
        - 다중컬럼
            
            
            ```sql
            -- 두개가 동시에 다 일치해야...
            SELECT * 
            	FROM EMP
            	WHERE (DEPTNO, SAL ) > ALL (SELECT DEPTNO, SAL FROM EMP WHERE JOB)
            ```
            
            ```sql
            -- 이름, 급여, 부서별급여합을 컬럼절의 서브커리로 구현하세요
            SELECT ENAME, SAL, (SELECT SUM(SAL) FROM EMP B WHERE A.DEPTNO = B.DEPTNO)
            FROM EMP A;
            ```
            
    
    - insert, update, delete
    
    ```sql
    INSERT INTO AAA(B,BB,BBB) VALUES((SELECT), (SELECT), (SELECT))
    
    INSERT INTO TABLE(COLUNM1, COLUMN2, COLUMN3)
    SELECT COLUNM1, COLUMN2, COLUMN3 FROM TABLE;
    
    CREATE TABLE EMP1
    AS
    SELECT * FROM EMP
    WHERE SAL>2000;
    
    SELECT *FROM EMP1;
    
    INSERT INTO EMP1(EMPNO, ENAME, JOB, DEPTNO)
    SELECT EMPNO, ENAME, JOB, DEPTNO
    FROM EMP
    WHERE SAL <2000;
    
    UPDATE TABLE SET COLUMN1 = (SELECT), COLUMN2 = DATA2
    WHERE COLUMN4 = (SELECT)
    
    DELETE FROM TABLE WHERE COLUMN1 = (SELECT ...)
    ```
    
    - create, drop
