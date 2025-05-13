# Client-side Framework

: 클라이언트 측에서 UI와 상호작용 개발하는 JS기반 프레임워크

### frontend develpment :

웹사이트와 웹 애플리케이션의 사용자 인터페이스와 사용자 경험을 만들고 디자인하는 것

ex ) React Angular Vue

client-side framework가 필요한 이유

1. 사용자의 복잡한 대화형 웹 사이트 : 웹 어플리케이션 
    - Js 기반의 client side fw의 출현으로 동적인 대화형 애플리케이션을 쉽게 구축

 2.  다루는 데이터의 방대함

- 애플리케이션의 기본 데이터를 안정적으로 추적하고 업데이트할 도구
- 애플리케이션의 상태를 변경할 때마다 일치하도록 UI 업데이트
1. 다양한 클라이언트 디바이스의 등장으로 서버 사이드 렌더링의 한계
    - 서버는 REST 방식으로 서비스하고 클라이언트에서 데이터를 활용하는 형태로 전환
    - 기존 백엔드 작업을 클라이언트에서 처리
    - 단순 View 역할에서 벗어나 일반적인 애플리케이션 역할 수행

![image](https://github.com/user-attachments/assets/348a6ffb-c71a-41cd-8833-702083cb5137)

# SPA

: Single Page Application

페이지 한 개로 구성된 웹 애플리케이션

![image](https://github.com/user-attachments/assets/2cd953b1-24d2-41a2-94fd-c869b24685ac)

1. 서버로부터 필요한 모든 정적 HTML을 한번에 가져옴
2. 브라우저가 페이지를 로드하면 Client-side fw는 각 HTML 요소에 적절한 JS 코드를 실행 (이벤트 응답, 데이터 요청 후 UI 업데이트 등)
    - 데이터 간 이동 시, 페이지 갱신에 필요한 데이터만을 JSON으로 전달받아 페이지 일부 갱신
    - Google Maps, Facebookm Instagram 등의 서비스에서 갱신 시 새로고침이 없는 이유
    

→ 웹 어플리케이션 초기 로딩 후 새로운 페이지 요청 없이 동적으로 화면으로 갱신하며 사용자와 상호작용하는 웹 애플리케이션 > CSR 방식

### CSR : Client-side Rendering

![image](https://github.com/user-attachments/assets/16d30b61-da66-483a-bfae-be2db8d332d3)

클라이언트에서 화면을 렌더링하는 방식

1. 브라우저는 페이지에 필요한 최소한의 HTML 페이지와 Js를 다운로드
2. 그런 다음 Js를 사용하여 DOM을 업데이트하고 페이지를 렌더링

장점 : 

1. 빠른 속도
    - 페이지 일부 재사용 가능 - 동일 웹 사이트의 다른 페이지로 이동하는 것이 더 빠름
    - 서버로 전송되는 데이터의 양을 최소화
2. 사용자 경험
    - 새로고침 X
3. Front와 Back의 명확한 분리
    - Front : UI렌더링, 사용자 상호작용 처리
    - Back : 데이터 및 API 제공 담당
    - 대규모 Application을 쉽게 개발, 유지 관리

단점 : 

1. 초기 구동속도 느림
    - 전체 페이지 보기 전에 약간의 지연
    - Js가 다운로드, 구문 분석 및 실행될 때까지 페이지가 완전 렌더링되지 않기 때문
2. SEO (검색 엔진 최적화) 문제
    - 페이지를 나중에 그려가기 때문에 검색에 잘 노출되지 않을 수 있음

# Vue

: 사용자가 인터페이스를 구축하기 위한 Js 프레임워크

특징

1. 쉬운 학습 곡선 및 간편한 문법
2. 문서, 생태계
3. 유연성과 가벼움

핵심 기능

1. 선언적 렌더링
    - HTML을 확장하는 템플릿 구문을 사용해 HTML이 Js 데이터를 기반으로 어떻게 보이는지 설명 가능
2. 반응성
    - Js가 상태 변경을 추적하고 변경사항 발생 시마다 자동으로 DOM을 효율적으로 업데이트

사용법 

1. CDN 방식
2. VITE를 활용한 SFC 방식

```java
<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>

<div id="app">{{ message }}</div>

<script>
  const { createApp, ref } = Vue

  createApp({
    setup() {
      const message = ref('Hello vue!')
      return {
        message
      }
    }
  }).mount('#app')
</script>
```

→ id가 app

message는 script에서 반환한 값을 사용

CDN 및 Appliation instance 작성

- 빠른 시작 > CDN에서 VUE사용하기 > ES 모듈 빌드 사용

![image](https://github.com/user-attachments/assets/9881a96d-b95c-4b67-a351-302ae718ffd3)

Application instance 

- 모든 Vue 애플리케이션은 createApp 함수로 새 application instance를 생성

![image](https://github.com/user-attachments/assets/58d7212f-95aa-42af-938a-09c489dbaa22)

app.mount()

- HTML 요소에 Vue 애플리케이션 인스턴스를 탑재
- 각 앱 인스턴스에 대해 mount() 한번만 호출

![image](https://github.com/user-attachments/assets/46af65ab-1a31-418b-9173-97dcc013973c)

### ref( )

: 반응형 상태 (데이터)를 선언하는 함수

- 내부적으로 getter의 값을 추적하고 setter에서 트리거 수행

![image](https://github.com/user-attachments/assets/187ad104-cc4f-453f-a192-66f4883948b7)

ref 함수

- 인자를 받아 value 속성이 있는 ref 객체로 래핑 (wrapping) 하여 반환
- ref로 선언된 변수의 값이 변경 시, 해당 값을 사용하는 템플릿(HTML 화면)에서 자동 업데이트
- 인자는 어떠한 타입도 가능 ↔ 객체 전용 : reactive

![image](https://github.com/user-attachments/assets/5d234849-9ce6-4200-a2f1-6227f367039f)

- 템플릿의 참조에 접근하려면 setup 함수에서 선언 및 반환 필요
- 템플릿에서 ref 사용 시 .value 작성 필요 없음 ↔ Js 영역에서는 .value 필요함

![image](https://github.com/user-attachments/assets/34cd1bef-44e0-405d-8747-dccaeacefcd1)

VUE 기본 구조 

- createApp() 에 전달되는 객체는 vue 컴포넌트
- 컴포넌트의 상태는 setup 함수 안에서 선언되어야 하며 객체를 반환해야함

![image](https://github.com/user-attachments/assets/b9852f2b-f154-48ce-bcd7-5c2c0f6e5f19)

템플릿 렌더링 

- 반환된 객체의 속성은 템플릿에서 사용 가능
- 콧수염 구문을 사용해서 메시지 값을 기반으로 동적 텍스트 렌더링

![image](https://github.com/user-attachments/assets/5601bb88-4784-4eb2-a063-0b7db43f3b73)

- 컨텐츠는 식별자나 경로에 국한 X, 유효한 Js 표현식 사용 가능

![image](https://github.com/user-attachments/assets/ab3833c9-8af0-41f2-8183-93b0b0052d31)

## EventListener in Vue

- v-on directive를 사용해서 DOM 이벤트를 수신할 수 있음
- 함수내에서 반응형 변수를 변경해서 구성 요소 상태 업데이트

![image](https://github.com/user-attachments/assets/cd3979b4-50ae-4c13-97eb-154abee370fd)

# Composition API & Option API

필요한 것을 쓴다.

만들어 쓴다.

Composition API

: import해서 가져온 API 함수들을 사용해 컴포넌트의 로직을 정의

![image](https://github.com/user-attachments/assets/1a233e28-5a96-4989-b1a4-e2551e444927)

Options API

: data, methods, mounted 같은 객체를 사용해서 컴포넌트의 로직 정의

![image](https://github.com/user-attachments/assets/62ce0859-446a-401a-a7a2-aa04898c87dc)

※ 권장

Composition API + SFC  : 규모가 있는 앱의 전체 구축

Option API : 빌드 도구를 사용하지 않거나 복잡성이 낮은 프로젝트에서 사용하는 경우

# Vite (SFC 빌드 툴)

: Node.js 기반의 애플리케이션 빌드를 위한 scaffolding 도구

- 빠른 개발 환경을 위한 빌드 도구와 개발 서버 제공

### Scaffolding

- 새로운 프로젝트나 모듈을 시작하기 위해 초기 구조와 기본 코드 자동 생성
- 초기설정, 폴더 구조, 파일 템플릿, 기본 코드등을 자동 생성

Vie Project 생성 

## Vite Proect 구조

node_modules 

- Nodejs 프로젝트에서 사용되는 외부 패키지 들이 저장되는 디렉토리
- 프로젝트 의존성 모듈 저장, 관리
- 프로젝트 실행 시 필요한 라이브러리, 패키지 포함

Package.json

- 프로젝트 메타정보, 의존성 패키지 목록  ( = pom.xml )
- 프로젝트 이름 버전 작성자 등등..
- package-lock.json과 함께 관리

Package-lock.json

- 정확한 버전 보장
- package.json은 정확한 버전이 아니라 범위를 명시하고 있음 → 메이져 버전 , 마이너버전 (새로운 api 추가),  패치버전(하위 호환에 문제X)     +     ^ : 캐럿 버전 (어디까지는 변경될 수 있음을 명시해줌)

Public 디렉토리

- 소스코드에서 참조되지 않는, 항상 같은 이름의 , import 할 필요없는 정적 파일 위치
- 항상 root 절대 경로를 사용해서 참조

src 디렉토리

- 주요 소스코드
- 컴포넌트, 스타일, 라우팅 등 핵심코드 관리

src/assets

- 프로젝트 내 사용되는 자원 관리 ( 이미지, 폰트, 스타일 시트 ) 관리
- 컴포넌트 자체 참조하는 내부 파일 저장
- 컴포넌트가 아닌 곳에서는 public 디렉토리의 파일 사용

src.components

src/App.vue

- Vue 앱의 최상위 Root 컴포넌트
- 다른 하위 컴포넌트들을 포함
- 애플리케이션 전체의 레이아웃과 공통적인 요소 정의

src/main.js

index.html

jsconfig.json

vite.config.js
