package com.ohm.api;

import com.ohm.dto.CeoDto.CeoDto;
import com.ohm.dto.ManagerDto.LoginDto;
import com.ohm.dto.ManagerDto.TokenDto;
import com.ohm.dto.responseDto.GymResponseDto;
import com.ohm.dto.responseDto.TrainerResponseDto;
import com.ohm.jwt.JwtFilter;
import com.ohm.jwt.TokenProvider;
import com.ohm.service.CeoService;
import com.ohm.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import com.ohm.dto.ManagerDto.ManagerDto;
import com.ohm.dto.requestDto.ManagerRequestDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
@Api(tags = {"CEO API"})
@RequiredArgsConstructor
public class CeoApiController {

    private final CeoService ceoService;

    @ApiOperation(value = "CEO code 인증(초기가입)", response = String.class)
    @PostMapping("/ceo/code/{code}")
    public ResponseEntity<String> check_code(
            @PathVariable String code) {

        boolean bool = ceoService.checkCode(code);
        if(bool == true){
            return ResponseEntity.ok("true");
        } else {
            return ResponseEntity.ok("false");
        }
    }


    @ApiOperation(value = "ceo 회원가입", response = ManagerDto.class)
    @PostMapping("/ceo")
    public ResponseEntity<CeoDto> ceo_signup(@Valid @RequestBody ManagerRequestDto managerDto) {
        return ResponseEntity.ok(ceoService.ceoSave(managerDto));
    }



    //관리자 어플에서 ceo만 사용
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
