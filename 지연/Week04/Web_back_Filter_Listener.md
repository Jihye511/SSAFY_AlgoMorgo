## ▶️ Filter

- 웹 컴포넌트가 실행되기 전/후에 요청과 응답을 가로채서 부가적인 기능을 수행하기 위한 웹 컴포넌트

### Filter Chain

여러 개의 필터가 모여 필터 체인 구성 가능

### Filter의 주요 용도

- 인증 및 권한 확인
- 로깅
    - 어떤 요청, 어떤 응답이 있었는지 추적
- 보안
    - 크로스 사이트 스크립팅(XSS)공격 등을 방지하기 위한 입력 값 검증

### SessionFilter

- 인증이 필요한 요청에 대해 전처리로 동작하는 Filter
    
    미 인증 사용자는 인증 처리 페이지로 redirect
    

## ▶️ Pgaing

- 많은 데이터의 일부분만 가져오는 기능

### SearchCondition

```
public class SearchCondition{
private String key;
private String word;
private int currentPage;
private int itemsPerPage;

public boolean hasKeyword(){
return key != null && !key.isBlank() &&
word != null && !word.isBlank();
}

}
```

### page

```
public class Page<T>{
private SearchCondition condition;  //검색조건
private int navSize = 5;            //네비게이션 사이즈
private int totalItems;             //전체 항목 수
private List<T> List;                 //보여줄 데이터
private int totalPages;             //전체 페이지 수
boolean hasPre;                     //[이전] 표시 여부
boolean hasNest;                    //[다음] 표시 여부
int startPage;                      //시작 페이지 번호
int endPage;                        //끝 페이지 번
}
```

## ▶️ Listener

- 웹 애플리케이션에서 발생하는 이벤트에 대한 모니터링 객체
- ServletContextListener
    - 웹 애플리케이션 생성에서 소멸까지의 주요 사항에 대해서 모니터링
    - 개별 서블릿 동작 전에 초기화하는데 비용이 ㅁ낳이 드는 공유 자원의 초기화에 주로 사

## ▶️ Exception처리

- 어떠한 경우도 예외에 대한 정보가 클라이언트에게 직접 전달되지 않도록 처리 필요

### 404 오류 처리 과정

- front controller까지 진입 후 처리할 sub controller를 발견하지 못한 경우
    
    > 즉, Front Controller(Servlet)는 요청을 받았지만 해당 요청을 처리할 세부 컨트롤러(Sub Controller)가 없는 경우입니다.
    > 
    - **요청 경로는 존재하나, 해당 경로에 매핑된 Sub Controller 없음**
        - 예: `/member/delete` 요청 → Front Controller는 받았지만 해당 경로를 처리할 Sub Controller가 없음
    - **처리 방법**
        - `RequestDispatcher.forward()`를 사용하여 **커스텀 404 페이지로 포워드 처리** 가능
            
            → 사용자가 지정한 예쁜 404 페이지로 이동
            
        - 또는 `response.sendError(404)`로 **404 오류를 명시적으로 발생시킴**
            
            → WAS(Web Application Server)가 기본 404 페이지로 응답 처리
            
- front controller까지 진입하지 못한 요청
    
    > 즉, 요청 자체가 Front Controller Servlet에 도달하지 못한 경우입니다.
    > 
    - **요청한 URL이 아예 존재하지 않음 (Servlet 매핑되지 않음)**
        - 예: `/abc/xyz` 요청 → 어떤 Servlet에도 매핑되지 않음
    - **처리 방법**
        - Front Controller조차 실행되지 않았기 때문에, 애플리케이션 차원에서 처리 불가
        - WAS가 자동으로 **기본 404 에러 페이지**를 응답함

### 500 오류 처리 과정

- front controller에서 try~ catch로 Checked Exception처리 후 관련 페이지에 에러 메시지 전달
    - 예외를 직접 잡고, 사용자에게 에러 메시지를 보여주는 방식
    - 에러 메시지를 JSP에 전달하여 커스텀 에러 페이지 표시
- WAS로 예외 전달

### WAS의 예외 처리 활용하기
