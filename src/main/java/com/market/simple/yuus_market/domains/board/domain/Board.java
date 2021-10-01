package com.market.simple.yuus_market.domains.board.domain;

import com.market.simple.yuus_market.domains.baseEntity;
import com.market.simple.yuus_market.domains.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Board extends baseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    private String location;

    @Enumerated
    private BoardStatus boardStatus;

}
