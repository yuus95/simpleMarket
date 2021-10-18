package com.market.simple.yuus_market.domains.jjim.application;

import com.market.simple.yuus_market.domains.InitClass;
import com.market.simple.yuus_market.domains.board.domain.Board;
import com.market.simple.yuus_market.domains.board.domain.BoardRepository;
import com.market.simple.yuus_market.domains.jjim.application.dto.JjimRegisterRequest;
import com.market.simple.yuus_market.domains.jjim.domain.Jjim;
import com.market.simple.yuus_market.domains.jjim.domain.JjimRepository;
import com.market.simple.yuus_market.domains.jjim.domain.JjimStatus;
import com.market.simple.yuus_market.domains.member.domain.Member;
import com.market.simple.yuus_market.domains.member.domain.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class JjimRegisterServiceTest {

    @Mock
    private  JjimRepository jjimRepository;
    @Mock
    private  BoardRepository boardRepository;
    @Mock
    private  MemberRepository memberRepository;

    @InjectMocks
    private JjimRegisterService jjimRegisterService;


    @Test
    public void 찜등록되있는경우() throws Exception{
        //given
        Board board = InitClass.initBoard();
        Member member = InitClass.initMember();
        Jjim jjim = InitClass.initJjim(board, member);

        JjimRegisterRequest jjimRegisterRequest= new JjimRegisterRequest();
        jjimRegisterRequest.setUserIdx(member.getId());
        jjimRegisterRequest.setBoardIdx(board.getId());

        Mockito.when(jjimRepository.findByMemberIdAndBoardId(member.getId(),board.getId())).thenReturn(Optional.of(jjim));
        //when
        jjimRegisterService.jjimRegister(jjimRegisterRequest);
        //then
        Assertions.assertThat(jjim.getJjimStatus()).isEqualTo(JjimStatus.CANCEL);
    }
    @Test
    public void 찜등록안된경우() throws Exception{
        //given
        Board board = InitClass.initBoard();
        Member member = InitClass.initMember();
        Jjim jjim = InitClass.initJjim(board, member);

        JjimRegisterRequest jjimRegisterRequest= new JjimRegisterRequest();
        jjimRegisterRequest.setUserIdx(member.getId());
        jjimRegisterRequest.setBoardIdx(board.getId());

        Mockito.when(jjimRepository.findByMemberIdAndBoardId(member.getId(),board.getId())).thenReturn(Optional.of(null));
        //when
        jjimRegisterService.jjimRegister(jjimRegisterRequest);
        //then
        Mockito.verify(jjimRepository.save(Mockito.any()));

    }
}