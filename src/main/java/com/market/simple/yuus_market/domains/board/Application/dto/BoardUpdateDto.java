package com.market.simple.yuus_market.domains.board.Application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardUpdateDto {
    private String title;
    private String content;
    private String location;

    @Builder
    public BoardUpdateDto(String title,String content,String location){
        this.title = title;
        this.content = content;
        this.location = location;
    }

    public static BoardUpdateDto from(BoardUpdateRequest boardUpdateRequest){
        return BoardUpdateDto.builder()
                .title(boardUpdateRequest.getTitle())
                .content(boardUpdateRequest.getContent())
                .location(boardUpdateRequest.getLocation())
                .build();
    }
}
