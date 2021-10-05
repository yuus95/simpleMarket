package com.market.simple.yuus_market.domains.board.Application;

import com.market.simple.yuus_market.domains.board.Application.dto.BoardPostRequest;
import com.market.simple.yuus_market.domains.board.domain.Board;
import com.market.simple.yuus_market.domains.board.domain.BoardRepository;
import com.market.simple.yuus_market.domains.member.domain.AuthProvider;
import com.market.simple.yuus_market.domains.member.domain.Authority;
import com.market.simple.yuus_market.domains.member.domain.Member;
import com.market.simple.yuus_market.domains.member.domain.MemberRepository;
import com.market.simple.yuus_market.domains.photo.application.FileHandler;
import com.market.simple.yuus_market.domains.photo.domain.PhotoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith({MockitoExtension.class})
class BoardPostServiceTest {
    @Mock
    private  BoardRepository boardRepository;
    @Mock
    private  FileHandler fileHandler;
    @Mock
    private  PhotoRepository photoRepository;
    @Mock
    private  MemberRepository memberRepository;

    @InjectMocks
    private BoardPostService boardPostService;

    @Test
    public void 게시판_생성() throws Exception{
        //given
        Member build = Member.builder()
                .userId("kkad45")
                .nickname("test")
                .password("1234")
                .phone("01047105883")
                .authority(Authority.ROLE_USER)
                .provider(AuthProvider.local)
                .build();
        BoardPostRequest boardPostRequest = new BoardPostRequest("kkad45", "테스트타이틀", "내용123", "서울",null );

        Board board = new Board(
                build,
                boardPostRequest.getTitle(),
                boardPostRequest.getContent(),
                boardPostRequest.getLocation()
        );
        Mockito.when(memberRepository.findByUserId("kkad45")).thenReturn(Optional.of(build));
        Mockito.when(boardRepository.save(Mockito.any(Board.class))).thenReturn(board);

        //when
        Long aLong = boardPostService.create(boardPostRequest);

        //then
        Mockito.verify(boardRepository).save(Mockito.any(Board.class));
    }
}