package ohm.ohm.service;

import com.ohm.entity.Gym.Gym;
import com.ohm.entity.Post.Post;
import com.ohm.repository.gym.GymRepository;
import com.ohm.repository.post.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
@ExtendWith(MockitoExtension.class)
@Rollback(value = false)
public class PostServiceTest {

//    @InjectMocks
//    private

}