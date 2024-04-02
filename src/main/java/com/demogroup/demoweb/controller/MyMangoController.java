package com.demogroup.demoweb.controller;

import com.demogroup.demoweb.domain.CustomUserDetails;
import com.demogroup.demoweb.domain.Mango;
import com.demogroup.demoweb.domain.User;
import com.demogroup.demoweb.repository.MangoRepository;
import com.demogroup.demoweb.service.DiseaseService;
import com.demogroup.demoweb.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/myCrop")
@RequiredArgsConstructor
public class MyMangoController {
    private final DiseaseService diseaseService;
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static String convertMangoListToJson(List<Mango> obj){
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

    @GetMapping("/all")
    public ResponseEntity getAllCropList(){

        //사용자 찾기
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();

        List<Mango> mangoList = diseaseService.mangoList(username);

        String response = convertMangoListToJson(mangoList);

        return ResponseEntity.ok().body(response);
    }
}
