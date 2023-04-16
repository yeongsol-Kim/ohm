package com.ohm.api;

import com.ohm.dto.Statistics.TotalStatisticsDto;
import com.ohm.service.TotalStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import com.ohm.dto.GymDto.GymDto;
import com.ohm.dto.GymDto.GymPriceDto;
import com.ohm.dto.GymDto.GymTimeDto;
import com.ohm.dto.requestDto.GymRequestDto;
import com.ohm.dto.responseDto.GymResponseDto;
import com.ohm.service.GymService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = {"Gym API"})
@RequiredArgsConstructor
public class GymApiController {

    private final GymService gymService;
    private final TotalStatisticsService totalStatisticsService;


    @ApiOperation(value = "Gym 등록(ROLE_CEO만 사용)", response = Long.class)
    @PostMapping("/gym/{ceoId}")
    @PreAuthorize("hasRole('ROLE_CEO')")
    public ResponseEntity<Long> save(
            @PathVariable Long ceoId,
            @Valid @RequestBody GymRequestDto gymRequestDto
    ) throws Exception {

        Long save = gymService.registerGym(gymRequestDto,ceoId);
        //헬스장 통계테이블 생성
        totalStatisticsService.registerTable(save);
        return ResponseEntity.ok(save);

    }

    @ApiOperation(value = "GymImg 등록(ROLE_CEO만 사용)", response = Long.class)
    @PostMapping("/gym/image/{gymId}")
    @PreAuthorize("hasRole('ROLE_CEO')")
    public ResponseEntity<Long> save_img(
            @PathVariable Long gymId,
            @RequestPart(value = "images", required = false) List<MultipartFile> files
    ) throws Exception {

        Long aLong = gymService.saveImg(gymId, files);
        return ResponseEntity.ok(aLong);
    }

    //모든헬스장 조회
    @ApiOperation(value = "모든 Gym 조회", response = GymResponseDto.class, responseContainer = "List")
    @GetMapping("/gyms")
    public ResponseEntity<List<GymResponseDto>> findall() throws Exception {
        List<GymResponseDto> findall = gymService.findall();
        return ResponseEntity.ok(findall);
    }

    @ApiOperation(value = "시간대별 평균 인원 조회", response = String.class, responseContainer = "List")
    @GetMapping("/gym/avg/{gymId}")
    public ResponseEntity<TotalStatisticsDto> search_avg(
            @PathVariable Long gymId

    ) throws Exception {
        TotalStatisticsDto value = totalStatisticsService.getStatistics(gymId);
        return ResponseEntity.ok(value);
    }


    //이름으로 헬스장 조회
    @ApiOperation(value = "이름으로 헬스장 조회", response = GymResponseDto.class, responseContainer = "List")
    @GetMapping("/gym/name/{gymName}")
    public ResponseEntity<List<GymResponseDto>> findByName(@PathVariable String gymName) throws Exception {
        List<GymResponseDto> byName = gymService.findByName(gymName);
        return ResponseEntity.ok(byName);
    }

    //ID로 헬스장 조회
    @ApiOperation(value = "ID로 헬스장 조회", response = GymResponseDto.class)
    @GetMapping("/gym/{gymId}")
    public ResponseEntity<GymResponseDto> findById(@PathVariable Long gymId) throws Exception {
        return ResponseEntity.ok(gymService.findById(gymId));
    }


    //현재 헬스장 인원
    @ApiOperation(value = "현재 Gym에 있는 인원조회", response = Integer.class)
    @GetMapping("/gym/count/{gymId}")
    public ResponseEntity<Long> current_count(@PathVariable Long gymId) throws Exception {
        Long currentCount = gymService.currentCount(gymId);
        return ResponseEntity.ok(currentCount);

    }

    //현재 헬스장 인원수 증가 api
    @ApiOperation(value = "현재 Gym 인원증가", response = Integer.class)
    @PostMapping("/gym/count-increase/{gymId}")
    public ResponseEntity<Long> increase_count(@PathVariable Long gymId) throws Exception {

        gymService.increaseCount(gymId);
        Long currentCount = gymService.findByIdCount(gymId);
        return ResponseEntity.ok(currentCount);
    }

    //헬스장 인원 감소 api
    @ApiOperation(value = "현재 Gym 인원감소", response = Integer.class)
    @PostMapping("/gym/count-decrease/{gymId}")
    public ResponseEntity<Long> decrease_count(@PathVariable Long gymId) throws Exception {
        gymService.decreaseCount(gymId);
        Long currentCount = gymService.findByIdCount(gymId);
        return ResponseEntity.ok(currentCount);
    }




    @ApiOperation(value = "gymId로 GymTime 조회", response = GymTimeDto.class)
    @GetMapping("/gym/time/{gymId}")
    public ResponseEntity<GymTimeDto> get_gymTime(@PathVariable Long gymId) throws Exception {
        GymTimeDto time = gymService.getTime(gymId);
        return ResponseEntity.ok(time);
    }


    @ApiOperation(value = "gymId로 GymPrice 조회", response = GymPriceDto.class, responseContainer = "List")
    @GetMapping("/gym/price/{gymId}")
    public ResponseEntity<List<GymPriceDto>> get_gymPrice(@PathVariable Long gymId) throws Exception {
        List<GymPriceDto> prices = gymService.getPrices(gymId);
        return ResponseEntity.ok(prices);
    }


    @ApiOperation(value = "gym Time등록", response = Long.class)
    @PostMapping("/gym/time/{gymId}")
    @PreAuthorize("hasRole('ROLE_CEO')")
    public ResponseEntity<Long> register_time(
            @RequestBody GymTimeDto gymTimeDto,
            @PathVariable Long gymId) throws Exception {
        Long aLong = gymService.registerTime(gymId, gymTimeDto);
        return ResponseEntity.ok(aLong);
    }

    @ApiOperation(value = "gym Time수정", response = String.class)
    @PatchMapping("/gym/time/{gymId}")
    @PreAuthorize("hasAnyRole('ROLE_CEO','ROLE_MANAGER','ROLE_TRAINER')")
    public ResponseEntity<String> update_time(
            @RequestBody GymTimeDto gymTimeDto,
            @PathVariable Long gymId) throws Exception {
        gymService.updateTime(gymId, gymTimeDto);
        return ResponseEntity.ok("UPDATE!");
    }

    //Post 수정
    @ApiOperation(value = "Gym 수정", response = String.class)
    @PreAuthorize("hasAnyRole('ROLE_CEO','ROLE_MANAGER','ROLE_TRAINER')")
    @PatchMapping("/gym")
    public ResponseEntity<String> update(
            @RequestBody GymDto gymDto
    ) {
        gymService.updateGym(gymDto);
        return ResponseEntity.ok("Update!");
    }

    //Post img 수정
    @ApiOperation(value = "GymImg 수정", response = String.class)
    @PreAuthorize("hasAnyRole('ROLE_CEO','ROLE_MANAGER','ROLE_TRAINER')")
    @PostMapping("/gym/image/update/{gymId}")
    public ResponseEntity<String> updateImg(
            @RequestParam List<Long> imgIds,
            @PathVariable Long gymId,
            @RequestPart(value = "images", required = false) List<MultipartFile> files
    ) throws Exception {
        gymService.deleteImg(imgIds);
        gymService.saveImg(gymId, files);
        return ResponseEntity.ok("UPDATE!");
    }

    @ApiOperation(value = "gym price등록", response = Long.class)
    @PostMapping("/gym/price/{gymId}")
    @PreAuthorize("hasAnyRole('ROLE_CEO','ROLE_MANAGER','ROLE_TRAINER')")
    public ResponseEntity<Long> registerPrice(
            @RequestBody GymPriceDto gymPriceDto,
            @PathVariable Long gymId) throws Exception {
        Long aLong = gymService.registerPrice(gymId, gymPriceDto);
        return ResponseEntity.ok(aLong);
    }

    @ApiOperation(value = "GymPrice 삭제", response = String.class)
    @PreAuthorize("hasAnyRole('ROLE_CEO','ROLE_MANAGER','ROLE_TRAINER')")
    @PatchMapping("/gym/price/{gymId}")
    public ResponseEntity<String> updatePrice(
            @RequestParam List<Long> priceIds,
            @PathVariable Long gymId

    ) throws Exception {
        gymService.deletePrice(priceIds);

        return ResponseEntity.ok("DELETE!");
    }

    @ApiOperation(value = "현재 인원수 0으로 초기화", response = String.class)
    @PreAuthorize("hasAnyRole('ROLE_CEO','ROLE_MANAGER','ROLE_TRAINER')")
    @PostMapping("/gym/reset/{gymId}")
    public ResponseEntity<String> resetCount(
            @PathVariable Long gymId
    ) throws Exception {
        gymService.resetCount(gymId);

        return ResponseEntity.ok("RESET!");
    }


}
