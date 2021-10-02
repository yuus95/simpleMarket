package com.market.simple.yuus_market.domains.photo.application;

import com.market.simple.yuus_market.domains.photo.domain.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhotoDeleteService {

    private final PhotoRepository photoRepository;

    public void deletePhotoId(Long photoId){
        photoRepository.deleteById(photoId);
        return;
    }
}
