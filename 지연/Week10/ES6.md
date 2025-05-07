# Framework:(Vue.js) : ES6 (JS New Features)

---

# ▶️ ES 2015

### arrow function

- 간결한 함수 문법

```jsx
-> 기존 함수 표현식
const add = function(a,b){
return a+b;
};

----------------------------------------------

-> 화살표 함수 
const addArrow = (a,b) => a+b;
```

- lexical한 this bining
    - 기존 함수 표현식의 `this`는 호출 시점에 결정되고, **런타임에 결정됨** (lexical이 아님).
    - 화살표 함수의 `this`는 정의된 위치의 상위 스코프를 따른다 , `this`는 **lexical 바인딩됨**,

### 강화된 객체 표현

```jsx
let language = "javascript";

let oldStyle = {
  language: language,
  sayLang: function () {
    console.log(`사용 언어는 ${this.language} 이다.`);
  },
};

let newStyle = {
  language,
  sayLang() {
    console.log(`사용 언어는 ${this.language} 이다.`);
  },
};
```

### spread operator(스프레드 연산자)

- 기존의 객체, 배열의 값을 다른 객체나 배열로 복제하거나 옮길 때 사용
- 언제나 값을 복사
    - 참조형은 주소 “값”

```jsx
// 원본 객체
let normal = {
  name: "hong",
  age: 30,
};

// oldHero는 normal의 일부 속성을 수동으로 복사
let oldHero = {
  name: normal.name,
  age: normal.age,
  nick: "욜도국왕",
};

// newHero는 oldHero 전체를 spread로 복사하고, wish 속성을 추가
let newHero = {
  ...oldHero,
  wish: `아버지를 아버지라고 부르고 싶어함.`,
};
```

### Module system

- 코드를 모율화해서 재사용
- import/ export 구문 활용

![image.png](attachment:5b1cd491-0a23-46d9-a141-92dd68da1453:image.png)

### Import

```jsx
script export_default.js
export default {
  sayHello: {
    kor(name) {
      console.log(`안녕 ${name}`);
    },
    eng(name) {
      console.log(`Hello ${name}`);
    },
  },
  sayHi() {
    console.log("Hi");
  },
};

-----------------------------------------------------------

import { name, add, addr as myaddr, age } from "./export_multi.js";
import greeting from "./export_default.js";
```

### promise

### Proxy

- 메서드이 기본적인 동작을 가로채서 추가적인 작업을 수행하거나 대체하는 행위/객체
- 반응성(Reactivity) :  B의 값을 변경하면 sum도 변경된다.
    
    ![image.png](attachment:5de082da-6963-4d59-be27-9a15f1cf6014:image.png)
    

# ▶️ ES 이후

### ES 2016

- 지수 연산자
    - ** 거듭연산

### ES 2017

### ES 2020

- Optional Chaining
    - 값이 있을 경우만 chaining
        
        ```jsx
        let users = [{name: "hong gil dong", email:{ id : "hong", domain: "def.net"}},
        {name: "hang gil san"}];
        
        users.forEach((user)=>{
        console.log(user.name, user.email.id, user.email.domain);
        });
        ```
        
    - 대책1 : 3항 연산자
        
        ```jsx
        users.forEach((user)=>{
        console.log(user.name, user.email.? user.email.id : undefined , 
        user.email ? user.email.domain : "undefined");
        });
        ```
        
    - 대책2 : Optional Chaining
        
        ```jsx
        users.forEach((user)=>{
        console.log(user.name, user.email?.id, user.email?.domain);
        });
        ```
        

- Null coalescing operator
    - || : falsy한 상황(null, undefined, false, 0, “”) 에서 무조건 오른쪽 피 연산자 반환
    - ?? : null 또는 undefined인 경우에만 오른쪽 피 연산자 반환하며 0, false, “” 와 같은 falsy 값은 왼쪽 피연산자 반환
