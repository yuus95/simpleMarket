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

@RequiredArgsConstructor
@Service
public class CommentCreateService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public void CommentCreate(CommentCreateReqeust commentCreateReqeust) {
        Long boardIdx = commentCreateReqeust.getBoardIdx();
        Board board = boardRepository.findById(boardIdx).orElseThrow(() -> new CustomException(ErrorCode.CANNOT_FIND_BOARD));
        Comment byId = null;
        Long userIdx = commentCreateReqeust.getUserIdx();
        Member member = memberRepository.findById(userIdx).orElseThrow(() -> new CustomException(ErrorCode.NOT_MATCHED_ID));
        if (commentCreateReqeust.getCommentOwner() != 0 ){
            byId = commentRepository.findById(commentCreateReqeust.getCommentOwner()).get();
        }


        Comment build = Comment.builder()
                .content(commentCreateReqeust.getContent())
                .board(board)
                .member(member)
                .commentOwner(byId)
                .commentStatus(CommentStatus.POSTING)
                .build();

        commentRepository.save(build);


    }

}
