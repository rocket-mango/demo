package com.demogroup.demoweb.domain.mango.dto;

import com.demogroup.demoweb.domain.mango.domain.Disease;
import com.demogroup.demoweb.domain.mango.domain.Mango;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class DiseaseResponseDto {
    private MangoDto mango;
    private List<String> top3List;
    private DiseaseDto disease;

    public static DiseaseResponseDto of(Mango mango, List<String> top3List, Disease disease){
        return DiseaseResponseDto.builder()
                .mango(MangoDto.of(mango))
                .top3List(top3List)
                .disease(DiseaseDto.of(disease))
                .build();
    }
}
