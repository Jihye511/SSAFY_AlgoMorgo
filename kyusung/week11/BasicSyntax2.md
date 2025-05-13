# Computed Properties

computed()

- 계산된 속성을 정의하는 함수
- 미리 계산된 속성을 사용하여 템플릿에서 표현식을 단순하게 하고 불필요한 반복연산을 없앤다

![image](https://github.com/user-attachments/assets/6e50b04a-82a4-481c-92eb-9b5c7b301990)

- 템플릿이 복잡해지며 todos에 따라 계산을 수행하게 됨
- 만약 이 계산을 템플릿의 여러 곳에서 사용하는 경우 반복 발생

→ computed 적용

- 반응형 데이터를 포함하는 복잡한 로직의 경우 미리 값 계산

![image](https://github.com/user-attachments/assets/486262d3-1685-49e9-b97d-a2216d702861)

⇒ 화면에 값을 사용 , return 문장이 있어야 함

특징

- 반환되는 값은 computed ref이며 일반refs와 유사하게 계산 결과를 .value로 참조해야함
- computed 속성은 의존된 반응형 데이터를 자동으로 추적
- 의존하는 데이터가 변경될 때만 재평가
    - restOfTodOs의 계산은 todos에 의존하고 있음
    - 따라서 todos가 변경될 때만 업데이트

주의

- computed 반환 값은 의존하는 데이터 파생 값
- 일종의 snapshot이며 의존하는 데이터가 변경될 때마다 새 snapshot이 생성됨
- snapshot을 변경하는 것은 의미가 없음 - > 계산된 반환값은 읽기 전용으로 취급
- 새 값을 얻기 위해 의존 데이터를 업데이트 해야함

computed 사용시 원본 배열 변경 X

- computed에서 reverse 및 sort 사용 시 원본 배열을 변경하기 때문에 복사본을 만들어서 진행해야함

![image](https://github.com/user-attachments/assets/d996978d-4c6f-401d-9fde-f7bdeacad24b)

spread (즉, 복사본 생성) 해서 뿌려라

### Computed vs Method

computed와 동일한 로직을 처리할 수 있는 method

- computed 속성 대신 method로도 동일한 기능 정의 가능
- 두가지 접근 방식은 실제로 완전히 동일

![image](https://github.com/user-attachments/assets/84dede8f-7b94-462b-84ca-dcf758addc79)

- Computed는 의존된 반응형 데이터를 기반으로 캐시된다.
- 의존하는 데이터가 변경된 경우에만 재평가
- 의존된 반응형 데이터가 변경되지 않는 한 이미 계산된 결과에 대한 여러 참조는 다시 평가할 필요없음

- method는 렌더링 될 때마다 항상 함수 실행

### 적절한 사용처 ?

computed 

- 의존하는 데이터에 따라  결과가 바뀌는 계산된 속성
- 동일한 의존성을 가진 여러 곳에서 사용 시 계산결과 캐싱 → 중복 방지

method

- 단순히 특정 동작 수행
- 데이터 의존 여부와 관계 없이 항상 동일 결과 반환 함수

정리

Computed → 의존 데이터가 변경 시 자동 업데이트

method → 호출해야 실행

- 무조건 computed가 아니라 사용 목적과 상황에 맞게 조합 ..

## v-if

: 표현식 값의 참거짓여부로 렌더링

- v-else direcive를 사용해서 else 블럭 사용 가능

![image](https://github.com/user-attachments/assets/dd65ef71-fdda-48fc-b9fb-ec3c492cd622)

- v-else-if

![image](https://github.com/user-attachments/assets/bd9b1228-c24e-4cb4-a1e7-9e7d1fb7d28a)

여러 요소에 대한 v-if

- directive 특성 상 단일 요소에만 연결
- 이 경우 template 요소에 v-if를 사용하여 하나 이상의 요소에 대해 적용할 수 있음

![image](https://github.com/user-attachments/assets/14b034ca-d6b2-4a69-a1bb-9db5bdab4864)

- 의미없이 묶는 경우 → template 사용 ⇒ 렌더링되지 않고, JS에서 적용 가능
    - 보이지 않는 wrapper

# v-if vs v-show

v-if  : 조건부로 렌더링 (false 라면 아예 disable)

v-show : 조건부로 가시성 전환 (false 라면 display = none)

- 예시 : 항상 렌더링되어 DOM에 남아있음
- CSS display 속성만 전환

![image](https://github.com/user-attachments/assets/ad4c1b61-f1e8-4202-986e-ff1f879f713f)

→ 화면에서 숨긴다. 크기도 0 X 0 

v- if 

- 초기 조건이 false인 경우 아무 작업도 X → cheap
- 토글 비용이 expensive

v-show 

- 초기 조건 상관없이 항상 렌더링 expensive
- 초기 렌더링 비용이 더 높음

⇒  컨텐트를 매우 자주 전환시 show, 실행 중에 조건이 변경되지 않는 경우 if 권장

# List Rendering

v-for

: 소스 데이터를 기반으로 요소 또는 템플릿 블록을 여러 번 렌더링 한다.

(Array, Objet, Number, String, Iterable)

- alias in expression 형식의 특수 구문을 사용해 반복되는 현재 요소에 대한 별칭 제공

![image](https://github.com/user-attachments/assets/e26db2b3-b606-483e-85f7-4fd42f8e672c)

- 인덱스 (객체에서는 키) 에 대한 별칭 지정 가능

![image](https://github.com/user-attachments/assets/61359930-4d35-422a-8442-851a686dbbe5)

- 배열 반복

![image](https://github.com/user-attachments/assets/c6fa0833-f6ff-40b3-b9ba-86977c23d102)

- 객체 반복

![image](https://github.com/user-attachments/assets/d63eaaa0-7842-48fa-9b6f-448f56b2b172)

여러 요소에 대한 v-for 적용 

- Template 사용 ㅋㅋ 딸깍

![image](https://github.com/user-attachments/assets/643df016-8cc5-457b-b6a3-6f0cbcadf9e6)

중첩된 v-for (2중 for문)

- 각 v-for 범위는 상위 범위에 접근할 수 있음

![image](https://github.com/user-attachments/assets/dd1efc4f-b362-4161-ac10-0363620d7d74)

### v-for with key

반드시 v-for와 key를 함께 사용한다 [반복문의 효과를 최적화하기 위해 . . ] 

: 내부 컴포넌트 상태 유지 → 데이터의 예측 가능한 행동 유지하기 위해 ( )

- key는 반드시 각 요소에 대한 고유한 값을 나타낼 수 잇는 식별자여야 함

![image](https://github.com/user-attachments/assets/bc9b3222-e29c-4156-9ed2-c5444b6446e6)

[주의] 배열의 인덱스를 v-for의 key로 사용하지 말 것 XXXXX

- 인덱스는 식별자가 아닌 배열의 항목 위치만 나타냄
    
    → Vue가 DOM을 변경할 때 여러 컴포넌트간 데이터 공유 시 문제 발생
    
- 직접 고유한  값을 만들어내는 메서드 사용 or 외부 라이브러리 활용 → 식별자 역할 영입

![image](https://github.com/user-attachments/assets/f6605fdc-6a27-43fc-9c93-c7e77a10a925)

# v-for with v-if

동일 요소에 v-for과 v-if를 함께 사용하지 않는다.

: v-if가 우선순위가 높기 때문에 변수에 접근 불가 → 근데 이제 오류남

해결법 1 : 애초에 computed를 활용해 필터링된 목록을 반환하여 반복하도록 설정

![image](https://github.com/user-attachments/assets/216af08a-779d-4408-b1fb-1560a5033f9a)

해결법 2 : v-for와 template요소를 사용하여 v-if 위치 이동

![image](https://github.com/user-attachments/assets/b91f336d-5c63-44fb-be4a-f5b05824a3d4)

v-for와 배열 - “배열 변경 감지”

- 수정 메서드 (원본 배열 수정)
    - vue 는 반응형 배열의 변경 메서드가 호출되는 것을 감지하여, 필요한 업데이트를 발생시킴
    - push, pop, shift, unshift, splice, sort, reverse
- 배열 교체
    - 원본  배열을 수정하지 않고 항상 새 배열 반환
    - filter, concat, slice

v-for와 배열 - “필터링 / 정렬 결과 표시”

- 원본 데이터를 수정하거나 교체하지 않고 필터링 되거나 정렬된 결과를 표시
    1. computed 활용
    
![image](https://github.com/user-attachments/assets/961d2ac9-59fc-497e-a0f5-b11702814eee)
    
    1. method 활용 (computed가 불가능한 중첩 v-for 의 경우)
    
![image](https://github.com/user-attachments/assets/c553ebe8-09f9-4ba1-b54a-644ae2b2ded9)
    

스타일 가이드

![image](https://github.com/user-attachments/assets/eaecb3b0-fbfd-400e-ba03-3c77c5b44b5d)

# Watchers

watch() : 반응형 데이터를 감시하고, 감시하는 데이터가 변경되면 콜백 함수를 호출

![image](https://github.com/user-attachments/assets/14caddaf-b1fe-43ce-8be6-96cac66731a2)

- varible : 감시 변수, 반응형이 아닌 경우 getter 처리 (이때는 깊은 감시 원하는 경우 deep : true 처리)
- newValue : variable이 변화된 값, 콜백 함수의 첫번째 인자
- oldValue : 콜백 함수의 두번째 인자

+) 3번째 인자로 옵션 객체 가능

- computed와 watch의 차이

![image](https://github.com/user-attachments/assets/a62e1997-2bb6-47e1-ba89-7d5b7c8bdf20)

computed는 return을 보통 가진다. → 화면 명시 데이터 관련 기능 → real dom 변경 안시키려고

watch → data 관련 기능 → 이걸 기반으로 어떤 기능이 다시 수행되어야 하기 때문에

# 생명주기 훅

![image](https://github.com/user-attachments/assets/e6a6f06b-aa3e-4569-a0ce-a49640711390)

1. setup = JS영역에서 처리 (Composition 방식에서는 create도 setup에서 처리)
2. 컴파일된 템플릿이 
    1. 있다 : continue
    2. 없다 : 컴파일
3. mount 과정 : (가상돔과 리얼돔을 연결한다.)
4. 초기 렌더링 실행 : DOM 노드 생성, 삽입
5. mounted 완료 시 ( 화면에 보여질 시 )  → 리렌더링 및 패치 반복
6. 내려가 (마운트 해제)

Lifecycle Hooks 

: vue 인스턴스 주기동안 특정 시점에 실행되는 함수 → 개발자가 특정 단계에서 의도하는 로직이 실행되도록 함

1. Vue 컴포넌트 인스턴스가 초기 렌더링 및 DOM 요소 생성이 완료된 후 특정 로직 수행

![image](https://github.com/user-attachments/assets/67ed5cc9-05fb-4897-a2ff-eb40d4da957b)

1. 반응형 데이터 변경으로 인해 컴포넌트의 DOM 업데이트 후 특정 로직 수행

![image](https://github.com/user-attachments/assets/a0fe492b-1ba0-4c0e-ba93-855247875aca)

1. 반응형 데이터의 변경으로 인해 컴포넌트의 DOM이 업데이트 된 후 특정 로직 수행 

특징

1. Vue는 라이프사이클에 등록된 콜백 함수들을 인스턴스들과 자동 연결
2. 동작하기 위해 hooks함수들은 반드시 동기적으로 작서 
3. 인스턴스 생애 주기의 여러 단계에서 호출되는 다른 hooks들도 있으며, 가장 일반적으로 사용되는 것은 onMounted, onUpdated, onUnmounted

![image](https://github.com/user-attachments/assets/8424df63-1ad9-4dfc-95c6-64939873fa1d)

Created : 

- Vue 객체가 만들어질 때만 동작
    - Vue 객체가 만들어지고 아직 화면과 연결되지 않은 상태
    - Vue 객체가 사용할 data에 대한 이야기 할 단계
    - Composition 방식에서는 setup 코드에서 created 포함

Mounted : 

- Vue 객체가 DOM에 mount 된 상태
    - DOM 트리가 완성되어 부모 Container 에 삽입 완료된 상태
    - 모든 자식 component들도 mount 완료된 상태
    - DOM 관련 동작을 수행하려 할 때

컴포넌트를 하나로 화면 구성시 재활용성이 떨어진다.

헤더 + 테일 + 바디 컴포넌트 . . . . . 계층적으로 만들어짐
