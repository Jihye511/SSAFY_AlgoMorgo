# ✅Framework

> 비즈니스 로직이 빠진 뼈대만 갖춰진 반제품 형태의 어플리케이션
> 
- 개발자는 좀 더 비즈니스 로직에 집중

<aside>
💡

SPRING + 설정 자동화, 단위 테스트 강화 등등 = SPRING BOOT(DataSource연결, WAS 설정)

</aside>

### **✏스프링 프로젝트 종류**

| **프로젝트** | **설명** |
| --- | --- |
| Spring Data | Data 연동을 위한 API를 제공, RDBMS와 NoSQL과 연동을 적은양의 코드로 처리 가능 |
| Spring Cloud | 마이크로 서비스 기반의 아키텍처에서 사용되는 모든 검증된 패턴을 구현 |
| Spring Security | 인증과 허가에 대한 기반 프레임워크를 제공하여 보안을 간단한 설정과 코드로 구현 가능 |
| Spring Batch | 데이터 처리, 흐름제어, 실패 제처리 등 배치 처리 App이 필요로 하는 기능을 제공 |
| Spring Integration | 시스템 간 연동을 위한 메시징 프레임워크를 제공 |
| Spring Social | 소셜 네트워크 연동을 위한 기능을 제공 |
| Spring Session | 사용자의 세션 정보를 관리하기 위한 API 및 구현을 제공 |
| Spring Rest Docs | RESTful 서비스를 자동 문서화 기능 제공 |
| Spring HATEOAS | Spring MVC로 작업할때 HATEOAS(일명 헤이티오스) 원칙을 따르는 REST 표현을 쉽게 생성할 수 있도록 API 기능 제공 |
| Spring Framework | Java 기반 어플리케이션 개발을 지원하는 오픈소스 어플리케이션 프레임워크 |
| **Spring Boot** | 기본적인 설정과 보일러 플레이트 코드(여러 곳에서 재사용 되는 코드) 작성을 최소화하고, 자동 설정과 컨벤션을 통해 개발자들이 빠르고 편리하게 어플리케이션을 개발할수 있도록 지원하는 도구 |

## **✏주요 특징**

- `DI(Dependency Injection: 의존성 주입)`
    - 의존성을 주입 받아 사용하므로 의존성이 변경되더라도 의조나는 객체는 변경될 필요가 없음
        - 코드의 유지보수성 향상!
- `AOP(Aspect Oriented Programming: 관점 지향 프로그래밍)`
    - 핵심 로직(비즈니스 로직)과 공통 관심 사항(로깅, 트랜잭션, 보안 등)을 **분리해서** 모듈화하는 프로그래밍 방식
    ⇒공통된 로직을 한 곳에 모아 처리하자
- `PSA(Portable Service Abstraction: 이식 가능한 서비스 추상화)`
    - 어렵고 복잡한 개념을 특정 환경에 종속되지 않고 쉽게 사용할 수 있게 추상화된 레이어 제공
    - JPA를 쓰건 MyBatis를 쓰건 스프링에서 T.X처리하는 방법은 동일
- `POJO(Plain Old Java Object)`
    - DI, AOP, PSA를 하기 위해 이제까지 하던 대로 그냥 평범한 객체를 만들면 됨
    - **POJO = 프레임워크에 종속되지 않은 순수 자바 객체**
    - 스프링은 POJO를 지원해서 코드가 **심플하고 유연**해짐
    - POJO를 쓰면 **의존성 최소화**, **테스트 용이성**, **가독성**이 향상됨

### 📌 요약 정리

| 항목 | 설명 |
| --- | --- |
| POJO | 프레임워크에 의존하지 않는 순수 자바 객체 |
| 스프링부트의 POJO 지원 | POJO 스타일을 **강제하지 않고 자유롭게 활용할 수 있게 함** |
| 장점 | 단위 테스트 용이, 프레임워크 교체 쉬움, 재사용성 높음 |

---

## ✅logging

### logging의 필요성

- 디버깅
- 장기간 동작하는 시스템 상태에 대한 기록

![image](https://github.com/user-attachments/assets/858f1aa9-5d1f-4d54-a00c-fe3f4bb19d6a)


- 특징
    - 심각도에 따라 trace < debug< info< warn< error 다섯 단계로 로그 구분
    - 사용자 설정에 따라 확인할 로그 레벨 결정
    - trace - 개발과정
    - info - 운영과정

---

## ✅junit

- java 코드의 단위 테스트 자동화를 위한 프레임워크

```jsx

	<!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api -->
	<dependency>
	    <groupId>org.junit.jupiter</groupId>
	    <artifactId>junit-jupiter-api</artifactId>
	    <version>5.12.2</version>
	    <scope>test</scope>
	</dependency>
  </dependencies>
</project>
```

- scope : 어디에서만 쓸 수 있는지

![image](https://github.com/user-attachments/assets/dfbc5701-5538-4a4b-b103-7b6252ffb9f0)


![image](https://github.com/user-attachments/assets/19902e47-d41b-4d8b-a8aa-50e8272500ee)

- beforeall은 static !!


![image](https://github.com/user-attachments/assets/67b800a7-035c-41be-ad2f-0629c9dce470)


- 기본적인 단정문들
    - equals vs same : equals 또는 == 에 대한 검증
        

        ![image](https://github.com/user-attachments/assets/9b302d02-986c-4a00-b8a1-7ce532d2e67e)
      
    - 배열의 내용 검증 : 배열으 ㅣ경우 equals 가 재정의 되지 않기 때문에 별도의 내용 비교 메서드 제공
        

        ![image](https://github.com/user-attachments/assets/c2fefaa2-5579-4695-9343-f2a4b0de36f7)

        
    - 예외에 대한 검증 : 특정 상황에서 예상하는 예외가 잘 발생하는지에 대한 검증
        

        ![image](https://github.com/user-attachments/assets/297ca7be-ff79-4aa9-8122-360e79949f5a)

        
    
    - 여러 assertions들을 함께 그룹핑해서 검증 : grouping 하지 않으면 중간에 하나만 실패햇을 때 전체가 바로 종료
    

![image](https://github.com/user-attachments/assets/756461c8-7c1c-4632-8596-6c15a1a12c97)


- springboottest 는 통합 테스트라 지양 → datajaptest는 슬라이스 테스트 good
