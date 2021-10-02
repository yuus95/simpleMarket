package com.market.simple.yuus_market.domains.board.Application.dto;

import com.market.simple.yuus_market.domains.board.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardGetListResponse {
    private Long boardIdx;
    private String title;
    private String userId;

    @Builder
    public BoardGetListResponse(Long boardIdx,String title, String userId){
        this.boardIdx= boardIdx;
        this.title= title;
        this.userId = userId;
    }

    public static BoardGetListResponse from (Board board){
        return  BoardGetListResponse.builder()
                .boardIdx(board.getId())
                .title(board.getTitle())
                .userId(board.getMember().getUserId())
                .build();
    }

}
