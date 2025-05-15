Framework:(Vue.js) : Component State Flow
### Prop Drilling

- 큰 컴포넌트 트리에서 조상 컴포넌트가 깊이 중첩된 자식 컴포넌트에게 정보를 전달하기는 번거로움

### 상위 component의 provide

- 상위 component의 인스턴스 내부에 provide 객체 생성
- value는 반응형을 포함한 모든 값 사용 가능
- 반응형 사용 시 하위 component와 양방향 소통 가능
- 앱 수준의 provide
    - 개별 component가 아닌 앱 수준에서 공유할 데이터 등록

## inject

- 하위 component에서 조상 component가 provide한 내용 조회
- 상위 component로 올라가면서 key로 provide된 데이터 추적

## 반응성 값 사용 시 문제점

props와 달리 양방향의 반응성을 가지므로 의도치 않은 값의 조작 발생 가능

- 대책 1 : readonly()로 감싸서 제공
    - 상위 component에ㅛㅓ의 변경은 연관된 모든 하위 component에 영향을 줌
    - 하위 component에서의 변경은 warning 발생, 스스로는 변경되지만 상위 컴포넌트로 전파하지는 않음
- 대책 2 : provide 시 validation 함수와 함께 객체 형태로 전달
    - 이 경우라도 하위 component가 함수를 통하지 않고 직접 값을 변경하는 것을 막지는 못함

![image.png](attachment:ea22a00b-99d5-49ab-916a-36a1b10503d8:image.png)

# Slot

상위 component에서 하위 component로 XX를 전달하는 방법

- props : 정보 전달 → 하위 component는 그 정보를 활용
    - 정보의 형태는 단순 데이터나 객체 등
    - html 형태의 템플릿 정보를 전달하기는 어려움
- slot : 템플릿 조각 전달 → 하위 component는 그 템플릿을 렌더링

- 하위 component에서 <slot>을 통해 상위 component가 전달해준 template을 표현할 자리 표시

### 대체 content

- slot에 대한 대체(fallback) content 지정 → content가 제공되지 않을 때만 rendering 처리

### component 간  data 활용

- slot content는 상위 component 소속
    - 하위 component에서 렌더링 되지만 상위 component의 데이터 범위에 접근 가능
    - slot content는 하위 component에서는 단순히 rendering 되므로 하위 component의 데이터에 접근은 불가
        - 필요 시 하위 component에서 상위 component로 데이터 전달 가능
        - <slot>을 제공한 하위 component가 오히려 상위 component, slot content가 하위 component 역할
    - slot content는 v-slot을 통해 전달받은 값 활용

### named slot

- 하나의 component에 여러 개의 <skot>을 작성하고 이들을 구별하기 위한 이름 부여
    - 특히 layout을 구성하는 경우 유용
- 이름을 지정할 때 <slot>의 name 속성 활용
- 이름 있는 <slot>에 content를 공급할 때는 <template> 엘리먼트와 함께 v-slot으로 slot이름 전달
    - v-slot:slot_name은 #slot_name으로 축약 가능
