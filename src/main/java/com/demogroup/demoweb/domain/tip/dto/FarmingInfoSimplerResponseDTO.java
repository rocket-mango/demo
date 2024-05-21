package com.demogroup.demoweb.domain.tip.dto;

import com.demogroup.demoweb.dom.dto.FarmingInfoDto;
import com.demogroup.demoweb.domain.tip.domain.FarmingInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FarmingInfoSimplerResponseDTO {
    private String category;
    private List<FarmingInfoDto> simpleFarmingInfoList;

    public static FarmingInfoSimplerResponseDTO of(String category, List<FarmingInfo> farmingInfoList){

        return FarmingInfoSimplerResponseDTO.builder()
                .category(category)
                .simpleFarmingInfoList(farmingInfoList.stream().map(FarmingInfoDto::of).collect(Collectors.toList()))
                .build();
    }
}
