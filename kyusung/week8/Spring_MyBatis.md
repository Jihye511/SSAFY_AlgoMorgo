# Model

Enterprise Application의 일반적 Model 계층 구조

![image](https://github.com/user-attachments/assets/3d47f910-b599-4359-bf75-d17fe37d1a20)

- Service
    - high level의 기능을 외부로 노출
    - use-case 비즈니스 로직이 구현되는 곳
    - 업무의 단위 (계좌 이체)로 트랜잭션 처리 필요
- Data Access (Repository)
    - 애플리케이션에서 데이터 저장소에 접근하는 계층
    - 단일 쿼리단위 (a계좌에서 입금, B계좌에서 인출)

다양한 DB 접속 기술에 대해 공통된 방식으로 접근

![image](https://github.com/user-attachments/assets/9690cdfc-d089-4100-8d3e-c8b1acd74ebd)

- @Transactional : DB 접근 기술과 무관한 선언적 Transaction 처리
- 리소스 자동 관리 : 사용 후 자동 close

- 범용적인 예외 처리 : DB마다 차이를 Spring이 흡수,별도 분석 필요x

![image](https://github.com/user-attachments/assets/ab41b178-178c-4c88-a420-a0e85dd16980)

런타임이라 예외처리가 편하단다.

# Spring과 MyBatis

- SQL과 Obect를 편리하게 매핑하는 framework

특징 

- 내부적으로 PreparedStatement 활용
    - 하지만 파라미터 설정 코드가 필요없음
- ResultSet 내용을 DTO에 저장하는 코드 필요없음
    - 1회 매핑룰 작성 필요
- 전통적으로 XML에서 SQL 관리
    - @ 사용 기능 추가

구조

![image](https://github.com/user-attachments/assets/b4de682f-d7a1-4460-90e4-85d63213c785)

- mapper.xml : SQL 쿼리를 정의, 등록하며 파라미터 및 결과에 대한 매핑처리
- 설정 파일 : DB접속 설정, Transactuion 등 MyBatis 동작 규칙 정의
    - 스프링 부트에선 application.properties로 통합

커넥션과 mybatis는 관련이 없다.

층이 다름 ( myBatis- hicary -  jdbc )

sql을 밖으로 뽑아내는.. ORM (JPA)

마이바티스는 xml 형태로 뽑아낸다.

```
// mybatis에서 dbutil - Sql session의 관계

```
