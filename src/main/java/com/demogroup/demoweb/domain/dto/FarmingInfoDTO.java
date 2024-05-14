package com.demogroup.demoweb.domain.dto;

import com.demogroup.demoweb.domain.FarmingInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FarmingInfoDTO {
    private String topic;
    private String summary;
    private String refImageUrl;

    public static FarmingInfoDTO of(FarmingInfo farmingInfo){
        return FarmingInfoDTO.builder()
                .topic(farmingInfo.getTopic())
                .summary(farmingInfo.getSummary())
                .refImageUrl(farmingInfo.getRefImageUrl())
                .build();
    }
}
