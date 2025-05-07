## `let`

### 설명:

- **블록 스코프**: `{}` 내부에서만 유효
- **재선언 불가**: 같은 스코프 내에서 같은 이름으로 중복 선언 불가능
- **호이스팅은 됨**: 하지만 **TDZ (Temporal Dead Zone)** 때문에 초기화 전 접근 시 오류 발생

### 예시:

```
js
복사편집
let x = 10;
if (true) {
  let x = 20; // 블록 스코프 내부
  console.log(x); // 20
}
console.log(x); // 10

```

---

## `const`

### 설명:

- `let`과 동일하게 **블록 스코프**
- **상수 선언**: 재할당 불가 (하지만 **객체의 속성은 수정 가능**)
- 선언 시 반드시 초기화해야 함

### 예시:

```
js
복사편집
const name = "chatgpt";
// name = "gpt"; // ❌ TypeError

const user = { age: 20 };
user.age = 30; // ✅ 가능 (객체 속성은 변경 가능)

```

---

## 화살표 함수 (`arrow function`)

### 설명:

- `function` 키워드보다 짧은 문법
- **자신만의 `this`를 가지지 않음** → 주변 스코프의 `this`를 그대로 사용
- **JS에서 this는 함수를 실행시키는 것을 가리킴**

### 예시:

```
js
복사편집
const add = (a, b) => a + b;

const user = {
  name: "chatgpt",
  greet: () => {
    console.log(`Hi, ${this.name}`); // ❌ undefined (this는 바인딩되지 않음)
  },
};

```

---

## 강화된 객체 표현

### 🔸 1. **Property Shorthand (프로퍼티 축약)**

- 객체 속성과 변수 이름이 같을 때 생략 가능

```
js
복사편집
const name = "chatgpt";
const age = 3;

const user = { name, age }; // name: name, age: age

```

---

### 🔸 2. **Concise Method (간결한 메서드 선언)**

- 객체 메서드를 짧게 선언할 수 있음

```
js
복사편집
const user = {
  greet() {
    console.log("Hello!");
  }
};

```

---

## Spread Operator (`...`)

### 설명:

- 배열이나 객체를 **펼쳐서 복사하거나 병합**할 수 있음

### 예시:

```
js
복사편집
const arr1 = [1, 2];
const arr2 = [...arr1, 3]; // [1, 2, 3]

const obj1 = { a: 1 };
const obj2 = { ...obj1, b: 2 }; // { a: 1, b: 2 }

```

---

## Destructuring vs Structuring

### 🔸 **Destructuring (구조 분해 할당)**

객체나 배열에서 **필요한 값만 꺼내 변수에 할당**.

```
js
복사편집
const user = { name: "chatgpt", age: 3 };
const { name, age } = user;
console.log(name); // chatgpt

const [a, b] = [1, 2];
console.log(b); // 2

```

---

### 🔸 **Structuring** (이건 공식 용어는 아니지만, 보통 반대 개념으로 쓰임)

여러 개의 값을 **하나의 객체나 배열로 묶는 행위**.

```
js
복사편집
const name = "chatgpt";
const age = 3;
const user = { name, age }; // 이것도 일종의 구조화(structuring)

```

### Proxy

- 메서드의 기본적인 동작을 가로채서 추가적인 작업을 수행하거나 대체하는 행위/객체
- Vue 3에서는 반응성을 처리하기 위해 Proxy사용
- handler : trap를 가지는 placeholder 객체
- Trap : target 객체의 property에 접근하기 위한 set/get등 메서드들로 내부 메서드와 연결됨
    - 관련 동작 재정의를 위한 확장 포인트
    
    ![image](https://github.com/user-attachments/assets/c3935328-5712-4404-ba51-f62c7cde7bc9)

