# Filter

### Filter와 Filter Chain

Filter : 웹 컴포넌트가 실행되기 전/후에 요청과 응답을 가로채서 부가적인 기능을 수행하기 위한 웹 컴포넌트

![image](https://github.com/user-attachments/assets/97bafa6b-c004-4912-83a5-85ef50dfe071)

filter chain : 여러 개의 필터가 모여 필터 체인 구성 가능

- 주 용도
    - 인증 및 권한 확인 - 사용자가 특정 페이지에 접근하기 전에 로그인 확인, 권한 확인
        - 비로그인 or 권한 부족 시 접근을 차단하고 로그인 페이지로 redirect 처리
    - 로깅  - 어떤 요청이 있었는지, 어떤 응답이 있었는지 추적
        - 문제 해결 및 사용자 행동 분석
    - 보안 - 요청을 검사하여 악의적인 코드나 공격 차단
        - 크로스 사이트 스크립팅 공격등을 방지하기 위한 입력값 검증
        - < → &lt   ,  > → &gt;
- 작성 : File > new > Filter (jakarta.servlet.http.httpFilter 상속받아 구현)
    - Filter Mapping : Filter 적용 대상 설정
    - Servlet : filter를 적용한 servlet 특정
    - (多) URL pattern : url 기반 filter 적용 대상 설정
    - select dispatcher : filter를 적용할 시점
        - request : 클라이언트 직접 요청인 경우 (기본값)
        - forward
        - include
        - error : 에러 페이지로 전달 시 (web.xml에 error-page 설정 필요)
    - @webfilter를 이용한 등록
    
![image](https://github.com/user-attachments/assets/8944bffa-57dd-42a3-bd17-0c0b7f1e0860)
    
    - web.xml을 이용한 등록
    
![image](https://github.com/user-attachments/assets/a89eddaa-31fc-44ac-b618-d392ad76c977)
    
    - filter interface : 서블렛과 유사한 life cycle 제공
        
![image](https://github.com/user-attachments/assets/a54b6883-2331-4d85-80b7-4763750f4746)
        
        - dofilter와 service에서 Request와 Response 형태가 다름
            
            dofilter는 세션 쿠키 이런 것들이 안됨
            
    - Session Filter : 인증이 필요한 요청에 대해 전처리로 동작하는 filter
        - 미인증 사용자는 인증처리 페이지로 reditect
        
![image](https://github.com/user-attachments/assets/a9140893-ec46-4203-a1ca-e4181a1cea8d)
        

# Paging

- 전체 멤버 목록 조회

### 페이징을 위해 필요한 것들

- SearchCondition

![image](https://github.com/user-attachments/assets/45c42641-c6db-4968-a8a4-35689783c9d7)

- Page

![image](https://github.com/user-attachments/assets/8c371122-9a79-4cc0-abfd-92a507a2036b)

화면 처리: 

- 처음 링크를 통한 화면 호출 (a)

![image](https://github.com/user-attachments/assets/c3b5767b-fe43-48cf-a0cd-ea41d8e38498)

- 검색 조건을 이용한 호출 (form)

![image](https://github.com/user-attachments/assets/e4217b7c-4463-42db-8563-842ea2a75419)

- 페이지 링크를 이용한 호출로 구분

![image](https://github.com/user-attachments/assets/faa9a8c3-33b0-4e60-ac57-80bd00e55988)

# Listener

## Connection pool

- 기존 JDBC connection의 문제와 Connection pool
    - connection은 network를 통해 얻어오는 비싼 자원
    
![image](https://github.com/user-attachments/assets/8405cdb8-ea0c-4755-9da8-be4cbe36c03d)
    
    - 커넥션 풀을 Hikari CP를 통해 구현
    
    ```java
    <!-- pom.sml -->
            <dependency>
                <groupId>com.zaxxer</groupId>
                <artifactId>HikariCP</artifactId>
                <version>${HikariCP.version}</version>
            </dependency>
            
            
    <!-- DBUtil.java -->
    private static HikariDataSource dataSource;
    	
    	static {
    		try {
    			//Class.forName("com.mysql.cj.jdbc.Driver");
    			HikariConfig config = new HikariConfig();
    			config.setJdbcUrl(URL);              // 연결할 DB 주소
    			config.setUsername(USER);           // DB 사용자명
    			config.setPassword(PASSWORD);       // DB 비밀번호
    			config.setDriverClassName("com.mysql.cj.jdbc.Driver");
    			config.setMaximumPoolSize(5);
    			config.setMinimumIdle(3);
    			
    			dataSource = new HikariDataSource(config);
    			System.out.println("연결 완료");
    			
    		} catch (Exception e) {
    			throw new RuntimeException("HikariCP Connection Pool initialization failed", e);
    		}
    	}
    
    	public static Connection getConnection() throws SQLException {
    		return dataSource.getConnection();
    	}
    ```
    

![image](https://github.com/user-attachments/assets/b724c38b-3c2f-489e-bf9c-06d9a166c3ca)

## Listener

: 웹 애플리케이션에서 발생하는 이벤트에 대한 모니터링 객체

![image](https://github.com/user-attachments/assets/d74cb8a7-a4fd-4454-ac33-e326bfbe3057)

# Exception 처리

- 기본 Exception 처리 정책 : 어떠한 경우에도 예외 정보가 클라이언트에 직접 전달되지 않도록
- 사용자에게 보여지는 페이지와 문제 해결을 위한 로깅이 병행 되어야함

### 404 오류 처리

: front Controller 까지 진입 후 처리할 sub controller를 발견하지 못한 경우?

- 경로가 존재하지 않는 경우 → forward 처리 (WAS가 기본 404페이지로 처리)

→ `Controller` 내부에서 `action == null` 등 조건문으로 404.jsp로 `forward`

front Controller까지 진입하지 못한 요청 : 서블릿에 도달하지 않았음으로 

처리 불가 → WAS가 기본 404 페이지 처리

→ `web.xml`의 `<error-page error-code="404">`로 처리

### 500 오류 처리

: front Controller에서 try-catch로 checked Exception 처리 후 관련 페이지에 에러 메시지 전달

- 예외가 발생하면 무조건 WAS로 넘기기보다는
- **try-catch로 잡고** 관련된 에러 페이지로 `forward`하거나 `setAttribute()`로 메시지를 전달하는 방식 사용
- 예외의 종류에 따른 처리 방식

| 예외 종류 | 설명 | 처리 방식 |
| --- | --- | --- |
| **Unchecked Exception** | 런타임 예외 (`NullPointerException`, `ArrayIndexOutOfBoundsException` 등) | 항상 WAS까지 전달됨 |
| **Checked Exception** | `SQLException`, `IOException` 등 | 직접 `catch`하거나 **ServletException으로 감싸서 throw**해야 WAS가 알 수 있음 |

```java
catch (Exception e) {
    throw new ServletException(e); // 꼭 감싸서 전달해야 함!
}

```

- 정리: 500 예외 처리 전략

| 상황 | 처리 방식 | 결과 |
| --- | --- | --- |
| 예상 가능한 예외 | `try-catch` 후 `forward` | 사용자에게 친절한 에러 제공 |
| 예측 불가능한 예외 | `throw new ServletException(e)` | WAS가 `error-page`로 처리 |
| Unchecked Exception | 자동으로 WAS까지 전파됨 | 500 에러 페이지 출력됨 |

### WAS의 예외 처리 활용

- 예외처리 페이지 지정

```java
<error-page>
  <error-code>404</error-code>
  <location>/error/404.jsp</location>
</error-page>

<error-page>
  <error-code>500</error-code>
  <location>/error/500.jsp</location>
</error-page>
```

![image](https://github.com/user-attachments/assets/3534369d-3ff9-4096-bd0b-31727b9c666b)

- DispatcherType.ERROR 이란?

> 🔥 에러 처리 과정 중에도 필터를 동작시킬 수 있다는 이야기야!
> 

### 예시 필터 설정:

```java
@WebFilter(
    urlPatterns = "/*",
    dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.ERROR}
)
public class LoggingFilter implements Filter {
    ...
}
```

### 이 설정이 있으면?

- 평소 요청에도 필터 작동 (`REQUEST`)
- 에러 페이지로 넘어갈 때도 필터 작동 (`ERROR`)

👉 예외 상황에서도 **로깅**, **모니터링**, **추적** 가능해짐.

# 오프라인

## 📌 오늘 수업 핵심 요약

---

### ✅ 1. **Connection Pool(커넥션 풀)**

### 💥 문제 상황

- DB 커넥션을 계속 새로 열면 성능 저하 & 자원 낭비
- *초기 커넥션 수(예: 10개)**가 차고, 계속 요청되면 **connection full 에러** 발생

### 🛠 해결 방식

- **Connection Pool**(커넥션을 미리 만들어두고 재사용)
- 대표적인 커넥션 풀: **HikariCP, DBCP, C3P0**

### 🧩 관련 코드 흐름

```java
DataSource ds = new HikariDataSource(); // DataSource 인터페이스 사용
Connection conn = ds.getConnection();   // 리턴 타입은 Connection

```

### 📌 추가 설명

- `DataSource`는 **인터페이스**
- Hikari, Oracle, MySQL 등 다양한 업체에서 이를 구현

---

### ✅ 2. **에러 처리 방식**

### 1) 서블릿 내부에서 처리

- 예외 발생 시 `req.setAttribute("errMsg", ...)`
- `forward` 방식으로 에러 페이지로 이동

### 2) WAS에서 전역 에러 처리

- `web.xml`에 에러 페이지 설정 가능

```xml
<error-page>
  <error-code>500</error-code>
  <location>/error/500.jsp</location>
</error-page>

<error-page>
  <exception-type>java.lang.NullPointerException</exception-type>
  <location>/error/null.jsp</location>
</error-page>

```

---

### ✅ 3. **web.xml 관련 내용**

### 💡 역할

- **웹 애플리케이션 전체 설정 파일**
- 서블릿 매핑, 필터, 에러 페이지, 인코딩 설정 등

### 📍 두 가지 종류

| 위치 | 설명 |
| --- | --- |
| `서버 web.xml` | 톰캣 서버의 전체 설정 관리 (`{tomcat}/conf/web.xml`) |
| `프로젝트 web.xml` | 개별 프로젝트 전용 설정 (`/WEB-INF/web.xml`) |

❗ **서버 web.xml 수정은 권장되지 않음** → 전역에 영향을 줌

✅ 대신 **프로젝트 안에 `/WEB-INF/web.xml`을 새로 만들어서 설정**

### 📌 생성 방법

- 이클립스 기준: DD(Deployment Descriptor)에서 `Generate Deployment Descriptor Stub` 클릭

---

## ✨ 핵심 요약 1줄씩

| 주제 | 요약 |
| --- | --- |
| 커넥션 풀 | DB 연결을 효율적으로 관리하기 위해 커넥션을 미리 만들어 재사용 |
| DataSource | 커넥션 풀의 대표 인터페이스 (Hikari, DBCP 등 구현체 존재) |
| 에러 처리 | 서블릿 내부 처리 or `web.xml`로 전역 에러 페이지 설정 |
| web.xml | 개별 프로젝트 설정을 위해 `/WEB-INF/web.xml`을 별도로 구성해야 함 |

---
