package ohm.ohm.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@ExtendWith(MockitoExtension.class)
@Rollback(value = false)
public class PostServiceTest {

//    @InjectMocks
//    private

}