package com.demogroup.demoweb.controller;

import com.demogroup.demoweb.domain.CustomUserDetails;
import com.demogroup.demoweb.domain.Mango;
import com.demogroup.demoweb.domain.User;
import com.demogroup.demoweb.domain.Weather;
import com.demogroup.demoweb.repository.UserRepository;
import com.demogroup.demoweb.service.AppService;
import com.demogroup.demoweb.service.DiseaseService;
import com.demogroup.demoweb.service.UserService;
import com.demogroup.demoweb.utils.MakeJsonUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AppApiController {

    private final DiseaseService diseaseService;
    private final UserRepository userRepository;

    private final AppService appService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String convertEntityToJson(HomePageResponse obj){
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.addMixIn(User.class, UserApiController.passwordIgnore.class);
        try{
            return objectMapper.writeValueAsString(obj);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }

    }

    //홈 화면에 띄울 정보를 보내는 컨트롤러. 기상청 정보와 사용자의 마이망고리스트를 보내야 한다.

    @GetMapping("/api/home")
    public ResponseEntity<String> homePage(){
        //사용자 찾기
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException());

        try {
            //기상청 날씨 정보
            Weather weather = appService.getWeather();

            //사용자의 마이망고리스트
            List<Mango> mangoList = diseaseService.mangoList(username);

            HomePageResponse obj = new HomePageResponse(mangoList,user,weather);

            String response = convertEntityToJson(obj);

            return ResponseEntity.ok().body(response);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail get weather information");
        }
    }


    //사용자의 마이망고리스트
    @Getter
    private class HomePageResponse{

        private List<Mango> mangoList;
        private User user;
        private Weather weather;

        // 생성자에서 각 필드에 대한 초기화
        HomePageResponse(List<Mango> mangoList, User user, Weather weather) {
            this.mangoList = new ArrayList<>(mangoList);
            this.user = user;
            this.weather = weather;
        }

    }

    abstract class passwordIgnore {
        @JsonIgnore
        private String password;
    }
}
