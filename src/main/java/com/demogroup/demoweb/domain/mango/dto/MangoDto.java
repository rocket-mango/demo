package com.demogroup.demoweb.domain.mango.dto;

import com.demogroup.demoweb.domain.mango.domain.Mango;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
@Builder
@Getter
public class MangoDto {
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private long mid;
    private String disease;
    private String img_url;
    private String location;
    private boolean is_disease;

    public static MangoDto of(Mango e){
        return MangoDto.builder()
                .mid(e.getMid())
                .disease(e.getDisease())
                .img_url(e.getImg_url())
                .location(e.getLocation())
                .is_disease(e.is_disease())
                .createdDate(e.getCreatedDate())
                .modifiedDate(e.getModifiedDate())
                .build();
    }
}
