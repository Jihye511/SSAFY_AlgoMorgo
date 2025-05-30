# 데이터베이스 모델링

## DataBase Modeling

- 현실 세계의 데이터를 체계적으로 분석하여 데이터베이스의 구조를 설계하는 과정
- 데이터의 논리적, 물리적 구조를 정의

### 필요성

- 데이터 무결성 유지
- 중복 최소화
- 효율적인 데이터 검색
- 유지보수 용이성

### 과정

1. 요구사항 수집 및 분석 
    1. 정보를 체계적으로 정리하고 분석하여 개발할 데이터베이스의 용도파악
    2. 비즈니스 규칙 도출 및 분석
    3. db가 저장하고 관리해야 할 데이터의 구조, 관계, 제약 조건 등을 명확히 정의하기 위해 요구사항을 분석하고 정리
2. 개념적 데이터 모델링
3. 논리적 데이터모델링
4. 물리적 데이터 모델링

**⇒데이터 베이스 구축**

## 개념적 데이터 모델링

- 개체의 유형
    - 유/무형에 따라
        - 유형 엔티티 : 물리적 형태
            - 사원, 상품, 도서
        - 개념 엔티티 : 물리적 형태는 없지만 관리해야 할 개념적 정보를 나타냄
            - 부서, 보험상품, 장속
        - 사건 엔티티 : 업무를 수행함에 따라 발생
            - 주문, 청구 ,대출
    - 발생 시점에 따라
        - 기본 엔티티 : 타 엔티티에 의존x , 독립적으로 생성 가능
            - 사원, 상품 ,고객
        - 중심 엔티티 : 기본 엔티티로부터 발생하느 엔티티로 업무에서 중심적인 역할
            - 주문, 청구, 계약
        - 행위 엔티티 : 중심 엔티티로부터 보다 더 세부적인 정보를 위해 파생된 엔티티
            - 주문 목록, 계약진행
- 개체 식별
    - 명사로 된 단어 찾기
        - 개체와 속성의후보
    - 비즈니스 분석 범위 내의 명사만 찾기
    - 찾은 명사 중 같은 의미로 사용되면서 다르게 표현되는 단어는 버릴 것
    - 여러개의 속성으로 구성될 수 있는 지 체크
    - 집합을 표현하는 지 체크
- 개체 검증
- 속성
    - 하나의 엔티티에 종속되는 명사적 단어
    - 엔티티를 ㅎ표현하는 특성, 상

---
![image](https://github.com/user-attachments/assets/f3a9d2ac-db80-46bc-81f2-1a32432086b0)


- 데이터 왜 사용? → 재사용위해
- 여러건의 데이터 → 구별가능해야함
- 유니크 &  NOT NULL & 엔티티의 속성을 대표할 수 있는 값 &

## 논리적  데이터 모델링

![image](https://github.com/user-attachments/assets/f3c12498-5d76-4827-9b9f-ed112e3bd2c8)


### 정규화

- 애트리뷰트 갂에 존재하는 함수적 종속성을 분석해서 관계형 스키마를 더
좋은 구조로 정제해 나가는 일렦의 과정
- 데이터의 중복의 제거하고 속성들을 본래의 제자리에 위치시키는 것
- 필수적인 건 아님( 더 좋은 구조로안만들면 되니까?)

### 제1 정규화

- 반복되는 데이터 정리

### 제2 정규화

- 테이블이 많아지면 관리할 테이블이 늘어남 → 어떤게 이득인 큰가… 현업에선 많이 싸움
- 복합키를 가진 애만 제 2정규화의 대상



![image](https://github.com/user-attachments/assets/e0cb1980-2189-4de5-a862-bb3567a52bac)


### 제3 정규화

- 기본키에 의존이 아니라 일반 컬럼에 의존하는 컬럼들을 제거
- 이행적 함수 종속관계 : 일반 컬럼에 의존

## 물리적 데이터 모델링

- DBMS 특성에 맞게 실제 데이터베이스 내의 개체들을 정의하는 단계
- 성능, 무결성, 보안 등을 고려하여 최적의 데이터베이스 구조를 설계
- 역정규화
    - 정규화된 데이터를 성능 향상을 위해 일부 중복을 허용하거나 다시 합치는 과정
    - 테이블을 병합하거나, 조회 속도를 높이기 위해 중복데이터를 허용하는 작업이 포함
- 산출물
    - 물리 erd

<aside>
💡

INDEX

- 고속도로 타는 거
- BUT 너무 많은 인덱스는 비효율
</aside>
