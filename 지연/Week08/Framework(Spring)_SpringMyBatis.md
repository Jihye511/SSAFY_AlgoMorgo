# Framework(Spring) : SpringMyBatis
---

## ▶️ MyBatis 연동

### MyBatis란?

- Persistence framework
- SQL과 Object를 편리하게 맵핑하는 framework
  
![image (24)](https://github.com/user-attachments/assets/9407cb0f-8398-4adf-837c-fd9ba0360b1b)

### Dao interface

- Connection에 대한 관리는 Spring Framework가 담당 ⇒ Connection Parameter삭제
- throws SQLException 제거

```java
public interface MemberDao {
    int insert(Connection con, Member member) throws SQLException;
}

------------------------------------------------ 수정

@Mapper
public interface MemberDao {
    int insert(Member member);
}
```

### mapper 연결

- DAO interface
- @Configuration
    - @MapperSCan으로 @Mapper검색

### mapper 작성

```java
  **기존코드**
  
  @Override
    public int insert( Member member)  {
        String sql = "insert into member (name, email, password) values(?,?,?)";
        int result = -1;
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getEmail());
            pstmt.setString(3, member.getPassword());
            result = pstmt.executeUpdate();
        }
        return result;
    }
    
--------------

**수정 후  member.xm**

<insert id="insert" parameterType="Member">
insert into member(name,email,password) 
values(#{name},#{email},#{password})
</insert>

```

```java
@Transactional // test에서는 자동으로 rollback

	@Test
	public void insertTest() {
		// TODO: 03-3. MemberDao의 insert를 테스트해보자.
		log.debug("mdao: {}", mDao);
		Member member = Member.builder().email("001@ssafy.com").name("hone").password("1234").build();
		int result = mDao.insert(member);
		Assertions.assertEquals(1, result);
		// END
	}
	
	+ test를 돌릴때 email 이 primary key 이기때문에 두번째부터 중복값이 들어가면서 
	오류가 날 수 있음 
	@Transactional을 쓰면 test할때 rollback되면서 위의 문제점 해결
	
```

### 조회 결과 매핑

- Database에서 select한 결과를 java Object에 매핑
    - 컬럼 명에 해당하는 DTO의 setter가 있다면 자동 매핑
    - 없을 경우 mapper에서 alias를 줘서 처리
        
        ```java
        <select id = "select">
            select*, detail_address as detailAddress from address where ano=#{ano}
            </select>
        ```
        
- resultMap을 이용한 결과 매핑 → 별도의 alias 안줘도 됨
    
    ```java
    <resultMap type = "Address" id = "addressMap">
    	<result column = "detail_address" property="detailAddress"/?
    </resultMap>
    ```
    

### has-one 관계 처리

- <association> : resultMap의 하위 태그
    - property : 연동할 객체의 속성 명
    - column :관계에서 사용된 컬럼 명
    - resultMap : 다른 resultMap 재사용
    - columnPrefix : 두 테이블의 컬럼 이름이 동일해서 구별할 때

```java
 <resultMap type="Address" id="addressMap">
        <id column="ano" property="ano" />
        <result column="mno" property="mno" />
        <result column="title" property="title" />
        <result column="address" property="address" />
        <result column="detail_address" property="detailAddress" />
        <result column="x" property="x" />
        <result column="y" property="y" />
    </resultMap>

    <resultMap type="Address" id="addressMemberMap" extends="addressMap">
        <association property="member" column="mno" 
        resultMap="com.ssafy.live.model.dao.MemberDao.memberMap"></association>
    </resultMap>

```

### has-many 관계 처리

- <collection> : resultMap의 하위 태그
    - property : 연동할 객체의 속성 명
    - column :관계에서 사용된 컬럼 명
    - ofType : List에 담길 N에 해당하는 객체의 타입
    - resultMap : 다른 resultMap 재사용
    - columnPrefix : 두 테이블의 컬럼 이름이 동일해서 구별할 때
    - notNullColumn : 해당값이 null일경우 collection 추가 안함
