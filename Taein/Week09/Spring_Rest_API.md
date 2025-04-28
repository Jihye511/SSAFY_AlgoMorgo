## REST (REpresentation State Transfer) API

Representational : 자원의 표현으로 JSON, XML 등의 형식으로 표현

STATE : 애플리케이션의 상태

Transfer : 네트워르르 통해 상태를 전송

## 특징

자원중심 : 모든 것은 자원으로 표현되며 각 자원은 고유한 URI를 갖는다

HTTP 메서드 활용 : 요청을 위해 HTTP의 GET/POST/PUT/DELETE 등을 사용

자원의 표현 : 자원은 JSON, XML 사용

무상태 : 각 요쳥은 이전 요청과 독립적이며 서버는 클라이언트의 상태를 저장하지 않는다

![캡처](https://github.com/user-attachments/assets/d971579e-25a8-4012-84a4-182c04edfd35)


## REST 서비스를 위한 URL 작성 권장 사항

- 자원 중심의 URL 사용 : URL은 자원을 나타내며 명사 형태로 작성
    - /members, /addresses
- HTTP 메서드 사용 : YRK 자체는 자원의 위치를 나타내며 HTTP 메서드(GET/POST/PUT/DELETE)로 작업의 의미 전달
- 계층 구조 반영 : 자원의 관계를 URL에 반영하여 계층 구조 표현
    - members/{mno}/addresses
- 목록과 개별 자원의 구분 : /members, /members/{mno}
- 버전관리 : API 버전을 URL에 포함하여 변경 사항에 유연하게 대응
    - /v1/members, /v2/members
- 소문자 사용 : 기본적으로 소문자를 사용하고 단어간 구분이 필요할 때는 하이픈(-) 또는 언더스코어(_) 사용
    - /member-profile
- 쿼리스트링 활용 : 필터링, 정렬 등 추가적인 정보는 쿼리 스트링으로 전달
    - /mebers?key=email?word=01&page=3

### @PathVariable

- URL 상의 변수 처리, @RequestParam 처럼 자동 형 변환 등 지원

### @ResponseBody

- 일반 @Controller에서 REST 서비스를 위해 사용
- 뷰를 연동하지 않고 데이터(문자열, JSON, XML)만 전송
- MessageConverter(Jackson Data Bind 등)가 데이터를 JSON등으로 가공해서 전달

### @RestController

- @Controller 이면서 @ResponseBody

### @RequestBody

- Body로 전송된 JSON 데이터를 객체로 변환
- 파라미터를 통해 전달되는 데이터를 처리하는 @ModelAttribute와 유사한 역할

# Cross-domain request 처리

- SOP (Same Origin Policy : 동일 근원 정책)
    - JS단에서 AJAX 사용 시 사용 문서와 동일한 ORigin으로만 데이터 전송 허용

same origin : Protocol, domain, port

- CORS : 서로 다른 domain 간의 리소스 공유 설정
    - @CrossOrigin : 컨트롤러 별 설정
    - WebMvcConfiguer를 통한 전역 설정

### RestTemplate

- Controller에서 REST API를 호출하기 위한 template
- HTTP 클라이언트 라이브러리를 통해 높은 수준의 API 제공
