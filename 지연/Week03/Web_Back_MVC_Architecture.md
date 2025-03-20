# Web(back) : MVC Architecture

## ▶️ MVC 디자인 패턴

### MVC 디자인 패턴

- Cntroller - 사용자의 요청을 받고  Model과 View간의 상호 작용을 조정하는 모듈
- Model - 업무(Service)를 담당하는 모듈로 필요 시 DB 연동(DAO)
- View - UI를 담당하는 모

![image.png](attachment:15710f81-15ce-4cfb-921a-1328e2d3a3fd:image.png)

### *Model 1 방식 ( Controller+View ) (Model)

- JSP가 Controller를 맡아 View 로직과 함께 수행
- 장점
    - 단순성
    - 빠른 개발
    - 직관적 : JSP 하나만 사용
- 단점
    - 유지보수 어려움
    - 확장성 제한

### Model 2 방식 ( Controller) (View ) (Model)    ——> 앞으로 싸피에서 할 방

- 엄격한 MVC 패턴
- 장점
    - 명확한 구조
    - 유지보수 용이
    - 확장성
- 단점
    - 복잡성
    - 개발 속도
    - 학습 곡선

![image.png](attachment:de09cc98-0e35-4c84-9f2e-76431d54df27:image.png)

### 웹 컴포넌트 호출

| forward | RequestDispatcher dispatcher = request.getRequestDispatcher(”/gugu-result.jsp);
dispatcher.forward(request, response); |
| --- | --- |
|  | 직접호출이 아니라 container가 호출하는 것 |
|  | 동일 웹 애플리케이션의 자원만 호출  |
|  | 2번의 호출로 URL이 변경됨 |
|  | /는 container root |
| redirect  | response.sendRedirect(”http://www.google.com”); |
|  | 현재 실행중인 페이지의 실행을 중단하고 다른 웹 자원이 
대신 호출되도록 |
|  | 서버 내부 리소스 뿐만 아니라 외부의 리소스까지 사용 가능 |
|  | 1번 호출로 URL 유지 |
|  | /는 context root |

## ▶️ maven

- Java 기반의 프로젝트 관리 도구로 주로 빌드 자동화와 의존성 관리에 사용
- 주요 기능
    - 프로젝트 구조 표준화
    - 의존성 관리
    - 빌드 자동화

