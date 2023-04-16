package com.ohm.api;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import com.ohm.dto.ManagerDto.ManagerDto;
import com.ohm.dto.requestDto.ManagerRequestDto;
import com.ohm.service.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Api(tags = {"Manager API"})
@RequiredArgsConstructor
public class ManagerApiController {

    private final ManagerService managerService;


    @ApiOperation(value = "manager 회원가입", response = ManagerDto.class)
    @PostMapping("/manager/{gymId}")
    @PreAuthorize("hasAnyRole('ROLE_CEO','ROLE_MANAGER')")
    public ResponseEntity<ManagerDto> managerSignup(
            @PathVariable Long gymId,
            @Valid @RequestBody ManagerRequestDto managerDto) {
        return ResponseEntity.ok(managerService.managerSave(managerDto,gymId));
    }



}
