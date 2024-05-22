package com.demogroup.demoweb.domain.home.dto;

import com.demogroup.demoweb.domain.tip.domain.FarmingInfo;
import com.demogroup.demoweb.domain.mango.domain.Mango;
import com.demogroup.demoweb.domain.user.domain.User;
import com.demogroup.demoweb.dom.dto.FarmingInfoDto;
import com.demogroup.demoweb.domain.mango.dto.MangoDto;
import com.demogroup.demoweb.domain.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@Getter
public class HomePageResponseDto {
    private List<MangoDto> mangoList;
    private UserDto user;
    private WeatherDto weather;
    private List<FarmingInfoDto> simpleFarmingInfoList;


    public static HomePageResponseDto of(List<Mango> mangolist, User user, WeatherDto weather, List<FarmingInfo> infoList){
        return HomePageResponseDto.builder()
                .mangoList(mangolist.stream().map(MangoDto::of).collect(Collectors.toList()))
                .user(UserDto.of(user))
                .weather(weather)
                .simpleFarmingInfoList(infoList.stream().map(FarmingInfoDto::of).collect(Collectors.toList()))
                .build();
    }
}
