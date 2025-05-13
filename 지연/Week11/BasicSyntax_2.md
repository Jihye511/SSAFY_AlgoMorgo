# Framework:(Vue.js) : Basic Syntax 2
---

# ▶️ Computed Properties

### Computed()

“계산된 속성”을 정의하는 함수

→ 미리 계산된 속성을 사용하여 템플릿에서 표현식을 단순하게 하고 불필요한 반복 연산을 줄임

- **[주의] computed의 반환 값은 변경하지 말 것**
    
    computed의 반환 값은 의존하는 데이터의 파생된 값
    
- **[주의] computed 사용 시 원본 배열 변경하지 말 것**
    
    computed에서 reverse() 및 sort() 사용 시 원본 배열을 변경하기 때문에 복사본을 만들어서 진행 해야함
    

### computed와 method 차이

- computed
    - computed 속성은 의존된 반응형 데이터를 기반으로 캐시(cached) 된다.
    - 의존하는 데이터가 변경된 경우에만 재평가됨
    - 적절한 사용처
        - 의존하는 데이터에 따라 결과가 바뀌는 계산된 속성을 만들 때 유용
- method
    - 반면, method호출은 다시 렌더링이 발생할 때마다 항상 함수 호출
    - 적절한 사용처
        - 단순히 특정 동작을 수행하는 함수 정의할 사용

# ▶️ Conditional Rendering

### v-if  , v-else , v-else-if

표현식 값의 T/F를 기반으로 요소를 조건부로 렌더링

### HTML<Template> element

- 페이지가 로드 될 때 렌더링 되지는 않지만 보이지 않는 javascript를 사용하여 나중에 문서에서 사용할 수 있도록 하는 HTML을 보유하기 위한 메커니즘
- 보이지 않는 wrapper역

### v-show

표현식 값의 T/F를 기반으로 요소의 가시성(visibility- 공간은 유지한채 투명)을 전환

### v-show 예시

```jsx
const isShow = ref(false)
<div v-show="isShow">v-show</div>
```

| **v-if** | **v-show** |
| --- | --- |
| Cheap initial load | Expensive initial load |
| expensive toggle | cheap toggle |
| 초기 조건이 false인 경우 아무작업도 수행 안함 | 초기 조건에 관계없이 항상 렌더링 |
| 토글 비용이 높음 | 초기 렌더링 비용이 높음 |

# ▶️ List Rendering

### v-for

- 인덱스(객체에서는 키)에 대한 별칭을 지정할 수 있음

```jsx
<div v-for="item in items">
{{item.text}}
</div>

<div v-for="(item, index) in items"></div>
```

- 배열 반복
- 객체 반복

```jsx
<script setup>
import { ref } from 'vue'

const myObj = ref({
  name: 'Cathy',
  age: 30
})
</script>

<template>
  <div v-for="(value, key, index) in myObj">
    {{ index }} / {{ key }} / {{ value }}
  </div>
</template>

```

### 중첩된 v-for

```jsx
const myInfo = ref{[
{name : 'Alice', age:20,friends: ['Bella','Cathy','Dan']},
{name : 'Bella', age:21,friends: ['Alice','Cathy']}
]}

<!--이중for문-->
<ul v-for="item in myInfo">
	<li v-for="friend in item.friends">
		{{ item.name}}-{{friend}}
	</li>
</ul>		
```

### 반드시 v-for와 key를 함께 사용한다.

내부 컴포넌트의 상태를 일관되게 유지

```jsx
<div v-for="item in items" :key="item.id">
      {<input v-model="item.id" type="text" />
      <input v-model="item.name" type="text" />
      <input
        type="text"
        placeholder="score"
      />}
    </div>
  
 
```

 키가 없으면 shift버튼을 눌렀을때 id, name, score값이 같이 움직이는게 아니라 id,name만 바뀌게 된다.

![image (33)](https://github.com/user-attachments/assets/099c698f-f630-43d3-b5f9-022473a258ce)

- :key="index” 는 안됨

# ▶️ 참고

### v-if 와 v-for를 함께 사용하지 않기

![image (30)](https://github.com/user-attachments/assets/a8e0d05c-719c-41e9-93a5-9f677f19ff78)

# ▶️ Watchers

### watch()

반응형 데이터를 감시하고, 감시하는 데이터가 변경되면 콜백 함수를 호출
![image (31)](https://github.com/user-attachments/assets/15d32a77-7467-44f8-8871-786710683287)

- variable
- newValue
- oldValue

```jsx
    <button @click="count++">Add 1</button>
    <p>Count: {{ count }}</p>
    
    watch(count, (newValue, oldValue) => {
  console.log(`newValue: ${newValue}, oldValue: ${oldValue}`)
})
```

## ✅ `computed` vs `watch` 비교

| 항목 | `computed` | `watch` |
| --- | --- | --- |
| **공통점** | 데이터의 변화를 감지하고 처리 | 데이터의 변화를 감지하고 처리 |
| **동작** | 의존하는 데이터 속성의 계산된 값을 반환 | 특정 데이터 속성의 변화를 감시하고 작업을 수행 |
| **사용 목적** | 템플릿 내에서 사용되는 데이터 연산용 | 데이터 변경에 따른 특정 작업 처리용 |
| **사용 예시** | 연산 된 길이, 필터링 된 목록 계산 등 | 비동기 API 요청, 연관 데이터 업데이트 등 |

> ❗ computed와 watch 모두 의존(감시)하는 원본 데이터를 직접 변경하지 않음
> 

# ▶️ Lifecycle Hooks

Vue인스턴스의 생애주기 동안 특정 시점에 실행되는 함수

![image (32)](https://github.com/user-attachments/assets/eb85105e-c081-4924-b402-0bae749f6f75)

### setup → mounted → update → unmounted

```jsx
const{createApp, ref, onMounted} = vue
```

- 잘쓰는 3가지
    - onMounted
    - onUpdated
    - onUnmounted
- Vue는 Lifecycle Hooks 에 등록된 콜백 함수들은 인스턴스와 자동 연결
