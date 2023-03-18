package com.ohm.api;

import com.ohm.dto.CeoDto.CeoDto;
import com.ohm.dto.ManagerDto.LoginDto;
import com.ohm.dto.ManagerDto.TokenDto;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
@Api(tags = {"Manager API"})
@RequiredArgsConstructor
public class CeoApiController {

    private final CeoService ceoService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

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

    @ApiOperation(value = "ceo 로그인", response = TokenDto.class)
    @PostMapping("/ceo/login")
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
}
