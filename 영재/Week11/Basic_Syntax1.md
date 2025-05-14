### Directive

- jsp에서 include와 같이 반응형 동작을 할 수 있게 해주는 특수 속성
- “v-”로 시작

![image](https://github.com/user-attachments/assets/be5cf561-3290-4151-8506-a2bbc109a026)


### 💡 주요 내장 디렉티브

| 디렉티브 | 설명 |
| --- | --- |
| `v-bind` | HTML 속성에 데이터를 바인딩
콜론(:)으로 생략 가능 |
| `v-model` | 양방향 데이터 바인딩 (form 요소와 함께 사용) |
| `v-if` / `v-else-if` / `v-else` | 조건부 렌더링 |
| `v-show` | 조건부 표시 (`display: none`으로 제어) |
| `v-for` | 리스트 렌더링 (`v-for="item in items"`) |
| `v-on` | 이벤트 리스너 연결 (`v-on:click="method"` 또는 `@click`) |
| `v-pre` | Vue 템플릿 문법 무시 (성능 최적화 용도) |
| `v-cloak` | 컴파일 완료 전까지 요소 숨김 처리 |
| `v-once` | 한 번만 렌더링 (반응형 바인딩 무시) |

### Template Syntax

- DOM에 Vue 객체 데이터를 선언적 바인딩할 수 있는 HTML 기반 템플릿 구문

### 👉 텍스트 보간 (Text Interpolation)

```html
html
복사편집
<span>{{ message }}</span>

```

- `message`가 `"Hello Vue!"`라면 → 결과: `<span>Hello Vue!</span>`

### 👉 HTML 보간 (`v-html` 사용)

```html
html
복사편집
<p v-html="rawHtml"></p>

```

- `rawHtml`이 `<strong>굵은 글씨</strong>`이면, 해당 HTML이 그대로 렌더링됨.

### 머스테치 안에서는 선언문 사용 불가능

### Attribute Bindings

| 형태 | 설명 |
| --- | --- |
| `v-bind:속성명="값"` | 표준 바인딩 |
| `:속성명="값"` | 축약형 |
| `v-bind="object"` | 여러 속성을 한 번에 바인딩 |
| Boolean 값 | `true/false`에 따라 속성 유무 결정 |

### 이벤트 핸들러

## 🔧 기본

```jsx
<!-- v-on 사용 -->
<button v-on:click="sayHello">클릭</button>

<!-- 축약형 -->
<button @click="sayHello">클릭</button>
```

`methods`에 정의된 `sayHello` 메서드가 버튼 클릭 시 실행됩니다.

```jsx
export default {
  methods: {
    sayHello() {
      alert('안녕하세요!');
    }
  
```

---

## 🧠 이벤트 객체 사용

이벤트 객체를 핸들러에서 받으려면 인자를 추가하면 됩니다.

```jsx
<button @click="doSomething($event)">이벤트 받기</button>

```

```jsx
methods: {
  doSomething(event) {
    console.log(event.target); // 클릭된 요소
  }
}

```

---

## 💡 인자 전달

```html

<button @click="greet('철수')">인사</button>

methods: {
  greet(name) {
    alert(`안녕하세요, ${name}님!`);
  }
}
```

---

## ⌨️ 이벤트 수식어 (Event Modifiers)

Vue에서는 자주 쓰는 이벤트 동작을 간단하게 표현할 수 있는 수식어(modifier)를 제공

| 수식어 | 설명 |
| --- | --- |
| `.stop` | `event.stopPropagation()` |
| `.prevent` | `event.preventDefault()` |
| `.capture` | 캡처링 단계에서 이벤트 핸들링 |
| `.self` | 이벤트가 자기 자신에게서 발생한 경우에만 실행 |
| `.once` | 이벤트 핸들러 1회만 실행 |
| `.passive` | 기본 동작을 막지 않겠다고 명시 (스크롤 성능 개선용) |

## 🎯 `.stop` vs `.capture` 차이

| 디렉티브 | 설명 | 작동 위치 |
| --- | --- | --- |
| `.stop` | 이벤트 전파 중지 | 버블링 중 중단 |
| `.capture` | 이벤트를 캡처링 단계에서 처리 | 캡처링 중 실행 |

### V-model

- 사용자가 `<input>`에 텍스트를 입력하면 `message` 값이 즉시 업데이트됨
- 반대로 `message` 값을 바꾸면 `<input>`에도 자동으로 반영됨
    
    👉 즉, **Vue 데이터 ↔ DOM 간의 양방향 데이터 흐름**
    

## 추가정리

- vite가 js,html,css를 사용할 수 있게 해줌
- 각각의 영역 script, template, style
- **directive 안에 들어가는 쌍따옴표 영역은 그냥 js코드 자체가 들어간다고 생각하기**
- v- 로 시작한는 것들은 가상돔에서 현실돔에서 옮겨가면서 모두 사라짐
    - 주석으로만 남음
- 어짜피 js 그래도인데 왜 굳이 vue로 하고 끌고와야 할까?????(왜 real dom에 바로 사용하지 않을까?)
    - 자바 스크립트는 서버와 통신 가능(ajax)
        - count 값만 바꾸면 UI가 **자동으로 갱신됨**. 명령형(`document.querySelector(...)`)이 아님
- ref와 reactive모두 반응성을 부여하지만 ref는 단일 값에 부여, reactive는 배열이나 객체에 부
