package com.market.simple.yuus_market.domains.comment.presentation;

import com.market.simple.yuus_market.domains.comment.application.CommentUpdateService;
import com.market.simple.yuus_market.domains.comment.application.dto.CommentUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentUpdateController {
    private final CommentUpdateService commentUpdateService;

    @PutMapping("/comment")
    public ResponseEntity commentUpdate(@RequestBody  CommentUpdateRequest commentUpdateRequest){
        commentUpdateService.commentUpdate(commentUpdateRequest);

        return ResponseEntity.ok().build();
    }
}
