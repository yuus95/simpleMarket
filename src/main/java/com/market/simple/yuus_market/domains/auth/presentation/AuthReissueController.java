package com.market.simple.yuus_market.domains.auth.presentation;

import com.market.simple.yuus_market.domains.ResponseDto;
import com.market.simple.yuus_market.domains.auth.application.AuthReissueService;
import com.market.simple.yuus_market.domains.auth.application.dto.TokenRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthReissueController {
    private final AuthReissueService authService;

    @PostMapping("/reissue")
    public ResponseEntity<ResponseDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<ResponseDto>(new ResponseDto(200, "토큰 재발행 성공", authService.reissue(tokenRequestDto)), HttpStatus.OK);
    }
}
