package com.market.simple.yuus_market.domains.comment.presentation;

import com.market.simple.yuus_market.domains.ResponseDto;
import com.market.simple.yuus_market.domains.comment.application.CommentListService;
import com.market.simple.yuus_market.domains.comment.application.dto.CommentListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentListController {
    private final CommentListService commentListService;

    @GetMapping("/comment/list")
    public ResponseEntity CommentList() {
        List<CommentListResponse> list = commentListService.getList();
        return new ResponseEntity(new ResponseDto(200, "댓글 목록 조회 성공", list), HttpStatus.OK);
    }
}
