package com.ohm.repository.gym;

import com.ohm.entity.Gym.Gym;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GymRepository extends JpaRepository<Gym,Long> {


    @Query("select g from Gym g where g.ceo.id = :ceoId")
    List<Gym> findallGymsByCeoId(Long ceoId);

    //fetch join을 어노테이션으로 대체@EntityGraph
    @EntityGraph(attributePaths = "imgs")
    List<Gym> findByNameContaining(String name);

    @Query("select g from Gym g left join fetch g.imgs")
    List<Gym> findAllFetchJoin();

    @Query("select g from Gym g left join fetch g.imgs where g.id = :id")
    Gym findGymFetchJoin(@Param("id")Long id);

    @Query("select g from Gym g where g.code = :code")
    Gym find_code(@Param("code")int code);

    @Query("select g from Gym g left join fetch g.gymTime where g.id = :id")
    Gym findTimeByGymId(@Param("id")Long id);

    @Query("select g from Gym g where g.code = :code")
    Gym checkCode(@Param("code") int code);
}
