package com.market.simple.yuus_market.domains.jjim.presentation;

import com.market.simple.yuus_market.domains.jjim.application.JjimRegisterService;
import com.market.simple.yuus_market.domains.jjim.application.dto.JjimRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JjimRegisterController {
    private final JjimRegisterService jjimRegisterService;

    @PostMapping("/jjim")
    public ResponseEntity jjimRegister(@RequestBody JjimRegisterRequest jjimRegisterRequest){
        jjimRegisterService.jjimRegister(jjimRegisterRequest);
        return ResponseEntity.ok().build();
    }
}
