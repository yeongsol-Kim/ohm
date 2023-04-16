package com.repository;


import com.ohm.entity.Ceo.Ceo;
import com.ohm.repository.ceo.CeoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;


@DataJpaTest
public class CeoRepositoryTest {

    @Autowired
    private CeoRepository ceoRepository;

    @Test
    public void ceo저장() {
        //given
        Ceo ceo = createCeoEntity();
        //when
        Ceo save = ceoRepository.save(ceo);
        //then
        Assertions.assertThat(save.getNickname()).isEqualTo("nickname");
    }

    @Test
    public void ceousername으로조회() {
        //given
        Ceo ceo = createCeoEntity();

        //when
        ceoRepository.save(ceo);
        Optional<Ceo> usernam = ceoRepository.findByUsername("username");

        //then
        Assertions.assertThat(usernam.get().getUsername()).isEqualTo("username");

    }

    private Ceo createCeoEntity() {
        return Ceo.builder()
                .nickname("nickname")
                .username("username")
                .password("password")
                .build();
    }
}
