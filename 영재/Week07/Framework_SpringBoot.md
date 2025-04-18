### 스프링부트

- 또 다른 스프링이 아니라 스프링 애플리케이션을 개발하는 템플릿
- 설정 자동화(pom.xml)
- 단위 테스트 강화
- 체크
    - Spring Boot DevTools
    - Lombok
    - MySQL Driver
    - Spring Web

### application.properties

- application  동작에 필요한 property 설정 : [application.properties](http://application.properties) or application.yml 형태로 작성
- .properties 작성법 : property와 value는 = 로 구분
- 코드에서의 속성 활용 : @Value(”${property_name}”)으로 속성 활용
    - 필요한 타입으로 형변환 가능

@SpringBootTest

- 통합테스트(단위 테스트 X)
- slice test 권장

Bean

- bean으로 관리하는 것들 : 재사용성이 높은것들(DTO는 포함되지 않는다)
- scope 디폴트 값 : singleton
    - singleton Scope : 비지니스 로직을 재사용하기 위해 빈을 관리하는scope
    - stateless해서 개별적으로 구분될 필요가 없기 때문에 singleton 개념으로 하나만 만들어서 관리
- prototype Scope : Singleton Scope와 달리 요청 할 때 마다 매번 새로운 빈 객체 생성
    - stateful 하게 각 빈이 독립적인 상태를 유지해야 하는 상황
    - 재사용되지 않으므로 꼭 빈으로 만들어야 하는지 고민할 필요
        - 다른 빈을 주입 받거나, 빈의 라이프 사이클에 따라 작업하거나 AoP를 적용하거나
- request Scope, session Scope…

### Lombok 주의점

- @Data는 많은 코드를 부지불식 간에 만들기 때문에 생각지 않았던 문제 발생 가능
    - ToString의 순환 참조 문제
    - @ToString.Exclude 와 같이 제거 가능

---

## ✅ 주요 어노테이션 정리

| 어노테이션 | 역할 | 대상 | 비고 |
| --- | --- | --- | --- |
| `@Component` | Spring이 관리하는 기본 Bean으로 등록 | 클래스 | 가장 기본, 다른 어노테이션들이 이걸 포함하고 있음 |
| `@Service` | 서비스 계층 클래스 등록 | 클래스 | `@Component`의 확장, 가독성 향상 |
| `@Repository` | DAO/저장소 계층 클래스 등록 | 클래스 | 예외 전환 기능 포함 (예: SQLException → DataAccessException) |
| `@Controller` | MVC 컨트롤러 클래스 등록 | 클래스 | 웹 요청 처리용 Bean |

---

## ✅ 의존성 주입 관련 어노테이션

| 어노테이션 | 역할 | 대상 | 비고 |
| --- | --- | --- | --- |
| `@Autowired` | 의존성 자동 주입 | 생성자, 필드, setter | 기본 어노테이션 |
| `@RequiredArgsConstructor` | `final` 필드 기반 생성자 자동 생성 (Lombok) | 클래스 | 생성자 주입을 깔끔하게 작성할 수 있게 해줌 |
| `@AllArgsConstructor` | 모든 필드 포함한 생성자 생성 (Lombok) | 클래스 | 테스트 등에서 편리함 |
| `@NoArgsConstructor` | 기본 생성자 생성 (Lombok) | 클래스 | JPA 엔티티 등에서 필수일 수 있음 |
| `@Qualifier("이름")` | 주입 대상 명시 | 필드, 파라미터 | 같은 타입의 Bean이 여러 개 있을 때 구분 |
