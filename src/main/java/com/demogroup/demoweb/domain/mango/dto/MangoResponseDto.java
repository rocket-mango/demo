package com.demogroup.demoweb.domain.mango.dto;


import com.demogroup.demoweb.domain.mango.domain.Disease;
import com.demogroup.demoweb.domain.mango.domain.Mango;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class MangoResponseDto {
    private MangoDto mango;
    private DiseaseDto disease;

    public static MangoResponseDto of(Mango mango, Disease disease){
        return MangoResponseDto.builder()
                .mango(MangoDto.of(mango))
                .disease(DiseaseDto.of(disease))
                .build();
    }
}
