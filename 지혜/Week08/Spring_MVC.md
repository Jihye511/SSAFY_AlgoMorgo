# Spring MVC 개요

### Spring@MVC 구성 요소

- DispatcherServlet→요청받기
    - Spring@MVC의 가장 중추적인 역할
    - Front Controller Pattern의 적용으로 client의 모든 request 접수
    - 주로 다른 객체들에게 위임하여 처리
- Spring@MVC infrastructure Components(제공됨)
    - HandlerMapping → “이런 request를 받았는데 어떤 Handler가 처리할 수 있나요?”
    - HandlerAdapters →”XXHandler에게 request 좀 처리해달라고 해~”
    - ViewResolvers →”이런 뷰가 필요한데 부탁해요~”


![image](https://github.com/user-attachments/assets/4ad0741a-6445-4df8-a27a-3a14d357a2ba)



![image](https://github.com/user-attachments/assets/81b25d7e-208f-4e27-ad18-ac29aaddb7c9)


## 다양한 응답의 형태

- 그냥 보내면 forward
    - return “index”;
- redirect: 붙이면 redirect
    - return “redirect:/”;

# `@Controller` 단위 테스트

- 기존의 테스팅 : 직접 호출하는 구조로 웹 환경에 대한 설정( WAS의 개입)이 없음


![image](https://github.com/user-attachments/assets/44578832-cfee-4bd3-91d6-b76d99ffaa9f)


- Mock 환경 : 실제 was에서 Spring@MVC와 소통하는 부분을 가상으로 제공

![image](https://github.com/user-attachments/assets/63caaf8f-bb9e-46e2-8e3d-2801d3a3ec9d)



![image](https://github.com/user-attachments/assets/e4881311-b3b4-4e40-8a4d-a06818201695)


# Handler 메서드의 파라미터

## Redirection 과 Flash Scope

- Redirect 시의 데이터 전달 기법
    - flash Scope: Redirect가 완료될 때까지만 저장되는 Spring 지원 scope
        - request<flash<session
            - 내부적으로는 한 번으 ㅣrequest 후 사라지는 session scope
