package com.demogroup.demoweb.domain.dto;

import com.demogroup.demoweb.domain.FarmingInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.swing.plaf.PanelUI;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FarmingInfoSimplerResponseDTO {
    private String category;
    private List<FarmingInfoDTO> simpleFarmingInfoList;

    public static FarmingInfoSimplerResponseDTO of(String category, List<FarmingInfo> farmingInfoList){
        return FarmingInfoSimplerResponseDTO.builder()
                .category(category)
                .simpleFarmingInfoList(farmingInfoList.stream().map(FarmingInfoDTO::of).collect(Collectors.toList()))
                .build();
    }
}
