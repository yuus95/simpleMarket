package com.market.simple.yuus_market.domains.auth.presentation;

import com.market.simple.yuus_market.domains.ResponseDto;
import com.market.simple.yuus_market.domains.auth.application.AuthSignUpService;
import com.market.simple.yuus_market.domains.auth.application.dto.MemberRequestDto;
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
public class AuthSignUpController {
    private final AuthSignUpService authService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@Valid @RequestBody MemberRequestDto memberRequestDto, BindingResult bindingResult) {

        return new ResponseEntity<>(new ResponseDto(200,"회원가입 성공",authService.signup(memberRequestDto)), HttpStatus.OK);
    }
}
