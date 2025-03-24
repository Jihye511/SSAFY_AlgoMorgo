# Cookie

### HTTP 특징

- Stateless : 상태를 기억하지 않음
    
![image](https://github.com/user-attachments/assets/65858873-b3b7-444a-982a-52c877435e3e)
    
    이유
    
    - 단순성 : 각 요청이 독립적으로 처리되어 이전 요청과 연결할 필요가 없고, 서버의 구성이 단순해 속도가 빠름
    - 확장성 : 여러 서버 간 공유해야 할 상태가 없기 때문에 여러 서버에 요청을 분산시킬 수 있어 부하 분산 및 시스템 확장에 용이
    - 신뢰성 : 요청들이 독립적이기 때문에 한 요청의 실패가 다른 요청에 영향을 미치지 않음으로 시스템 신뢰도 향상
    - 자원 절약 : 서버에서 상태를 저장하지 않기 때문에 그만큼 서버의 메모리 저장공간 절약
- Cookie, Session 등으로 stateless 특성 보완

### Cookie

- 웹 서버에서 정보를 생성해서 클라이언트(웹 브라우저)에 보관하는 데이터

![image](https://github.com/user-attachments/assets/eee69abd-654a-4ffa-9fe1-7f2d6376c00b)

- 쿠키의 구현
    - 백엔드 : jakarta.servlet.http.Cookie
    - 프론트엔드: document.cookie 객체 활용

### Cookie의 주요 Property

- name을 제외한 property는 setter / getter 접근 가능
- name : 쿠키를 만들 때 전달하는 쿠키의 이름으로 각각의 쿠키를 구별하는 유일한 값
    - 동일한 이름의 쿠키는 덮어쓰기 (키 처럼)
    - 알파벳, 숫자, `-` , `_` , `~` , `.`  으로 구성
- value : 쿠키의 값으로 쿠키 생성시 전달하거나 setValue() 메서드를 통해 설정 가능
    - 작성 규칙은 name과 동일
- domain : 지정 도메인과 하위 도메인에서 쿠키를 공유할때 사용
    - 기본적으로 설정된 도메과 그 하위 도메인만 접근 가능
- path : 쿠키가 유효한 경로
    - path가 설정된 하위 경로에서만 쿠키 전송
        
![image](https://github.com/user-attachments/assets/89cd865f-8efb-49e7-89ff-f75bf7c3a086)
        
    - 경로 미설정시 : context root  설정
    - / 즉 container root로 지정 시 동일 도메인의 다른 애플리케이션까지 접근
        - [localhost:8080/mvc](http://localhost:8080/mvc) , [localhost:8080/mvc2](http://localhost:8080/mvc2) 의 경우 도메인이 같아 쿠키를 공유 - 보안 이슈
- maxAge : 쿠키의 유효기간
    - 양수 : 초 단위로 해당 시간까지 쿠키 존재, 시간 지나면 폐기
    - 음수 or 미 지정 : 세션 쿠키로 브라우저 종료 등으로 세션 종료시 폐기
    - 0 : 브라우저에 도착하는 즉시 폐기
- secure : HTTPS 에서만 전송 허용
- httpsOnly : Javascript에서 접근 불가 설정

+) JS가 못하게 하려던 것 : CORS ( cross origin resourse ~ ) 
자기 도메인에서만 접근하고 외부에서는 못오게.

→ SOP 정책 (같은 도메인에서만 사용)

### 쿠키의 생성과 사용

![image](https://github.com/user-attachments/assets/90189fc3-07db-46b0-af5d-c2d5d1a11d25)

---

![image](https://github.com/user-attachments/assets/da714a5c-48a5-442e-a732-df6a71e6029a)

---

![image](https://github.com/user-attachments/assets/2236aca8-fa3e-4adc-8aab-3e897945cc56)

메인 controller에서 response가 아직 안내려간 상태에서 page가 호출됨  → cookie 내용이 화면에 출력되지 않음. (F12 쿠키에서는 확인 가능) → 새로고침하면 화면에도 출력된다. 

새로고침 시 : 

![image](https://github.com/user-attachments/assets/c0debe80-7b73-4d07-bfc1-71d6176c582d)

# Session

### 웹의 기본 Scope

![image](https://github.com/user-attachments/assets/ad2851b3-a40f-4624-80d1-81b95699cf74)

| `영역`  | `구성`  | `존재` |
| --- | --- | --- |
| page 영역 | 하나의 page | jsp 페이지 내에서만 존재 |
| request 영역 | 여러 page | 하나의 요청이 끝날 때까지 존재 (응답할 때) |
| session 영역 | 여러 request | 브라우저를 닫는 등 세션이 종료될 때까지 존재 |
| application 영역 | 여러 session  | 애플리케이션이 종료될 때까지 존재 |

## Session

서버에 클라이언트의 상태 값을 저장

- 하나의 브라우저 당 하나의 세션 성립
- 쿠키는 브라우저를 닫아도 유지될 수 있으나 세션은 브라주러를 닫으면 종료

- 세션이 동작하기 위해서는 JSESSIONID라는 이름의 쿠키가 필요함
    - 서버의 세션 공간에 들어가기 위한 키
    
![image](https://github.com/user-attachments/assets/d1140330-6bd3-401b-b292-8b6274eb7b44)
    

### HttpSession 사용

- Servlet에서 Session 객체 획득
    - request.getSession() : 현재의 세션을 반환하며 아직 없을 경우 새로 생성

### 🗂️ `HttpSession` 주요 메서드 정리

| 메서드 시그니처 | 설명 | 비고 / 추가 정보 |
| --- | --- | --- |
| `Object getAttribute(String name)` | 세션에 저장된 속성(attribute)을 `name`으로 가져옴 | 로그인 사용자 정보 조회 등 |
| `void setAttribute(String name, Object value)` | 세션에 속성 저장 | 로그인 시 사용자 객체 저장 등에 사용 |
| `void removeAttribute(String name)` | 세션에서 해당 이름의 속성만 제거 | 세션은 유지되고, 특정 값만 삭제됨 |
| `void invalidate()` | 세션 무효화 (전체 삭제), 로그아웃 처리 시 사용 | 세션 자체를 종료시킴. 모든 attribute도 삭제됨 |
| `String getId()` | 현재 세션의 고유 ID 반환 | 디버깅이나 세션 관리에 사용 |

---

### ✅ `removeAttribute()` vs `invalidate()` 차이

| 항목 | removeAttribute() | invalidate() |
| --- | --- | --- |
| 작동 대상 | **하나의 속성**만 제거 | **세션 전체** 제거 |
| 세션 유지 여부 | 유지됨 | 제거됨 |
| 사용 예 | 장바구니에서 특정 상품만 제거 | 로그아웃 처리, 시간 초과 등 |
| 기타 | 다른 속성은 그대로 사용 가능 | 이후 `getAttribute()` 호출 시 `null` 반환됨 (세션 종료 상태) |

---

### 💡 예시 코드

```java
// 세션 속성 추가
session.setAttribute("userName", "Yoon");

// 특정 속성만 제거
session.removeAttribute("userName");

// 세션 자체 무효화 (로그아웃)
session.invalidate();

```

---

## ✅ 세션 시간 관련 메서드 정리

![image](https://github.com/user-attachments/assets/d38895d3-eaa7-4627-b3bc-acb03393b033)

| 메서드 | 설명 | 반환값 |
| --- | --- | --- |
| `getCreationTime()` | 세션이 **최초로 생성된 시각** | `long` (timestamp, 밀리초 단위) |
| `getLastAccessedTime()` | 세션에 **마지막으로 접근한 시각** | `long` (timestamp, 밀리초 단위) |
| `getMaxInactiveInterval()` | 세션이 **사용되지 않아도 유지되는 최대 시간** | `int` (초 단위) |
| `setMaxInactiveInterval(int interval)` | 세션 최대 비활성 시간 설정 | 없음 (`int` 초 단위) |

---

## 🕒 세션 유효 시간 흐름 이해하기

아래 그림을 간단히 해석해보면:

```
|<------------ maxInactiveInterval ------------>|
 ↑                                            ↑
creationTime                        +         lastAccessedTime
                                                │
                             사용 안 하고 시간이 흐르면
                                                ↓
                                 세션 사용 실패 → 소멸

```

---

### 💡 핵심 포인트

- **세션의 유효 시간은 `lastAccessedTime`을 기준으로 계산**됨!
- 즉, **마지막 요청 이후**로부터 `getMaxInactiveInterval()` 시간(초)이 지나면 세션은 **자동으로 소멸됨**
- 사용자가 페이지를 새로고침하거나 요청을 보내면 → `lastAccessedTime`이 갱신됨 → 유효시간도 다시 초기화됨 (연장됨)

---

## 🔁 예시로 쉽게 이해하기

```java
session.setMaxInactiveInterval(1800); // 30분

// 세션 생성 시각
long created = session.getCreationTime(); // 예: 14:00:00

// 마지막 접근 시각
long lastUsed = session.getLastAccessedTime(); // 예: 14:15:00

// 그럼 소멸 예정 시각은?
// → 14:15:00 + 1800초(30분) = 14:45:00

// 14:45:01부터 세션은 만료됨

```

---

## 🧠 정리 요약

| 구분 | 의미 | 주의할 점 |
| --- | --- | --- |
| `creationTime` | 세션이 처음 생성된 시점 | 이건 고정됨 |
| `lastAccessedTime` | 마지막 요청/접근 시각 | 이 값이 바뀌어야 세션이 유지됨 |
| `maxInactiveInterval` | 비접근 상태로 유지 가능한 시간 | 초 단위 (기본값: 보통 30분) |
| 세션 소멸 조건 | `lastAccessedTime + maxInactiveInterval` 지남 | 이후엔 `session.getAttribute()` 호출 시 `null` 반환됨 |

---
- 세션은 getLastAccessedTime() 부터 getMaxInactiveInterval() 까지 유효

![image](https://github.com/user-attachments/assets/1c32682d-3bfe-4959-8a5d-4ac0a18d27cf)

### HttpSession의 유효기간 설정

- WAS의 web.xml에 container 차원의 session-timeout 설정
    - 설정 단위는 분
    
![image](https://github.com/user-attachments/assets/4dee00f0-242e-4aea-aa90-737676c6a456)
    
- 애플리케이션 별 web.xml 에서 context단위 재정의 가능
- HttpSession#setMaxInactiveInteval(int sec)을 통해 프로그래밍적으로  재정의 가능
    - 초 단위로 유효기간 설정

### log in / logout 처리

session에서 로그인하는 객체가 empty 하면 로그인 ↔ 아니면 로그아웃

ID가 서버가 아니라 local storage나 Cookie를 사용해야겠구나.
# 오프라인

![image](https://github.com/user-attachments/assets/5fcee4c5-b36d-49d8-a6f5-2e9f2428c398)

다이어그램은 **웹 애플리케이션에서 쿠키 처리 흐름**을 설명하려는 그림이야. 전체 흐름을 정리해보면 **클라이언트(B) → 웹 서버(WebServer)** 간의 쿠키 요청/응답 흐름, 그리고 세션과의 연관성을 설명하는 구조로 보여.

---

## 🧩 그림 구성 요소 분석

### 🔸 왼쪽: 클라이언트 (브라우저 / B)

- JavaScript, `document.cookie`
- 쿠키를 읽거나 출력 (`Cookie {file?}` 라고 적혀 있음)
- 요청 시 쿠키를 포함해서 서버로 전송 (`req 전달`)

---

### 🔸 오른쪽: WebServer

- `JSESSIONID`, 세션 관련 작업 수행
- 쿠키를 읽음: `req.getCookie()`
- 새 쿠키 생성: `new Cookie(...)`
- 쿠키 응답 전송: `resp.addCookie(...)`
- 하단엔 세션 관련 메서드도 정리되어 있음:
    - `setAttribute`, `getAttribute`, `removeAttribute`, `invalidate`

---

## 🔄 전체 흐름 요약

| 단계 | 설명 |
| --- | --- |
| ① 클라이언트가 요청을 보냄 | 이때 브라우저는 기존 쿠키를 자동으로 `request`에 실어 서버로 보냄 |
| ② 서버는 쿠키를 꺼냄 | `req.getCookies()`로 쿠키 배열 획득 |
| ③ 조건에 따라 쿠키 생성 | 로그인/RememberMe 등으로 새 쿠키 필요 시 `new Cookie(...)` |
| ④ 응답에 쿠키 추가 | `resp.addCookie()`를 통해 클라이언트에 다시 전달됨 |
| ⑤ 클라이언트는 쿠키 저장 | 이후 JavaScript로 접근 가능 (`document.cookie`) |
| ⑥ 세션 처리도 병행 | `setAttribute`, `invalidate()` 등의 호출이 세션 인증과 함께 수행됨 |

---

## 💬 정리 문장으로 표현

> 사용자가 브라우저를 통해 요청을 보낼 때 기존 쿠키 정보가 함께 전송되며, 서버는 req.getCookies()로 이를 조회하고 필요 시 새로운 쿠키를 생성하여 resp.addCookie()로 응답에 포함시킨다. 응답을 받은 브라우저는 해당 쿠키를 저장하며 이후 JavaScript에서 document.cookie를 통해 접근이 가능하다. 동시에 서버는 세션 정보를 setAttribute()나 invalidate() 등을 통해 관리하며 로그인 상태를 유지하거나 해제한다.
> 

---

장점 : 단순함

단점 : 보안, 많은 데이터를 넣을 수 없다 (4KB) 부족

### 세션

서버는 세션을 하나씩, 칸으로 나눠서 관리한다.

몇번째 칸이라는 건 어떻게 결정되냐? - 

getSession () = 내 사물함 줘. 

request.getSession( ) ;  왜 request에서 달라고 하냐? - 사물함의 주인임을 알려주고 , 그 키의 역할을 하는 것이JSessionID (쿠키의 일종)

만약의 쿠키를 완벽하게 차단했을 때 세션을 사용할 수 있을까 ?

- 없다. - > JSessionID가 없으면 세션 사용이 불가
