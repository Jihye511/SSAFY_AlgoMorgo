Framework : (Vue.js) : Basic Syntax1

# Directive

- v-  접두사기 있는 특수 속성

## 1. Arguments

- 일부 directive는 directive 뒤에 콜론 (”:”)으로 표시되는 인자를 사용할 수 있음
- 아래 예시의 href는 HTML a 요소의 href 속성 값을 myUrl 값에 바인딩 하도록 하는 v-bind의 인자
- 아래 예시의 click은 이벤트 수신할 이벤트 이름을 작성하는 v-on 인자

## 2. Modifiers

- “ . (dot)” 로 표시되는 특수 접미사로, directive가 특별한 방식으로 바인딩되어야 함을 나타냄
- 아래 예시의 .prevent는 발생한 이벤트에서 event.preventDefault()를 호출하도록 v-on에 지시하는 modifier

## 3. value

- Directive의 속성 값은 단일 JS 표현식이어야함 (v-for, v-on 제외)
- 표현식 값이 변경될 때 DOM에 반응적으로 업데이트를 적용

# Template Syntax

- Dom에 Vue 객체의 데이터를 선언적으로 바인딩할 수 있는 HTML 기반 템플릿 구문을 사용

## 1. Text Interpolation

- 데이터 바인딩의 가장 기본적인 형태
- 이중 중괄호 구문 (콧수염 구문)을 사용’
- 콧수염 구문은 해당 구성 요소 인스턴스의 msg 속성 값으로 대체
- msg 속성이 변경될 때마다 업데이트 됨

## 2. Raw HTML

- 콧수염 구문은 데이터를 일반 텍스트로 해석하기 때문에 실제 HTML을 출력하려면 v-html을 사용해야

## 3. Attribute Bindings

- 콧수염 구문은 HTML 속성 내에서 사용할 수 없기 때문에 v-bind를 사용
- HTML의 id 속성 값을 vue의 dynamicId 속성과 동기화 되도록 함
- 바인딩 값이 null이나 undefind인 경우 렌더링 요소에서 제거됨

## 4. JS Expressions

- Vue는 모든 데이터 바인딩 내에서 JS 표현식의 모든 기능을 지원
- Vue 템플릿에서 JS 표현식을 사용할 수 있는 위치
    - 콧수염 구문 내부
    - 모든 directive의 속성 값 (”v-”로 시작하는 특수 속성)

### Expressions 주의사항

- 각 바인딩에는 하나의 단일 표현식만 포함될 수 있음
    - 표현식은 값으로 평가할 수 있는 코드 조각 (return 뒤에 사용할 수 있는 코드여야 함)
- 작동하지 않는 경우

npm run dev : package.json에서 script 에서 있는 dev 예약어

# Vue Style Guide

- Vue의 스타일 가이드 규칙은 우선순위에 따라 4가지 범주로 나눔
- 규칙 범주
    - 우선순위 A : 필수
        - 오류 방지하는데 도움이 되므로 어떤 경우에도 규칙을 학습하고 준수
    - 우선순위 B : 적극 권장
        - 가독성 및/ 또는 개발자 경험 향상
        - 규칙을 어겨도 코드는 여전히 실행되겠지만, 정당항 사유가 있어야 규칙 위반 가능
    - 우선순위 C : 권장
        - 일관성을 보장하도록 임의의 선택을 할 수 있음
    - 우선순위 D : 주의 필요
        - 잠재적  위험 특성을 고려

## v-bind

- 하나 이상의 속성 또는 컴포넌트 데이터를 표현식에 동적으로 바인딩
- 클래스 속성 바인딩할때 자주 사용

### Attribute Bindings

- HTML의 속성 값을 Vue의 상태 속성 값과 동기화 되도록 함
    - 가장 많이 쓰는거 : v-text
    - 두번째 많이 쓰는거 : v-bind
- v-bind shorthand → :
- Dynamic attribute name (동적 인자 이름)
    - 대괄호 ([])로 감싸서 directive argument에 JS 표현식 사용 가능
    - JS표현식에 따라 동적으로 평가된 값이 최종 argument 값으로 사용됨
- 여러 속성을 동적으로 바인딩
    - 인자 없이 v-bind를 사용
        - 약어로 사용 불가

## Class and Style Bindings

- 클래스와 스타일은 모두 HTML 속성이므로 v-bind를 사용하여 다른 속성과 마찬가지로 동적으로 문자열 값을 할당할 수 있음
- 그러나 단순히 문자열 연결을 사용하여 이러한 값을 생성하는 것은 번거롭고 오류가 발생하기가 쉬움
- Vue는 클래스 및 스타일과 함게 v-bind를 사용할 때 객체 또는 배열을 활용한 개선 사항을 제공

# v-on

- DOM 요소에 이벤트 리스너를 연결 및 수신
- handler 종류
    - inline handlers : 이벤트가 트리거 될 때 실행 될 JS 코드
    - Method handlers : 컴포넌트에 정의된 메서드 이름
- v-on shrothand : @

### 1. Inline handlers

- inline handlers는 주로 간단한 상황에 사용

### 2. Method Handlers

- inline handlers로는 불가능한 대부분의 상황에서 사용
- Method Handlers는 이를 트리거하는 기본 Dom event 객체를 자동으로 수신

### Inline Handlers 에서의 메서드 호출

- 메서드 이름에 직접 바인딩하는 대신 Inline Handlers에서 메서드를 호출할 수 도 있음
- 이렇게 하면 기본 이벤트 대신 사용자 지정 인자를 전달할 수 있음

### Inline Handlers에서의 event 인자에 접근

- Inline Handlers에서 원래 DOM 이벤트에 접근하기
- $event 변수를 사용하여 메서드에 전달

### Event Modifiers

- Vue는 v-on에 대한 Event Modifiers를 제공해 event.preventDefault()와 같은 구문을 메서드에 작성하지 않도록 함
- stop, prevent, self, capture, once 등 다양한 modifiers를 제공

### Key Modifiers

- Vue는 키보드 이벤트를 수신할 때 특정 키에 관한 별도 modifiers를 사용할 수있음

### DOM 요소에 직접 접근

- Vue는 기본적으로 DOM 요소에 직접 접근하는 것을 지양
    - 반응형 시스템으로 가상 DOM을 수정했을 때 실제 DOM이 수정되도록 유도
- 이벤트의 포커스 관리 등 상황에서 특정 요소를 지정해야할 때는 ref 활용
    - ref는 실제 DOM에 접근하기는 하지만 vue 관리하에서 처리

## Form Input Bindings

- form을 처리할 때 사용자가 input에 입력하는 값을 실시간으로 JS 상태에 동기화해야 하는 경우
- 양방향 바인딩 방법
    - v-bind와 v-on을 함께 사용
    - v-model 사용 (내부적으로 1의 방법 사용)
        - form input 요소 또는 컴포넌트에서 양방향 바인딩을 만듦

### 1. v-bind와 v-on을 함께 사용

- v-bind를 사용하여 input 요소의 value 속성 값을 입력 값으로 사용
- v-on을 사용하여 input 이벤트가 발생 할 때마다 input 요소의 value 값을 별도 반응형 변수에 저장하는 핸들러를 호출

### 2. v-model 사용

- v-model을 사용하여 사용자 입력 데이터와 반응형 변수를 실시간 동기화
- 단순 text input 뿐만 아니라 Checkbox, Radio, Select 등 다양한 타입의 사용자 입력 방식과 함께 사용 가능

# 강사님 정리

### Vue

directive : v- 로 시작 → .vue 파일에 가상 vue 즉 스크립트에 들어감, readDOM은 template

브라우저는 .vue를 이해 못한다

.vue파일을 바꿔주는게 vite

vite 환경 안에서만 .vue를 쓸 수 있다

vue는 하나의 객체로 만들어야 함

vue life cycle : main하나 만들고 거기다 다 처리하고 싶다 → setup

setup에서 다 할거면 scripte에서 처리할거는 setup밖에 없잖아

→ <script setup>

<template> : real DOM

<script> : virtual DOM

v-text, v-html의 차이 :

문자열 안에 html이 있으면 text냐 rendering을 돌릴거냐 차이

만약에 v-bind, v-tag를 써서 문자열을 주면

안에 들어가는 코드는 문자열이 아니다 그 안에는 JS 코드다

directive안에 “ “ 영역은 vue안에 들어가는 JS코드로 이해하면 된다

{{}}안에 코드는 JS코드로 이해해봐라

v-bind와 같이 하는 이유? → 동적으로 변환할 수 있어서

ref → 반응형때문에 (내부적으로 get써서 값이 바뀌면 동적으로 변환)

“JS”

함수로 실행하려면 count()

변수로 하려면 count

콜백함수는 ()하지말자

→ event가 발생하면 실행되는 함수라서

v-bind : 가상 DOM을 real DOM으로 (단방향)

근데 realDOM값이 바뀌면 가상 DOM 이 바뀌진 않는다

v-model : 양방향 (가상 DOM → ← REAL DOM)
