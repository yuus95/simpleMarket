package com.market.simple.yuus_market.domains.comment.application.dto;

import lombok.Getter;

@Getter
public class CommentCreateReqeust {
    private Long userIdx;
    private Long boardIdx;
    private Long commentOwner;
    private String content;

}
