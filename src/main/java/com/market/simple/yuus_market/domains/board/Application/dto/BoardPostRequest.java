package com.market.simple.yuus_market.domains.board.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardPostRequest {
    private String userId;
    private String title;
    private String content;
    private String location;
    private List<MultipartFile> files;
}
