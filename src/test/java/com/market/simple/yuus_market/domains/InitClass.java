package com.market.simple.yuus_market.domains;

import com.market.simple.yuus_market.domains.board.Application.dto.BoardPostRequest;
import com.market.simple.yuus_market.domains.board.domain.Board;
import com.market.simple.yuus_market.domains.member.domain.AuthProvider;
import com.market.simple.yuus_market.domains.member.domain.Authority;
import com.market.simple.yuus_market.domains.member.domain.Member;

public class InitClass {

    public static Member initMember(){
        Member build = Member.builder()
                .userId("kkad45")
                .nickname("test")
                .password("1234")
                .phone("01047105883")
                .authority(Authority.ROLE_USER)
                .provider(AuthProvider.local)
                .build();
        return build;
    }

    public static Board initBoard(){
        BoardPostRequest boardPostRequest = new BoardPostRequest("kkad45", "테스트타이틀", "내용123", "서울",null );
        Member member= initMember();
        Board board = new Board(
                member,
                boardPostRequest.getTitle(),
                boardPostRequest.getContent(),
                boardPostRequest.getLocation()
        );
        return board;
    }
}
