# Framework(Spring) Spring Architecture와 ID
---

## ▶️ 의존성과 DI

### 빈의 컨테이너로써의 스프링과 DI

- 스프링의 빈(Bean)이란?
    - 스프링 프레임워크에 의해 생성되고 관리되는 자바 객체
- 의존 관계란?
    - 어떤 객체가 비지니스로직 처리를 위해 다른 객체에 의존하는 관계 - 객체 간 has-a 관계
- 스프링을 IOC(Inversion Of Control) 컨테이너라고 불림

### 결국 DI란..

- 객체의 의존성 즉 멤버 변수를 외부에서 주입하는 것
- 멤버 변수를 설정하는 방법은?
    1. 직접 filed에 할당
    2. 생성자를 활용
    3. setter 메서드를 이용

### Spring의 빈 관리 과정
![image (6)](https://github.com/user-attachments/assets/b1ea0be9-a580-4319-8ad6-a540b7cd8107)

![image (7)](https://github.com/user-attachments/assets/a5c360c1-390b-4146-9df8-3da097fd4363)

1. 개발자는 POJO로 빈 작성

![image (8)](https://github.com/user-attachments/assets/d3bff63a-e4af-4004-8022-faacb4c8006b)

![image (9)](https://github.com/user-attachments/assets/c1b00857-19c0-4522-b0bf-0ecd782ae740)

2. 메타 정보(빈의 생성 방법 및 관 설정 정보)를 스프링 컨테이너에게 전달

![image (10)](https://github.com/user-attachments/assets/ecffdb6f-92b9-44d9-9375-4533a751d4f3)

![image (11)](https://github.com/user-attachments/assets/40607e80-5a6a-4d0e-b85b-ba7fb268aebf)

3. 런타임에 
    - 스프링은 메타 정보를 보고 빈 객체 생성 → 싱글턴 형태로 관리
    - 빈 관계에 정보에 따라 빈 주입 처리

## ▶️ 명시적 DI

- @Configuration에서 @Bean사용

```java
	public class WasherConfig {
	@Bean
	public LWasher lWasher() {
		return new LWasher();
	}
	}
	
	public class LWasher implements Washer {

	@Override
	public void wash(String cloths) {
		// TODO Auto-generated method stub
		System.out.println("lwasher가 " + cloths + " 를 세탁한다.");
	}

}

public class WasherTest {
@Test
	public void usertest() {
		Washer washer = ctx.getBean("lWasher", Washer.class);
		washer.wash("양말");
		
	}
	}
```

## ▶️ 묵시적 DI

- 명시적 DI처럼 @Configuration에서 @Bean사용하지 않는 형태
- @Component
- 클래스 이름은 첫글자를 소문자

### @Autowired

- 빈을 주입하기 위해 사용되는 annotation

### @ComponentScan

- @Configuration이 선언된 클래스에 사용

---

### ✅ 설정 방식에 따른 DI 방법의 비교

| 항목 | **명시적 DI** | **묵시적 DI** |
| --- | --- | --- |
| **관심사 분리** | 비즈니스 로직과 빈 관리 로직을 **분리 가능**→ 의존성 주입 코드가 명확하게 보임 | 비즈니스 로직과 빈 관리 로직이 **결합**→ 전체적인 빈의 구조를 파악하기 어려움→ 하지만 개발 툴이 잘 지원함 |
| **설정 작성** | 빈 설정 코드가 별도로 관리되어야 함→ 의존성 주입 코드가 복잡해질 수 있음 | 개발자가 의존성 주입 코드를 작성하지 않아도 됨 |
| **외부 라이브러리를 빈으로 활용** | 가능 | 제한적 |

## ▶️ 빈의 생명 주기

![image.png](attachment:cfe975c6-91b4-4c5b-9843-3b47e4a06cb7:image.png)
