//package ohm.ohm.service;
//
//
//import com.ohm.config.AppConfig;
//import com.ohm.dto.CeoDto.CeoDto;
//import com.ohm.dto.requestDto.ManagerRequestDto;
//import com.ohm.entity.Ceo.Ceo;
//import com.ohm.entity.Enum.Role;
//import com.ohm.repository.ceo.CeoRepository;
//import com.ohm.repository.manager.ManagerRepository;
//import com.ohm.service.CeoService;
//import com.ohm.service.ManagerService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.swing.*;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//
//
//@ExtendWith(MockitoExtension.class)
//public class CeoServiceTest {
//
//    //@Mock 어노테이션은 Mock클래스로 생성해야하는 가짜 객체임을 알려준다.
//    @Mock
//    private CeoRepository ceoRepository;
//
//    //@InjectMocks 어노테이션은 Mock 객체를 주입받을 객체에 사용한다.
//    //Service 클래스를 @InjectMocks로 선언함으로써 @Mock으로 선언된 가짜 객체들을 의존한 Service객체가 생성된다.
//    @InjectMocks
//    private CeoService ceoService;
//
//    @Mock
//    private ManagerRepository managerRepository;
//    @Mock
//    private PasswordEncoder passwordEncoder;
//    @Mock
//    private AppConfig appConfig;
//
//
//
//
//    @Test
//    @DisplayName("Ceo save service test")
//    public void ceo생성() throws Exception{
//        //given
//        ManagerRequestDto ceoRequest = createRequestDto();
//        Ceo ceo =  createCeoEntity(ceoRequest);
//        CeoDto ceoDto2 = createCeoDto(ceoRequest);
//
//
//
//        given(ceoRepository.findByUsername(any())).willReturn(Optional.ofNullable(null));
//        given(managerRepository.findByUsername(any())).willReturn(Optional.ofNullable(null));
//        given(ceoRepository.save(any())).willReturn(ceo);
//        given(passwordEncoder.encode(any())).willReturn("1234");
//        given(appConfig.modelMapper().map(any(),any())).willReturn(ceoDto2);
//
//        //when
//        CeoDto ceoDto = ceoService.ceo_save(ceoRequest);
//
//        Assertions.assertEquals(ceoDto.getNickname(),ceoRequest.getNickname());
//
//
//        //then
//
//    }
//
//    private Ceo createCeoEntity(ManagerRequestDto ceoRequest){
//        return Ceo.builder()
//                .nickname(ceoRequest.getNickname())
//                .username(ceoRequest.getUsername())
//                .build();
//
//    }
//
//    private CeoDto createCeoDto(ManagerRequestDto ceoRequest){
//        return CeoDto.builder()
//                .nickname(ceoRequest.getNickname())
//                .username(ceoRequest.getUsername())
//                .build();
//    }
//
//    private ManagerRequestDto createRequestDto(){
//        //실제 요청에선 ManagerRequestDto로 받음
//        ManagerRequestDto managerDto = ManagerRequestDto.builder()
//                .nickname("테스트nickname")
//                .username("테스트name")
//                .role(Role.ROLE_CEO)
//                .password("1234")
//                .build();
//
//        return managerDto;
//        //Entity 변환
//    }
//
//}
