package com.ohm.api;

import com.ohm.dto.CeoDto.CeoDto;
import com.ohm.dto.responseDto.GymResponseDto;
import com.ohm.service.CeoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import com.ohm.dto.ManagerDto.ManagerDto;
import com.ohm.dto.requestDto.ManagerRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
@Api(tags = {"CEO API"})
@RequiredArgsConstructor
public class CeoApiController {

    private final CeoService ceoService;



    @ApiOperation(value = "ceo 회원가입", response = ManagerDto.class)
    @PostMapping("/ceo")
    public ResponseEntity<CeoDto> ceo_signup(@Valid @RequestBody ManagerRequestDto managerDto) {
        return ResponseEntity.ok(ceoService.ceoSave(managerDto));
    }



    @ApiOperation(value = "ceo가 모든 gym 조회", response = GymResponseDto.class,responseContainer = "List")
    @GetMapping("/ceo/gyms/{ceoId}")
    @PreAuthorize("hasAnyRole('ROLE_CEO')")
    public ResponseEntity<List<GymResponseDto>> findall_gym(
            @PathVariable Long ceoId,
            @RequestPart(value = "images",required = false) MultipartFile file
    ) throws Exception {
        List<GymResponseDto> gymResponseDtos = ceoService.findallGyms(ceoId);
        return ResponseEntity.ok(gymResponseDtos);
    }



}
