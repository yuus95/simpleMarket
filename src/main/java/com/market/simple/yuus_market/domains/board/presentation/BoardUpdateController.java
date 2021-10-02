package com.market.simple.yuus_market.domains.board.presentation;

import com.market.simple.yuus_market.domains.board.Application.BoardFindService;
import com.market.simple.yuus_market.domains.board.Application.BoardUpdateService;
import com.market.simple.yuus_market.domains.board.Application.dto.BoardUpdateDto;
import com.market.simple.yuus_market.domains.board.Application.dto.BoardUpdateRequest;
import com.market.simple.yuus_market.domains.photo.application.PhotoDeleteService;
import com.market.simple.yuus_market.domains.photo.application.PhotoFindService;
import com.market.simple.yuus_market.domains.photo.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardUpdateController {
    private final BoardUpdateService boardUpdateService;
    private final PhotoFindService photoFindService;
    private final PhotoDeleteService photoDeleteService;
    private final BoardFindService boardFindService;


    @PutMapping("/board/{boardId}")
    public ResponseEntity update(@PathVariable Long boardId, @RequestBody BoardUpdateRequest boardUpdateRequest) throws Exception {

        boardFindService.findByBoardWithId(boardId, boardUpdateRequest.getUserIdx());

        // DB에 저장되어있는 파일 불러오기
        List<Photo> dbPhotoList = photoFindService.findAllByBoardId(boardId);
        // 전달되어온 파일들
        List<MultipartFile> multipartList = boardUpdateRequest.getFiles();
        // 새롭게 전달되어온 파일들의 목록을 저장할 List 선언
        List<MultipartFile> addFileList = new ArrayList<>();

        if (CollectionUtils.isEmpty(dbPhotoList)) { // DB에 아예 존재 x
            if (!CollectionUtils.isEmpty(multipartList)) { // 전달되어온 파일이 하나라도 존재
                for (MultipartFile multipartFile : multipartList)
                    addFileList.add(multipartFile);    // 저장할 파일 목록에 추가
            }
        } else {  // DB에 한 장 이상 존재
            if (CollectionUtils.isEmpty(multipartList)) { // 전달되어온 파일 아예 x
                // 파일 삭제
                for (Photo dbPhoto : dbPhotoList)
                    photoDeleteService.deletePhotoId(dbPhoto.getId());
            } else {  // 전달되어온 파일 한 장 이상 존재

                // DB에 저장되어있는 파일 원본명 목록
                List<String> dbOriginNameList = new ArrayList<>();

                // DB의 파일 원본명 추출
                for (Photo dbPhoto : dbPhotoList) {
                    // file id로 DB에 저장된 파일 정보 얻어오기
                    Photo dbPhotoDto = photoFindService.findByPhotoId(dbPhoto.getId());
                    // DB의 파일 원본명 얻어오기
                    String dbOrigFileName = dbPhotoDto.getOrigFileName();

                    if (!multipartList.contains(dbOrigFileName))  // 서버에 저장된 파일들 중 전달되어온 파일이 존재하지 않는다면
                        photoDeleteService.deletePhotoId(dbPhoto.getId());  // 파일 삭제
                    else  // 그것도 아니라면
                        dbOriginNameList.add(dbOrigFileName);    // DB에 저장할 파일 목록에 추가
                }

                for (MultipartFile multipartFile : multipartList) { // 전달되어온 파일 하나씩 검사
                    // 파일의 원본명 얻어오기
                    String multipartOrigName = multipartFile.getOriginalFilename();
                    if (!dbOriginNameList.contains(multipartOrigName)) {   // DB에 없는 파일이면
                        addFileList.add(multipartFile); // DB에 저장할 파일 목록에 추가
                    }
                }
            }
        }
        BoardUpdateDto fromBoardUpdateDto = BoardUpdateDto.from(boardUpdateRequest);
        boardUpdateService.update(boardId, fromBoardUpdateDto, addFileList);
        return ResponseEntity.ok().build();
    }
}
