package com.market.simple.yuus_market.domains.board.domain;

import com.market.simple.yuus_market.domains.baseEntity;
import com.market.simple.yuus_market.domains.member.domain.Member;
import com.market.simple.yuus_market.domains.photo.domain.Photo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "boards")
public class Board extends baseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_Id")
    private Member member;

    @OneToMany(mappedBy = "board",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Photo> photo = new ArrayList<>();

    private String location;

    @Enumerated(EnumType.STRING)
    private BoardStatus boardStatus;

    @Builder
    public Board(Member member,String title,String content,String location){
        this.member= member;
        this.title=title;
        this.content = content;
        this.location=location;
        this.boardStatus=BoardStatus.POSTING;
    }

    public void update(String title,String content,String location){
        this.title=title;
        this.content=content;
        this.location = location;
    }

    public void addPhoto(Photo photo){
        this.photo.add(photo);
        if(photo.getBoard() != this){
            photo.setBoard(this);
        }
    }

}
