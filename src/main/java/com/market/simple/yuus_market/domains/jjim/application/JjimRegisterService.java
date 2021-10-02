package com.market.simple.yuus_market.domains.jjim.application;

import com.market.simple.yuus_market.common.handler.ex.CustomException;
import com.market.simple.yuus_market.common.handler.ex.ErrorCode;
import com.market.simple.yuus_market.domains.board.domain.Board;
import com.market.simple.yuus_market.domains.board.domain.BoardRepository;
import com.market.simple.yuus_market.domains.jjim.application.dto.JjimRegisterRequest;
import com.market.simple.yuus_market.domains.jjim.domain.Jjim;
import com.market.simple.yuus_market.domains.jjim.domain.JjimRepository;
import com.market.simple.yuus_market.domains.member.domain.Member;
import com.market.simple.yuus_market.domains.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JjimRegisterService {

    private final JjimRepository jjimRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public void jjimRegister(JjimRegisterRequest jjimRegisterRequest) {
        System.out.println("member"+jjimRegisterRequest.getBoardIdx());
        System.out.println("board"+jjimRegisterRequest.getUserIdx());

        Member member = memberRepository.findById(jjimRegisterRequest.getUserIdx()).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Board board = boardRepository.findById(jjimRegisterRequest.getBoardIdx()).orElseThrow(() -> new CustomException(ErrorCode.CANNOT_FIND_BOARD));

        if (jjimRepository.findByMemberIdAndBoardId(jjimRegisterRequest.getUserIdx(), jjimRegisterRequest.getBoardIdx()).isPresent()) {
            new CustomException(ErrorCode.CANNOT_PROVIDER);
        }

        Jjim build = Jjim.builder()
                .board(board)
                .member(member)
                .build();

        jjimRepository.save(build);

    }
}
