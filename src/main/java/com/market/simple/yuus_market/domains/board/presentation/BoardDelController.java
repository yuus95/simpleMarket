package com.market.simple.yuus_market.domains.board.presentation;

import com.market.simple.yuus_market.common.handler.ex.CustomException;
import com.market.simple.yuus_market.common.handler.ex.ErrorCode;
import com.market.simple.yuus_market.domains.board.Application.BoardDeleteService;
import com.market.simple.yuus_market.domains.board.Application.dto.BoardDeleteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardDelController {
    private final BoardDeleteService boardDeleteService;

    @DeleteMapping("/api/board")
    public ResponseEntity DeleteBoard(@RequestParam("userIdx") Long userIdx, @RequestParam("boardIdx") Long boardIdx){
        System.out.println("userIdx" + userIdx);
        if( userIdx == 0 || boardIdx == 0 ){
            new CustomException(ErrorCode.CANNOT_PROVIDER);
        }
        BoardDeleteRequest boardDeleteRequest = new BoardDeleteRequest(userIdx, boardIdx);
        boardDeleteService.boardDelete(boardDeleteRequest);
        return ResponseEntity.ok().build();
    }

}
