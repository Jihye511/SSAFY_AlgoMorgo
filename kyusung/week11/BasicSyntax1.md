# Directive

: v- 라는 접두서가 있는 특수 속성

JSP에서도 배웠었음 (page directive)

![image](https://github.com/user-attachments/assets/9b7d62b4-b3be-4ef3-900b-3be6e195b255)

이름  : 인자 . 제한자 = “ 값 ”

빌트인 directives

![image](https://github.com/user-attachments/assets/7e3a7f8b-fab9-47b0-bcca-08ba101edd84)

Directive - Arguments 

- 일부 디렉티브는 뒤에 콜론 “ :“ 으로 표시되는 인자를 사용가능
- 아래 예시는 href는 HTML a요소의 속성 값을 myUrl값에 바인딩하도록 하는 c-bind의 인자

![image](https://github.com/user-attachments/assets/eb75cd53-383e-4cfe-badd-e0328e120a14)

Modifier 

- . (dot) 으로 표시되는 특수 접미사 , directive가 특별한 방식으로 바인딩되어야 함
- 아래 예시는 prevent에서 event.preventDefault()를 호출하도록 v-on에 지시하는 modifier

![image](https://github.com/user-attachments/assets/79433551-6daf-47f1-85c1-6b43a811556a)

Value

![image](https://github.com/user-attachments/assets/d362f644-d155-465b-b877-cb9a2ade481c)

# Template Syntax (화면 html)

- DOM에 Vue 객체 데이터를 선언적으로 바인딩할 수 있는 HMTL 기반 템플릿 구문 사용
1. Text Interplation

![image](https://github.com/user-attachments/assets/d20ea3bb-2353-4d6d-a36b-c2b1a7095d8f)

1. Raw HTML

![{{}} 는 innerHTML과 동치이다.](![image](https://github.com/user-attachments/assets/c803f91d-6c3e-4f75-a513-cac68bc2b3b4)


{{}} 는 innerHTML과 동치이다.

1. Attribute Bindings

![image](https://github.com/user-attachments/assets/bcaa3bf4-678f-4b17-9a7d-ca5ec5677975)

1. JS Expressions

![: (콜론)만 되어 있으면 v- 의 축약형임.]
![image](https://github.com/user-attachments/assets/2d2df35d-cf53-4404-9d3c-9ae6ca5702d9)

: (콜론)만 되어 있으면 v- 의 축약형임.

프로젝트 설치 후 : npm install

실행 : npm run dev

dev tool : alt + shift + D

Expression s 주의 사항

- 각 바인딩에서 하나의 단일 표현식만 포함
    - 표현식은 값으로 평가할  수 있는 코드 조각 (retrun 뒤에 있는 코드)

![image](https://github.com/user-attachments/assets/f52bd8ac-e42b-47ab-80bb-7d331c883cd2)

# View Style Guide

![image](https://github.com/user-attachments/assets/dd5ce0fd-89bd-471b-aab2-c6cfa74e7ee2)

![image](https://github.com/user-attachments/assets/251a10a8-33bb-46c1-8095-30b9a9111d0a)

# Dynamically data binding

v- bind : 하나 이상의 속성 또는 컴포넌트 데이터를 표현식에 동적으로 바인딩

1. Attribute Bindings
2. Class and Style bindings

### Attribute bindings

![image](https://github.com/user-attachments/assets/09c48b38-7862-4754-94f8-374efe2dba7b)

v-bind:src ~~  == :src

![image](https://github.com/user-attachments/assets/0f11c552-1313-4783-8f96-21dbb706462d)

![image](https://github.com/user-attachments/assets/1d88319d-d151-49f0-a9f3-773ef3433aaa)

예시

![image](https://github.com/user-attachments/assets/21b730e8-de32-4c6a-bfea-5076aeded056)

![image](https://github.com/user-attachments/assets/881f79e3-75da-40bf-94b7-c02d57008adc)

### Class aand Style Bindings

![image](https://github.com/user-attachments/assets/6a53501c-7e24-449d-acec-e5edd4491036)

![image](https://github.com/user-attachments/assets/0d56d98c-387d-4d56-99ee-addfff7f3e4c)

# Event Handling

v-on : DOM 요소에 이벤트 리스너 연결, 수신

![image](https://github.com/user-attachments/assets/a898e5f7-044c-4562-9a89-fd3547f02ff8)

@ : 이벤트 약어

- inline 핸들러

![image](https://github.com/user-attachments/assets/643b69b2-95b0-45e2-9642-51f5da23885f)

- 메서드 핸들러

![myCallback 이벤트 객체가 작동으로 삽입]
![image](https://github.com/user-attachments/assets/32726192-2248-4a7a-9511-f77096e33719)


myCallback 이벤트 객체가 작동으로 삽입

![image](https://github.com/user-attachments/assets/829200d9-e77a-4c9d-8831-5e790d1f0a03)

Inline Handler에서의 메서드 호출

- 메서드 이름에 직접 바인딩하는 대신 Inline Handlers에서메서드 호출 가능
- 기본 이벤트 대신 사용자 지정 인자를 전달할 수 있다.

![image](https://github.com/user-attachments/assets/7597e0ae-ae58-46db-bd34-60dfb7151d8b)

![image](https://github.com/user-attachments/assets/a139b01a-fc28-4ee3-b889-a7ec6937ba92)

Event Modifiers

- Vue는 v-on에 대한 Event Modifier 제공 - evnent.preventDefault()와 같은 구문을 메서드에서 작성하지 않도록 함
- stop , prevent , self, captue, once 등 다양한 modifirs를 제공
- 메서드는 DOM 이벤트에 대한 처리보다 데이터에 관한 논리 작성에 집중

![image](https://github.com/user-attachments/assets/b035b360-5934-4a35-8a48-edf257eaad58)

Key Modifiers 

- Vue는 키보드 이벤트 수신시 특정 키에 관한 별도 modifiers를 사용할 수 있음
- 예시 : Key가 Enter일 때만 onSubmit 이벤트 호출하기

<input @keyup.enter = “onSubmit”>

DOM 요소에 직접 접근

- Vue는 기본적으로 DOM 요소에 직접 접근 지양
    - 반응형 시스템으로 가상 DOM 수정 시 실제 DOM이 수정되도록 유도
- 이벤트의 포커스 관리 등 상황에서 특정 요소를 지정해야할 때는 ref 활용
    - ref는 실제 DOM에 접근하지만, vue 관리 하에 처리

![image](https://github.com/user-attachments/assets/2b0f127e-b2f7-438f-a44c-32bd5c798d7a)

# Form Input Bindings

- form을 처리할 때 사용자가 input에 입력하는 값을 실시간으로 JavaScript 상태에 동기화해야하는 경우 (양방향 바인딩)
- 양방향 바인딩 방법
    1. v-bind와 v-on을 함께 사용
    2. v-model 사용 (내부적으로 1의 방법)

### v-bind와 v-on을 함께 사용

1. v-bind를 사용 , input 요소의 value 속성 값을 입력 값으로 사용
2. v-on을 사용하여 input 이벤트가 발생할 때마다 input 요소의 value 값을 별도 반응형 변수에 저장하는 핸들러 호출

![image](https://github.com/user-attachments/assets/fc659632-bdec-48f1-be69-5b0a32116b9e)

### v-model

: form input 요소 또는 컴포넌트에서 양방향 바인딩을 만든다.

- v-model을 사용하여 사용자 입력 데이터와 반응형 변수를 실시간 동기화

![image](https://github.com/user-attachments/assets/cbff2938-4c6c-4e05-af42-20f98b60fdf5)

- v-model을 사용하여 사용자 입력 데이터와 반응형 변수를 실시간 동기화

![image](https://github.com/user-attachments/assets/585ef648-10df-4600-9bb3-1d9b3da5a870)
