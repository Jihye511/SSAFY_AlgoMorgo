# Framework:(Vue.js) : Basic Syntax 1
---

# ▶️ Directive

### Arguments

- 일부 directive 는 directive뒤에 콜론(”:”) 으로 표시되는 인자를 사용할 수 있음
- 아래 예시는 HTML a 요소의 href 속성 값을 myUrl 값에 바인딩 하도록 하는 v-bind의 인자

```jsx
<a v-vind:href="myUrl"> Link</a>
```

### Modifiers

- “.(dot)” 로 표시되는 특수 접미사

### value

- 표현식 값이 변경될 때 DOM에 반응적으로 업데이트를 적용

# ▶️ Template Syntax

1. Text Interpolation
    
    ```jsx
    <p> Message : {{msg}}</p>
    
    * 데이터 바인딩의 가장 기본적인 형태
    * 이중 중괄호 구문(콧수염 구문)을 사용
    ```
    
2. Raw HTML
    
    ```jsx
    <div v-html = "rawHTML"></div>
    const rawHtml = ref('<span style="color:red">This should be red.</span>')
    
    * 콧수염 구문은 데이터를 일반 텍스트로 해석하기 때문에
    실제 HTML을 출력하려면 v-html을 사용해야함
    ```
    
3. Attribute Bindings
    
    ```jsx
    <div v-bind = id="dynamicId"></div>
    const dynamicId = ref('my-id')
    
    * 콧수염 구문은 HTML 속성 내에서 사용할 수 없기 때문에 v-bind를 사용
    ```
    
4. JavaScript Expressions
    
    ```jsx
    {{number+1}}
    
    * Vue 템플릿에서 JavaScript 표현식을 사용할 수 있는 위치
    1. 콧수염 구문 내부
    2 모든 directive의 속성 값("v-" 로 시작하는 특수 속성)
    ```
    

# ▶️ Vue Style Guide

![image.png](attachment:d2b26c9e-733f-420b-ada9-34c8e4faac01:image.png)

# ▶️ Dynamically data binding

### v-bind

하나 이상의 속성 또는 컴포넌트 데이터를 표현식에 동적으로 바인딩

- 사용처
    - Attribute Bindings
    - Class and Style Bindings

### Attribute Bindings

- “:” 약어 가능
- 대괄호([]) 로 감싸서 Dynamic attribute name(동적 인자 이름 ) 가능
- 여러 속성 동적 바인딩 가능, 이 경우 약어 사용 불가

### Class and Sytle Bindings

- Binding HTML Classes
    - Binding to Objects
    - Binding to Arrays
- Binding Inline Styles
    - Binding to Objects
    - Binding to Arrays

# ▶️ Event Handling

### v-on 구성

- handler 종류
    1. Inline handlers
    2. Method handlers
- “@” 약어 사용 가능

---

[보충](https://www.notion.so/1f1604d1c8f280e89760d215874bb964?pvs=21)
