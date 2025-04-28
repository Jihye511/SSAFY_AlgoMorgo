# REST API

- `Representational` : 자원의 표현으로 JSON, XML 등의 형식으로 표현됨
- `State` : 애플리케이션의 상태
- `Transfer` : 네트워크를 통해 상태를 전송함

### 주요특징

- 자원 중심 : 모든 것은 자원으로 표현되며 각 자원은 고유한 URL을 갖는다
- HTTP 메서드 활용: 요청을 위해 HTTP의 GET/ POST/ PUT/DELETE 등을 사용한다
- 자원의 표현: 자원은 JSON, XML을 사용한다
- 무상태 : 각 요청은 이전 요청고 ㅏ독립적이며 서버는 클라이언트의 상태를 저장하지 않는다

![image](https://github.com/user-attachments/assets/00aa7273-2fe3-4078-8b48-84d39a051d2c)


---

## REST API를 위한 Annotation

- `@PathVariable`
    - URL 상의 변수를 처리하기 위한 annotation
    - `@RequestParam` 처럼 자동 형 변환 등 지원

```jsx
@GetMapping("/members/mno}")
@ResponseBody
public Map<String, Object> getMember(@PathVariable int mno){}
```

- `@ResponseBody`
    - 일반 @ Controller에서 REST 서비스를 위해 사용
    - 뷰를 연동하지 않고 데이터 (문자열, JSON, XML)만 전송
    - MessageConverter(Jackson Data Bind 등)가 데이터를 json등으로 가공해서 전달
- `@ RestController`
    - @ Controller이면서 @ ResponseBody
        - Controller의 모든 메서드 반환은 뷰가 아닌 데이터 → 모든 요청 처리 메서드는 @ ResponseBody를 갖는다
- `@RequestBody`
    - body로 전송된 json 데이터를 객체로 변환
    - 파라미터를 통해 전달되는 데이터를 처리하는 `@ModelAttribute` 와 유사한 여갈
