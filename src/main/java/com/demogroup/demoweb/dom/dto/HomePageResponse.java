package com.demogroup.demoweb.dom.dto;

import com.demogroup.demoweb.domain.tip.domain.FarmingInfo;
import com.demogroup.demoweb.domain.mango.domain.Mango;
import com.demogroup.demoweb.domain.user.domain.User;
import com.demogroup.demoweb.dom.Weather;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomePageResponse{

    private List<Mango> mangoList;
    private User user;
    private Weather weather;
    private List<FarmingInfoDto> simpleFarmingInfoList;

    // 생성자에서 각 필드에 대한 초기화
    public static HomePageResponse of(List<Mango> mangoList, User user, Weather weather, List<FarmingInfo> list) {
        return HomePageResponse.builder()
                .mangoList(mangoList)
                .user(user)
                .weather(weather)
                .simpleFarmingInfoList(list.stream().map(FarmingInfoDto::of).collect(Collectors.toList()))
                .build();
    }


}