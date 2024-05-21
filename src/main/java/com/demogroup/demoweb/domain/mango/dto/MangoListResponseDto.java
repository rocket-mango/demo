package com.demogroup.demoweb.domain.mango.dto;

import com.demogroup.demoweb.domain.mango.domain.Mango;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@Getter
public class MangoListResponseDto {
    private List<MangoDto> mangolist;

    public static MangoListResponseDto of(List<Mango> mangoList){
        return MangoListResponseDto.builder()
                .mangolist(mangoList.stream().map(MangoDto::of).collect(Collectors.toList()))
                .build();
    }
}
