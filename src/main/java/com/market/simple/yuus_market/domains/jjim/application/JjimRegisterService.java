package com.market.simple.yuus_market.domains.jjim.application;

import com.market.simple.yuus_market.common.handler.ex.CustomException;
import com.market.simple.yuus_market.common.handler.ex.ErrorCode;
import com.market.simple.yuus_market.domains.board.domain.Board;
import com.market.simple.yuus_market.domains.board.domain.BoardRepository;
import com.market.simple.yuus_market.domains.jjim.application.dto.JjimRegisterRequest;
import com.market.simple.yuus_market.domains.jjim.domain.Jjim;
import com.market.simple.yuus_market.domains.jjim.domain.JjimRepository;
import com.market.simple.yuus_market.domains.jjim.domain.JjimStatus;
import com.market.simple.yuus_market.domains.member.domain.Member;
import com.market.simple.yuus_market.domains.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JjimRegisterService {

    private final JjimRepository jjimRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public void jjimRegister(JjimRegisterRequest jjimRegisterRequest) {

        Optional<Jjim> byMemberIdAndBoardId = jjimRepository.findByMemberIdAndBoardId(jjimRegisterRequest.getUserIdx(), jjimRegisterRequest.getBoardIdx());


        if(byMemberIdAndBoardId.isPresent()){
            if(byMemberIdAndBoardId.get().getJjimStatus() == JjimStatus.JJIM){
                byMemberIdAndBoardId.get().statusUpdate(JjimStatus.CANCEL);
            }
            else{
                byMemberIdAndBoardId.get().statusUpdate(JjimStatus.JJIM);
            }
        }
        else{
            Member member = memberRepository.findById(jjimRegisterRequest.getUserIdx()).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
            Board board = boardRepository.findById(jjimRegisterRequest.getBoardIdx()).orElseThrow(() -> new CustomException(ErrorCode.CANNOT_FIND_BOARD));

            Jjim build = Jjim.builder()
                    .board(board)
                    .member(member)
                    .jjimStatus(JjimStatus.JJIM)
                    .build();

            jjimRepository.save(build);
        }


    }
}
