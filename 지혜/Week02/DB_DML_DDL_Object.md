# DML

### INSERT

- 새로운 행 삽입
- 레코드 수 증가
- 단일 행 삽입

![image.png](attachment:4e7b05da-1d2a-49a5-8c4e-ba23f7124764:image.png)

- INTO절 컬럼 리스트 명시X → 테이블 생성시 정의한 모든 컬럼을생성 순서와 동일한 순서로 입력
- 다중 행 삽입
- 생략 가능한 컬럼
    - NULL이 허용된 컬럼
    - DEFAULT가 설정된 컬럼
    - AUTO_INCREMENT가 설정된 컬럼 (한테이블 당 하나)

### UPDATE

- 기존 데이터를 수정
- 레코드수 변화X

### UPDATE와 JOIN

- 수정에 쓰일 데이터를 조인된 결과에서 가져와서 처리

# 트랜잭션

- ALL or NOTHING
- 하나의 논리적인 작업 단위
- 특징(ACID)

| 특징 | 의미 |
| --- | --- |
| Atomicity(원자성) | 트랜잭션 내의 모든 연산이 성공하거나 하나라도 실패하면 전체가 취소됨 |
| Consistenct(일관성) | 트랜잭션이 실행되기 전과 후의 데이터베이스 상태가 일관성 유지
데이터의 무결성 및 비즈니스 규칙이 항상 유지되느 상태를 의미 |
| Isolation(고립성) | 하나의 트랜잭션이 완료될 때까지 다른 트랜잭션이 접근하지 못함
 |
| Durability(지속성) | 트랜잭션이 성공적으로 완료되면 그 결과가 영구적으로 반영 |

<aside>
💡

**잠금** : 동시성을 제어하기 위한 기능

**격리 수준** : 하나 또는 여러 트랜잭션 간의 작업 내용을 어떻게 공유하고 차단할 것인지를 결정하는 레벨

</aside>

- mysql의 default ⇒ REPEATABLE READ

![image.png](attachment:804f131c-16f1-4b82-a27d-132a17baa693:image.png)

- 트랜잭션 성능 최적화
    - 트랜잭션 범위를 최소화하여 잠금 유지 시간을 줄임
    - 적절한 격리 수준 선택하여 동시성 개선
- TCL

| 명령어 | 의미 |
| --- | --- |
| START TRANSACTION | 명시적인 트랜잭션 시작 |
| COMMIT | 트랜잭션 성공적으로 완료하고 변경 사항 저장 |
| ROLLBACK [TO SAVEPOINT명] | 트랜잭션 취소하고 변경 사항 되돌림 |
| SAVEPOINT SAVEPOINT명 | 트랜잭션 내에서 특정 지점을 저장하여 부분 롤백을 가능하게 함 |

![image.png](attachment:94d75386-b26c-4a52-87ac-4b00c7ea1a70:image.png)

# VIEW

- 테이블이나 또 다른 뷰를 기반으로 한 논리적인 가상의 테이블
