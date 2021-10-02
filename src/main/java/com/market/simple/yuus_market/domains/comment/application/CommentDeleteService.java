package com.market.simple.yuus_market.domains.comment.application;


import com.market.simple.yuus_market.common.handler.ex.CustomException;
import com.market.simple.yuus_market.common.handler.ex.ErrorCode;
import com.market.simple.yuus_market.domains.comment.domain.Comment;
import com.market.simple.yuus_market.domains.comment.domain.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentDeleteService {
    private final CommentRepository commentRepository;

    @Transactional
    public void CommentDelete(Long commentIdx) {
        Comment comment = commentRepository.findById(commentIdx).orElseThrow(() -> new CustomException(ErrorCode.CANNOT_PROVIDER));
        comment.Update("삭제된 댓글입니다.");

    }
}
