package com.market.simple.yuus_market.domains.board.presentation;

import com.market.simple.yuus_market.domains.ResponseDto;
import com.market.simple.yuus_market.domains.board.Application.BoardListService;
import com.market.simple.yuus_market.domains.board.Application.GetBoardByIdxService;
import com.market.simple.yuus_market.domains.board.Application.dto.GetBoardByIdxResponse;
import com.market.simple.yuus_market.domains.photo.application.PhotoFindService;
import com.market.simple.yuus_market.domains.photo.application.dto.PhotoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BoardListController {
    private final BoardListService boardListService;
    private final PhotoFindService photoFindService;
    private final GetBoardByIdxService getBoardByIdx;


    @GetMapping("/board/list")
    public ResponseEntity getList() {
        return new ResponseEntity<>(new ResponseDto(200, "로그인 성공", boardListService.getList()), HttpStatus.OK);
    }

    @GetMapping("/board/list/{boardId}")
    public ResponseEntity searchById(@PathVariable Long boardId) {


        List<PhotoResponse> photoResponseList = photoFindService.findAllByBoardId(boardId)
                .stream()
                .map(o -> new PhotoResponse(o))
                .collect(Collectors.toList());
        List<Long> photoId = new ArrayList<>();
        // 각 첨부파일 id 추가
        for (PhotoResponse photoResponseDto : photoResponseList)
            photoId.add(photoResponseDto.getFileId());
        GetBoardByIdxResponse boardByIdxResponse = getBoardByIdx.searchById(boardId, photoId);
        return new ResponseEntity(new ResponseDto(200, "상세 조회 완료 ", boardByIdxResponse), HttpStatus.OK);

    }
}
