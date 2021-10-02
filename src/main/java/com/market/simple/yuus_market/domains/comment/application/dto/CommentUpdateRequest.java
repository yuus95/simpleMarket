package com.market.simple.yuus_market.domains.comment.application.dto;

import lombok.Getter;

@Getter
public class CommentUpdateRequest {
    private Long commentIdx;
    private String content;
}
