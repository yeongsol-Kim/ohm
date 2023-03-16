package com.ohm.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import com.ohm.dto.GymDto.GymDto;
import com.ohm.dto.GymDto.GymPriceDto;
import com.ohm.dto.GymDto.GymTimeDto;
import com.ohm.dto.ManagerDto.ManagerDto;
import com.ohm.dto.Statistics.StatisticsDto;
import com.ohm.dto.requestDto.GymRequestDto;
import com.ohm.dto.responseDto.GymResponseDto;
import com.ohm.service.GymService;
import com.ohm.service.ManagerService;
import com.ohm.service.StatisticsService;
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
    private  final StatisticsService statisticsService;
    private final ManagerService managerService;


    @ApiOperation(value = "Gym 등록(ROLE_CEO만 사용)", response = Long.class)
    @PostMapping("/gym")
    @PreAuthorize("hasRole('ROLE_CEO')")
    public ResponseEntity<Long> save(
            @Valid @RequestBody GymRequestDto gymRequestDto
    ) throws Exception {

        ManagerDto managerDto = managerService.getMyManagerWithAuthorities();

        Long save = gymService.save(gymRequestDto);

        statisticsService.register_table(save);

        managerService.register_gym(save, managerDto.getId());

        return ResponseEntity.ok(save);

    }

    @ApiOperation(value = "GymImg 등록(ROLE_CEO만 사용)", response = Long.class)
    @PostMapping("/gym/image/{gymId}")
    @PreAuthorize("hasRole('ROLE_CEO')")
    public ResponseEntity<Long> save_img(
            @PathVariable Long gymId,
            @RequestPart(value = "images", required = false) List<MultipartFile> files
    ) throws Exception {

        Long aLong = gymService.save_img(gymId, files);
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
    public ResponseEntity<StatisticsDto> search_avg(
            @PathVariable Long gymId

    ) throws Exception {


        StatisticsDto value = statisticsService.get_statistics(gymId);
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
    public ResponseEntity<Integer> current_count(@PathVariable Long gymId) throws Exception {
        int current_count = gymService.current_count(gymId);
        return ResponseEntity.ok(current_count);

    }

    //현재 헬스장 인원수 증가 api
    @ApiOperation(value = "현재 Gym 인원증가", response = Integer.class)
    @PostMapping("/gym/count-increase/{gymId}")
    public ResponseEntity<Integer> increase_count(@PathVariable Long gymId) throws Exception {

        gymService.increase_count(gymId);
        int current_count = gymService.findById_count(gymId);
        statisticsService.add_statistics(gymId,current_count);
        return ResponseEntity.ok(current_count);
    }

    //헬스장 인원 감소 api
    @ApiOperation(value = "현재 Gym 인원감소", response = Integer.class)
    @PostMapping("/gym/count-decrease/{gymId}")
    public ResponseEntity<Integer> decrease_count(@PathVariable Long gymId) throws Exception {
        gymService.decrease_count(gymId);
        int current_count = gymService.findById_count(gymId);
        statisticsService.add_statistics(gymId,current_count);
       // inputService.insert_data(current_count, gymId, "output");
        return ResponseEntity.ok(current_count);
    }


    //Trainer가 회원가입시 code
    @ApiOperation(value = "code로 GymId조회", response = Long.class)
    @GetMapping("/gym/code/{code}")
    public ResponseEntity<Long> check_code(@PathVariable int code) throws Exception {
        Long aLong = gymService.check_code(code);
        return

                ResponseEntity.ok(aLong);
    }


    //CEO가 Gym등록시 가입코드 중복 조회
    @ApiOperation(value = "Gym가입코드 조회", response = Long.class)
    @PostMapping("/gym/code/{code}")
    @PreAuthorize("hasRole('ROLE_CEO')")
    public ResponseEntity<String> duplication_code(
            @PathVariable int code
    ) throws Exception {

        boolean bool = gymService.duplication_code(code);
        if (bool == true) {
            return ResponseEntity.ok("OK");
        } else {
            return ResponseEntity.ok("NO");
        }

    }


    @ApiOperation(value = "gymId로 GymTime 조회", response = GymTimeDto.class)
    @GetMapping("/gym/time/{gymId}")
    public ResponseEntity<GymTimeDto> get_gymTime(@PathVariable Long gymId) throws Exception {
        GymTimeDto time = gymService.get_time(gymId);
        return ResponseEntity.ok(time);
    }


    @ApiOperation(value = "gymId로 GymPrice 조회", response = GymPriceDto.class, responseContainer = "List")
    @GetMapping("/gym/price/{gymId}")
    public ResponseEntity<List<GymPriceDto>> get_gymPrice(@PathVariable Long gymId) throws Exception {
        List<GymPriceDto> prices = gymService.get_prices(gymId);
        return ResponseEntity.ok(prices);
    }


    @ApiOperation(value = "gym Time등록", response = Long.class)
    @PostMapping("/gym/time/{gymId}")
    @PreAuthorize("hasRole('ROLE_CEO')")
    public ResponseEntity<Long> register_time(
            @RequestBody GymTimeDto gymTimeDto,
            @PathVariable Long gymId) throws Exception {
        Long aLong = gymService.register_time(gymId, gymTimeDto);
        return ResponseEntity.ok(aLong);
    }

    @ApiOperation(value = "gym Time수정", response = String.class)
    @PatchMapping("/gym/time/{gymId}")
    @PreAuthorize("hasRole('ROLE_CEO','ROLE_MANAGER','ROLE_TRAINER')")
    public ResponseEntity<String> update_time(
            @RequestBody GymTimeDto gymTimeDto,
            @PathVariable Long gymId) throws Exception {
        gymService.update_time(gymId, gymTimeDto);
        return ResponseEntity.ok("UPDATE!");
    }

    //Post 수정
    @ApiOperation(value = "Gym 수정", response = String.class)
    @PreAuthorize("hasAnyRole('ROLE_CEO','ROLE_MANAGER','ROLE_TRAINER')")
    @PatchMapping("/gym")
    public ResponseEntity<String> update(
            @RequestBody GymDto gymDto
    ) {
        gymService.update_gym(gymDto);
        return ResponseEntity.ok("Update!");
    }

    //Post img 수정
    @ApiOperation(value = "GymImg 수정", response = String.class)
    @PreAuthorize("hasAnyRole('ROLE_CEO','ROLE_MANAGER','ROLE_TRAINER')")
    @PostMapping("/gym/image/update/{gymId}")
    public ResponseEntity<String> update_img(
            @RequestParam List<Long> imgIds,
            @PathVariable Long gymId,
            @RequestPart(value = "images", required = false) List<MultipartFile> files
    ) throws Exception {
        gymService.delete_img(imgIds);
        gymService.save_img(gymId, files);
        return ResponseEntity.ok("UPDATE!");
    }

    @ApiOperation(value = "gym price등록", response = Long.class)
    @PostMapping("/gym/price/{gymId}")
    @PreAuthorize("hasRole('ROLE_CEO')")
    public ResponseEntity<Long> register_price(
            @RequestBody GymPriceDto gymPriceDto,
            @PathVariable Long gymId) throws Exception {
        Long aLong = gymService.register_price(gymId, gymPriceDto);
        return ResponseEntity.ok(aLong);
    }

    //Post Price 수정
    @ApiOperation(value = "GymPrice 수정", response = String.class)
    @PreAuthorize("hasAnyRole('ROLE_CEO','ROLE_MANAGER','ROLE_TRAINER')")
    @PatchMapping("/gym/price/{gymId}")
    public ResponseEntity<String> update_price(
            @RequestParam List<Long> priceIds,
            @PathVariable Long gymId

    ) throws Exception {
        gymService.delete_price(priceIds);

        return ResponseEntity.ok("DELETE!");
    }

    //Post Price 수정
    @ApiOperation(value = "현재 인원수 0으로 초기화", response = String.class)
    @PreAuthorize("hasAnyRole('ROLE_CEO','ROLE_MANAGER','ROLE_TRAINER')")
    @PostMapping("/gym/reset/{gymId}")
    public ResponseEntity<String> reset_count(
            @PathVariable Long gymId
    ) throws Exception {
        gymService.reset_count(gymId);

        return ResponseEntity.ok("RESET!");
    }


}
