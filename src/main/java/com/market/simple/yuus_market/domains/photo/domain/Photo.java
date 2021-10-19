package com.market.simple.yuus_market.domains.photo.domain;

import com.market.simple.yuus_market.domains.BaseEntity;
import com.market.simple.yuus_market.domains.board.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Photo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String origFileName;
    private String filePath;
    private Long fileSize;

    @Builder
    public Photo( String origFileName,String filePath,Long fileSize){
        this.board=board;
        this.origFileName=origFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public void setBoard(Board board){
        this.board =board;

        if(!board.getPhoto().contains(this)){
            board.getPhoto().add(this);
        }
    }
}
