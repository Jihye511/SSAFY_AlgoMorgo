![image](https://github.com/user-attachments/assets/067a9092-7b46-4399-be16-0c5944cda3dd)


- `@Transactional` : DB 접근 기술과 무관한 선언적 Transaction 처리
- 리소스 자동관리 - 사용 후 자동 close
- 범용적인 예외 처리 : DB 마다으 ㅣ차이를 Spring이 흡수, 별도로 분석할 필요가 없음

# ✅MyBatis

> SQL 과 Object를 편리하게 맵핑하는 framework
> 

### **✏**특징

- 내부적으로 PreparedStatement 사용
    - 하지만 파라미터를 설정하기 위한 코드가 필요 없음
- ResultSet 내용을 DTO에 저장하시 위한 코드가 필요 없음
    - 1회 매핑 룰 작성 필요
- 전통적으로 XML에서 SQL을 관리
    - @ 사용 기능 추가

### **✏구조**

![image](https://github.com/user-attachments/assets/986088af-85c3-48c4-a985-d88c40c81d00)


- mapper xml
    - SQL쿼리를 정의 해서 등록하며 파라미터 및 결과에 대한 매핑 처리
- 설정파일: DB 접속 설정, Transaction 등 MyBatis 동작 규칙 정의
    - Spring Boot 에서는 application.properties로 통합

### Dao interface

- Connection에 대한 관리는 Spring Framework가 담당 - 코드적으로 Connection을 전달하지 않음
    - Dao의 Connection Parameter 삭제
- DB관련 예외는 RuntimeException 계열로 전달
    - throws SQLException 제거
- Dao는 mapper로써 runtime에 구현체에 대한 proxy 생성
    - 기존의 Dao 구현체(BasicXXDao)들은 불필요

## mapper 작성

- parameterType은 메서드에서 추정 가능하므로 생략가능

| 엘리먼트 | 설명 |
| --- | --- |
| resultMap | - 결과 매핑을 사용하기 위한 최 상위 엘리먼트
- 매핑을 구분하기 위한  id 속성과 매핑 대상 클래스를 정의하는 type 속성 포함 |

### 💡MyBatis VS JPA

### ✅ **기본 개념**

| 구분 | MyBatis | JPA (Hibernate 등) |
| --- | --- | --- |
| 타입 | SQL 매퍼 프레임워크 | ORM (Object-Relational Mapping) 프레임워크 |
| 접근 방식 | SQL 중심 (SQL을 직접 작성) | 객체 중심 (SQL 자동 생성) |
| 목표 | SQL을 손쉽게 매핑 | 자바 객체와 DB 테이블 간 자동 매핑 |

---

### ✅ **작성 방식의 차이**

| 항목 | MyBatis | JPA |
| --- | --- | --- |
| SQL 작성 | 직접 XML이나 어노테이션으로 작성 | 필요시만 작성 (JPQL 또는 Native Query) |
| 쿼리 제어 | 개발자가 SQL 로직 직접 제어 | JPA가 SQL 생성 및 실행 자동 처리 |
| 매핑 방식 | XML 또는 어노테이션 기반 SQL 매핑 | 어노테이션 기반 객체-테이블 매핑 |

---

### ✅ **장단점 비교**

| 구분 | MyBatis | JPA |
| --- | --- | --- |
| 장점 | <ul><li>SQL 직접 제어 → 복잡한 쿼리 대응 유리</li><li>직관적이고 디버깅 쉬움</li></ul> | <ul><li>생산성 높음 (자동 매핑)</li><li>객체 지향 설계와 잘 어울림</li><li>캐시, 지연 로딩, 변경 감지 등 고급 기능 제공</li></ul> |
| 단점 | <ul><li>코드 양 많고 반복적</li><li>비즈니스 로직과 SQL 분리 어려움</li></ul> | <ul><li>학습 곡선 존재</li><li>자동 SQL이 복잡한 쿼리엔 불리</li><li>JPA의 캐시, 연관관계 관리 실수시 버그 발생</li></ul> |

---

### ✅ **어떤 상황에 어떤 걸 쓰면 좋을까?**

| 상황 | 추천 프레임워크 |
| --- | --- |
| SQL을 직접 컨트롤하고 싶은 경우 | ✅ MyBatis |
| 간단한 CRUD 중심 + 객체 지향 모델 사용 | ✅ JPA |
| 복잡한 JOIN, 프로시저 연동이 많은 시스템 | MyBatis 선호 |
| 생산성과 유지보수를 중요시하는 시스템 | JPA 선호 |

## **✏**✅
