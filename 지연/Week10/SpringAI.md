# Framework(Spring) : Spring AI
---

# ▶️ Spring AI

- 애플리케이션에 AI를 쉽게 통합할 수 있도록 설계된 모율형 프레임워크

## AI——> 나한테

### Model

정보를 처리하고 생성하도록 설계된 알고리즘 → Chat GPT(Chat Generative Pre-Trained Transfer)

### Prompt

모델이 사용할 질문이나 명령을 생성하는 과정

- Template : prompt 생성 및 관리를 위해 사용하는 템플릿
- Spring AI에서는 String Template 활용

### Embeddings

고차원의 데이터(단어, 이미지)를 저차원의 벡터 공간으로 변환하는 과정

<img width="637" alt="스크린샷 2025-05-11 오후 12 38 32" src="https://github.com/user-attachments/assets/adf261ed-5a5b-42f0-8d8b-e85f08a3bb9b" />

### Tokens

- AI 모델의 작동 방식의 기본 구성 요소
- 모델은 입력 된 문장(prompt)를 토큰으로 변환해서 처리하고 출력시 토큰을 다시 단어로 변환

## 내가——> AI

### Fine Tuning

전통적인 머신 러닝 기법으로 모델을조정하고 내부 가중치를 변경하는 작업

### Prompt Stuffing(프롬프트 채우기) - RAG

사용자의 데이터를 모델에 제공하기 위해 프롬프트 내에 데이터를 삽입하는 방법

### Tool Calling

LLM을 외부 시스템의 API(사용자 정의 서비스)에 연결

<img width="439" alt="image (2)" src="https://github.com/user-attachments/assets/dfc1e501-0c1b-4ec7-97a3-c73d7742fc8c" />

### RAG(Retrieval Augmented Generation: 검색 증강 생성)

정보 검색과 텍스트 생성을 결합하여 사용자 질문에 대한 최적의 답변 제공

# ▶️ 프로젝트 설정

1. pom.xml

```xml
<properties>
    <java.version>17</java.version>
    <spring-ai.version>1.0.0-M6</spring-ai.version>
</properties>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.ai</groupId>
            <artifactId>spring-ai-bom</artifactId>
            <version>${spring-ai.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-openai-spring-boot-starter</artifactId>
</dependency>

```

1. application.properties

```
# spring ai 관련 속성들
# 서비스 키 - 필수
spring.ai.openai.api-key=sk-proj-키는_알아서

# 사용할 OpenAI chat model의 이름
spring.ai.openai.chat.options.model=gpt-4o
# 창의성을 제어하는데 사용되는 샘플링 온도: 높을수록 창의성이 높아짐 - 헛소리할 확률도.. (0~2)
spring.ai.openai.chat.options.temperature=0.7
# 출력과 추론에 사용되는 토큰 수의 상한선
spring.ai.openai.chat.options.maxCompletionTokens=1000

# system prompt
ssafy.ai.system-prompt=You are an artificial intelligence known as an omniscient scholar. When you speak, use {language} and answer with a {character} personality.

```

# ▶️ Chat Clinet API

AI 모델과의 통신을 위한 API

# ▶️ Advisor API

스프링 애플리케이션에서 AI 기반의 상호 작용을 가로채고 수정하고 향상시킬 수 있는 방법

### 핵심 컴포넌트

- AdvisedRequest : 프롬프트 요청 객체
- AdvisedResponse : 채팅 완료 응답을 위한 객체

### 동작 절차

<img width="855" alt="image (3)" src="https://github.com/user-attachments/assets/2135fd05-c858-4ec1-b396-bf1b20a211a0" />
