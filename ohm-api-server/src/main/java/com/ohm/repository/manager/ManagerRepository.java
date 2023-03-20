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

    @Query("select m from Manager m left join fetch m.gym where m.id = :manager_id")
    Manager findManagerFetchJoinGym(@Param("manager_id")Long manager_id);

    @EntityGraph(attributePaths = "gym")
    Optional<Manager> findOneWithGymById(Long id);


    @Modifying(clearAutomatically = true)
    @Query(value = "update manager m set m.gym_id = :gym_id where m.manager_id =:manager_id",nativeQuery = true)
    void registerByGymId(@Param("manager_id")Long manager_id,@Param("gym_id")Long gym_id);



    @Query(value = "select * from manager where manager.gym_id = :gym_id",nativeQuery = true)
    List<Optional<Manager>> findall_byGymId(@Param("gym_id")Long gym_id);


}
