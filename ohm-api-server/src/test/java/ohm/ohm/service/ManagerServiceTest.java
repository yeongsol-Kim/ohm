package ohm.ohm.service;

import com.ohm.service.ManagerService;
import ohm.ohm.dto.ManagerDto.ManagerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
//@Rollback(value = false)
public class ManagerServiceTest {

    @Autowired
    ManagerService managerService;







}