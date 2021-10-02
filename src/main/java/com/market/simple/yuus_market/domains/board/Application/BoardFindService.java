package com.market.simple.yuus_market.domains.board.Application;

import com.market.simple.yuus_market.common.handler.ex.CustomException;
import com.market.simple.yuus_market.common.handler.ex.ErrorCode;
import com.market.simple.yuus_market.domains.board.domain.Board;
import com.market.simple.yuus_market.domains.board.domain.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardFindService {
    private final BoardRepository boardRepository;

    public void findByBoardWithId(Long userIdx,Long boardIdx){
        Board board = boardRepository.findById(boardIdx).orElseThrow(() -> new CustomException(ErrorCode.CANNOT_FIND_BOARD));

        if(board.getMember().getId() != userIdx){
           new CustomException(ErrorCode.CANNOT_PROVIDER);
        }

    }
}
