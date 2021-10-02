package com.market.simple.yuus_market.domains.board.Application;

import com.market.simple.yuus_market.domains.board.Application.dto.BoardGetListResponse;
import com.market.simple.yuus_market.domains.board.domain.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardListService {

    private final BoardRepository boardRepository;

    public List<BoardGetListResponse> getList() {
        PageRequest createAt = PageRequest.of(0, 5, Sort.by("createDate").descending());

        List<BoardGetListResponse> collect = boardRepository.findAll(createAt)
                .stream()
                .map(o -> BoardGetListResponse.from(o))
                .collect(Collectors.toList());
        return collect;
    }
}
