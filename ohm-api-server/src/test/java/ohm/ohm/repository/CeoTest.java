package ohm.ohm.repository;


import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

//@DataJpaTest는 기본적으로 @Transactional 내장 , 테스트가ㅣ 끝나면 자동으로 롤백
@DataJpaTest
public class CeoTest {
}
