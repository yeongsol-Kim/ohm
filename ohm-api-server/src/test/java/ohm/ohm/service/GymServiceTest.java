package ohm.ohm.service;

import com.ohm.config.AppConfig;
import com.ohm.service.GymService;
import ohm.ohm.dto.GymDto.GymDto;
import ohm.ohm.entity.Gym.Gym;
import com.ohm.repository.gym.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@Rollback(value = false)
public class GymServiceTest {

    @Autowired
    GymService gymService;

    @Autowired
    GymRepository gymRepository;

    @Autowired
    AppConfig appConfig;








//    @Test
//    public void currentcount_test() throws Exception{
//        GymDto gymDto = new GymDto("HIGYM",10,5);
//        Gym map = appConfig.modelMapper().map(gymDto, Gym.class);
//        Gym save = gymRepository.save(map);
//
//        int i = gymService.current_count(save.getId());
//        Assertions.assertThat(i).isEqualTo(5);
//    }




}