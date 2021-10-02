package com.market.simple.yuus_market.domains.comment.application;

import com.market.simple.yuus_market.common.handler.ex.CustomException;
import com.market.simple.yuus_market.common.handler.ex.ErrorCode;
import com.market.simple.yuus_market.domains.board.domain.Board;
import com.market.simple.yuus_market.domains.board.domain.BoardRepository;
import com.market.simple.yuus_market.domains.comment.application.dto.CommentCreateReqeust;
import com.market.simple.yuus_market.domains.comment.domain.Comment;
import com.market.simple.yuus_market.domains.comment.domain.CommentRepository;
import com.market.simple.yuus_market.domains.comment.domain.CommentStatus;
import com.market.simple.yuus_market.domains.member.domain.Member;
import com.market.simple.yuus_market.domains.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentCreateService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void CommentCreate(CommentCreateReqeust commentCreateReqeust) {
        Long boardIdx = commentCreateReqeust.getBoardIdx();
        Board board = boardRepository.findById(boardIdx).orElseThrow(() -> new CustomException(ErrorCode.CANNOT_FIND_BOARD));
        Long userIdx = commentCreateReqeust.getUserIdx();
        Member member = memberRepository.findById(userIdx).orElseThrow(() -> new CustomException(ErrorCode.NOT_MATCHED_ID));



        Comment build = Comment.builder()
                .content(commentCreateReqeust.getContent())
                .board(board)
                .member(member)
                .commentStatus(CommentStatus.POSTING)
                .build();

        commentRepository.save(build);


    }

}
