- 애플리케이션에 AI를 쉽게 통합할 수 있도록 설계된 모듈형 프레임워크
    - 기업 데이터 및 API와 다양한 AI모델을 효율적으로 연결 가능
    
    ![image](https://github.com/user-attachments/assets/2f0cb89c-6675-48b3-97d9-b62192b29b51)

    
    - 만들어져있는 AI를 쉽게 가져다 쓸수 있도록 해주는 프레임 워크
- Model : 정보를 처리하고 생성하도록 설계된 알고리즘

- Prompt
    - 맵 형태로 전달
    - String Template
- Redis = 벡터 DB
- Tokens
    - AI 모델의 작동방식의 기본 구성 요소
    - 모델은 입력된 문장을 토큰으로 변환시켜 처리 → 다시 단어로 변환
- Structured Output Converter
    - AI모델의 출력은 전통적으로 단순한 java.lang.String
    - JSON으로 변환해줄 도구 필요
    

### RAG(Retrieval Augmented Generation : 검색 증강 생성)

- 정보 검색과 텍스트 생성을 결합하여 사용자 질문에 대한 최적의 답변 제공

### RAG 처리 흐름

- 사용자의 데이터 모델에 제공하기 위해 프롬프트 내에 데이터를 삽입하는 방법

```
[1] 사용자의 질문
 ↓
[2] 질문을 벡터로 변환
 ↓
[3] 벡터DB에서 관련 문서 검색 (ex. 삼성전자 리포트)
 ↓
[4] 찾아온 문서를 LLM에 함께 전달 (프롬프트에 삽입)
 ↓
[5] LLM이 문서 내용을 기반으로 대답 생성

```

- Tool Calling
    - LLM은 학습했을때까지의 지식으로 동작하기 때문에 **실시간 정보를 반영하기 어려움**
        - 외부 API와 연동하여 실시간 데이터에 대한 응답 가능
        - @Tool형태로 만들어 프롬프트 옵션 전달 가능

### Advisor API

- 스프링 애플리케이션에서 AI기반의 상호 작용을 가로채고 수정하고 향상시킬 수 있는 방법
    - 반복되는 생성 AI패턴에 대한 캡슐화
    - LLM과 주고받는 데이터의 변환
- Advised Request
    - 요청 객체
- AdvisedResponse
    - 채팅 완료 응답을 위한 객체
