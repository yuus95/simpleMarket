package com.market.simple.yuus_market.domains.jjim.presentation;

import com.market.simple.yuus_market.domains.ResponseDto;
import com.market.simple.yuus_market.domains.jjim.application.JjimListService;
import com.market.simple.yuus_market.domains.jjim.application.dto.JjimListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JjimListController {
    private final JjimListService jjimListService;

    @GetMapping("/jjim/list/{userIdx}")
    public ResponseEntity getJjimList(@PathVariable Long userIdx) {
        List<JjimListResponse> list = jjimListService.getList(userIdx);
        return new ResponseEntity(new ResponseDto(200, "찜리스트 조회 성공", list), HttpStatus.OK);
    }
}
