- 자바에서는 DB에 접속하기 위해서는 JDBC사용 필연적
    - Spring에서 좀 더 편하게 Spring JDBC가 있다
        - 거의 쓰지 않는다
    - MyBatis, JPS lib를 사용
        - Spring에서 MyBatis Spring, Spring Data JPA등을 만들어서 지원
        - @Transactional : DB접근 기술과 무관하게 선언적 Transaction 처리
        - 리소스 자동관리
        - 범용적인 예외 처리 :

### MyBatis

- SQL과 Object를 편리하게 맵핑 하는 프레임 워크
- 기존 JDBC는 하나씩 ResultSet를 사용해서 DTO에 저장하기 위해서 코드를 사용
    - MyBats는 1회만 매핑해두면 재장성 불필요
- 내부적으로는 PreparedStatement를 사용하지만 파라미터를 설정하기 위한 코드가 존재하지 않음
- 전통적으로 XML에서 SQL 관리

![image](https://github.com/user-attachments/assets/885aec5e-2b40-4b87-bd49-52744ec62ba8)


### MyBatis 관련 설정

- 기본적으로 DBUtil에 있는것들 applicationproperties로 옮기기
- DTO 있는 곳 지정
    - mybatis.type-aliases-package=
- mapper 경로 설정
    - mybatis.mapper-locations=

- Dao interface
    - 이제는 DB관련 모든 예외들이 런타임 Exception
    - Connection에 대한 관리는 이제 Spring Framework가 담당 - 코드적으로 Connection을 전달하지 않음
    - Dao는 Mapper로써 runtime에 구현체에 대한 proxy생성

### Mapper 작성

- <mapper> : mapper파일의 root element
    - namespace : 각각의 매퍼 파일을 구별하기 위한 고유 이름
        - 그냥 경로 그대로 쓰기
- <insert> : 처리하려는 작업의 성격에 따라 insert,update,delete,select.tag 제공

![image](https://github.com/user-attachments/assets/c95cb7b9-eac6-4c01-8d8b-068439182dbd)


- 기본적으로 ‘#’을 사용한다
    - $ 아님

### resultMap

- DB와 자바에서 변수명과 컬럼명을 매번 매핑하기 어려움
- resultMap를 사용해서 매핑
