Framework:(Vue.js) : Router

# Routing

네트워크에서 경로를 선택하는 프로세스

웹 애플리케이션에서 다른 페이지 간의 전환과 경로를 관리하는 기술

## SSR에서의 Routing

- SSR에서 routing은 서버 측에서 수행
    - 서버는 사용자가 방문한 URL 경로를 기반으로 응답을 전송
- 링크를 클릭하면 브라우저는 서버로부터 HTML 응답을 수신하고 새 HTML로 전체 페이지를 다시 로드

### CSR/SPA에서의 Routing

- SPA에서 routing은 클라이언트 측에서 수행
- 클라이언트 측 JS가 새 데이터를 동적으로 가져와서 처리
    - 전체 페이지를 다시 로드 하지 않음
- 페이지는 1개이지만, 링크에 따라 여러 컴포넌트를 새로 렌더링하여 마치 여러 페이지를 사용하는 것처럼 보이도록 해야 함

만약 routing이 없다면

- 유저가 URL을 통한 페이지의 변화를 감지할 수 없음
- 페이지가 무엇을 렌더링 중인지에 대한 상태를 알 수 없음
    - URL이 1개이기 때문에 새로 고침 시 처음 페이지로 되돌아감
    - 링크를 공유할 시 첫 페이지만 공유 가능
- 브라우저의 뒤로 가기 기능을 사용할 수 없음

### Router Link

- 페이지를 다시 로드 하지 않고 URL 생성/변경 및 관련 로직을 처리
- 결과적으로 HTML의 a 태그 렌더링

### RouterView

- URL에 해당하는 컴포넌트를 표시
- 어디에나 배치하여 레이아웃에 맞출 수 있음

### router/index.js

- 라우팅 관련된 정보 및 설정이 작성 되는 곳
- router에 URL과 컴포넌트를 매핑

### views

- RouterView위치에 렌더링 할 컴포넌트를 배치
- 기존 components 폴더와 기능적으로 다른 것은 없으며 단순 분류의 의미로 구성됨
- 일반 컴포넌트와 구분하기 위해 컴포넌트 이름을 view로 끝나도록 작성하는 것을 권장

# Named Routes

- 경로에 이름을 지정하는 라우팅
- name 속성 값에 경로에 대한 이름을 지정
- 경로에 연결하려면 RouterLink에 v-bind 를 사용해 to props 객체로 전달

### 장점

- 하드 코딩 된 URL을 사용하지 않아도 됨
- URL 입력 시 오타 방지

### 매개 변수를 사용한 동적 경로 매칭

- 주어진 패턴 경로를 동일한 컴ㅍ넌트에 매핑 해야 하는 경우 활용
- 예를 들어 모든 사용자의 ID를 활용하여 프로필 페이지 URL을 설계한다면?
    - user/1, user/2, user/3
    - 일정한 패턴의 URL 작성을 반복해야 함

## 라우팅 기본

- index.js에 라우터 관련 설정 작성 (주소, 이름, 컴포넌트)
- RouterLink의 to 속성에 index.js에서 정의한 주소 속성 값을 지정

## 프로그래밍 방식 네비게이션

- 프로그래밍으로 URL 이동하기
- router의 인스턴스 메서드를 사용해 RouterLink로 a태그를 만드는 것처럼 프로그래밍으로 네비게이션 관련 작업을 수행할 수 있음

### router.push()

다른 위치로 이동하기

- 다른 URL로 이동하는 메서드
- 새 항목을 history stack에 push하므로 사용자가 브라우저 뒤로 가기 버튼을 클릭하면 이전 URL로 이동할 수 있음
- RouterLink를 클릭했을 때 내부적으로 호출되는 메서드이므로 RouterLink를 클릭하는 것은 router.push()를 호출하는 것과 같음

### router.replace()

현재 위치 바꾸기

- push 메서드와 달리 history stack에 새로운 항목을 push 하지 않고 다른 URL로 이동

## Navigation Guard

Vue router를 통해 특정 URL에 접근할 때 다른 URL로 redirect를 하거나 취소하여 네비게이션을 보호

- 인증 정보가 없으면 특정 페이지에 접근하지 못하게 함

1. globally (전역 가드)
    1. 애플리케이션 전역에서 동작
    2. 작성위치 : index.js
2. Per-route (라우터 가드)
    1. 특정 route에서만 동작
    2. 작성위치 : index.js의 각 routes
3. In-component (컴포넌트 가드)
    1. 특정 컴포넌트 내에서만 동작
    2. 작성위치 : 각 컴포넌트의 script

### Globally guard

router.beforeEach() : Global Before Guards

다른 URL로 이동하기 직전에 실행되는 함수

- to : 이동할 URL 정보가 담긴 Route 객체
- from : 현재 URL 정보가 담긴 Route 객체
- 선택적 반황(return) 값 : 1. false, 2. Route Location
- 반환 값이 false 일 때
    - 현재 내비게이션을 취소
    - 브라우저 URL이 변경된 경우 (사용자가 수동으로 또는 뒤로 버튼을 통해) from 경로의 URL로 재설정
- 반환 값이 Route Location 일 때
    - router.push()를 호출하는 것처럼 경로 위치를 전달하여 다른 위치로 redirect
- return 이 없다면 ‘to’ URL Route 객체로 이동

### Per-route Guard

router.beforeEnter() 

특정 route에 진입했을 때만 실행되는 함수

단순히 URL의 매개변수나 쿼리 값이 변경될 때는 실행되지 않고, 다른 URL에서 탐색할 때만 실행됨

- routes 객체에서 정의
- 함수의 to, from, 선택 반환 인자는 이전 beforeEach()와 동일

### In-component Guard

- onBeforeRouteLeave()
    - 현재 라우트에서 다른 라우트로 이동하기 전에 실행
    - 사용자가 현재 페이지를 떠나는 동작에 대한 로직을 처리
- onBeforeRouteUpdate()
    - 이미 렌더링 된 컴포넌트가 같은 라우트 내에서 업데이트되기 전에 실행
    - 라우트 업데이트 시 추가적인 로직을 처리
