package com.market.simple.yuus_market.domains.board.Application;

import com.market.simple.yuus_market.common.handler.ex.CustomException;
import com.market.simple.yuus_market.common.handler.ex.ErrorCode;
import com.market.simple.yuus_market.domains.board.Application.dto.BoardUpdateStatusRequest;
import com.market.simple.yuus_market.domains.board.domain.Board;
import com.market.simple.yuus_market.domains.board.domain.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardUpdateStatusService {
    private final BoardRepository boardRepository;

    @Transactional
    public void UpdateStatus(BoardUpdateStatusRequest boardUpdateStatusRequest) {
        Board board = boardRepository.findById(boardUpdateStatusRequest.getBoardIdx()).orElseThrow(() -> new CustomException(ErrorCode.CANNOT_FIND_BOARD));
        board.updateStatus(boardUpdateStatusRequest.getBoardStatus());

    }

}
