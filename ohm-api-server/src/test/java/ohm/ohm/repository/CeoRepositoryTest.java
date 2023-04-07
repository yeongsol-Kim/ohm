package ohm.ohm.repository;


import com.ohm.entity.Ceo.Ceo;
import com.ohm.repository.ceo.CeoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@DataJpaTest는 기본적으로 @Transactional 내장 , 테스트가ㅣ 끝나면 자동으로 롤백
@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CeoRepositoryTest {

    @Autowired
    private CeoRepository ceoRepository;

    @Test
    void ceo저장(){
        Ceo ceo = Ceo.builder()
                .nickname("testceo")
                .nickname("testnickname")
                .build();

        Ceo save = ceoRepository.save(ceo);
        Assertions.assertThat(save.getNickname()).isEqualTo(ceo.getNickname());
    }
}
