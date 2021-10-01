package com.market.simple.yuus_market.domains.board.presentation;

import com.market.simple.yuus_market.domains.board.Application.BoardPostService;
import com.market.simple.yuus_market.domains.board.Application.dto.BoardPostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardPostController {

    private final BoardPostService boardPostService;

    @PostMapping("/board")
    public Long create(@RequestBody BoardPostRequest boardPostRequest) throws Exception {


        return boardPostService.create( boardPostRequest);
    }
}



