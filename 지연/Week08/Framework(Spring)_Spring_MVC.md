# Framework(Spring) : Spring MVC
---

## ▶️ Spring MVC 개요

### Spring MVC

- MVC 기반(model2)의 Web Application을 작성하기 위한 Spring Framework의 하위 모듈

### Spring@MVC 구성요소

- DispatcherServlet → client의 모든 request 접수
- HandlerMapping  → 이런 request를 받았는데 어떤 handler 가능?
- HandlerAdapters  → ~~Handler에게 request  좀 처리해달라고 하셈
- ViewResolvers →  이런 뷰가 필요하니까 부탁~~
- 
![image (18)](https://github.com/user-attachments/assets/7ac12b38-bd6d-4cbb-b026-8d483653ae88)

## ▶️ @Controller

### Controller

- Handler의 한 종류로 MVC에서 Client의 요청을 받아들이는 역할을 하는 클래스
- 다수의 요청 처리 메소드 포함  → RequestMapping

```java
@Target(ElementType.TYPE)
@Component
public @interface Controller{}
```

### @RequestMapping

- 요청 처리 메서드를 작성하기 위한 annotation
    - value(=path) 속성을 이용해서 처리할 경로 지정
        - 클래스 레벨
        - 메서드 레벨

```java
@Controller
@RequestMapping("/member")
Public class MemberController{
@RequestMapping("/regist")
public String regist(){return null;}            => /member/regist
@RequestMapping
public String list(){return null;}              => /member
}
```

- 요청방식(method 지정)

```java
@RequestMapping(value="/onlyget", method = RequestMethod.GET)
@RequestMapping(value="/onlypost", method = RequestMethod.POST)

@GetMapping  =   @RequestMapping(value="/onlyget", method = RequestMethod.GET)
@PostMapping  =  @RequestMapping(value="/onlyget", method = RequestMethod.POST)
```

### 요청 처리 메서드의 동작 절차 및 역할

![image (19)](https://github.com/user-attachments/assets/e43df67a-769e-429a-8490-f531ebf0fbf9)

### MVC로 DispatcherServlet이 받은 요청을 다른 JSP에서 처리

- forward / redirect

```java
@RequestMapping("/")
public String welcome(Model model){
model.addAttribute("message", service.sayHello());
return "index";                         forward          
}

return "redirect:target";                               redirect          
return "redirect:/target";                              redirect          
return "redirect:http://www.google.com";                redirect       
return "redirect:/";                                    redirect         
```

- json = @ResponseBody사용
    
    ```java
    @GetMaping("/json")
    public @ResponseBody Map<String, Object> json(){
    return Map.of("name", "hong gil dong", "age", 10);
    }  
    ```
    

## ▶️ @Controller 클래스 단위 테스트

![image (20)](https://github.com/user-attachments/assets/db0c61d6-1fc0-411c-997d-43340b189347)

### MockMvc

### @ResponsBody 테스트( ex forward)

```java
@WebMvcTest(value = SimpleController.class) // 테스트할 대상 Controller
@Slf4j
public class SimpleControllerTest {

	@Autowired
	MockMvc mock; // 가상의 웹 환경을 위한 MockMvc 객체

	@MockitoBean // 실제 서비스를 테스트할 계획은 없다.
	SimpleService sService;

	@Test
	public void forwardTest() throws Exception {
		// TODO: 04-2. /simple/forward가 잘 동작하는지 테스트 해보세요.
		when(sService.helloMVC()).thenReturn("Hello");

		var request = get("/simple/forward");
		mock.perform(request).andExpect(status().isOk()).andExpect(view().name("mvc/simple"))
				.andExpect(model().attribute("data", "Hello"));
		// END
	}
}
```

## ▶️ Hendler 메서드의 파라미터

### Request Handling method의 parameter

- 다양한 타입의 parameter들이 순서에 상관 없이 필요한 경우 사용됨

### @RequestParam

```java
@Target(ElementType.PARAMETER)
public @interface RequestParam{ 
@AliasFor("value")
String name() defalt "";
boolean required() default true;
String defaultValue() default ValueConstants.DEFAULT_NONE
}
```

### @ModelAttribute

- 전달된 파라미터들을 DTO의 property 이름 기반으로 setter와 자동 연결해서 DTO 생성
    - “email” → setEmail(” “)

```java
	@GetMapping("/regist")
	public @ResponseBody Map<String, Object> regist(~~@RequestParam String name, @RequestParam String email,
			@RequestParam String password~~   @ModelAttribute Member member  , Model model) {
		~~Member member = new Member(name, email, password);~~
		log.debug("member: {}", member);
		~~model.addAttribute("member", member);~~
		log.debug("model: {}", model.getAttribute("member"));
		return Map.of("result", member);
	}
```

### @CookieValue

- Cokkie의 name값에 매핑된 value를 얻어올 때 용이
    - 생성은 기존과 동일
    - @RequestParam 과 마찬가지로 자동 형 변환 지

### Redirection 과 Flash Scope

- session에 저장하면 관리를 위해 계속 지워야 하는 번거로움이 있기때문에 → Redirection 사용
