package com.ohm.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import com.ohm.dto.ManagerDto.ManagerDto;
import com.ohm.dto.requestDto.ManagerRequestDto;
import com.ohm.jwt.TokenProvider;
import com.ohm.service.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Api(tags = {"CEO API"})
@RequiredArgsConstructor
public class TrainerApiController {

    private final ManagerService managerService;


    @ApiOperation(value = "trainer 회원가입", response = ManagerDto.class)
    @PostMapping("/trainer/{gymId}")
    @PreAuthorize("hasAnyRole('ROLE_CEO','ROLE_MANAGER')")
    public ResponseEntity<ManagerDto> trainer_signup(
            @PathVariable Long gymId,
            @Valid @RequestBody ManagerRequestDto managerDto) {
        return ResponseEntity.ok(managerService.trainer_save(managerDto,gymId));
    }

}
