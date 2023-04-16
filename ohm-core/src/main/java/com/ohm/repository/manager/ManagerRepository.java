package com.ohm.repository.manager;
import com.ohm.entity.Ceo.Ceo;
import com.ohm.entity.Gym.Gym;
import com.ohm.entity.Manager.Manager;
import org.apache.catalina.Role;
import org.apache.catalina.users.AbstractUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager,Long> {


    Optional<Manager> findByUsername(String username);


    @Query(value = "select * from manager where manager.gym_id = :gym_id",nativeQuery = true)
    List<Optional<Manager>> findall_byGymId(@Param("gym_id")Long gym_id);


}
