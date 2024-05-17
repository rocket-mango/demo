package com.demogroup.demoweb.domain.mango.controller;

import com.demogroup.demoweb.domain.user.controller.UserApiController;
import com.demogroup.demoweb.global.auth.domain.CustomUserDetails;
import com.demogroup.demoweb.domain.mango.domain.Disease;
import com.demogroup.demoweb.domain.mango.domain.Mango;
import com.demogroup.demoweb.domain.user.domain.User;
import com.demogroup.demoweb.dom.dto.MangoDTO;
import com.demogroup.demoweb.domain.mango.service.DiseaseService;
import com.demogroup.demoweb.domain.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/disease")
@RequiredArgsConstructor
public class DiseaseApiController {
    private final DiseaseService diseaseService;
    private final UserService userService;

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

    public static String convertEntityToJson(DiagnosisResponse obj){
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

    //망고 질병 검색을 진행하는 컨트롤러
    //리턴값 : top 3 결과와 망고 결과 정보를 리턴한다.
    @PostMapping(value = "/diagnosis", consumes =  MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity mangoDiagnosis(@RequestPart(value = "mangoImage") MultipartFile mangoImage,
                                         @RequestPart(value = "location") String location) throws Exception{

        System.out.println("여기에 왔다..!");

        //사용자 찾기
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        User user = userService.findByUsername(username);

        //이미지를 s3에 저장하고, 망고 검사를 진행하는 페이지
        String s3Url = diseaseService.saveToS3(mangoImage);
        List<String> resultList = diseaseService.diagnosis_mango(s3Url);

        //망고 결과 저장하기
        boolean is_disease=true;
        String diseaseName="";
        Disease disease=null;

        if(resultList.get(0).equals("Healthy")){
            is_disease=false;
        }
        else {
            diseaseName=resultList.get(0);
            disease=diseaseService.findDisease(diseaseName);
        }
        MangoDTO dto =new MangoDTO(user,is_disease,diseaseName, s3Url, location);
        Mango mango = diseaseService.saveMango(dto);

        DiagnosisResponse obj = new DiagnosisResponse(mango, resultList, disease);
        String response = convertEntityToJson(obj);


        return ResponseEntity.ok().body(response);
    }

    //망고 리스트 가져오기
    @GetMapping("/my-mango-list")
    public ResponseEntity myMangoList(){
        //사용자 찾기
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();

        //해당 사용자의 망고 리스트 반환
        List<Mango> mangoList=diseaseService.mangoList(username);

        List<Mango> sortedMangoList = mangoList.stream()
                .sorted((m1, m2) -> m2.getCreatedDate().compareTo(m1.getCreatedDate()))
                .collect(Collectors.toList());

        String response = convertMangoListToJson(sortedMangoList);
        response="{ \"mangolist\" : "+response+"}";

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{mid}")
    public ResponseEntity getMangoResultById(@PathVariable("mid") Long mid){
        Mango mango = diseaseService.findByMid(mid);
        Disease disease = diseaseService.findByDiseaseName(mango.getDisease());
        DiagnosisResponse obj=new DiagnosisResponse(mango,null,disease);
        String response = convertEntityToJson(obj);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/test/{mid}")
    public ResponseEntity getMangoById(@PathVariable("mid") String mid){
        Long id = Long.parseLong(mid);
        Mango mango = diseaseService.findByMid(id);
        Disease disease = diseaseService.findByDiseaseName(mango.getDisease());
        DiagnosisResponse obj=new DiagnosisResponse(mango,null,disease);
        String response = convertEntityToJson(obj);

        return ResponseEntity.ok().body(response);
    }

    //프런트엔드에서 location을 보내면 location에 맞는 리스트만 뽑아 보내주는 컨트롤러
    @GetMapping("/lists/byLocation")
    public ResponseEntity listByLocation(String location){
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=principal.getUsername();
        List<Mango> mangoList = diseaseService.mangoListByLocation(location,username);

        List<Mango> sortedMangoList = mangoList.stream()
                .sorted((m1, m2) -> m2.getCreatedDate().compareTo(m1.getCreatedDate()))
                .collect(Collectors.toList());

        String response = convertMangoListToJson(sortedMangoList);

        response="{ \"mangolist\" : "+response+"}";

        return ResponseEntity.ok().body(response);
    }

    //망고 리스트에서 해당 망고 객체를 삭제한다.
    @GetMapping("/lists/delete/{mid}")
    public ResponseEntity listDelete(@PathVariable("mid") Long mid){
        //사용자 찾기
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        try{
            diseaseService.deleteMango(mid);
            return ResponseEntity.ok().body("검사 결과가 정상적으로 삭제되었습니다.");
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID에 해당하는 검사 결과가 존재하지 않습니다.");
        }
    }

    @Getter
    @AllArgsConstructor
    public static class SaveCropRequest{
        User user;
        String s3Url;
        List<String> resultList;
        String location;
    }

    @Getter
    private class DiagnosisResponse{
        Mango mango;
        List<String> top3List;
        Disease disease;

        public DiagnosisResponse(Mango mango, List<String> top3List,Disease disease) {
            this.mango = mango;
            this.top3List = top3List;
            this.disease=disease;
        }
    }


}
