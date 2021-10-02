package com.market.simple.yuus_market.domains.board.Application;

import com.market.simple.yuus_market.domains.board.Application.dto.BoardUpdateDto;
import com.market.simple.yuus_market.domains.board.domain.Board;
import com.market.simple.yuus_market.domains.board.domain.BoardRepository;
import com.market.simple.yuus_market.domains.photo.application.FileHandler;
import com.market.simple.yuus_market.domains.photo.domain.Photo;
import com.market.simple.yuus_market.domains.photo.domain.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardUpdateService {
    private final BoardRepository boardRepository;
    private final FileHandler fileHandler;
    private final PhotoRepository photoRepository;


    @Transactional
    public void update(
            Long id,
            BoardUpdateDto requestDto,
            List<MultipartFile> files
    ) throws Exception {
        Board board = boardRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        List<Photo> photoList = fileHandler.parseFileInfo(files);

        if (!photoList.isEmpty()) {
            for (Photo photo : photoList) {
                board.addPhoto(photoRepository.save(photo));
            }
        }

        board.update(requestDto.getTitle(),
                requestDto.getContent(),requestDto.getLocation());
    }

}
