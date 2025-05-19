Framework:(Vue.js) : Axios

# Axios

- promise 기반의 HTTP Client로 browser는 물론 node.js에서도 동작
    - 브라우저로부터 XMLHttpRequest 요청을 보내거나 Node.js에서 http 요청 생성
    - promise API 지원 : 비동기 처리할 때 콜백 지옥 안 보고 .then(), .catch(), async/await 쓰기 용이

### 주요 특징

- 브라우저를 위해 XMLHttpRequest t 생성
- node.js를 위해 http 요청 생성
- Promise API 지원
- 요청 및 응답 인터셉트
- 요청 밑 응답 인터셉트
- 요청 밑 응답 데이터 변환
- 요청 취소
- JSON 데이터 자동 변환
- XSRF를 막기 위한 클라이언트 사이드 지원
