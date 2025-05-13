# Spring AI

- 애플리케이션에 AI를 쉽게 통합할 수 있도록 설계된 모듈형 프레임워크
- 엔터프라이즈 애플리케이션의 데이터와 API를 생성형 API와 연결하는데 최적화된 구조 제공
    - 다양한 포터블 API 제공
    - chat text-img 변환, 임베딩 모델 등 지원

![image](https://github.com/user-attachments/assets/1efcbcf6-57d1-4019-bc4c-fccf6607227f)

Model : 정보를 처리하고 생성하도록 설계된 알고리즘 (ex. chat gpt)

- 대규모 데이터 세트에서 패턴과 통찰력을 학습해 추론, 텍스트, 이미지 또는 기타 출력 생성
- GPT : generative Pre-trained Transfer

![image](https://github.com/user-attachments/assets/0684e44c-56e6-404e-a844-f43a7bd2f99d)

Prompt : 모델이 사용할 질문이나 명령을 생성하는 과정

- template : 프롬프트 생성 및 관리를 위해 사용하는 템플릿
- Spring AI : StringTemplate 활용
- 일반적으로 Map 형태의 객체로 교체된 데이터 전달

![image](https://github.com/user-attachments/assets/c84679b6-cbe9-4358-b755-172a98d60558)

Embedding 

- 고차원 데이터를 저차원의 벡터공간으로 변환하는 과정 : 입력간의 관계를 포착
    - 벡터는 원래 데이터의 의미적 특성을 보존한다.
- 텍스트, 이미지, 또는 비디오의 수치적 표현으로 입력간의 관계를 나타냄
- word Embedding

![image](https://github.com/user-attachments/assets/267ae89b-3bf6-4d6b-858c-5c678c34917b)

- RAG 시스템과 임베딩
    - 외부 지식 소스의 문서들을 임베딩으로 변환하여 벡터 DB에 저장
    - 사용자의 질의도 같은 임베딩 공간으로 변환 후 벡터 유사도를 계산하여 관련성이 높은 문서 검색 후 응답 생성

Tokens 

- AI 모델의 작동 방식의 기본구성요소
- 모델은 입력된 문장을 토큰으로 변환해 처리하고 출력 시 토큰을 다시 단어로 변환
- 영어에서 하나의 토큰은 대략 75%
- 컨텍스트 윈도우
    - 단일 API 호출에서 처리할 수 있는 텍스트의 양, 이 제한을 초과하는 텍스트는 처리되지 않음
    - gpt 3 : 4000

Structured Output Converter

- AI 모델의 출력은 전통적으로 단순한 java.lang.String
- JSON으로 출력해줘 → JSON 형태의 문자열로 처리 → JSON 변환 도구 필요

![image](https://github.com/user-attachments/assets/1171194c-1a98-4614-942f-3d47bfad00dd)

사용자의 Data와 API를 모델에 전달하기

- Fine Tuning : 전통적인 머신 러닝 기법, 모델을 조정하고 내부 가중치를 변경하는 작업
    - 매우 고난도, 리소스 집약도 높음. 폐쇄형 모델도 존재 → 처리 불가
- prmpt Stuffing : 프롬프트 채우기 - RAG
    - 사용자의 데이터를 모델에 제공하기 위해 프롬프트 내에 데이터를 삽입

![image](https://github.com/user-attachments/assets/24c65131-8c7a-43d5-9102-bd59269cca8d)

- Tool Calling
    - LLM을 외부 시스템의 API (사용자 정의 서비스)에 연결

RAG : 검색 증강 생성

- 정보 검색과 텍스트 생성을 결합하여 사용자 질문에 대한 최적 답변 생성
- 정보 검색 : 사용자 질문에 대한 정보를 포함하는 문서, 벡터DB 활용
- 텍스트 생성 : 검색된 정보를 바탕으로 새로운 텍스트 생성 - 자연어 처리 기술 활용

![image](https://github.com/user-attachments/assets/6946cea2-1b95-470a-9355-9e2a098479df)

Tool Calling

- LLM은 학습될 때가지의 지식으로 동작 - 실시간 정보를 반영하기 어려움
    - LLM을 외부 시스템 API와 연결해서 실시간 데이터를 제공하고 데이터 처리 작업 수행
    - SPring aI에서는 해당 함수를 @Tool 형태로 만들어 프롬프트 옵션으로 전달 가능

![image](https://github.com/user-attachments/assets/f118c963-d3f4-4fff-ada4-b3bc21069613)

# Chat Client API

# Advisor API
