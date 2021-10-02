package com.market.simple.yuus_market.domains.comment.application;

import com.market.simple.yuus_market.domains.comment.application.dto.CommentListResponse;
import com.market.simple.yuus_market.domains.comment.domain.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentListService {
    private final CommentRepository commentRepository;

    public List<CommentListResponse> getList(){
        PageRequest createDate = PageRequest.of(0, 5, Sort.by("createDate").descending());
        List<CommentListResponse> collect = commentRepository.findAll(createDate).stream()
                .map(o -> CommentListResponse.from(o))
                .collect(Collectors.toList());
        return collect;
    }
}
