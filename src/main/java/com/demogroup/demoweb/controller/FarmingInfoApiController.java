package com.demogroup.demoweb.controller;

import com.demogroup.demoweb.domain.FarmingInfo;
import com.demogroup.demoweb.domain.FarmingInfoCategory;
import com.demogroup.demoweb.domain.User;
import com.demogroup.demoweb.repository.FarmingInfoCategoryRepository;
import com.demogroup.demoweb.repository.FarmingInfoRepository;
import com.demogroup.demoweb.repository.UserRepository;
import com.demogroup.demoweb.service.FarmingInfoService;
import com.demogroup.demoweb.utils.MakeJsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/farmingInfo")
public class FarmingInfoApiController {

    private final FarmingInfoService farmingInfoService;
    private final FarmingInfoCategoryRepository farmingInfoCategoryRepository;
    private final FarmingInfoRepository farmingInfoRepository;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    //모든 카테고리
    @GetMapping("/list/category")
    public ResponseEntity categoryList(){
        List<FarmingInfoCategory> list = farmingInfoCategoryRepository.findAll();
        String response;
        //json 변환
        try {
            response = objectMapper.writeValueAsString(list);
            response="{ \"categories\" : "+response+"}";
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return ResponseEntity.status(404).body(e);
        }

        return ResponseEntity.ok().body(response);
    }

    //카테고리별 리스트
    @GetMapping("/list/{category}")
    public ResponseEntity findByCategory(@PathVariable("category") Long fcid){
        List<FarmingInfo> byCategoryList = farmingInfoRepository.findByCategory_Fcid(fcid);
        String response;
        //json 변환
        try {
            response = objectMapper.writeValueAsString(byCategoryList);
            response="{ \"tiplist\" : "+response+"}";
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return ResponseEntity.status(404).body(e);
        }

        return ResponseEntity.ok().body(response);
    }

    //해당 FarmingInfo
    @GetMapping("/{fid}")
    public ResponseEntity farmingInfoByFid(@PathVariable("fid")Long fid){
        FarmingInfo info = farmingInfoRepository.findByFid(fid).orElseThrow(()->new RuntimeException());

        String response;
        //json 변환
        try {
            response = objectMapper.writeValueAsString(info);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return ResponseEntity.status(404).body(e);
        }

        return ResponseEntity.ok().body(response);

    }


}
