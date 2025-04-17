# ✅의존성과 DI

> **의존성**
우리가 무언가 행동을 하기 위해 사용해야 하는 도구
> 

> **스프링 빈(Bean)
스프링 프레임워크에 의해 생성되고 관리되는 자바 객체
스프링은 빈의 생성, 의존관계 설정, 객체 관리 등 빈의 라이프 사이클을 관리**
> 
- 의존관계?
    - 어떤 객체가 비즈니스로직 처리를 위해 다른 객체에 의존하는 관계 - 객체간 has-a관계

</br>
<aside>
💡Spring은 빈의 Container!!

</aside>


![image](https://github.com/user-attachments/assets/0d015a7e-29f0-41cf-b36d-12d74a342435)


## **✏결국 DI란..**

- 객체의 의존성 즉 멤버 변수를 외부에서 주입하는 것

## **✏Spring의 빈 관리 과정**

1. POJO로 빈 작성
2. 메타 정보(빈의 생성 방법 및 관계 설정 정보)를 스프링 컨테이너에게 전달
3. 런타임에
    1. 스프링은 메타 정보를 보고 빈 객체 생성 → 싱글턴 형태로 관리
    2. 빈 관계에 정보에 따랄 빈 주입 처리

![image](https://github.com/user-attachments/assets/57e1ee9c-9a8f-4ad8-b3cb-d21b80765636)


---

# ✅명시적 DI

- 빈을 생성하고 의존성을 주입하는 코드를 별도의 파일에 명시 -”이것은 빈 입니다”
- `@ Configuration`
    - java기반으로 설정 파일을 만들기 윟나 annotation으로 class level에 선언
    
    ![image](https://github.com/user-attachments/assets/43559c6d-6154-4859-bcd1-8bd44a82f5a7)

    
- `@ Bean`
    - 빈을 선언하기 위한 annotation으로 method level에 선언하며 method의 이름은 빈의 이름
    
    ![image](https://github.com/user-attachments/assets/26331b7a-2aae-4b6e-b45e-4ccbefb53bc3)

    

![image](https://github.com/user-attachments/assets/c3cbf862-81bb-49d3-a22d-642521d24969)


## ✅묵시적 DI

- 명시적 DI 처럼 @ Configuration에서 @ Bean을 사용하지 않는 형태
- `@Component`
    - 특별한 의미를 가지지 않은 단순히 “빈의 대상” 임을 나타내는애너테이션
    - 스테리오 타입
        - 용도에 따라 미리여러 형태로 정형화 해놓은 타입
        - 내부적으로 `@Component`를 포함 - 빈으로 관리됨
    - 빈으로 사용할 각각의 클래스들이 `@Component`
    
    ![image](https://github.com/user-attachments/assets/434e472e-97fe-41ee-83dc-11776fa59670)

    
    <aside>
    💡가급적이면 스테레오타입을 쓰고 해당하는게 없으면 `@Component`
    
    </aside>
    
- 기본 빈의 이름
    - 클래스 이름이 Pascal case인 경우는 첫 글자를 소문자로 한 camel case, 그렇지 않은 경우는 클래스 이름 그대로 사용

- `@Autowired`
    - 빈을 주입하기 위해 사용되는 annotation
    - 타입 기반으로 빈 자동 주입 - 해당 빈은 반드시 하나만 존재 해야 함
        - 타입 충돌이 발생할 경우: 이름 기반으 ㅣ조건을 추가하기 위해 `Qualifier` 활용

## **✏주입 방식에 따른 DI 방법의 비교**

- 생성자 주입 : 가장 권장
    - 빈의 모든 의존성이 반드시 필요하다는 것을 명시적으로 보여줌
    - 혹시나 발생할 수 있는 빈의 순환 의존성 문제를 빈생성 시점에 즉시 발견 가능
    - 많은 경우 field를 blank final로 선언하고 생성자 주입을 사용하는데 lombok의 `@RequiredArgsConstructor` 활용
        - 빈의 불변성 확보
- setter 주입 : 선택적인 의존성을 가진 빈의 주입에 적합
- field 주입 : 비추

<aside>
💡묵시적 DI를 기본으로 하고 명시적 DI를 보조적으로 사용

</aside>
