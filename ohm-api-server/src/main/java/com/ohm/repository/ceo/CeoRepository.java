package com.ohm.repository.ceo;

import com.ohm.entity.Ceo.Ceo;
import com.ohm.entity.Gym.Gym;
import com.ohm.entity.Manager.Manager;
import org.apache.catalina.users.AbstractUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CeoRepository extends JpaRepository<Ceo,Long> {

//    @EntityGraph(attributePaths = "authorities")
//    Optional<Ceo> findOneWithAuthoritiesByName(String name);

    Optional<Ceo> findByUsername(String username);

    @Query("select c from Ceo c where c.username = :name")
    Optional<AbstractUser> findAbstract(String name);
}
