# Framework(Spring) : Interceptor
---

## ▶️ Controller를 @Controller

### 주요 변경 사항

### ControllerHelper

- inplements ControllerHelper
    - setupCookie만 사용
    
    ```java
    
    public class MemberController implements ControllerHelper {
    
    setupCookie("remember-me", "bye", 0, null, response);
    
    }
    ```
    

### handler method

- HttpServletRequest 사용 지양 → Model과 @RequestParam, @ModelAttribute 적극 사용
- HttpServletRequest 사용 → 쿠키 생성 시
- ServletException, IOException 처리 제거
- action 값 경로로 변경
    
    ```java
    
    "/auth?action=member-detail&email="+email;
    
    "/auth/member-detail?email=" +email;
    ```
    
- jsp 역시 변경
    
    ```java
    
    <a href = '${root }/auth?action=member-detail&email=1' class="mx3">멤버목록</a>
    
    <a href = '${root }/auth/member-detail?email=1' class="mx3">멤버목록</a>
    ```
    
    ![image (21)](https://github.com/user-attachments/assets/2005275a-3acb-4167-9688-3d2cba23ae53)


## ▶️ Interceptor

### Handler Interceptor 동작 형태

![image (22)](https://github.com/user-attachments/assets/a1ea732d-07de-4141-b0af-aa4300f2f284)

### WebMvcConfigurer

```java
private final SessionInterceptor si;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(si).addPathPatterns("/auth/**").excludePathPatterns("/auth/help");
	}
```

### Handler Interceptor

- Servlet Filter vs AOP vs Interceptor
    
    ![image.png](attachment:b516c2cf-84ed-439b-bd98-c29ca54a3815:image.png)
    
- Servlet Filter
    - spring과 무관하며 DispatcherServlet 호출 이전에 동작
- HandlerInterceptor
    - spring에서 관리, DispatcherServlet 이후 동작
- AOP
    - Service, DAO에 적합, HTTP 관련 작업 부적합

## ▶️ 예외처리

- 기존의 error page연결
    - web.xml을 통한 전역 처리
- StringBoot의 error page연결
    - /error → error.jsp로 forward
    
    ```java
    public class CustomErrorController extends BasicErrorController {
    
    	String viewName = switch (hs) {
    		case NOT_FOUND -> "error/404";
    		case INTERNAL_SERVER_ERROR -> "error/500";
    		default -> "error/commonerror";
    		};
    }
    ```
    

## ▶️ File Upload

### 서버 설정 → application.properties

```java
# 파일당 최대 크기
spring.servlet.multipart.max-file-size=10MB         
# 여러 파일 업로드 시 최대 크기
spring.servlet.multipart.max-request-size=50MB    
```

### Controller

```java
@PostMapping("/fileupload")
	public String fileUpload(@RequestParam MultipartFile file, @RequestParam(required = false) String desc,
			RedirectAttributes redir) {
		File localFile = new File(filePath, file.getOriginalFilename());
		try {
			file.transferTo(localFile);
			redir.addFlashAttribute("file", file.getOriginalFilename());
			redir.addFlashAttribute("desc", desc);
			redir.addFlashAttribute("alertMsg", "등록되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
```

+ 충분히 잘됨 하지만 생각해볼 것

- 자료가 DB에 올라가서 file name으로 저장되면 같은이름이 올라가면 문제
- 이런 점들 처리해야함

## ▶️ File Download

```java
	@Value("${spring.servlet.multipart.location}")
	String filePath;

	// TODO: 14-5. 특정 위치의 리소스를 서버에서 사용할 수 있도록 등록해보자.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload/**").addResourceLocations("file:" + filePath + "/");

	}
```

## ▶️ APPENDIX : Ajax 와 Base64
