package com.ohm.api;

import com.ohm.dto.CeoDto.CeoDto;
import com.ohm.service.CeoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import com.ohm.dto.ManagerDto.ManagerDto;
import com.ohm.dto.requestDto.ManagerRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
@Api(tags = {"Manager API"})
@RequiredArgsConstructor
public class CeoApiController {

    private final CeoService ceoService;


    @ApiOperation(value = "CEO code 인증(초기가입)", response = String.class)
    @PostMapping("/ceo/code/{code}")
    public ResponseEntity<String> check_code(
            @PathVariable String code) {

        boolean bool = ceoService.check_code(code);
        if(bool == true){
            return ResponseEntity.ok("true");
        } else {
            return ResponseEntity.ok("false");
        }
    }


    @ApiOperation(value = "ceo 회원가입", response = ManagerDto.class)
    @PostMapping("/ceo")
    public ResponseEntity<CeoDto> ceo_signup(@Valid @RequestBody ManagerRequestDto managerDto) {
        return ResponseEntity.ok(ceoService.ceo_save(managerDto));
    }
}
