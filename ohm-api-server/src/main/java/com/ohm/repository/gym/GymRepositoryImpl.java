//package com.ohm.repository.gym;
//
//import lombok.RequiredArgsConstructor;
//import com.ohm.entity.Gym.Gym;
//
//
//
//import java.util.List;
//
//@RequiredArgsConstructor
//public class GymRepositoryImpl implements GymRepositoryCustom {
//
//    private final JPQLQueryFactory jpqlQueryFactory;
//
//    @Override
//    public List<Gym> findAllGymList() {
//        return jpqlQueryFactory.selectFrom(gym)
//                .leftJoin(gym.imgs, gymImg)
//                .fetch();
//    }
//}
