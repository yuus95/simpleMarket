package com.market.simple.yuus_market.domains.board.Application;

import com.market.simple.yuus_market.domains.InitClass;
import com.market.simple.yuus_market.domains.board.Application.dto.BoardDeleteRequest;
import com.market.simple.yuus_market.domains.board.domain.Board;
import com.market.simple.yuus_market.domains.board.domain.BoardRepository;
import com.market.simple.yuus_market.domains.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BoardDeleteServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardDeleteService boardDeleteService;

    @Test
    public void 게시판삭제기능테스트() throws Exception {
        //given
        Member member = InitClass.initMember();
        Board board = InitClass.initBoard();
        BoardDeleteRequest boardDeleteRequest = new BoardDeleteRequest(1L,1L);

        Mockito.when(boardRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(board));

        //when
        boardDeleteService.boardDelete(boardDeleteRequest);
        //then
        Mockito.verify(boardRepository).deleteById(Mockito.any());
    }
}