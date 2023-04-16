package ohm.ohm.service;

import com.ohm.config.AppConfig;
import com.ohm.dto.requestDto.GymRequestDto;
import com.ohm.entity.Ceo.Ceo;
import com.ohm.entity.Gym.Gym;
import com.ohm.repository.ceo.CeoRepository;
import com.ohm.service.GymService;
import com.ohm.repository.gym.GymRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class GymServiceTest {

    @Mock
    private GymRepository gymRepository;

    @Mock
    private CeoRepository ceoRepository;

    @Spy
    private AppConfig appConfig;

    @InjectMocks
    private GymService gymService;

    //registerGym
    @Test
    public void gym등록() throws Exception {
        //given
        Ceo ceo = createCeo();
        //when
        given(ceoRepository.findById(any())).willReturn(Optional.ofNullable(ceo));
        given(gymRepository.save(any())).willReturn(createGymEntity());
        Long aLong = gymService.registerGym(createGymRequestDto(), ceo.getId());

        //then
        Assertions.assertThat(aLong).isNotNull();
    }

//    @Test
//    public void gym이름검색() throws Exception{
//        //given
//
//        //when
//        given(gymRepository.findByNameContaining())
//
//        //then
//    }


    public Gym createGymEntity() {
        return Gym.builder()
                .id(3L)
                .address("헬스장주소")
                .area("500평")
                .count(100)
                .build();
    }

    public GymRequestDto createGymRequestDto() {
        return GymRequestDto.builder()
                .name("헬스장이름")
                .address("헬스장주소")
                .area("500평")
                .count(100)
                .build();
    }

    public Ceo createCeo() {
        return Ceo.builder()
                .id(1L)
                .username("ceousername")
                .nickname("ceonickname")
                .build();
    }


}