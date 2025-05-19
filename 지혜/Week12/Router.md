## ✅Routing

> 네트워크에서 경로를 선택하는 프로세스
웹 애플리케이션에서 다른 페이지 간의 전환과 경로 지정
> 

- SPA에서 routing은 클라이언트 측에서 수행
- 페이지 바꾸는게 아니라 링크에 따라 여러 컴포넌트를 새로 렌더링(바꿔줌)
- 페이지 교체 X, 컴포넌트(vue) 교체 O

## ✅Vue Router

- `RouterLink`
    - 페이지를 다시 로드 하지 않고 url 생성,변경 및 관련 로직처리
    - 결과적으로 html의 a 태그를 렌더링
- `RouterView`
    - url에 해당하는 컴포넌트가 표시되는 영역

- 라우팅 기본
    - index.js에 라우터 관련 설정 작성(주소, 이름, 컴포넌트)
    - RouterLink의 ‘to’ 속성에 index.js에서 정의한 주소 속성값(path) 지정

## ✅Named Routes

> 경로에 이름을 지정해서 라우팅
> 
- 객체형태로 값 전달 → to 라는 속성에 바인딩

## 매개 변수를 사용한 동적 경로 매칭

- 주어진 패턴 경로를 동일한 컴포넌트에 매핑해야 하는 경우 활용

## router.replace()

- push메서드와 달리 history stack에 새로운 항목을 push하지 않고 다른 url로 이동(===이동 전 url로 뒤로 가기 불가)
- 선언적 : <RouterLink: to=”…” replace>
- 프로그래밍적: router.replace(…)

## Navigation Guard

- Globally(전역 가드)

![image](https://github.com/user-attachments/assets/87171a73-3d2f-4fb9-8377-95f4e78f2b40)


## Navigation Guard

- onBeforeRouteLeave()
    - 현재라우트에서 다른라우트로 이동하기 전에 실행
    - 사용자가 현재 페이지를 떠나는 동작에 대한 로직을 처리
- onBeforeRouteUpdate()
    - 이미 렌더링 된 컴포넌트가 같은 라우트 내에서 업데이트 되기 전에 실행
    - 라우트 업데이트 시 추가적인 로직을 처리

## **✏**✅
