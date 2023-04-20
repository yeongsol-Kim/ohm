package ohm.ohm.service;

import com.ohm.config.AppConfig;
import com.ohm.dto.GymDto.GymDto;
import com.ohm.dto.GymDto.GymTimeDto;
import com.ohm.dto.requestDto.GymRequestDto;
import com.ohm.entity.Ceo.Ceo;
import com.ohm.entity.Gym.Gym;
import com.ohm.entity.Gym.GymTime;
import com.ohm.repository.ceo.CeoRepository;
import com.ohm.repository.gym.GymTimeRepository;
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

    @Mock
    private GymTimeRepository gymTimeRepository;

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

    @Test
    public void gymTime조회() throws Exception {
        //given
        Gym gym = createGymAndTimeEntity();
        given(gymRepository.findTimeByGymId(any())).willReturn(gym);

        //when
        GymTimeDto time = gymService.getTime(gym.getId());
        //then
        Assertions.assertThat(time.getId()).isEqualTo(gym.getId());

    }


    @Test
    public void gymTime등록() {
        //given
        Gym gym = createGymEntity();
        GymTime gymTime = createGymTimeEntity();
        given(gymRepository.findById(any())).willReturn(Optional.ofNullable(gym));
        given(gymTimeRepository.save(any())).willReturn(gymTime);
        //when
        Long aLong = gymService.registerTime(gym.getId(), createGymTimeDtoEntity());
        //then
        Assertions.assertThat(aLong).isNotNull();
    }

    @Test
    public void gym업데이트() {
        //given
        Gym gym = createGymEntity();
        GymDto updateGymDto = updateGymDto();
        given(gymRepository.findById(any())).willReturn(Optional.ofNullable(gym));
        //when
        Optional<Gym> gym1 = gymService.updateGym(updateGymDto);
        //then
        Assertions.assertThat(gym1.get().getName()).isEqualTo("변경이름");
    }

    @Test
    public void gymPrice등록(){
        //given

        //when

        //then

    }

    public Gym createGymAndTimeEntity() {
        GymTime gymTime = GymTime.builder()
                .id(1L)
                .thursday("00:00 ~ 20:00")
                .build();

        return Gym.builder()
                .gymTime(gymTime)
                .id(1L)
                .name("헬스장이름")
                .build();

    }


    public GymTime createGymTimeEntity() {
        return GymTime.builder()
                .id(1L)
                .holiday("08:00 ~ 23:00")
                .sunday("08:00 ~ 23:00")
                .saturday("08:00 ~ 23:00")

                .build();
    }


    public GymTimeDto createGymTimeDtoEntity() {
        return GymTimeDto.builder()
                .holiday("08:00 ~ 23:00")
                .sunday("08:00 ~ 23:00")
                .saturday("08:00 ~ 23:00")

                .build();
    }

    public Gym updateGymEntity() {
        return Gym.builder()
                .id(3L)
                .name("변경이름")
                .address("헬스장주소")
                .area("500평")
                .count(100)
                .build();
    }

    public GymDto updateGymDto() {
        return GymDto.builder()
                .id(3L)
                .name("변경이름")
                .address("헬스장주소")
                .area("500평")
                .count(100)
                .build();
    }

    public Gym createGymEntity() {
        return Gym.builder()
                .id(3L)
                .name("테스트이름")
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