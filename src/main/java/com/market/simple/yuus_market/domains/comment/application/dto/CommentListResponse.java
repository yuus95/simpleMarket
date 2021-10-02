package com.market.simple.yuus_market.domains.comment.application.dto;

import com.market.simple.yuus_market.domains.comment.domain.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentListResponse {

    private String content;
    private String userId;


    @Builder
    public CommentListResponse(String content, String userId) {
        this.content = content;
        this.userId = userId;
    }

    public static CommentListResponse from(Comment comment) {
        return CommentListResponse.builder()
                .content(comment.getContent())
                .userId(comment.getMember().getUserId())
                .build();
    }

}
