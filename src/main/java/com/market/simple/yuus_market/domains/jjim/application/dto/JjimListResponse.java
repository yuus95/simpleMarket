package com.market.simple.yuus_market.domains.jjim.application.dto;

import com.market.simple.yuus_market.domains.jjim.domain.Jjim;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JjimListResponse {
    private String title;
    private Long boardIdx;

    @Builder
    public JjimListResponse(String title,Long boardIdx){
        this.title=title;
        this.boardIdx=boardIdx;
    }

    public static JjimListResponse from(Jjim jjim){
        return JjimListResponse.builder()
                .title(jjim.getBoard().getTitle())
                .boardIdx(jjim.getBoard().getId())
                .build();
    }
}
