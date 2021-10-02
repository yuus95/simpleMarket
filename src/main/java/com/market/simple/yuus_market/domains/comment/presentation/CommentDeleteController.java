package com.market.simple.yuus_market.domains.comment.presentation;

import com.market.simple.yuus_market.domains.comment.application.CommentDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentDeleteController {
    private final CommentDeleteService commentDeleteService;

    @DeleteMapping("/api/comment")
    public ResponseEntity CommentDeleete(@RequestParam("boardIdx") Long boardIdx) {
        commentDeleteService.CommentDelete(boardIdx);

        return ResponseEntity.ok().build();

    }
}
