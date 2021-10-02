package com.market.simple.yuus_market.domains.photo.application;

import com.market.simple.yuus_market.domains.photo.domain.Photo;
import com.market.simple.yuus_market.domains.photo.domain.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoFindService {
    private final PhotoRepository photoRepository;

    public List<Photo> findAllByBoardId(Long boardId) {
        List<Photo> allbyBoardId = photoRepository.findAllByBoardId(boardId);

        return allbyBoardId;
    }

    public Photo findByPhotoId(Long photoId){
        Optional<Photo> byId = photoRepository.findById(photoId);
        return byId.get();
    }
}
