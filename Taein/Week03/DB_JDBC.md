## Java DataBase Connectivity

- 자바 애플리케이션에서의 DB 접근 및 사용 방법에 대한 표준화 필요성 부각
- 자바 애플과 DB와의 독립성 필요
- 표준화된 인터페이스 제공

개발자

- 통신 위한 자바 어플 사용방법
- DB 제품 상관 없이 표준화된 인터페이스 이용
- 각 DB 접속 방법 및 구현에 대한 상세한 정보 알 필요 X

DB

- 통신기능 제공
- DBMS만의 고유 클래스 구현
- 독자적 구현 유지 가능

## JDBC Driver

- DB밴더들은 자신만의 고유한 구현
- 구현체를 JDBC Driver로 부름

## JDBC 이용한 DB 연결

JDBC Driver Class로딩 → connection 생성 → statement 생성 → sql 실행, 결과 집합 처리 → 자원 반납

DBMS와 연결 수립 역할의 JDBC Driver Classs 메모리에 로딩 및 등록

명시적 로딩 : Class.forName(”com.mysql.cj.jdbc.Driver”);

묵시적 로딩 : JDBC 4.0 이후 자동

sql.statement : SQL Injection 공격에 취약

## DAO 디자인

데이터 처리 로직과 애플 비즈니스 로직 분리

장점 :

- 비즈니스 로직과 데이터 액세스 분리
- 코드 재사용성 증가
- 유지 보수성 향상

## 트랜잭션

기본적으로 AutoCommit 모드 활성화

여러개 SQL 실행을 하나의 트랜잭션으로 묶으려면 AutoCommit 비활성화

메소드

- setAutoCommit(boolean)
- commit()
- rollback()
