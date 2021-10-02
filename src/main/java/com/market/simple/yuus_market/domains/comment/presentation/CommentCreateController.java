package com.market.simple.yuus_market.domains.comment.presentation;

import com.market.simple.yuus_market.domains.comment.application.CommentCreateService;
import com.market.simple.yuus_market.domains.comment.application.dto.CommentCreateReqeust;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentCreateController {

    private final CommentCreateService commentCreateService;

    @PostMapping("/comment")
    public ResponseEntity commentCreate(@RequestBody  CommentCreateReqeust commentCreateReqeust) {
        commentCreateService.CommentCreate(commentCreateReqeust);
        return ResponseEntity.ok().build();
    }
}
