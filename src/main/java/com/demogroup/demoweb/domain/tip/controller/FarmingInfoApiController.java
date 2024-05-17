package com.demogroup.demoweb.domain.tip.controller;

import com.demogroup.demoweb.domain.tip.domain.FarmingInfo;
import com.demogroup.demoweb.domain.tip.domain.FarmingInfoCategory;
import com.demogroup.demoweb.dom.dto.FarmingInfoSimplerResponseDTO;
import com.demogroup.demoweb.global.exception.AppException;
import com.demogroup.demoweb.global.exception.ErrorCode;
import com.demogroup.demoweb.domain.tip.repository.FarmingInfoCategoryRepository;
import com.demogroup.demoweb.domain.tip.repository.FarmingInfoRepository;
import com.demogroup.demoweb.domain.tip.service.FarmingInfoService;
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


    @GetMapping("/list/all")
    public ResponseEntity findAllTips(){
        List<FarmingInfo> all = farmingInfoRepository.findAll();
        String response;
        try {
            response = objectMapper.writeValueAsString(all);
            response="{ \"tiplist\" : "+response+"}";
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return ResponseEntity.status(404).body(e);
        }

        return ResponseEntity.ok().body(response);

    }

    //카테고리별 리스트
//    @GetMapping("/list/{category}")
//    public ResponseEntity findByCategory(@PathVariable("category") Long fcid){
//        List<FarmingInfo> byCategoryList = farmingInfoRepository.findByCategory_Fcid(fcid);
//        String response;
//        //json 변환
//        try {
//            response = objectMapper.writeValueAsString(byCategoryList);
//            response="{ \"tiplist\" : "+response+"}";
//        }catch (JsonProcessingException e){
//            e.printStackTrace();
//            return ResponseEntity.status(404).body(e);
//        }
//
//        return ResponseEntity.ok().body(response);
//    }

    @GetMapping("/list/{categoryId}")
    public ResponseEntity farmingInfoListByCategory(@PathVariable("categoryId")Long categoryId){
        List<FarmingInfo> list = farmingInfoRepository.findByCategory_Fcid(categoryId);
        FarmingInfoCategory farmingInfoCategory = farmingInfoCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND, ""));

        FarmingInfoSimplerResponseDTO response = FarmingInfoSimplerResponseDTO.of(farmingInfoCategory.getCategoryName(), list);
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