# Web(back) : Session/Cookie

## ▶️ HTTP 특징

- Stateless : 상태를 기억하지 않음
    
    ![image](https://github.com/user-attachments/assets/92121629-2696-4715-8d80-2e296e8e9588)
    - 단순성 : 각 요청이 독립적이기 때문에 이전 요청과 연결할 필요 없어서 단순해지며 속도가 빨라짐
    - 확장성 : 여러 서버 간 공유할 필요가 없기 때문에 여러 서버의 요청을 분산할 수 있어 시스템 확장 용이
    - 신뢰성 : 요청이 독립적이기 때문에 하나가 실패해도 다른 하나에 영향 없음, 신뢰도 향상
    - 자원절약 : 상태를 저장하지 않기 때문에 그만큼 서버의 메모리, 저장 공간 절약

⇒ Cookie, session 등으로 stateless 특성 보완

## ▶️ Cookie

- 웹 서버에서 정보를 생성해서 클라이언트(웹 브라우저)에 보관하는 데이터
    1. 브라우저 에서 서버에 요청
    2. 서버 쿠키 생성
    3. resopnse시 쿠키 전송
    4. 브라우저에 쿠키 저장
    5. request 시 cookie 전송

### Property

- name을 제외한 property는 setter/getter로 접근 가능
- name
    - 유일한 값
- value
    - setValue() 메서드를 통해 설정 가능
- domain
    - 지정 도메인과 그 하위 도메인이 쿠키를 공유할 때 사용
- path
    - 쿠키가 유효한 경로
- maxAge
    - 쿠키의 유효 기간
    - 양수 : 초 단위로 시간이 지나면 자동 폐기
    - 음수 또는 미지정 : 브라우저 종료 등 세션 종료 시 폐기
    - 0 : 브라우저에 도착하는 즉시 폐기

### Cookie의 생성과 사용

```
Cookie cookie = new Cookie("user","andy");
cookie.setMaxAge(60*1);
response.addCookie(cookie);
```

```
Cookie[] cookies = request.getCookies();
```

## ▶️ Session

- 서버에 클라이언트의 상태 값을 저장
- 하나의 브라우저 당 하나의 세션 성립
- 쿠키는 브라우저를 닫아도 유지 될 수 있으니, 세션은 브라우저를 닫으면 종료

### HttpSession 사용

- request.getSession();

### HttpSession의 유효기간

- 주요 메서드
    - long getCreationTime()
    - long getLastAccessedTime() ~~ 부터
    - int getMaxInactiveInterval() ~~까지 유효
    - void setMaxInactiveInterval(int interval)
- HttpSession의 유효기간 설정
    - WAS의 web.xml에 container차원의 session-timeout설정
    - 애플리케이션 별 web.xml에서 context 단위 재정의 가능

---

[보충](https://www.notion.so/1c0604d1c8f28062bbdee4d676198a4d?pvs=21)
