package com.demogroup.demoweb.global.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
/*모든 엔티티에 공통으로 가져가야 하는
* 속성들을 이렇게 상위 클래스에 모아놓는 것이다.
*/
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    @Column(name = "created_date")
    @CreatedDate
    private Timestamp createdDate;

    @Column(name="modified_date")
    @LastModifiedDate
    private Timestamp modifiedDate;

    @PrePersist
    public void onPrePersis(){
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.createdDate= timestamp;
        this.modifiedDate=this.createdDate;

    }

    @PreUpdate
    public void onPreUpdate(){
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.modifiedDate=timestamp;
    }
}
