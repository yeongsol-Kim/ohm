package com.ohm.service;

import com.ohm.dto.GymDto.GymDto;
import com.ohm.entity.Gym.Gym;
import com.ohm.repository.GymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GymService {

    private final GymRepository gymRepository;

    public GymDto getGymMemberCountPageInfo(Long id) {
        Gym gym = gymRepository.findById(id).orElse(null);

        return GymDto.builder()
                .currentCount(gym.getCurrentCount())
                .name(gym.getName())
                .build();
    }

    public Boolean increaseCurrentCount(Long id) {
        Gym gym = gymRepository.findById(id).orElse(null);
        // 널체크

        gym.increase_count();
        return true;
    }

    public Boolean decreaseCurrentCount(Long id) {
        Gym gym = gymRepository.findById(id).orElse(null);
        // 널체크

        gym.decrease_count();
        return true;
    }
}
