## JDBC(Java Data Base Connectivity)

- 자바 애플리케이션과 db와의 독립성을 위한 표정화된 인터페이스 제공

![image](https://github.com/user-attachments/assets/c8d346a2-c2f5-4033-94ae-b27a5787b308)


- JDBC Driver
    - JDBC인터페이스에 대한 db 벤더들이 구현한 클래스 라이브러리

## JDBC API

- JDBC를 이용한 DB 연동
1. JDBC Driver(DBMS 연결 해주는 역할) Class 연동
2. Connection 생성
![image](https://github.com/user-attachments/assets/e0b3afde-2883-44dd-a397-24d87a6043a9)


3. Statement 생성
    
  ![image](https://github.com/user-attachments/assets/5ff60f1c-a1f2-493f-8f5f-2d48f3b6739d)

    
    - java.sql.PreparedStatement
        - 반복적인 sql 작업을 최적화하여 통로 생성 시 실행할 sql을 미리 결정하는 statement
        - sql문에서 바뀌는 데이터 부분은 ?로 표기
        - 실행하기 전에?로 표기된 부분에 값 바인딩 필요
4. SQL 실행
    - 둘의 차이.. preqpredstatement는 실행시 전달 x(아님 에러남)
    
    ![image](https://github.com/user-attachments/assets/7dba4e7a-a7bc-40fb-a95f-07ddc73db26b)

    
    - INSERT, UPDATE, DELETE → excuteUpdate 메소드 → 리턴값이 int ( 행 개수 출력..)
    - 모든 SQL은 boolean 리
5. 결과 집합 처리


    ![image](https://github.com/user-attachments/assets/10c9a4b5-720c-429b-b17c-29c48fb7c870)

    
6. 자원 반납

<aside>
💡

하나의 메소드에서 여러가지 SQL문 실행 → Statement

DAO에서는 하나의 SQL → PREPAREDSTATEMENT ㄱㅊ

</aside>

## DAO 디자인 패턴

- 데이터 처리 로직, 비즈니스 로직을 분리하는 디자인 패턴
