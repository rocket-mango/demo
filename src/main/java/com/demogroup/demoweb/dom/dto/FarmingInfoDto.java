package com.demogroup.demoweb.dom.dto;

import com.demogroup.demoweb.domain.tip.domain.FarmingInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FarmingInfoDto {
    private Long id;
    private String topic;
    private String summary;
    private String refImageUrl;

    public static FarmingInfoDto of(FarmingInfo farmingInfo){
        return FarmingInfoDto.builder()
                .id(farmingInfo.getFid())
                .topic(farmingInfo.getTopic())
                .summary(farmingInfo.getSummary())
                .refImageUrl(farmingInfo.getRefImageUrl())
                .build();
    }
}
