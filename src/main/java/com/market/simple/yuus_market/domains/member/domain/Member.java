package com.market.simple.yuus_market.domains.member.domain;


import com.market.simple.yuus_market.domains.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@Getter
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 25, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String userId;

    private String password;

    //    @Column(length = 25 ,nullable = false)
    @Column
    private String phone;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;


    public String getKey(Authority authority) {
        if (authority == Authority.ROLE_USER) {
            return "ROLE_USER";
        } else {
            return "ROLE_ADMIN";
        }
    }

    public Member update(String name) {
        this.nickname = name;
        return this;
    }

    @Builder
    public Member(String nickname, String userId, String password, String phone, Authority authority, AuthProvider provider) {
        this.nickname = nickname;
        this.userId = userId;
        this.password = password;
        this.phone = phone;
        this.authority = authority;
        this.provider = provider;
    }
}
