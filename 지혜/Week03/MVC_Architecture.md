# **✨**MVC디자인 패턴

- 애플리케이션을 역할에 따라 Model, View, Controller
    - `Controller` : 사용자의 요청을 받고 Model과 View간의 상호 작용을 조정하는 모듈
    - `Model` : 비즈니스 로직 즉 업무를 담당하는 모듈로 필요시 DB연동(DAO)
    - `View` : 사용자와의 상호작용을 위한 UI를 담당하는 모듈

![image](https://github.com/user-attachments/assets/7845fdff-4b7d-492e-83c6-22a7fb3cc18d)


- 장점
    - 분리된 관심사로 독립적 개발 및 유지보수 용이
    - 코드의 가독성, 재사용성 향상

## Model 1 방식

- JSP ( Controller + View)  + Model

## Model 2

- MVC패턴을 따름

![image](https://github.com/user-attachments/assets/5f6d90c3-98a4-406f-9577-5a086f3ba837)


![image](https://github.com/user-attachments/assets/beed5799-d3b7-4515-9933-fecd75eed661)


# **✨**웹 컴포넌트 호출

| 구분 | `forward` (RequestDispatcher) | `sendRedirect` (HttpServletResponse) |
| --- | --- | --- |
| **이동 방식** | **서버 내부**에서 페이지 이동 | 클라이언트에게 **새로운 URL**로 이동 요청 |
| **URL 변화** | 변경되지 않음 | 변경됨 (브라우저 주소창에 새로운 URL 표시) |
| **요청 유지** | 기존 요청(request) 객체 유지 | 새로운 요청 생성 (기존 요청 정보 사라짐) |
| **속도** | 상대적으로 빠름 (서버 내부 처리) | 상대적으로 느림 (클라이언트와 다시 통신) |
| **사용 목적** | 같은 웹 애플리케이션 내에서 데이터 공유가 필요할 때 | 다른 도메인 또는 새로운 요청을 보낼 때 |
| **예제 코드** | `request.getRequestDispatcher("target.jsp").forward(request, response);` | `response.sendRedirect("target.jsp");` |
| **제한 사항** | 같은 웹 애플리케이션 내에서만 가능 | 외부 사이트(URL)로도 이동 가능 |

### 📌 **정리**

- `forward`는 서버 내에서 이동하며, 기존 요청 데이터를 유지할 수 있어 성능이 좋고 내부 이동에 적합함.
- `sendRedirect`는 클라이언트에게 새로운 URL을 요청하도록 해서, 외부 사이트 이동이나 새롭게 요청을 생성할 때 유용함.

# **✨**maven

- java 기반의 프로젝트 관리 도구로 주로 `빌드 자동화` 와 `의존성 관리`에 사용
- 주요 기능
    - 프로젝트 구조 표준화
    - 의존성 관리
    - 빌드 자동화
