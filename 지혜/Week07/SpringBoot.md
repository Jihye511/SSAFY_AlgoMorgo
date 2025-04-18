# ✅Spring Boot

- 템플릿엔진
    - 웹 만들때  html 안에 데이터 동적으로 서버에 보내주는거 → 다이나믹웹
    - 우리가 배웠던건 jsp
    - 스프링부트에서는 thymeleaf

![image](https://github.com/user-attachments/assets/ee1119ef-68d8-4e10-b006-e7e67cd68669)

## **✏application.properties**

- application 동작에 필여한 property 설정 : [application.properties](http://application.properties) or application.yml 형태로 작성
- .properties 작성법 : property 와 value는 =로 구분
- 코드에서의 속성 활용 : `@Value`(”${property_name}”) 으로 속성 활용
    - 필요한 타입으로 자동 형 변환 가능

---

# ✅Spring Hrm

![image](https://github.com/user-attachments/assets/17dcafa6-b22b-4084-b04f-0a2d87b1b52b)


- 재사용하는  것들 - 재사용성 기준
- 쉽게 말해 싱글톤으로 만들려고 했던 거

### **✏**빈의 스코프

- `Singleton Scope`: 비즈니스 로직을 재사용하기 위해 빈을 관리하는  scope
- `Prototype Scope` : Singleton Scope와 달리 요청 할 때 마다 매번 새로운 빈 객체 생성
    - stateful하게 각 빈이 독립적인 상태를 유지해야 하는 경우 적합
    - 재사용되지 않으므로 꼭 빈으로 만들어야 하는지 고민할 필요
    - 스프링 빈으로써의 장점을 얻을 수 있음
        - 다른 빈을 주입 받거나, 빈의 라이프 사이클에 따라 작업하거나 AOP(빈에 대해서만 적용)를 적용하거나
- `Request Scope, Session Scope` : 목적은 `Prototype Scope` 와 유사

## ✅lombok을 활용한 DTO작성

- `@Data` 사용 주의
    - `@Data` 는 많은 코드를 부지불식 간에 만들기 때문에 생각지 않았던 문제 발생 가능 - `@ToString` 의 순환 참조 문제
    - 해결방법
    
    ```jsx
    @Data //getter, setter, tostring, equalsandhashcode, requiredargsconstructor
    public class Member {
        private int mno;
        private String name;
        private String email;
        private String password;
        private String role;
        // 주소 목록
        @ToString.Exclude
        @EqualsAndHashCode.Exclude
        private List<Address> addresses = new ArrayList<>();
    }
    ```
    
    ## **✏**DBUtil 수정
    
    - DBUtil은 재사용 가능한 컴포넌트
    - singleton으로 되어있던 코드를 `@Component` 로 바꿔보자
