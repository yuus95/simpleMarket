package com.market.simple.yuus_market.domains.comment.domain;

import com.market.simple.yuus_market.domains.baseEntity;
import com.market.simple.yuus_market.domains.board.domain.Board;
import com.market.simple.yuus_market.domains.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Comment extends baseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    private Long commentOwner;

    @Enumerated(EnumType.STRING)
    private CommentStatus commentStatus;

}
