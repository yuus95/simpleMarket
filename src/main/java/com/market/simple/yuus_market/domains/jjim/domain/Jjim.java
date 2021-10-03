package com.market.simple.yuus_market.domains.jjim.domain;

import com.market.simple.yuus_market.domains.baseEntity;
import com.market.simple.yuus_market.domains.board.domain.Board;
import com.market.simple.yuus_market.domains.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Jjim extends baseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Enumerated(value = EnumType.STRING)
    private JjimStatus jjimStatus;

    @Builder
    public Jjim(Member member,Board board,JjimStatus jjimStatus){
        this.member = member;
        this.board = board;
        this.jjimStatus=jjimStatus;
    }

    public void statusUpdate(JjimStatus jjimStatus){
        this.jjimStatus = jjimStatus;
    }
}
