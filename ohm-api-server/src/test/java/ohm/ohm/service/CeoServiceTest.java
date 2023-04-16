package ohm.ohm.service;
import com.ohm.config.AppConfig;
import com.ohm.dto.CeoDto.CeoDto;
import com.ohm.dto.requestDto.ManagerRequestDto;
import com.ohm.entity.Ceo.Ceo;
import com.ohm.entity.Enum.Role;
import com.ohm.repository.ceo.CeoRepository;
import com.ohm.repository.gym.GymRepository;
import com.ohm.repository.manager.ManagerRepository;
import com.ohm.service.CeoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class CeoServiceTest {

    //가짜 객체를 만듬
    @Mock
    private CeoRepository ceoRepository;

    @Mock
    private GymRepository gymRepository;

    @Mock
    private ManagerRepository managerRepository;

    //@Spy는 given으로 지정해준 로직을 제외하곤 모두 실제 객체를 사용함 appconfig에서 사용하는 modelMapper()의 의존성을 갖고 있지만 단순히 형변환을 해주는 용도이므로 그냥 사용
    @Spy
    private  AppConfig appConfig;

    @Mock
    private  PasswordEncoder passwordEncoder;

    //만든 가짜 객체를 주입함
    @InjectMocks
    private CeoService ceoService;



    @Test
    public void ceo생성() {
        //given
        Ceo ceo = createCeoEntity();
        ManagerRequestDto managerRequestDto = createManagerRequestEntity();

        //when
        given(ceoRepository.findByUsername(any())).willReturn(Optional.empty());
        given(managerRepository.findByUsername(any())).willReturn(Optional.empty());
        given(ceoRepository.save(any())).willReturn(ceo);

        CeoDto ceoDto = ceoService.ceoSave(managerRequestDto);

        //then
        Assertions.assertEquals(ceoDto.getUsername(),ceo.getUsername());
    }

    @Test
    public void ceoId로모든gym조회(){
        //given

        //when
//        ceoService.findallGyms();

        //then


    }

//    private List<Gym> createGyms(){
//        Gym gym = Gym.builder()
//
//                .build();
//    }


    private ManagerRequestDto createManagerRequestEntity(){
        ManagerRequestDto managerRequestDto = ManagerRequestDto
                .builder()

                .nickname("username")
                .password("1234")
                .username("testuser")
                .build();
        return managerRequestDto;
    }



    private Ceo createCeoEntity(){
        Ceo ceo = Ceo.builder()
                .username("username")
                .id(1L)
                .role(Role.ROLE_CEO)
                .password("1234")
                .nickname("testuser")
                .build();

        return ceo;

    }


}
