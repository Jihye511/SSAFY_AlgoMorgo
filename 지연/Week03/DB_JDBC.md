# 6. DB : JDBC

## ▶️ JDBC

- Java DataBase Connectivity
    - 자바 애플리케이션에서의 DB접근 및 사용 방법에 대한 표준화 필요성 부각 ⇒ 유지보수 위해
    - 표준화된 인터페이스 제공
    
    ![image.png](attachment:726db41c-4458-4a58-b9c2-164d1adfef3b:image.png)
    

### Java DataBase Connectivity

- 개발자 관점 : What
    - DB와의 통신을 하기 위해 java Application에서 사용하는 방법
    - 각 DB 접속방법 및 구현에 대한 상세한 정보를 알 필요 없음
- DataBase 관점 : How
    - java Application을 위한 표준화된 통신기능을 제공하기 위해 구현해야하는 약속, 명세
    - 다른 DB 벤더와 독자적인 구현을 유지 할 수 있음(동그라미를 유지하며 알맹이는 나의 것)

### JDBC Driver

- DB 교체하면 해당 DB에 맞는 드라이버로 교체

### JDBC를 이용한 DB  연동

1. JDBC Driver Class 로딩 
2. Connection 생성
3. Statement 생성
4. SQL 실행
5. 결과 집합 처리
6. 자원 반납

### 1. JDBC Driver Class 로딩

- DBMS와 연결을 수립해주는 역할의 JDBC Driver Class 메모리에 로딩 및 등록
- 명시적 로딩 ( 현재는 자동 처리)

```sql
Class.forName(”com.mysql.cj.jdbc.Driver”);
```

### 2. Connection 생성

- DBMS와 연결 생성
    - url 의 mysql, oracle 을 확인하고 db에서 맞는 driver 사용하여 연결
    - return 타입은 connection 으로 앞선 그림에서의 동그라미

```sql
String url = "jdbc:mysql://localhost:3306/ssafydb";   //DB URL
String user = "ssafy";                                //DB 사용자
String password = "ssafy";                            //DB 비밀번호
Connection conn = DriverManager.getConnection(url,user,password);
```

### 3. Statement 생성

- 통로 생성
    
    ![image.png](attachment:9cb42d01-2b48-4249-b1aa-596be1f1317f:image.png)
    
    - java.sql.Statement
        - 가장 기본이 되는 Statement,다른 Statement의 상위 클래스
        - 실행할 SQL이 정해지지 않아 매 실행마다 실행할 SQL 전송
        - SQL Injection 취약
            
            ```sql
            Statement stmt = conn.createStatement();
            ```
            
    - java.sql.PreparedStatement
        - 반복적인 SQL 작업을 최적화하여 통로 생성시 실행할 SQL을 미리 결정
        - SQL 문에서 바뀌는 데이터 부분은 ? 로 표기
        - 실행하기 전에 ?로 표기된 부분에 값 바인딩 필요 → setXXX()
        - SQL Injection 대비 가능
            
            ```sql
            String sql = "SELECT * FROM emp WHERE empno = ?";
            PreparedStatement stmt = conn.PrepareStatement(sql);
            stmt.setInt(1,empNo);
            ```
            
    - java.sql.CallableStatement
        - DB내의 Stored Procedure을 호출하는 SQL을 실행
        - 속도는 가장 빠르지만 DBMS에 종속적

### 4. SQL 실행

- java.sql.Statement

```sql
int rowCnt = stmt.executeUpdate(sql);
ResultSet rs = stmt.executeQuery(sql);
boolean hasResult = stmt.execute(sql);
```

- java.sql.PreparedStatement

```sql
int rowCnt = pstmt.executeUpdate();
ResultSet rs = pstmt.executeQuery();
boolean hasResult = pstmt.execute();

(sql)=> 실행시 전달하면 에러
```

### 5. 결과 집합 처리

- java.sql.Statement
    
    ```sql
    String sql = "SELECT empno, ename FROM emp";
    ResultSet rs = stmt.executeQuery(sql);
    while(rs.next()){
    EmpDTO emp = new EmpDTO();
    emp.setEmpNo(rs.getInt("empno"));
    ...
    }
    ```
    
- ResultSet처리
    - 커서를 이용하여 레코드 탐색
    - next 부르면 다음으로 이동

### 6. 자원 반납

- 자원 반납
    - 사용한 모든 자원에 대한 반납 처리
    
    ```sql
    if(rs != null)   re.close();
    if(stmt != null) stmt.close();
    if(conn != null) conn.close();
    ```
    
    - ResultSet.close() : Statement에 의해 묵시적 반납 가능

## ▶️ DAO 디자인 패턴

### Data Access Object 디자인 패턴

![image.png](attachment:10b76e18-54e9-4efa-b144-89af25768c9e:image.png)

# 보충

---

DAO = repository

오늘 배운건 jdbc이용해 정보 저장하는 방법

- VO
    - DTO
    - Storage

survlet? 은 controller가 아니다??
