# Framework(Spring) : Rest API
---

## ▶️ REST API

### REST(Representational State Transfer) API

- Representational : 자원의 표현으로 JSON, XML 등의 형식으로 표현됨
- State : 애플리케이션의 상태
- Transfer : 네트워크를 통해 상태를 전송

### 기존 방식과 REST 방식의 차이

![image (25)](https://github.com/user-attachments/assets/5211ede9-7753-4681-907c-582001b32ec3)

### REST API를 위한 Annotatioin

- @PathVariable
    - URL상의 변수를 처리하기 위한 annotation
    - @RequestParam 처럼 자동 형 변환 등 지원
- @ResponseBody
- @RestController =  @ResponseBody + @Controller
- @RequestBody ~ @ModelAttribute

## ▶️ Swagger

### Swagger란?

- 개발한 REST API에 대한 자동 문서 생성/관리 시스템
- API문서화 : Annotation을 기반으로 자동으로 API문서 생성
- @OpenAPIDefinition

```java
@OpenAPIDefinition(
info = @Info(
title = "My API",
version = "v1",
description = "My API에 대한 설명"
),
servers = {
@Server(url = "[http://localhost:8080](http://localhost:8080/)")
}
)
```

## ▶️ RestTemplate

### RestClient프로그램

### cross-domain request 처리

- SOP(Same origin Policy : 동일 근원 정책)
    - javaScript단에서 Ajax 사용 시 사용 문서와 동일한 Origin으로만 데이터 전송 허용
        
        ![image (26)](https://github.com/user-attachments/assets/e5471672-3adf-48d9-85b4-d63413613a6a)

    

### 해결방법

- @CrossOrigin : 컨트롤러 별 설정
- WebMvcConfigurer를 통한 전역 설정

```java
public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/v1/**").allowedMethods("GET", "POST", "DELETE", "PUT", "HEAD", "PATCH");
	}
```

### RestTemplate

- Controller에서 REST API를 호출하기 위한 temlate

```java
@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
```
