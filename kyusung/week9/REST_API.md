API : application programming interface

HTTP 프로토콜 기반으로 기능을 제공

### REST API : Representational State Transfer

- 어따 쓰지? 에 대한 답을 줬다.

get    post   put   fetch   delete 

특징 : 

- 자원 중심 : 모든 것은 자원으로 표현되며 각 자원은 고유한 URI를 갖는다.
- HTTP 메서드 활용 : 요청을 위해 HTTP의 메서드들을 사용
- 자원 푷련 : 자원은 JSON XML을 사용
- 무상태 : stateless, 각 요청은 이전 요청과 독립적, 서버는 클라이언트의 상태를 저장하지 않는다.

![image](https://github.com/user-attachments/assets/20937e93-5248-4156-84b6-6ee8b15b2f66)

- REST의 URL는 자원만 나타내기 때문에 동작을 알 수 없다.
- 응답 형식 변화 : HTML → JSON , XML 등 표준화된 형식
- 클라이언트 - 서버 : 혼합 → 명확히 역할 분리
- 확장성 : 높아졌다. (세션등의 상태를 관리하지 않기 때문에)

### URL 작성 권장 사항

- 자원 중심의 UR 사용 : URL == 자원, 명사 형태로 작성
- HTTP 메서드 사용 : url = 자원 위치, HTTP 메서드로 작업 의미 전달
- 계층 구조 반영 : 자원의 관계를 URL에 반영해 계층 구조 표현
- 목록과 개발 자원 구분
- 버전 관리 : API 버전에 URL 포함, 변경 사항에 유연히 대응
- 소문자 사용, 단어간 구분에는 -, _ 을 사용
- 쿼리 스트링 활용 : 필터링, 정렬 등 추가적 정보 를 전달할때 사용

![image](https://github.com/user-attachments/assets/41749e3f-61d8-4817-a620-c13cf56928c5)

### 예시

![image](https://github.com/user-attachments/assets/84970e64-0602-4274-ab8b-b526b451c9e0)

123 : mno (member number)

### 관련 Annotation

@PathVarivable

- URl 상의 변수를 처리하기 위한 annotation
- @RequestParam 처럼 자동 형변환 지원

![image](https://github.com/user-attachments/assets/743d7433-541e-41d3-bda3-2e9ba7795f94)

- ${} 삭제 (오타)

@ResponseBody

- 일반 Controller에서 REST 서비스를 위해 사용
- 뷰를 연동하지 않고 데이터만 전송
- MessageConverter(잭슨 데이터 바인드)가 데이터를 JSON등으로 가공해서 전달

![image](https://github.com/user-attachments/assets/5141e089-0b50-4165-8d3c-5436d9dfa989)

@RestController

- Controller & ResponseBody
- 모든 메서드 반환이 뷰가 아닌 데이터, 모든 요청 처리 메서드가 @ResponseBody를 갖는다.

@RequestBody 

- body로 전송된 JSON 데이터를 객체로 변환
- 파라미터를 톨해 전달되는 데이터를 처리하는 @ModelAttribute와 유사 역할
    - 파라미터가 아닌 JSON일때 (폼 데이터가 아닌.. )

![image](https://github.com/user-attachments/assets/230d5d50-1c66-4ad9-a248-c85861bbcf58)

# Swagger

: REST API에 대한 자동 문서 생성, 관리
