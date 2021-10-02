package com.market.simple.yuus_market.domains.board.Application.dto;

import com.market.simple.yuus_market.domains.board.domain.Board;
import com.market.simple.yuus_market.domains.board.domain.BoardStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetBoardByIdxResponse {
    private Long id;
    private String title;
    private String content;
    private BoardStatus boardStatus;
    private List<Long> fileId;  // 첨부 파일 id 목록

    public GetBoardByIdxResponse(Board entity, List<Long> fileId) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.boardStatus= entity.getBoardStatus();
        this.content = entity.getContent();
        this.fileId = fileId;
    }
}
