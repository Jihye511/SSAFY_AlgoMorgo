### Computed

- 계산된 속성을 정의하는 함수
- 미리 계산된 속성을 사용하여 템플리셍서 표현식을 단순하게 하고 불필요한 연산을 템플릿에서 제거
- return이 동반됨
- 데이터가 변경될 때 마다 새 snapshot이 생성됨
- 반환값은 읽기전용
- **정렬 : reverse()나 sort()사용시 원본을 건드리므로 복사본 필수**

### Computed vs Methods

- 두 가지 접근 방식은 실제로 완전히 동일
- Coumputed는 속성처럼 쓰고, Methods는 함수를 호출
- Computed 속성은 의존된 반응형 데이터를 기반으로 **캐시** 된다
    - 의존된 반응형 데이터가 변경되지 않는 한 이미 계산된 결과에 대한 여러 참조를 재평가 할 필요 없이 즉시 반환
- Methods는 랜더링 될때마다 실행

## v-if

- if문 사용하기 위해서 묶으려면 template사용하기
    - 개발자 도구에서는 보이지 않는다
- v-if vs v-show
    - 자리를 자지하지 않거나, 자리를 차지하거나(visibility, display)
    - v-show 요소는 항상 렌더링 되어 DOM에 남아있는다

### v-for

- 소스 데이터를 기반으로 요소 또는 템플릿 블록을 여러번 렌더링
- 인덱스(주소, 키)에 대한 별칭 지정 가능
- value, key index 순
- 2중포문은 그대로 사용
- **반드시 v-for와 key를 함께 사용**
    - 내부 컴포넌트의 상태를 일관되게 유지
    - key는 반드시 각 요소에 대한 고유한 값을 나타낼 수 있는 식별자이어야 함
- **반드시 배열의 index를 v-for의 key로 사용하지 말 것**
    - 매번 렌더링 되는 상황이 발생 가능
- **동일한 요소에 for과 if를 함꼐 사용하지 말 것**
    - if가 for보다 우선순위가 높기 때문에 혼용시 에러 발생
    - 만약 함께 써야한다면 computed를 활용해 필터링 된 목록을 반환하여 반복하도록 설정
    - template를 사용해서 분리

### Watcher

- 반응형 데이터를 감시하고, 감시하는 데이터가 변경되면 콜백 함수를 호출

![image](https://github.com/user-attachments/assets/5b004a7e-15d0-4069-9f63-58ae773fd390)


- variable : 감시하는 변수. 반응형이 아닌 경우는 getter로 처리
- newValue : 감시하는 변수가 변화된 값, 콜백함수 첫번째 인자
- oldValue : 콜백함수 두번째 인자

![image](https://github.com/user-attachments/assets/5a9f57e3-c744-48a2-bee8-ed888883545b)


- Computed는 화면에 보여지는 기능으로 수행
- Watchers는 값이 변화되면 다른 작업을 수행

### Lifecycle hook

- Vue 인스턴스의 생애주기 동안 특정 시점에 실행되는 함수
    - 개발자가 특정 단계에서 의도하는 로직이 실행될 수 있도록 함
- Vue는 Lifecycle Hooks에 등록된 콜백 함수들을 인스턴스와 자동 연결
- **hooks함수들은 반드시 동기적으로 작성**
- 주로 onMounted, onUpdated, onUnmounted 사용

https://ko.vuejs.org/guide/essentials/lifecycle.html참고 site

watch effect까지 참고
