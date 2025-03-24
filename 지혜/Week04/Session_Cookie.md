# Cookie

- Http 특징
    - stateless 상태를 기억하지 않음
    - 전에 했던 행동을 기억하지 못함
    - 이유?
        - 단순성 : 이전 요청과 연결할 필요 x
        - 확장성 : 여러 서버에 요청 분산 가능
        - 신뢰성 : 요청들이 독립적 → 한 요청의 실패가 다른요청에 영향 미치지 X
        - 자원절약 : 서버에 상태 저장 x → 서버의 메모리, 저장 공간 절약

<aside>
💡

Cookie, Session 등으로 stateless 특성 보완

</aside>

## Cookie

> 웹 서버에서 정보를 생성해서 클라이언트(웹 브라우저)에 보관하는 데이터
> 

### Cookie의 주요 property

- name을 제외한 property는 setter/getter로 접근 가능
- `name` : 쿠키를 만들 때 전달하는 쿠키의 이름으로 각각의 쿠키를 구별하는 유일한 값
    - 동일한 이름의 쿠키는 기존 쿠키 덯어씀
- `value` : 쿠키의 값으로 쿠키 생성시 전달하거나 setValue() 메서드를 통해 설정 가능
- `domain` : 지정 도메인과 그 하위 도메인에서 쿠키를 공유하고자 할 때 사용
- `path` : 쿠키가 유효한 경로
    - path가 설정된 하위 경로에서만 쿠키 전송
    - 경로 미 설정 시: context root 설정
        - 동일 도메인의 다른 애플리케이션까지 접근.. 주의!
            - localhost:8080/mvc, [localhost:8080/mvc2](http://localhost:8080/mvc2) → 같은 쿠키 보유
- `maxAge` : 쿠키의 유효 기간
    - 양수 : 초단위로 해당 시간까지 쿠키 존재
    - 음수 or 미 지정 : 세션 쿠키로 브라우저 종료 등으로 세션 종료 시 폐기
    - 0 : 브라우저에 도착하는 즉시 폐기 ( 쿠키는 특별히 삭제 메서드가 없고 유효기간이0 인 쿠키로 덮어씀)
- `secure` : HTTPS에서만 전송 허용
- `httpOnly` : JavaScript에서 접근 불가 설정

### Cookie의 생성과 사용

![image.png](attachment:c733effb-7cdb-4bc0-a054-d494db116630:image.png)

---

# Session

![image.png](attachment:b0281353-a618-4b93-a9f0-a781dbefb4a6:image.png)

- 서버에 클라이언트의 상태값을 저장
- 하나의 브라우저 당 하나의 세션 성립
- 쿠키는 브라우저 닫아도유지, 세션은 브라우저 닫으면 종료
- 세션이 동작하기 위해서는 JSESSIONID라는이름의 쿠키필요
    - JSESSIONID는 서버의 세션 공간에 들어가기 위한 키!

### HttpSession

- 주요 메서드
    - `removeAttribute(String name)` : 하나만 딸랑 지우는 거
    - `invalidate()` : 세션 자체 삭제 ( 무효화, logout처리)
- 유효기간 주요 메서드
    - 세션은 `getLastAccessedTime()` (세션 마지막 접근 시)부터 `geMaxInactiveInterval()`(최대세션 유효 기간)까지 유효

![image.png](attachment:2be1de9f-88d9-441c-9b69-72bb264db313:image.png)

### 유효시간 설정

- WAS의 web.xml에 container 차원의 session-timeout 설정
    - 설정 시간은 분 단위
