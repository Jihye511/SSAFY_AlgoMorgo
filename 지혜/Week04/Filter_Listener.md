# 1.Filter

> 웹 컴포넌트가 실행되기 전/후에 요청과 응답을 가로채서 부가적인 기능을 수행하기 위한 웹 컴포넌트
> 

![image](https://github.com/user-attachments/assets/aca93ddd-fede-4b54-94fc-176a9f39901b)


- Filter Chain : 여러개의 필터가 모여 필터 체인 구성 가능

### 주요 용도

- 인증 및 권한 확인 :로그인 했는지 (인증), 권한있는지
- 로깅 - 어떤 요청이 있었는지 어떤 응답이 있었는지 추
- 보안 - 요청 검사하여 악의적인 코드나 공격 차단

![image](https://github.com/user-attachments/assets/7796a3c2-1f02-4627-af80-3ee0f3e4b561)


# 2. Paging

- 데이터가 많을 때 **한 번에 다 보내지 않고 나눠서 보내는 기술**.
- 클라이언트가 페이지를 지정하면 해당 범위의 데이터만 응답.

### 필요성

- 성능 향상 (한 번에 100만 개 안 보내도 됨)
- 사용자 경험 (스크롤, 페이지 넘김 등)

### 주요 파라미터:

- `page`: 몇 번째 페이지인지
- `size` 또는 `limit`: 한 페이지에 몇 개 보일지
- `offset`: 시작 인덱스

```sql
SELECT * FROM users
LIMIT 10 OFFSET 20; -- 3번째 페이지 (한 페이지 10개)

```

# 3. Listener

- **특정 이벤트가 발생했을 때 자동으로 실행되는 로직**.
- 비동기적으로 동작하거나, 이벤트 기반 처리에 자주 사용.

### 예시:

- 회원 가입 완료 → `회원가입 완료 이벤트` 발생 → `이메일 전송 리스너`가 자동 실행
- 결제 완료 → `포인트 적립 리스너` 작동

```java
@EventListener
public void handleUserRegistered(UserRegisteredEvent event) {
    // 회원가입 축하 메일 전송
}
```

## 📌정리

| 개념 | 요약 |
| --- | --- |
| Filter | 요청/응답을 가로채 공통 처리 (보안, 로깅 등) |
| Paging | 많은 데이터를 나눠서 처리 (페이지 단위로 응답) |
| Listener | 특정 이벤트 발생 시 자동 반응 (이메일 발송 등) |

---

---

![image](https://github.com/user-attachments/assets/5a22cb34-6c02-4c4b-87cb-237252f8c8b7)


- **Filter**를 사용하여 서블릿이 실제로 실행되기 전에 요청/응답을 가로채어 처리하는 구조

![image](https://github.com/user-attachments/assets/a20ad502-88c6-4198-9585-e612a810f208)


## 🧠전체 흐름 요약

```scss

브라우저 → HttpServletRequest → Filter → Servlet → DAO → 응답(HttpServletResponse)
```

---

## 🖼️ 그림 구성 설명

### 1. **왼쪽 박스: 클라이언트 (사용자 브라우저)**

- 사용자가 웹 브라우저에서 요청을 보냄 (ex: `GET /login`)

---

### 2. **중앙 검정 세로선: Filter**

- 그림에서 `filter`라고 쓰여 있음
- **Filter는 서블릿보다 먼저 실행**되어 요청을 가로챔
- 주요 메서드:
    - `init()` : 필터 초기화
    - `doFilter(req, resp, chain)` : 필터 본체
    - `destroy()` : 필터 종료 시 실행
- 필터에서는 요청/응답을 체크하거나 가공 가능
    
    예: 로그인 안 했으면 서블릿 안 넘기고 로그인 페이지로 `redirect`
    

---

### 3. **Servlet**

- 필터를 통과한 요청이 도착
- 서블릿의 `doGet()`이나 `doPost()` 같은 메서드 실행
- 그림에선 `Servlet → service()` 흐름으로 표시됨

---

### 4. **DAO (Data Access Object)**

- 실제 DB와 연결해서 데이터를 가져오거나 저장하는 로직 처리
- 사용자 요청에 따라 DB에서 데이터 조회 후 결과를 리턴

---

### 5. **응답 흐름**

- DAO → Servlet → Filter → 클라이언트로 응답 (`resp`)
- 응답도 필터를 다시 거쳐서 나감 (post-processing 가능)

---

### 6. **노란 화살표 (Forward)**

- 서블릿 내에서 `RequestDispatcher.forward()`로 JSP 등으로 포워딩
- ex: `request.getRequestDispatcher("result.jsp").forward(request, response);`
- 클라이언트가 알지 못하는 내부 이동

---

### 7. **윗부분 텍스트:**

- `request`, `forward`, `include`는 요청 처리 방식
    - `forward` : 서블릿 내부에서 요청 넘기기
    - `include` : 일부 페이지만 포함시키기

---

## ✅ 정리하면

| 컴포넌트 | 역할 |
| --- | --- |
| **Filter** | 요청/응답 전처리/후처리 (보안, 인코딩 등) |
| **Servlet** | 비즈니스 로직 처리 |
| **DAO** | DB 처리 |
| **forward/include** | 내부 요청 이동 처리 방식 |
