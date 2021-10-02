package com.market.simple.yuus_market.domains.board.Application.dto;

import com.market.simple.yuus_market.domains.board.domain.BoardStatus;
import lombok.Getter;

@Getter
public class BoardUpdateStatusRequest {
    private Long boardIdx;
    private BoardStatus boardStatus;

}
