package com.market.simple.yuus_market.domains.board.Application;

import com.market.simple.yuus_market.common.handler.ex.CustomException;
import com.market.simple.yuus_market.common.handler.ex.ErrorCode;
import com.market.simple.yuus_market.domains.board.Application.dto.BoardDeleteRequest;
import com.market.simple.yuus_market.domains.board.domain.Board;
import com.market.simple.yuus_market.domains.board.domain.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardDeleteService {
    private final BoardRepository boardRepository;

    @Transactional
    public void boardDelete(BoardDeleteRequest boardDeleteRequest) {
        Board board = boardRepository.findById(boardDeleteRequest.getBoardIdx()).orElseThrow(() -> new CustomException(ErrorCode.CANNOT_FIND_BOARD));

        if (board.getMember().getId() != boardDeleteRequest.getUserIdx()) {
            new CustomException(ErrorCode.CANCOT_AUTHORIZATION);
        }
        boardRepository.deleteById(boardDeleteRequest.getBoardIdx());
    }
}
