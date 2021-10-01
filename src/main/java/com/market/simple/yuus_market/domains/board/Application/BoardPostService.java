package com.market.simple.yuus_market.domains.board.Application;

import com.market.simple.yuus_market.common.handler.ex.CustomException;
import com.market.simple.yuus_market.common.handler.ex.ErrorCode;
import com.market.simple.yuus_market.domains.board.Application.dto.BoardPostRequest;
import com.market.simple.yuus_market.domains.board.domain.Board;
import com.market.simple.yuus_market.domains.board.domain.BoardRepository;
import com.market.simple.yuus_market.domains.member.domain.Member;
import com.market.simple.yuus_market.domains.member.domain.MemberRepository;
import com.market.simple.yuus_market.domains.photo.application.FileHandler;
import com.market.simple.yuus_market.domains.photo.domain.Photo;
import com.market.simple.yuus_market.domains.photo.domain.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardPostService {
    private final BoardRepository boardRepository;
    private final FileHandler fileHandler;
    private final PhotoRepository photoRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long create(
            BoardPostRequest boardPostRequest
    ) throws Exception {
        // 파일 처리를 위한 Board 객체 생성
        System.out.println("boardPostRequest" + boardPostRequest.getUserId());
        Member member = memberRepository.findByUserId(boardPostRequest.getUserId()).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));


        Board board = new Board(
                member,
                boardPostRequest.getTitle(),
                boardPostRequest.getContent(),
                boardPostRequest.getLocation()
        );

        List<Photo> photoList = fileHandler.parseFileInfo(boardPostRequest.getFiles());

        // 파일이 존재할 때에만 처리
        if (!photoList.isEmpty()) {
            for (Photo photo : photoList)
                // 파일을 DB에 저장
                board.addPhoto(photoRepository.save(photo));
        }

        return boardRepository.save(board).getId();
    }
}
