## ES2015

### var를 대체하는 변수 선언 키워드

- let
    - 변수의 scope가 선언된블록으로 한정되며 동일한 이름의 변수를 재 선언할 수 없음
    - var 변수처럼 호이스팅이 발생하긴 하지만 초기화 전까지 사용할 수 없음 - 접근 시 referenceError 발생
- const
    - let 의 속성 + 불변성
![image](https://github.com/user-attachments/assets/effa078f-48e3-43f6-9823-142122873d0f)


### arrow function

![image](https://github.com/user-attachments/assets/b2c0dc78-c077-4cfb-af85-b130443fc256)


### spread operator( 스프레드 연산자)

- 기존의 객체, 배열의 값을 다른 객체나 배열로 복제하거나 옮길 때 사용

![image](https://github.com/user-attachments/assets/ebf58eb1-8181-4af7-abcd-39193192245a)


### structuring vs de-structuring

- structuring : 객체, 배열을 이용해서 여러가지 데이터를 하나의 변수에 담는 것
- de-stucturing : 하나의변수(객체 , 배열)에 담긴 여러 값을 여러 변수로 분리하는 것

### Module system

- 코드를 모듈화해서 재사용
- import / export 구문 활용

![image](https://github.com/user-attachments/assets/481e976e-d405-4c38-9575-287675f52859)


### Proxy

- 메서드으 ㅣ기본적인 동작을 가로채서 추가적인 작업을 수행하거나 대체하는 행위/ 객체
    - vue 3.x에서는 반응성을 처리하기 위해  proxy사용
- 반응성 : B의 값을 변경하면 sum도 변경된다
- handler:trpe을 가지는 placeholder 객체
- Trap : target( 비즈니스 로직을 가지고 있는 원본) 객체의 property에 접근하기 위한 set/get 등 메서드들(이미 정의됨)로 내부 메서드와 연결됨
    - 관련 동작 재정의를 위한 확장 포인트

---
