package com.market.simple.yuus_market.domains;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


/**
 * Builder에 넣을필요가 없다.
 *
 * EntityListeners : JPA Entity에서 이벤트가 발생할 때마다 특정 로직을 실행시킬수 있는 어노테이션
 * Auditing - > Spring Data JPA에서 시간에 대해서 자동으로 값을 넣어주는 기능
 */ 
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class baseEntity {

    @CreatedDate // Entity가 생성되어 저장될 떄 시간이 자동 저장된다.
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
