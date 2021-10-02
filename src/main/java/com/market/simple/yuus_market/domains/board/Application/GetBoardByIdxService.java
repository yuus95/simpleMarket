package com.market.simple.yuus_market.domains.board.Application;

import com.market.simple.yuus_market.domains.board.Application.dto.GetBoardByIdxResponse;
import com.market.simple.yuus_market.domains.board.domain.Board;
import com.market.simple.yuus_market.domains.board.domain.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetBoardByIdxService {
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public GetBoardByIdxResponse searchById(Long id, List<Long> fileId){
        Board entity = boardRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));


        return new GetBoardByIdxResponse(entity, fileId);
    }
}
