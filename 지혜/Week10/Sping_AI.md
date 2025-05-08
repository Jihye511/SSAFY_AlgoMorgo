## ✅ **Spring AI의 주요 기능**

1. **AI 모델 호출 지원**
    - OpenAI, Azure OpenAI, HuggingFace 같은 **AI API에 쉽게 요청을 보낼 수 있는 인터페이스 제공**
    - `ChatClient` → 메시지를 보내고 응답 받기
2. **프롬프트 템플릿 지원**
    - AI에 보낼 프롬프트를 코드가 아닌 **템플릿 파일**로 관리
    - Spring에서 `@Value`로 파일 불러오듯이 사용 가능
3. **벡터 데이터베이스 연동**
    - Pinecone, Weaviate, Milvus, Chroma 등 **Vector DB와 연동**
    - AI 응답 + 벡터 검색 결합한 **RAG (Retrieval Augmented Generation)** 구현 가능
    - `RAG`
        - 정보검색과 텍스트 생성을 결합하여 사용자 질문에 대한 최적의 답변 제공
        - 정보 검색 : 사용자의 질문에 관련된 정보를 포함하는 문서 검색 - 벡터  데이터베이스 활용
        - 텍스트 생성: 검색된 정보를 바탕으로 새로운 텍스트 생성 - 자연어 처리 기술 활용
4. **스프링 부트 스타터 제공**
    - `spring-ai-openai-starter`, `spring-ai-pinecone-starter` 같은 의존성만 추가하면 자동 설정
    - Spring DI/IoC 기반으로 쉽게 Bean 등록

## ✅ **Spring AI Built-in Advisors**

- Spring AI에서 **Advisor**는 **AI 기능을 AOP(Aspect Oriented Programming) 방식으로 메서드에 결합하도록 도와주는 컴포넌트**

👉 **특정 메서드를 호출할 때 AI 응답 로직을 자동으로 실행하도록 도와주는 역할**

👉 **프롬프트 생성, AI API 호출, 응답 처리 → 전부 어드바이저가 자동으로 처리**

<aside>
💡

즉, 메서드에 **애너테이션(@)** 하나만 붙이면 → Spring AI의 내장된 어드바이저가 동작해서

AI로부터 결과를 가져오고 → 메서드 리턴값에 세팅해주는 구조!

</aside>
