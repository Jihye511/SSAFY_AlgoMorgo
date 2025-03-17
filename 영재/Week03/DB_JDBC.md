# JDBC

## Java DataBase Connectivity

- 자바 애플리케이션에서의 DB 접근 및 사용 방법에 대한 표준화 필요성 부각
- 자바 애플리케이션과 DB와의 독립성 필요
- 표준화된 인터페이스 제공
- java.sql 패키지
- JDBC를 사용하면 데이터베이스 종류에 관계없이 동일한 방식으로 작업할 수 있다.
- **다양한 데이터베이스에 대해 일관된 인터페이스를 제공하기 때문에, 데이터베이스 시스템을 변경해도 코드를 크게 수정하지 않아도 된다.**
- 개발자 관점
    - DB와의 통신을 하기 위해 Java Application에서 사용하는 방법
- DataBase 관점
    - Java Application을 위한 표준화된 통신 기능을 제공하기 위해 구현해야하는 약속, 명세
    

### JDBC Driver

- JDBC 인터페이스에 대한 DB벤더들이 구현한 클래스 라이브러리
- JDBC 드라이버는 Java 애플리케이션이 데이터베이스와 연결할 수 있도록 필요한 네트워크 연결을 설정
- 개발이 끝난 후 실행 시에 반드시 필요함(알멩이임)

## JDBC를 이용한 DB연동(6단계)

1. JDBC Driver Class로딩(4.0 이후 버전부터 생략 가능)
    - Class.forName(”com.mysql. cj.jdbc,Driver”)
2. Connection 생성
    - 유선전화기를 연결
    
    ```sql
    String url = "jdbc :mysql://localhost:3306/ssafydb"
    String user = "ssafy"
    String password = "ssafy"
    Connection conn= DriverManager.getConnection(url,user,password);
    ```
    
3. Statement(통로) 생성
    
    일반 Statement
    
    ```sql
    Statement stmt=conn.createStatement();
    ```
    
    - 가장 기본이 되는 Statement
    - 다른 Statement의 상위 클래스
    - 생성 시 실행할 SQL이 정해지지 않은 Statement
    - **전화 통화할 때 상대방과 대화를 시작하기 전에 무엇을 말할지 미리 정하지 않은 상태이다.**
    
    Prepared Statement
    
    ```sql
    String sql = "Select * From emp WHERE empno = ? "
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setInt(1,empNo);
    ```
    
    - 반복적인 SQL작업을 최적화하여 통로 생성 시 실행할 SQL을 미리 결정하는 Statement
    - SQL문에서 바뀌는 데이터 부분은 ? 로 표기
    - 실행하기 전에 ?로 표기된 부분에 값 바인딩 필요 → setXXX();
    - **미리 대화의 흐름을 정해놓고, 대화가 시작되면 특정 부분만 수정해서 쉽게 사용할 수 있다.**
4. SQL 실행
    
    ```sql
    int RowCnt = stmt.excuteUpdate(sql);
    //일반 Statement(실행시 뭘 전달할지 안정해져서 인자 필요)
    int RowCnt = stmt.excuteUpdate(sql);
    //prepared Statement(인자를 넣어서는 안됨)
    ```
    
    - INSERT, UPDATE, DELETE
        - return type : int
    - SELECT문이면
        - 결과 집합 처리 (selece문이면 하고 아니면 넘어가기)
            - 커서를 이용하여 레코드 탐색
            - 커서의 방향 기본값 : 순방향
            - rs.next()를 통해서 커서를 이동
        - return type : ReturnSet
5. 자원 반납
    
    ```sql
            if (rs != null) rs.close();  // ResultSet 반납
            if (stmt != null) stmt.close();  // Statement 반납
            if (conn != null) conn.close();  // Connection 반납
    ```
    

# DAO 디자인 패턴

- 데이터 처리 로직과 애플리케이션의 비즈니스 로직을 분리하는 디자인 패턴
- 비즈니스 로직을 처리하는 서비스(Service) 계층과 데이터베이스 작업을 수행하는 DAO 계층을 나누어 코드의 역할을 명확히 함
