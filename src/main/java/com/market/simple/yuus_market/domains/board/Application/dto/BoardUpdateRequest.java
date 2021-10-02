package com.market.simple.yuus_market.domains.board.Application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
public class BoardUpdateRequest {
    private Long userIdx;
    private String title;
    private String content;
    private String location;
    private List<MultipartFile> files;

}
