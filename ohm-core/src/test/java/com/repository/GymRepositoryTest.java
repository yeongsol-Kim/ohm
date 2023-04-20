package com.repository;


import com.ohm.entity.Ceo.Ceo;
import com.ohm.entity.Gym.Gym;
import com.ohm.entity.Gym.GymImg;
import com.ohm.entity.Gym.GymTime;
import com.ohm.repository.ceo.CeoRepository;
import com.ohm.repository.gym.GymImgRepository;
import com.ohm.repository.gym.GymRepository;
import com.ohm.repository.gym.GymTimeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
public class GymRepositoryTest {

    @Autowired
    private GymRepository gymRepository;

    @Autowired
    private GymTimeRepository gymTimeRepository;

    @Autowired
    private GymImgRepository gymImgRepository;

    @Autowired
    private CeoRepository ceoRepository;


    @Test
    public void gym정보등록(){
        //given
        Gym gym = createGymEntity();
        GymTime gymTime = createGymTimeEntity();
        GymImg gymImg = createGymImgEntity();

        //when
        Gym saveGym = gymRepository.save(gym);
        GymTime saveTime = gymTimeRepository.save(gymTime);
        GymImg saveImg = gymImgRepository.save(gymImg);
        //then
        Assertions.assertEquals(gym.getName(),saveGym.getName());
        Assertions.assertEquals(gymTime.getCloseDay(),saveTime.getCloseDay());
        Assertions.assertEquals(gymImg.getFilePath(),saveImg.getFilePath());
    }

    @Test
    public void ceoId로gym모두조회(){
        //given
        Ceo ceo = createCeo();
        Gym gym = createGymWithCeo(ceo);
        //when

        //현재 ceoRepository에 의존
        ceoRepository.save(ceo);
        Gym save = gymRepository.save(gym);
        List<Gym> gyms = gymRepository.findallGymsByCeoId(ceo.getId());
        //then
        Assertions.assertEquals(gyms.size(),1);
    }

    @Test
    public void gym이름으로조회(){

        //given
        Gym gym = createGymEntity();
        //when
        gymRepository.save(gym);
        List<Gym> name = gymRepository.findByNameContaining("name");
        //then
        Assertions.assertEquals(name.get(0).getName(),gym.getName());
    }

    @Test
    public void gym모두조회(){
        //given
        

        //when
        List<Gym> allFetchJoin = gymRepository.findAllFetchJoin();

        //then
    }
    






    private Gym createGymWithCeo(Ceo ceo){
        return Gym.builder()
                .name("gymname")
                .address("gymaddress")
                .count(100)
                .onelineIntroduce("한줄소개")
                .introduce("헬스장소개")
                .area("400평")
                .currentCount(0L)
                .build();
    }


    private Ceo createCeo() {
        return Ceo.builder()
                .id(1L)
                .nickname("nickname")
                .username("username")
                .build();
    }


    private Gym createGymEntity(){
        return Gym.builder()
                .name("gymname")
                .address("gymaddress")
                .count(100)
                .onelineIntroduce("한줄소개")
                .introduce("헬스장소개")
                .area("400평")
                .currentCount(0L)
                .build();
    }

    private GymImg createGymImgEntity(){
        return GymImg.builder()
                .filePath("경로")
                .origFileName("원본사진이름")
                .build();
    }

    private GymTime createGymTimeEntity(){
        return GymTime.builder()
                .closeDay("일요일")
                .monday("00:00 ~ 12:00")
                .tuesday("00:00 ~ 12:00")
                .wednesday("00:00 ~ 12:00")
                .thursday("00:00 ~ 12:00")
                .friday("00:00 ~ 12:00")
                .saturday("00:00 ~ 12:00")
                .sunday("00:00 ~ 12:00")
                .holiday("00:00 ~ 12:00")
                .build();
    }
}