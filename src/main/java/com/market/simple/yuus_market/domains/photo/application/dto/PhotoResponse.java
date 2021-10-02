package com.market.simple.yuus_market.domains.photo.application.dto;

import com.market.simple.yuus_market.domains.photo.domain.Photo;
import lombok.Getter;

@Getter
public class PhotoResponse {
    private Long fileId;  // 파일 id


    public PhotoResponse(Photo entity) {
        this.fileId = entity.getId();
    }
}
