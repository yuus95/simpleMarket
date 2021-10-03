package com.market.simple.yuus_market.domains.jjim.domain;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JjimRepository extends JpaRepository<Jjim,Long> {
   Optional<Jjim> findByMemberIdAndBoardId(Long memberId, Long boardId);

   List<Jjim> findAllByMemberId(Long userIdx, Sort createDate);
}
