package com.market.simple.yuus_market.domains.auth.presentation;

import com.market.simple.yuus_market.domains.ResponseDto;
import com.market.simple.yuus_market.domains.auth.application.AuthLoginService;
import com.market.simple.yuus_market.domains.auth.application.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthLoignController {
    private final AuthLoginService authService;


    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@Valid @RequestBody LoginDto loginDto,
                                             BindingResult bindingResult)//꼭 valid 옆에 넣기
    {

        return new ResponseEntity<>(new ResponseDto(200, "로그인 성공", authService.login(loginDto)), HttpStatus.OK);

    }

}
