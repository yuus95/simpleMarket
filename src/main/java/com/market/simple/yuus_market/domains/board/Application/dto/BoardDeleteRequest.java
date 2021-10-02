package com.market.simple.yuus_market.domains.board.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardDeleteRequest {
    private Long userIdx;
    private Long boardIdx;
}
