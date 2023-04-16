package com.ohm.repository.gym;

import com.ohm.entity.Gym.GymTime;
import com.ohm.entity.Manager.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymTimeRepository extends JpaRepository<GymTime, Long> {
}
