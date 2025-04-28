REST API

- 모두가 공통적으로 사용하는 HTTP 프로토콜 기반으로 기능 제공
- Representational : 자원의 표현으로 JSON, XML 등의 형식으로 표현
- State : 애플리케이션의 상태
- Transfer : 네트워크를 통해 상태를 전송

- 닥터 로이 핑딩이 제안한 아키텍처 스타일
    - get이랑 post만 쓰니까 사용법을 제시

![image](https://github.com/user-attachments/assets/84b46a95-36c9-418a-9fbd-29546e074409)


### REST 서비스를 위한 URL 권장사항

- 자원 중심 URL
- HTTP 메서드 : get/post/put/delete
- 계층 구조 반영
- 목록과 개별 자원의 구분
- 버전관리
- 소문자 사용
- 쿼리스트링 활용 : 정렬, 필터링같은것들은 쿼리스트링을 활용

![image](https://github.com/user-attachments/assets/489e2df6-40c2-4ed1-9f27-073a77b00c5a)


### Annotation

- @PathVariable
    - URL상의 변수를 처리하기 위한 어노테이션
    - 사용자 ID계속 가져가서 쓸때
- @ResoponseBody
    - 일반 컨트롤러에서 REST서비스를 위해 사용
    - 뷰를 연동하지 않고 데이터(JSON,XML,문자열)전송
- RestController
    - @Controller and @ResponseBody
- RequestBody
    - Body로 전송된 JSON데이터를 객체로 변환
    - @ModelAttribute와 유사

### Swagger

- 개발한 REST API에 대한 자동 문서 생성/관리 시스템

### CORS

- CORS(Cross-Origin Resource Sharing)는 웹 페이지가 다른 도메인, 프로토콜, 또는 포트에서 리소스를 요청할 때 발생하는 보안 정책
- 해결 방법
    - @CrossOirgin : 컨트롤러별 설정
    - WebMvcConfigurer : 전역설정
- 내가 만든 서버가 아니라면?
    - 확장프로그램 CORS
    -
