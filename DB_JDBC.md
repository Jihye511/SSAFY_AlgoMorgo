# JDBC

## Java Databse Connectivity

- 자바 애플리케이션에서 DB 접근 및 사용 방법에 대한 표준화 필요성 부각
- 자바  애플리케이션과 DB와의 독립성 필요
- 표준화된 인터페이스 제공
    - 제공자 - 사용자 입장에 따라 표준이 달라짐
    - jdbc 표준 : SUN 마이크로사 에서 개발
- java.sql 패키지

고유한 역할을 잃지 않으면서 연결을 한다.

JDBC : 인터페이스의 집합이다.

![image](https://github.com/user-attachments/assets/525ac132-1c01-4bb6-becc-d5668487555e)

## JDBC Driver

- 인터페이스에 대한 DB 벤더들이 구현한 클래스 라이브러리
- JDBC 이용하는 Java Application 개발 후 실행 시 반드시 필요함
- 사용하려는 DB 벤더 홈페이지에서 다운 or  Maven의 의존성을 통해 관리
- DB 교체 시 DB에 맞는 드라이버로 교체

# JDBC API

## DB 연동 6단계

1. **~~JDBC 드라이버 클래스 로딩~~**
    - 4.0 이후부터 생략 가능
    - DBMS와 연결을 수립
    
    | 로딩 방식 | 설명 | 코드 |
    | --- | --- | --- |
    | **명시적 로딩** | 개발자가 직접 드라이버 클래스를 로드해야 함 | `Class.forName("com.mysql.cj.jdbc.Driver");` |
    | **묵시적 로딩 
    (JDBC 4.0 이후)** | `DriverManager.getConnection` 호출 시 자동으로 드라이버 로드 | 자동 처리 |
2. Connection 생성
    - DBMS와 연결 생성
    
    ```jsx
    
    String url = "jdbc:mysql://localhost:3306/ssafydb"; // DB URL
    String user = "ssafy"; // DB 사용자
    String password = "ssafy"; // DB 비밀번호
    Connection conn = DriverManager.getConnection(url, user, password);
    ```
    
    - getConnection 에서 여러 커넥션의 종류를 반환하지만, 다형성에 의해 conn에 모두 담을 수 있음
3. Statement 생성
    
![image](https://github.com/user-attachments/assets/5e01773b-d5b0-406f-a7ee-135f040d0a45)
    
    - 전기 신호 ↔ 소리 전환하는 **전화기** 역할 : statement
    - 전신주에 닿는 연결선 : Connection
    - **java.sql.Statement**
        - 생성 : Statement stmt = conn.createStatement();
        - 다른 Statement의 상위 클래스
        - 생성 시 실행할 SQL이 정해지지 않은 Statement
        - 매 실행 마다 실행할 SQL 전송
        - **SQL Injection 공격에 취약**
    - **java.sql.preparedStatement**
        - 생성 :
        
        ```jsx
        String sql = "SELECT * FROM emp WHERE empno = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, empNo);
        ```
        
        - 반복적인 SQL 작업 최소화→ 통로 생성 시 실행할 SQL을 미리 결정
        - SQL문에서 바뀌는 데이터는 ?로 표기
        - 실행 전 ? 부분은 값 바인딩 필요 → setXXX();
        - **SQL Injection 대비 가능**
    - java.sql.CallableStatement (콜러블)
        - 생성 :
        
        ```jsx
        CallableStatement cstmt = conn.prepareCall("{CALL processReturn(?, ?)}");
        cstmt.setInt(1, 250303001);
        cstmt.setString(2, "ssafy");
        cstmt.execute();
        ```
        
        - DB 내의 Stored Procedure 호출하는 SQL 실행하기 위한 statement
        - 속도는 가장 빠름
        - DBMS에 종속적
        - **.preparedStatement의 확장형**
4. SQL 실행
    - 일반 Statement : 파라미터가 필요 (경로를 지정하기 위해?)
    - pstmt : 있으면 오류
    
    ```jsx
    int rowCnt  = stmt.executeUpdate(sql);
    ResultSet rs = stmt.executeQuery(sql);
    boolean hasResult = stmt.execute(sql);
    
    int rowCnt  = pStmt.executeUpdate();
    ResultSet rs = pStmt.executeQuery();
    boolean hasResult = pStmt.execute();
    ```
    
    | SQL 유형 | 메소드 | 리턴값 |
    | --- | --- | --- |
    | SELECT | `executeQuery` | `ResultSet` |
    | INSERT, UPDATE, DELETE | `executeUpdate` | `int` |
    | 모든 SQL | `execute` | `boolean` |

| 구분 | `Statement` | `PreparedStatement` |
| --- | --- | --- |
| **SQL 전달 방식** | SQL을 직접 문자열로 전달 (`executeQuery(sql)`) | 미리 준비된 SQL 실행 (`executeQuery()`) |
| **가변 데이터 처리** | 값이 SQL 문자열 내에 포함됨 | `?` 플레이스홀더를 사용하여 따로 설정 |
| **SQL Injection 방어** | ❌ 취약함 | ✅ 방어 가능 |
| **성능 최적화** | ❌ SQL 매번 파싱 | ✅ 미리 컴파일됨 |
1. 결과 집합 처리
    - select 문이면 조회.. 아니면 생략 !
    - 커서를 이용해 레코드 탐색
    - 커서의 방향 기본값 : 순방향
    - 커서는 레코드가 아니라 레코드 이전에 있다고 생각

![image](https://github.com/user-attachments/assets/fa17baf3-6108-4e25-8693-f74378c8eb89)

1. 자원 반납
    - 사용한 모든 자원 반납 처리
    
    ```jsx
    if (rs != null)    rs.close();
    if (stmt != null)  stmt.close();
    if (conn != null)  conn.close();
    ```
    

# DAO 디자인 패턴

## Data Access Object

- 데이터 처리 로직(DAO)과 애플리케이션 비즈니스 로직(서비스파트)을 분리
- DAO는 DB의 CRUD를 캡슐화함
- 장점
    - 로직과 데이터 액세스 분리
    - 코드 재사용성
    - 유지보수성
    
![image](https://github.com/user-attachments/assets/6e318837-fdbb-4655-adbf-28b1dc0e9cb8)
    

# MVC 구조 + 계층형 아키텍쳐

### 1. **전체적인 구조**

```
[ View ]
   |
   v (DTO 전달)
[ Controller ]
   |
   v (DTO 전달)
[ Service ]
   |
   v (DTO 전달)
[ DAO ]
   |
   v (JDBC 활용)
[ Database ]
```

---

### 2. **각 계층의 역할**

### **① View (화면, 사용자 인터페이스)**

- 사용자가 직접 상호작용하는 부분.
- `Controller`에 데이터를 전달하고 응답을 받음.
- 예: HTML, JSP, React, Vue 등.

### **② Controller (컨트롤러, 흐름 제어 담당)**

- `View`에서 받은 데이터를 `Service`로 전달.
- 요청을 처리한 후 다시 `View`로 응답을 반환.
- `DTO`를 사용하여 데이터를 전달.
- 예: Spring MVC의 `@Controller`.

### **③ Service (비즈니스 로직 담당)**

- 주요 로직을 처리하는 계층.
- `DAO`를 호출하여 필요한 데이터를 가져오거나 저장.
- 데이터 검증 및 가공 수행.
- 예: 회원 가입, 주문 처리 등의 비즈니스 규칙 적용.

### **④ DAO (데이터 접근 계층, 저장소 역할)**

- 데이터베이스와 직접 통신하는 계층.
- SQL을 실행하여 데이터를 조회, 저장, 수정, 삭제.
- `JDBC`, `MyBatis`, `JPA` 등을 사용.
- 예: `EmployeeDAO.getEmployeeById(int id)`

### **⑤ Database (DB)**

- 실제 데이터를 저장하는 저장소.
- DAO를 통해 데이터를 읽고 쓰는 작업이 수행됨.

---

### 3. **DTO와 DAO의 차이**

- **DTO (Data Transfer Object)**: 계층 간 데이터 이동을 위해 사용됨.
- **DAO (Data Access Object)**: DB와 직접 연결하여 데이터를 조회/저장하는 역할.

## **DAO vs DTO vs VO 차이 정리**

| 구분 | DAO (Data Access Object) | DTO (Data Transfer Object) | VO (Value Object) |
| --- | --- | --- | --- |
| **역할** | DB와 직접 연결하여 SQL 실행 | 데이터를 계층 간 전달 | **불변 객체**로 데이터 저장 |
| **데이터 변경 여부** | 변경 가능 (DB에 CRUD 수행) | 변경 가능 (setter 가능) | **변경 불가능 (불변 객체)** |
| **비즈니스 로직 포함 여부** | SQL 실행 포함 | ❌ 없음 (단순한 데이터 저장) | ❌ 없음 (값만 저장) |
| **주요 사용처** | 데이터 조회/저장/수정/삭제 | Controller ↔ Service ↔ DAO 간 데이터 이동 | JPA 값 타입, 캐시 데이터 등 |
| **예제** | `EmployeeDAO` | `EmployeeDTO` | `EmployeeVO` |

### **예제 코드**

### **✅ DTO 예제**

```
public class EmployeeDTO {
    private int empNo;
    private String empName;
    private double salary;

    public EmployeeDTO(int empNo, String empName, double salary) {
        this.empNo = empNo;
        this.empName = empName;
        this.salary = salary;
    }
    public int getEmpNo() { return empNo; }
    public String getEmpName() { return empName; }
    public double getSalary() { return salary; }
}
```

### **✅ DAO 예제 (JDBC 사용)**

```
public class EmployeeDAO {
    private Connection conn;

    public EmployeeDAO(Connection conn) {
        this.conn = conn;
    }

    public EmployeeDTO getEmployeeById(int empId) throws SQLException {
        String sql = "SELECT empno, ename, sal FROM emp WHERE empno = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new EmployeeDTO(rs.getInt("empno"), rs.getString("ename"), rs.getDouble("sal"));
            }
        }
        return null;
    }
}
```

---
