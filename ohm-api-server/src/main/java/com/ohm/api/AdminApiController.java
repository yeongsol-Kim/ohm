package com.ohm.api;


import com.ohm.dto.CeoDto.CeoDto;
import com.ohm.dto.responseDto.AdminResponseDto;
import com.ohm.service.CeoService;
import com.ohm.service.CustomAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import com.ohm.dto.ManagerDto.LoginDto;
import com.ohm.dto.ManagerDto.ManagerDto;
import com.ohm.dto.ManagerDto.TokenDto;
import com.ohm.dto.responseDto.TrainerResponseDto;
import com.ohm.jwt.JwtFilter;
import com.ohm.jwt.TokenProvider;
import com.ohm.service.ManagerService;
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

import javax.validation.Valid;
import java.util.List;



//ceo,manager 공통API
@RestController
@RequestMapping("/api")
@Api(tags = {"ADMIN API"})
@RequiredArgsConstructor
public class AdminApiController {


    private final ManagerService managerService;
    private final CustomAdminService customAdminService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    @ApiOperation(value = "manager,trainer,ceo 로그인", response = TokenDto.class)
    @PostMapping("/admin/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getName(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer" + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }





    //현재 로그인된 Manager 정보조회
    @ApiOperation(value = "로그인된 정보조회", response = ManagerDto.class)
    @GetMapping("/admin")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_TRAINER','ROLE_CEO')")
    public ResponseEntity<AdminResponseDto> getManagerInfo() {

        return ResponseEntity.ok(customAdminService.getMyManagerWithAuthorities());
    }


    @ApiOperation(value = "ID로 관리자 계정 정보조회", response = ManagerDto.class)
    @GetMapping("/admin/info/{managerId}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_TRAINER','ROLE_CEO')")
    public ResponseEntity<ManagerDto> getManagerInfoById(
            @PathVariable Long managerId
    ) {
        ManagerDto managerInfo = managerService.getManagerInfo(managerId);
        return ResponseEntity.ok(managerInfo);
    }



    //분리
    @ApiOperation(value = "계정프로필 사진 수정", response = String.class)
    @PatchMapping("/admin/image/{managerId}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_TRAINER','ROLE_CEO')")
    public ResponseEntity<String> profile_update(
            @PathVariable Long managerId,
            @RequestPart(value = "images",required = false) MultipartFile file
    ) throws Exception {
        managerService.profile_edit(managerId,file);
        return ResponseEntity.ok("image upload!");
    }




    @ApiOperation(value = "계정정보 수정", response = String.class)
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_TRAINER','ROLE_CEO')")
    @PatchMapping("/admin")
    public ResponseEntity<String> update(
            @RequestBody ManagerDto managerDto
    ) {
        managerService.update(managerDto);
        return ResponseEntity.ok("Update!");
    }


    @ApiOperation(value = "ADMIN 회원탈퇴", response = String.class)
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_TRAINER','ROLE_CEO')")
    @DeleteMapping("/admin/{managerId}")
    public ResponseEntity<String> remove() {
        customAdminService.delete();
        return ResponseEntity.ok("Remove!");
    }

    @ApiOperation(value = "GymId로 해당 Gym에 소속된 manager모두조회", response = TrainerResponseDto.class,responseContainer = "List")
    @GetMapping("/admin/findall/{gymId}")
    public ResponseEntity<List<TrainerResponseDto>> trainer_findall(
            @PathVariable Long gymId
    ) {
        List<TrainerResponseDto> trainerResponseDtos = managerService.trainer_findall(gymId);
        return ResponseEntity.ok(trainerResponseDtos);
    }

    @ApiOperation(value = "Id로 Manager(ROLE이 Trainer)조회", response = TrainerResponseDto.class)
    @GetMapping("/admin/{managerId}")
    public ResponseEntity<TrainerResponseDto> trainer_find(
            @PathVariable Long managerId
    ) {
        TrainerResponseDto byID = managerService.findByID(managerId);
        return ResponseEntity.ok(byID);
    }


}
