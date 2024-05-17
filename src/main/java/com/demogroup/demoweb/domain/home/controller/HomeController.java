package com.demogroup.demoweb.domain.home.controller;


import com.demogroup.demoweb.domain.home.dto.HomePageResponseDto;
import com.demogroup.demoweb.domain.home.dto.WeatherDto;
import com.demogroup.demoweb.domain.mango.domain.Mango;
import com.demogroup.demoweb.domain.tip.domain.FarmingInfo;
import com.demogroup.demoweb.domain.user.domain.User;
import com.demogroup.demoweb.global.auth.annotation.AuthUser;
import com.demogroup.demoweb.domain.home.service.HomeService;
import com.demogroup.demoweb.domain.mango.service.DiseaseService;
import com.demogroup.demoweb.domain.tip.service.FarmingInfoService;
import com.demogroup.demoweb.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final DiseaseService diseaseService;
    private final HomeService appService;
    private final FarmingInfoService farmingInfoService;
    private final UserService userService;


    @GetMapping("/api/home")
    public ResponseEntity homePage(@AuthUser User user){
        //사용자 찾기
        User findUser = userService.findById(user.getUid());

        try {
            //기상청 날씨 정보
            WeatherDto weatherDto = appService.getWeather();

            //사용자의 마이망고리스트
            List<Mango> mangoList = diseaseService.mangoList(user.getUsername());
            List<FarmingInfo> infoList = farmingInfoService.findForHomeInfos();

            //responseDto 생성
            HomePageResponseDto responseDto = HomePageResponseDto.of(mangoList, findUser, weatherDto, infoList);

            return ResponseEntity.ok().body(responseDto);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail get weather information");
        }
    }
}
