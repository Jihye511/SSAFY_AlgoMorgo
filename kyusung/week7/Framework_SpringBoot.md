# SpringBoot

: Stand Alone  어플리케이션

→ WAS 없이 혼자 실행할 수 있는 어플리케이션 (WAS를 내장하고 있기 때문에)

→ 써드파티 자동설정

→ 완제품 정도로 만들어놨다 뭘 ? :

→ XML 도 필요없다 ( 톰캣에서 쓰던 놈 , 어제 )

SpringBoot : 스프링 어플리케이션을 개발하는 일종의 템플릿

- 설정 자동화를 통해 스프링 개발 속도 향상
- 단위테스트 강화로 프로젝트 안정성 강화

## Spring Starter 프로젝트 초기 설정

### JAR (Java Archive)

- JAVA 어플리케이션이 동작할 수 있도록 자바 프로젝트를 압축한 파일
- Class (JAVA리소스, 속성 파일), 라이브러리 파일을 포함함
- JRE(JAVA Runtime Environment)만 있어도 실행 가능함 (java -jar 프로젝트네임.jar)

### WAR (Web Application Archive)

- Servlet / Jsp 컨테이너에 배치할 수 있는 웹 애플리케이션(Web Application) 압축파일 포맷
- 웹 관련 자원을 포함함 (JSP, Servlet, JAR, Class, XML, HTML, Javascript)
- 사전 정의된 구조를 사용함 (WEB-INF, META-INF)
- 별도의 웹서버(WEB) or 웹 컨테이너(WAS) 필요
- 즉, JAR파일의 일종으로 웹 애플리케이션 전체를 패키징 하기 위한 JAR 파일이다.

추가할 의존성 : DevTools, lombok, Spring web,  MySQL Driver

![image](https://github.com/user-attachments/assets/aa1c41d1-14a3-427c-8b02-f72360aca76b)

- 구조

![image](https://github.com/user-attachments/assets/6c317fb5-d14a-49ae-b18f-0b8b4149332b)

static (정적 웹 리소스) : html css js

application.properties : 환경설정 파일 (스프링 특성 중 XML 사라짐에 관련)

## application.properties

- application 동작에 필요한 property 설정 : application.properties / application.yml 형태로 작성
- 작성법이 달라도 내부에선 동일하게 동작
- .property 작성 : property_name = property_value
- 코드에서 속성 활용 : @Value(”${property_name}”)으로 활용
    - 필요한 타입으로 자동 형변환 가능
    - 타입 기반 의존성 주입

## pom.xml

- 버전 내용이 따로 없는 이유?

: 의존성에 대한 버전 등은 미리 spring starter에서 설정을 해두기 때문에 따로 버전 관리를 할 필요가 없다.

단, 미리 테스트 하지 않은 (관리하지 않는) 버전은 명시해야한다.

## @SpringBootApplication

:  애플리케이션 시작점인 클래스에 선언하는 애너테이션 (보통 pjtNameApplication)

- @SpringBootConfiguration, @EnableAutoConfiguration @Componentsacn

가 내부적으로 존재

- @SpringBootConfiguration
    - Configuration이기 때문에 Bean을 생성할 수는 있지만 일반적으로 전용 파일 생성 권장
- @EnableAutoConfiguration
    - 의존성에 근거해 자동 설정 지원
- @Componentscan
    - 묵시적 빈 형태로  등록된 Bean을 scan
    - @SpringBootApplication이 선언된 클래스의 하위 패키지를 항시 scan한다.

## @SpringBootTest

: 스프링을 위한 통합 테스트

→ 사용마다 스프링이 사용되므로 무거워질 수 있음, 단위 테스트에 부적합

- slice Test를 권장하지만 좀 귀찮음 , 하지만 실무에서는 권장

# Spring Hrm

- 스프링에서 일반적인 Bean으로 관리할 대상들은 ?
    
    →  재사용성이 높은 Controller , Dao, Service, MVC 등
    

![image](https://github.com/user-attachments/assets/2db1d6b1-39c1-4ae9-ab7c-613c7bbc306c)

- Controller를 싱글톤으로 구현하진 않았었지만 TomCat에서 비슷한 방식으로 구동했었음
- 하나씩 만들던 것들을 Bean으로 등록
- DTO : 한번쓰고 버리는 종이컵 같은 녀석 - lombok을 이용해서 관리  - @Data

### 빈의 스코프

@Scope : 빈 객체가 어떤 범위까지 존재할 것인가?

- 스프링 컨테이너 가 빈 객체를 어떻게 생성하고 언제까지 관리할 것인지 결정

![image](https://github.com/user-attachments/assets/530dbe0b-2554-44b0-a8ac-a72e7b317b18)

### 스코프 별 특징

- Singleton : 비즈니스 로직 재사용 목적으로 빈을 관리하는 Scope
    - stateless해서 개별적으로 구분될 필요가 없기 때문에 하나만 만들어서 관리
    - Default 값
- Prototype : 요청마다 매번 새로운 빈 객체 생성
    - Stateful하게 각 빈이 독립적인 상태를 유지해야 하는 경우 적합
    - 재사용되지 않음 → 꼭 빈으로 만들어야 하는지 고민할 필요가 있음
    - 스프링 빈으로써의 장점을 얻을 수 있음
        - 다른 빈을 주입받거나 , 빈의 라이프 사이클에 따라 작업하거나 AOP 적용하거나 .
- Request, Session
    - 각각 웹 환경에서 사용되는 Scope , 목적은 Prototype과 유사

# lombok

![image](https://github.com/user-attachments/assets/92a181bc-5c08-4272-936f-f5753ae426ab)

private static final Logger log = … → @Slf4j로 대체

@Data 사용 시 주의 점

: 많은 코드를 만들기 떄문에 문제 발생 : @ToString의 순환 참조 문제

![image](https://github.com/user-attachments/assets/cf0013e7-a18d-4a39-84f8-23ddbc1dc2db)

ToString.Exclude 로 없앨 수 있다.

## 생성자 관리 - @Builder

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder			
// 클래스에 사용 시 AllArgsConstructor 가 필요
// 생성자 사용 시 해당 생성자에만 해당
// 메서드 기반으로 객체를 만들 수 있게 함 
public class Member {
    private int mno;
    private @NonNull String name;
    private @NonNull String email;
    private @NonNull String password;
    private String role;
    // 주소 목록
    
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default			// 계속 쓸거니까 null로 바꾸지마라
    private List<Address> addresses = new ArrayList<>();

}
```

@Builder 

: 생성자 파라미터를 이용해 빌더 패턴형식으로 객체 설정

- 클래스 레벨에 선언 시 전체 생성자 필요 (@AllArgsConstructor)
- 생성자 레벨에 선언 시 생성자의 파라미터 기반으로 빌더 구성
- Default 값 : AllArgsConstructor에 적용된 @Builder는 모든 field의 값을 디폴트 초기화

기본값을 유지하기 위해 @Builder.Default 사용

@RequiredArgsConstructor

# 수업 종료 후 보충

## Lombok

장점 : 편하다 .

단점 : 설치해야함. 가독성 .

→ 메서드가 없으니 가독성이 안좋다 ..

## 스프링 MVC

- 스프링 MVC와 스프링 웹은 다른 것

MVC : 패턴

스프링 웹 : dispatcher servlet ~ resolve 까지 필요한 새로운 개념

new 스프링 컨퍼런트
