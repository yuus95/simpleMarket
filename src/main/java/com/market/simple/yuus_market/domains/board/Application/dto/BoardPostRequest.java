package com.market.simple.yuus_market.domains.board.Application.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class BoardPostRequest {
    private String userId;
    private String title;
    private String content;
    private String location;
    private List<MultipartFile> files;
}
