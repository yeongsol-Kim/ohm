package ohm.ohm.service;

import com.ohm.service.AdminService;
import ohm.ohm.dto.AdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;


@SpringBootTest
@Transactional
@Rollback(value = false)
public class AdminServiceTest {


    @Autowired
    AdminService adminService;

//    @Test
//    public void save_admin(){
//        AdminDto adminDto = new AdminDto("testddadmin");
//        Long join = adminService.join(adminDto);
//        assertThat(join).isNotNull();
//    }




}