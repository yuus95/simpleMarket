package com.market.simple.yuus_market.domains.jjim.application;

import com.market.simple.yuus_market.domains.InitClass;
import com.market.simple.yuus_market.domains.board.domain.Board;
import com.market.simple.yuus_market.domains.jjim.application.dto.JjimListResponse;
import com.market.simple.yuus_market.domains.jjim.domain.Jjim;
import com.market.simple.yuus_market.domains.jjim.domain.JjimRepository;
import com.market.simple.yuus_market.domains.jjim.domain.JjimStatus;
import com.market.simple.yuus_market.domains.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class JjimListServiceTest {

    @Mock
    private JjimRepository jjimRepository;

    @InjectMocks
    private JjimListService jjimListService;

    @Test
    public void 찜리스트() throws Exception {
        //given
        Board board = InitClass.initBoard();
        Member member = InitClass.initMember();
        List<JjimListResponse> jjimListResponses = new ArrayList<>();
        List<Jjim> jjimList = new ArrayList<>();

        Jjim build = Jjim.builder()
                .board(board)
                .member(member)
                .jjimStatus(JjimStatus.JJIM)
                .build();
        jjimList.add(build);
        Mockito.when(jjimRepository.findAllByMemberId(Mockito.any(), Mockito.any())).thenReturn(jjimList);
        Jjim jjim = jjimList.get(0);
        JjimListResponse from = JjimListResponse.from(jjim);
        jjimListResponses.add(from);

        //when
        List<JjimListResponse> list = jjimListService.getList(member.getId());


        //then
        Assertions.assertThat(list.get(0).getTitle()).isEqualTo( jjimListResponses.get(0).getTitle());
        Assertions.assertThat(list.get(0).getBoardIdx()).isEqualTo( jjimListResponses.get(0).getBoardIdx());



    }


}