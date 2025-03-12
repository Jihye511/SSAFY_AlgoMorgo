# DataBase

- 특정 시스템 내에서 기능을 운영하기 위해 다수의 사용자들이 공유할 수 있도록 통합시키고 컴퓨터 저장 장치에 저장시킨 데이터의 집합
- DB vs DBMS
    - DBMS는 DB를 관리해주는 시스템

## DataBase 특징

- 실시간 접근 가능
- 데이터의 동시 공유
- 데이터의 독립성
- 데이터의 무결성
- 데이터 중복 최소화(정규화)
- 데이터 보안
- 데이터 일관성
- 데이터의 지속성

## DBMS

- DataBase Management System
- 응용프로그램과 데이터베이스의 중개자로 데이터의 독립성 제공
- 실제로 DB에 직접 접근이 아니라 DBMS를 통한 간접 접근
- 예시 : MySql, Oracle, Maria DB

## RDBMS

- 관계형 모델을 지원하는 DBMS
- 구성 요소 : 개체, 관계, 속성
    - 개체(Entity) : 현실 세계에서의 유/무형의 실체(객체와 유사)
        - Table
    - 관계(Relationship) : 2개 이상의 개체 간의 상호 작용이나 연결을 정의 한 것, 1:1관계 1:N관계 N:M 관계
        - 외래키(Foreign Key)
    - 속성(Attribute) : 개체가 포함하고 있는 하나 하나의 성질
        - 열(Column)

## SQL

- Structured Query Language
- 관계형 데이터베이스 관리 시스템(RDBMS)의 데이터를 관리하기 위한 프로그래밍 언어
- ANSI SQL(표준 SQL)을 사용하면 모든 DBMS에서 사용 가능
    
    ![image.png](attachment:60b85a30-3f07-4086-9ff4-8afdcab5e46a:image.png)
    

## SQL구문의 종류

![image.png](attachment:7d3a2ee1-c2dc-41c6-b87d-91806b975113:image.png)

- 주로 DML, TCL

## 식별/비식별 관계

- 식별 관계란, **부모 테이블의 기본키 또는 유니크 키를 자식 테이블이 자신의 기본키로 사용하는 관계**
- 비 식별 관계란 **부모 테이블의 기본키 또는 유니크 키를 자신의 기본키로 사용하지 않고, 외래 키로 사용하는 관계**

## SQL 예시

- 비교 연산자
    
    ```sql
    select ename,deptno
    from emp
    where deptno =10 || deptno=20;
    ```
    
- 논리 연산자
    
    ```sql
    select ename, sal
    from emp
    where sal >= 1000 and sal<=2000;
    ```
    
- CASE 연산자
    
    ```sql
    # 사원번호, 사원이름, 월급여, 월급여가 0~2000미만 : C등급, 2000~4000미만 : B등급, 4000~ : A등급으로 조회
    select empno, ename, sal,
    	case
    		when sal<2000 then 'C'
            when sal<4000 then 'B'
            else 'A'
    	end as salary_grade
    from emp;
    ```
    
- 정렬
    
    ```sql
    # 사원번호, 이름, 부서번호, 월급여를 부서번호가 빠른 순으로, 같은 부서안에서는 월급여를 많이 받는 순으로 사원 조회
    select empno, ename, deptno,sal
    from emp
    order by deptno , sal desc;
    ```
    
- LIMIT 행결과 제한
    
    ```sql
    # 가장 많은 월급여를 받는 상위 5명 이후 5명의 사원이름, 월급여 조회
    select ename, sal
    from emp
    order by sal desc
    limit 5,5;
    
    ```
    

## 함수

- 함수의 처리 과정에 따라 단일행 함수, 다중 행 함수로 구분
- 단일행 함수
    - 숫자, 문자,날짜,변환,기타 함수
    
    ![image.png](attachment:394f6563-a103-4bae-9963-7ef66e5c2a56:image.png)
    
    - []로 싸여있으면 선택, 안싸여있으면 필수
    
    ![image.png](attachment:f896fb8a-db27-48c0-ab8b-9b87fd9e88a0:image.png)
    
    - CONCAT, INSTR, SUBSTRING
    
    ![image.png](attachment:4ff1344a-2a7a-44ed-a179-9eb10bd3e208:image.png)
    
    - (L,R)TRIM, LENGTH, CHAR_LENGHT,FORMAT
    
    ![image.png](attachment:37c02f37-cf46-452b-99d4-41cc6edeca38:image.png)
    
    - NOW,CURDATE,DATE_ADD,SATE_SUB,YEAR,MONTH
    
    ![image.png](attachment:ea37f8d5-9266-46e0-8e5e-554fecf0c64d:image.png)
    
    - EXTRACT, DATE_FORMAT
- 다중행 함수
    - 집계함수 :  그룹별 집계 처리
    - 윈도우 함수 : 개별 데이터를 조회 + window별 집계 결과

## 강사님

- SQL은 대소문자 구분 X (다만, 데이터에 대해서는 대소문자를 구분)
- Table은 실제 데이터를 가지고 있는것이 아니다 ⇒ 실제 데이터는  column
- 대표적인 단일행 함수
    - 숫자 : count, max, min, sum, avg
    - 문자 : upper, lower
    - 날짜 : date_add, date_sub
- where는 데이터 건수만큼 실행
- null은 비교 불가
    - hiredate = null (X)
    - hiredate is null (O)
- 다중행 함수는 단일행함수와 함께 사용 불가
- 다중행 함수안에 다중행 함수는 사용 불가
- upper와 같이 인덱스를 변경시킨 순간 Full Table Scan하여 오랜 시간 소요
