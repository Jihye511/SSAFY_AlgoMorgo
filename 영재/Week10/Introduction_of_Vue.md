### Client-side frameworks

- 웹에서 하는 일이 많아졌다
- 복잡한 대화형 웹 사이트를 “웹 어플리케이션”이라 부름
    - 웹 페이지 X
- 다루는 데이터가 많아졌다
    - Vanilla JS(순수 자바스크립트)를 사용한다면 다루기 힘듬
- 다양한 클라이언트 디바이스의 등장으로 서버 사이드 렌더링의 한계
- 기존에 백엔드에서 처리하던 작업들을 클라이언트에서 처리하게 됨
    - 여러가지 복잡한 일을 처리해줄 framework필요

### SPA(Single Page Application)

![image](https://github.com/user-attachments/assets/6e0a8893-853c-4172-a620-083d2a692e6c)


- CSR방식(Client Side Randering)
    - 이전에는 SSR(Server Side Randering)
- 장점
    - 빠른 속도
        - 페이지 일부 재사용
    - 사용자 경험
        - 새로고침이 발생하지 않아 네이티브 앱과 유사항 사용자 경험을 제공
    - Frontend와 Backend의 명확한 분리
        - Frontend는 UI렌더링 및 사용자 상호 작용 처리를 담당&Backend는 데이터 및 API제공을 담당
        - 대규모 애플리케이션을 더 쉽게 개발하고 유지 관리 가능
- 단점
    - 초기 구동속도가 느림
    - SED(검색 엔진 최적화)answp
        - 페이지를 나중에 그려 나가는 것이기 때문에 검색에 잘 노출되지 않을 수 있다

### Vue

- UI를 구현하기 위한 자바스크립트 프레임워크
- **핵심 기능**
    - 선언적 렌더링
        - HTML을 확장하는 템플릿 구문을 사용하여 HTML이 JavaScript 데이터를 기반으로 어떻게 보이는지 설명 가능
    - 반응성(Reactivity)
        - JavaScript 상태 변경을 추적, 변경사항이 발생할때 자동으로 DOM을 효율적으로 업데이트
- **ref()**
    - 내부적으로 getter에서 값을 추적하고 setter에서 트리거 수행
    - .value를 사용하여 객체에 접근
        - ref는 값을 .value안에 감싼다

### Composition API & Option API

- Composition API(Vue 3)
    - import해서 가져온 API 함수들을 사용하여 컴포넌트의 로직을 정의
    - 테이블 위에 다 올려두고 작업하는 방식
    - **이 방식 사용**
- Option API(Vue 2)
    - data, methods 및 mounted같은 객체를 사용하여 컴포넌트 로직을 정의

### Vite(SFC build tool : single file component)

- 
- Node.js기반의 애플리케이션 빌드를 위한 scaffolding 도구
    - **빠른 개발 환경을 위한 빌드 도구와 개발 서버를 제공(실행을 빠르게 해줌)**
- packge.json
    - 프로젝트의 메타 정보와 의존성 패키지 목록을 포함
- packge-lock.json
    - 패키지들의 실제 버전,  의존성 관계등 모든 정보를 포함
- public
    - 주로 정적 파일을 위치
    - import할 필요 없는 파일들
    - 항상 root절대 경로를 사용하여 참조
- vite.donfig.js
    - 여기서 alias @를 사용해 src를 @로 나타냄

Real DOM(

## 📘 1. Real DOM (실제 DOM)

- 브라우저가 렌더링하는 **실제 HTML 문서 구조**입니다.
- DOM(Document Object Model)은 트리 형태로 구성되어 있으며, 모든 HTML 요소들이 노드로 표현됩니다.

### ❌ 단점

- **느림**: DOM을 직접 조작하면 브라우저가 바로 렌더링을 다시 해야 해서 성능 저하가 발생할 수 있음.
- 특히 복잡한 UI를 가진 앱에서는 조그만 변경도 전체 레이아웃을 다시 계산해야 할 수 있음.

---

## ⚡ 2. Virtual DOM (가상 DOM)

- **메모리 상의 가짜 DOM 트리**입니다.
- 실제 DOM을 직접 건드리지 않고, 먼저 메모리에서 변경 사항을 계산함.
- Vue, React 같은 프레임워크가 이 방식을 사용합니다.

### 동작 방식

1. 데이터 변경 → Virtual DOM을 새로 그림
2. 이전 Virtual DOM과 새 Virtual DOM을 비교 (`diffing`)
3. 변경된 부분만 **실제 DOM에 최소한으로 반영** (`patching`)
