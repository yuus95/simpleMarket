package com.market.simple.yuus_market.domains.photo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo,Long> {

   List<Photo> findAllbyBoardId(Long boardId);

}
