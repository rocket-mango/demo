package com.demogroup.demoweb.domain.mango.domain;

import com.demogroup.demoweb.dom.dto.MangoDTO;
import com.demogroup.demoweb.domain.mango.dto.MangoDto;
import com.demogroup.demoweb.domain.user.domain.User;
import com.demogroup.demoweb.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Mango extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;


    @ManyToOne
    @JoinColumn(name="uid")
    private User user;

    @Column(columnDefinition = "tinyint(1)")
    private boolean is_disease;

    @Column(length = 50)
    private String disease;

    private String img_url;

    @Column(length = 50)
    private String location;

    public static Mango toEntity(MangoDTO dto, User user){
        return Mango.builder()
                .user(user)
                .is_disease(dto.is_disease())
                .disease(dto.getDisease())
                .img_url(dto.getImg_url())
                .location(dto.getLocation())
                .build();
    }

}

