package ohm.ohm.service;

import com.ohm.config.AppConfig;
import com.ohm.service.GymService;
import com.ohm.repository.gym.GymRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class GymServiceTest {

    @Mock
    private GymRepository gymRepository;

    @Spy
    private AppConfig appConfig;

    @InjectMocks
    private GymService gymService;



}