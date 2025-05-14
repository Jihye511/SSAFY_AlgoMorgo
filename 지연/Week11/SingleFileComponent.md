# Framework:(Vue.js) : Single File Component

---

# ▶️ Sigle-File Components(SFC)

### Component

재사용이 가능한 코드 블럭

### SFC

컴포넌트의 템플릿, 로직 및 스타일을 하나의 파일로 묶어낸 특수한 파일 형식(*.vue 파일)

HTML + CSS + JavaScript

### 언어 블록

- <Template> 하나만
- <script setup> 하나만
- <style scoped> 여러개
    - scoped 가 지정되면 CSS는 현재 컴포넌트에만 적용

![image (34)](https://github.com/user-attachments/assets/c2e96800-6143-448c-b63e-5d669e941070)

---

### 싱글 파일 컴포넌트 파일명 대/소문자(파스칼 케이스, 케밥케이스)

| Bad | Good |
| --- | --- |
| components/mycomponet.vue | components/MyComponent.vue |
| components/myComponet.vue | components/my-comonent.vue |

### 기본 컴포넌트 이름(Base,App,V와 같은 특정 접두사)

| Bad | Good |
| --- | --- |
| components/
├─ MyButton.vue
├─ VueTable.vue
├─ Icon.vue | components/
├─ BaseButton.vue
├─ BaseTable.vue

components/
├─ AppButton.vue
├─ AppTable.vue

components/
├─ VButton.vue
├─ VTable.vue |

### 긴밀하게 결합된 컴포넌트 이름(자식은 부모이름 접두사)

| Bad | Good |
| --- | --- |
| components/
├─ TodoList.vue
├─ doItem.vue | components/
├─ TodoList.vue
├─ TodoListItem.vue |
| components/
├─ SearchSidebar.vue
├─ NavigationForSearchSideBar.vue | components/
├─ SearchSidebar.vue
├─ SearchSideBarNavigation,vue |

### 컴포넌트 이름 내 단어 순서(일반적인 단어 → 설명적인 단어)

| Bad | Good |
| --- | --- |
| components/
├─ ClearSearchButton.vue
├─ ExcludeFromSearchInput.vue | components/
├─ SearchButtonClear.vue
├─ SearchButtonRun.vue
├─ SearchInputQuery.vue
├─ SearchInputExcludeGlob.vue
├─ SettingsCheckboxTerms.vue
├─ SettingsCheckboxLaunchOnStartup.vue |

### 약어보다는 완전한 단어 사용

---

### SFC의 CSS 기능 - scope

- scoped를 사용하면 부모 컴포넌트의 스타일이 자식 컴포넌트로 유출되지 않음
- 단, 자식 컴포넌트의 최상위 요소(root element)는 부모와 자식의 CSS 모두의 영향을 받음

# ▶️ Passing Props


![image (35)](https://github.com/user-attachments/assets/e52100e0-112b-4268-b6c0-943daa4a8f25)

부모는 자식에게 데이터를 전달( Pass Props),

자식은 자신에게 일어난 일을 부모에게 알림(Emit Event)

### One-Way Data Flow

모든 props는 자식 속성과 부모 속성 사이에 하향식 단방향 바인딩을 형성

(one-way-down-binding)

- 단방향인 이유 → 하위 컴포넌트가 실수로 상위 상태를 변경하여 전체적인 데이터 흐름을 이해하기 어렵게 만드는 것을 방지하기 위함
    - 데이터 흐름의 일관성 및 단순

### Props선언

부모 컴포넌트에서 내려보낸 props를 사용하기 위해서는 

자식 컴포넌트에서 명시적인 props 선언이 필요

- ‘문자열 배열’을 사용한 선언
- ‘객체’를 사용한 선언   ——→객체 선언 문법 사용 권장

```jsx
**PropParent.vue**
<template>
  <div>
    <fieldset>
      <h2>PropParent</h2>
      <PropChild myMsg="msg" />
    </fieldset>
  </div>
</template>
```

```jsx
**PropChild.vue**

<template>
  <div>
    <h2>propchild</h2>
    <ul>
      <li>{{ myMsg }}</li>
    </ul>
  </div>
</template>

**<!--배열-->**

<script setup>
defineProps(['myMsg'])
</script>

-------------------------------------
**<!--객체-->

<script setup>
const props = defineProps({
  myMsg: String,
})
</script>**
```

# ▶️ Static & Dynamic props

v-bind를 사용하여 동적으로 할당된 props를 사용할 수 있음

```jsx
**PropParent.vue**

<template>
  <div>
    <fieldset>
      <h2>PropParent</h2>
      <PropChild myMsg="msg" :dynamicProps="name"/>
    </fieldset>
  </div>
</template>

<script setup>
import PropChild from '@/components/PropChild.vue'
import { ref } from 'vue'
</script>
```

```jsx
**PropParent.vue**

<template>
  <div>
    <fieldset>
      <h2>PropParent</h2>
      <PropChild myMsg="msg" :dynamicProps="name"/>
    </fieldset>
  </div>
</template>

<script setup>
import PropChild from '@/components/PropChild.vue'
import { ref } from 'vue'
</script>
```

# ▶️ Component Events

### $emit()

자식 컴포넌트가 이벤트를 발생시켜 부모 컴포넌트로 데이터를 전달하는 역할의 메서드

### 구조

$emit(event, …args)

- event - 커스텀 이벤트 이름
- args   - 추가 인자

### 이벤트 발신 및 수신(Emitting and Listening to Events)

---

### emit이벤트 선언

defineEmits()를 사용하여 명시적으로 발신할 이벤트를 선언할 수 있다.

```jsx
**PropChild.vue**

<template>
  <div>
    <h2>propchild</h2>
    <ul>
     <li><button @click="toParent">부모님께</button></li>
    </ul>
  </div>
</template>

<script setup>
const emit = defineEmits(['Present'])
const toParent = () => {
  console.log('propchild이벤트 발생')
  emit('Present')
}
</script>
```

```jsx
**PropParent.vue**

<template>
  <div>
    <fieldset>
      <h2>PropParent</h2>
       <PropChild myMsg="msg" :dynamicProps="name" @Present="receive" />
    </fieldset>
  </div>
</template>

<script setup>
import PropChild from '@/components/PropChild.vue'
import { ref } from 'vue'
const name = ref('hone')
const receive = () => {
  console.log('잘받든지..')
}
</script>
```
