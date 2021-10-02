package com.market.simple.yuus_market.domains.board.presentation;

import com.market.simple.yuus_market.domains.ResponseDto;
import com.market.simple.yuus_market.domains.board.Application.BoardListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardListController {
    private final BoardListService boardListService;

    @GetMapping("/board/list")
    public ResponseEntity getList() {
        return new ResponseEntity<>(new ResponseDto(200, "로그인 성공", boardListService.getList()), HttpStatus.OK);

    }
}
