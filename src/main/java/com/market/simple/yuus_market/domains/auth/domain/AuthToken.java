package com.market.simple.yuus_market.domains.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class AuthToken {
    @Id
    private String  idKey;

    @Column(length = 1000)
    private String value;

    public AuthToken updateValue(String token) {
        this.value = token;
        return this;
    }

    @Builder
    public AuthToken(String idKey, String value) {
        this.idKey = idKey;
        this.value = value;
    }
}



