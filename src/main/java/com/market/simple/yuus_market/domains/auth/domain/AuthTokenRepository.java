package com.market.simple.yuus_market.domains.auth.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthTokenRepository extends JpaRepository<AuthToken,String> {
    Optional<Object> findByIdKey(String idKey);
}
