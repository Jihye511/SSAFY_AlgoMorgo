# Framework:(Vue.js) : Introduction of Vue

---

# ▶️ Frontend Development

### Client-side frameworks가 필요한 이유

1. 웹에서 하는 일이 많아졌다. ( 문서 → 음악, 영상 채팅)
2. 다루는 데이터가 많아졌다.
3. 다양한 클라이언트 디바이스의 등장으로 서버 사이드 렌더링의 한계
4. 기존에 백엔드에서 처리하던 작업들을 클라이언트에서 처리하게 됨

# ▶️ SPA(Single Page Application)

![Uploading image (27).png…]()

- 웹 애플리케이션의 초기 로딩 후 새로운 페이지 요청 없이 동적으로 화면을 갱신하며 사용자와 상호작용하는 웁 애플리케이션→ CSR 방식

### CSR(Client-side REndering 방식)

- 장점
    - 빠른속도
        - 서버로 전송되는 데이터의 양을 최소화
    - 새로고침이 발생하지 않아 네이티브 앱과 유사한 사용자 경험을 제공
    - Frontend와 Backend의 명확한 분리
- 단점
    - 초기 구동속도가 느림
    - SEO(검색 엔진 최적화) 문

# ▶️ Vue

```jsx
<!DOCTYPE html>
<html lang="en">
    <head>
        
        <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
    </head>
  <body>
  <div id="app">{{message}}</div>
  </body>
  <script>
    const {createApp, ref}=Vue;
    createApp({
        setup(){
            const message = ref("Hello Vue!!");
            return {
                message
            };
        },
    }).mount("#app");
    </script>
</html>

```

### ref()

- 반응형 상태(데이터)를 선언하는 함수
- 내부적으로 getter에서 값을 추적하고 setter에서 트리거 수

# ▶️ Composition API & Option API

![image (28)](https://github.com/user-attachments/assets/d39f01ce-5cf9-40c8-a478-5d6db1f91c4d)

# ▶️ SFC build tool(Vite)

### Vite

- Node.js기반의 애플리케이션 빌드를 위한 scaffolding도구

### Vite Project구조

- node_modules
- package.json
- package-lock.json
- public 디렉토리
- src 디렉토리
- src/assets
    - 프로젝트 내에서 사용되는 자원(이미지, 폰트, 스타일 시트 등)을 관
- src/components
    - Vue 컴포넌트들을 작성하는
- src/App.vue
    - Vue앱의 최상위 Root 컴포넌트
- src/main.js
    - 필요한 라이브러리 import하고 전역 설정 수행
- index.html
    - Vue앱의 기본 HTML파일
