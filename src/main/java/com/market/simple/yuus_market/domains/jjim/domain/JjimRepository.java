package com.market.simple.yuus_market.domains.jjim.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JjimRepository extends JpaRepository<Jjim,Long> {
   Optional<Jjim> findByMemberIdAndBoardId(Long memberId, Long boardId);
}
