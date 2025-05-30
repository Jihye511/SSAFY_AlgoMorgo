## ES 2015

let : 변수의 scope가 선언된 블로 ({}) 으로 한정되며 변수의 hoisting 발생하지 않음, 동일한 이름의 변수 재선언 X

const : let의 속성 + 불변성

### arrow function

setTimeout( function()  ←- window가 가짐

- lexical 하다는 말은 코드가 작성된(선언된) 물리적 위치에 기반한다는

### 강화된 객체 표현

- Property shorthand : 객체의 속성 이름과 참조하려는 변수의 이름이 같을 경우는 하나만 사용
    - 대상 변수가 없을 경우 reference 오류
- concise method : 객체에 함수 정의 시 속성 이름을 함수 이름으로 사용

### Spread operator (스프레드 연산자)

- 기존의 객체, 배열의 값을 다른 객체나 배열로 복제하거니 옮길 때 사용
- 언제나 값을 복사
    - 참조형 주소 “값”

### structuring vs de-structuring

- structuring : 객체, 배열을 이용해서 여러 가지 데이터를 하나의 변수에 담는 것
- de-structuring : 하나의 변수(객체, 배열)에 담긴 여러 값을 여러 변수로 분리하는 것

### Computed Property name

- 동적으로 property 이름을 생성하는 방법
    - 속성 선인 시 []를 선언하며 내용을 실행해서 속성 이름으로 사용
        - 객체 리터럴 내에서 함수 호출 결과를 키로 사용할 때
        - 다른 객체의 속성을 키로 사용할 때

### Module system

- 코드를 모듈화해서 재사용
- import / export 구문 사용

![image.png](attachment:76fe2dee-1b22-4576-ad48-2ed2a2649c8e:image.png)

- Export
    - export 구문을 통해 모듈의 변수, 함수 클래스 등을 export 처리
    - 여러항목 export
        - export let name = “`214134134”;
    - 단일 항목 export
        - export default 구문을 사용하며 배열, 객체 등 하나의 객체만 export 하며 이름이 없음
        - export default { hello:{kor(){},eng(){},},hi(){}};

- Import
    - export 된 모듈을 다른 모듈에서 import 구문을 통해 참조
    - 여러 항목이 export 된 경우
        - as 키워드로 rename
    - 단일 항목이 export 된 경우
        - 단일 항목을 할당할 변수 이름 선언
    - html에서는 <script type=”module”> 구문 사용

### Promise

- callback hell에 대한 대책으로 비동기 작업이 성공적으로 종료된 이휴의 결과값 또는 실패 사유를 처리하기 위한 API
- pending : 실제 작업을 위한 준비단계
    - Promise 객체 생성 및 fulfilled, rejected 상황에서 호출할 handler 함수 바인딩
- fulfilled(이행) : 동작이 성공적으로 완료된 상태
- rejected(거부) : 동작이 실패한 상태

### Proxy

- 메서드의 기본적인 동작을 가로채서 추가적인 작업을 수행하거나 대체하는 행위 / 객체
    - Vue 3.x에서는 반응성을 처리하기 위해 Proxy 사용
- 반응성 : B의 값을 변경하면 sum도 변경된다.
    - 선언적인 방식으로 어떤 값에 대한 변경에 대한 제어를 수행하는 프로그래밍 방식
- Handler : trap을 가지는 placeholder 객체
- Trap : target 객체의 property에 접근하기 위한 set/get 등 메서드들(이미 정의됨)로 내부 메서드와 연결됨

![image.png](attachment:c23ab850-ea49-4c19-aaad-f8c712e2965b:image.png)

# ES 2016

지수 연산자

- ** 연산자로 거듭제곱
- 배열에 includes() 메서드 추가

### async ~ await

- then - catch의 문제접 : callback hell 다음의 then hell 유발
- async : 비동기 함수를 동기적으로 사용할 함수에 선언하며 async가 선언된 함수는 자동으로 promise가 됨
- await : 비동기 함수를 호출할 때 사용
    - 반드시 async 함수 내에서만 사용
    - fulfilled의 parameter로 전달되는 값은 return으로 처리되며 rejected는 error 발생시켜 catch에서 처리
- 전체적으로 try ~ catch ~ finally 구문을 사용

### Optional Chaining

- 값이 있을 경우만 chaining
    - let user = [{~~~
    - user.email.id
- 대책1 : 3항 연산자
    - user.email ? user.email.id
- 대책2 : optional Chaning
    - user.email?.id

### Null coalescing operator

- || : falsy 한 상황 (null, undefined, false, 0, “”)에서 무조건 오른쪽 피 연산자 반환
- ?? : null 또는 undefined인 경우에만 오른쪽 오른쪽 피 연산자 반환하며 0, false, “”와 같은 falsy 값은 왼쪽 피연산자 반환

# 강사님정리

ES6 부터는 표현방식이 달라짐 → 좀더 편하게 가능

let도 호이스팅 되지만 값이 할당 되기 전까진 참조할 수 없다

자바 this : 스택영역에 있음, final로 선언됨 거기에 그 함수가 가지고 있는 객체의 주소가 들어김

JS this : 함수 안에서 사용 가능

객체 선언 방법 → 클래스라는 키워드가 없어서 함수를 이용해 객체 생성가능

{}, new object, 함수안에 {}

함수가 객체가 될 수 있따

JS의 this

함수 안에서 사용 가능

이 함수를 일반 function으로 본다

자바의 function은 전역메소드라는게 없다

JS

객체 안에 function, 전역 function 가능

그냥 this 쓰면 함수안에 쓸 수 있다.

JS는 this 값이 바뀐다

## JS의 this는 그 함수를 실행하는 객체를 가르킴

만든 이유 : 객체지향 프로그래밍으로 JS 개발할때

1. 전역변수로 toString 만들어서 사용

toString안에는 this 사용 가능

1. 다른 메소드를 가져와 사용하면 그 함수 안에 this를 자동적으로 변환 가능

객체지향언어가 모든건 다 지원하지 않고 어디까지 지원하겠다는 한계가 있다

arrow function ( ⇒) 이거의 this는 일반 함수의 this랑 다르다

나를 가지고 있는 놈 즉 하나 더 올라감

밖{나 { 쟤 (⇒)}}

이러면 밖을 가르킴

setTineout : 내부에 있는 callback function이다

() { } : 그냥 function

() ⇒ {} : arrow function

둘의 차이점은 this가 다르다

var name = p1 → p1 = p1 + 10

즉

var name (p1) {

return p1 = p1 + 1

}

() ⇒ { }

인자 없으면 소괄호 제거 X

var a = () ⇒ {

return {id:ssafy}

}

var b = () → ({id:ssafy})

### proxy

observer 두고 계속 보고 있다가 값이 바뀌는 순간 통지

바꾼놈이 나한테 알려주는거 : signal

storage 개념

a라는 변수를 객체로 둔다

그래서 객체라는건 기능을 가지는데 set과 get을 지니는 객체로 만듬

a로 선언하기만 하면 객체로 만듬 → 프레임워크가 해줌

변수 → 객체 → 프록시 객체로 만듬 (set, get 들어있는거)

# Spring Security

시큐리티 하려면 필터를 만들어야 한다 → 내부적으로 필터가만들어짐

필터의 설정을 담당하는것 : @EnableWebSecurity()

1. 로그인 로그아웃 : SecurityFilterChain
- 요청이 오면 권한이 있는지 없는지 확인 (authorizeHttpRequests ) → 가장 중요
- csrf
- formLogin → UserDefineLoginForm
    - passwordParameter하면 나오는거
        - 페이지 출력, 로그인 과정, id, pw 넘김 → 이것만으로 로그인이 끝남 (컨트롤러까지 함)
        - id pw 넘기는건 나중에 security를 위해서이다 (없어도 됨)
        - loginPage, loginProcessingUrl(컨트롤러호출) 이 두개가 없으면 에러
        - /member/login 하면 controller의 @Postmapping(”/login”)을 대신할 수 있다
        - 그게 successHandler 부분 (db가서 확인해서 있으면 ok)
        - 없으면 fail ( 없으면 없다고 보냄)
    - userDetail, UserDetailService가 db가서 확인하는 작업
    - UserDetails는 username을 던지면 유저 정보 가져온다

기본 코드는 메모리에서 찾고

override하면 db로가서 찾도록 변경

User는 VO

UserDetail은 정보를 호출할 메소드를 가짐

- id, pw, name, roll 등

실제 쓰려면 이게 다다

시쿠리티 자체 에러면 AccessDeniedHandler

시큐리티 쓰는 이유 :

페이지 열때 마다 권한있는지 쳌 → 인터셉터의 역할인데

시큐리티는 이 부분을 해결함

Security에서는 세션이 아니고 쿠키를 이용해야 하지만

세션을 쓰긴함

우리가 안쓰긴해도 세션에 저장함

UserDetail을 저장함

—> 무슨 키값으로 저장하지? SPRING_SECURITY_CONTEXT 에 저장함

—> PRINCIPAL : USER DETAIL
