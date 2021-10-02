package com.market.simple.yuus_market.domains.board.presentation;

import com.market.simple.yuus_market.domains.board.Application.BoardUpdateStatusService;
import com.market.simple.yuus_market.domains.board.Application.dto.BoardUpdateStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardUpdateStatusController {
    private final BoardUpdateStatusService boardUpdateStatusService;

    @PostMapping("/board/status-update")
    public ResponseEntity updateStatus(@RequestBody BoardUpdateStatusRequest boardUpdateStatusRequest) {
        boardUpdateStatusService.UpdateStatus(boardUpdateStatusRequest);
        return ResponseEntity.ok().build();
    }
}
