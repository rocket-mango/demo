package com.demogroup.demoweb.domain.mango.dto;

import com.demogroup.demoweb.domain.mango.domain.Disease;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class DiseaseDto {
    private long did;
    private String name;
    private String ename;
    private String reason;
    private String symptom;
    private String handle;

    public static DiseaseDto of(Disease e){
        return DiseaseDto.builder()
                .did(e.getDid())
                .name(e.getName())
                .ename(e.getEname())
                .reason(e.getReason())
                .symptom(e.getSymptom())
                .handle(e.getHandle())
                .build();
    }
}
