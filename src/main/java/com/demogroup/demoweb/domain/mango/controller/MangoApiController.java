package com.demogroup.demoweb.domain.mango.controller;

import com.demogroup.demoweb.domain.mango.dto.DiseaseResponseDto;
import com.demogroup.demoweb.domain.mango.dto.MangoListResponseDto;
import com.demogroup.demoweb.domain.mango.dto.MangoResponseDto;
import com.demogroup.demoweb.global.auth.annotation.AuthUser;
import com.demogroup.demoweb.domain.mango.domain.Disease;
import com.demogroup.demoweb.domain.mango.domain.Mango;
import com.demogroup.demoweb.domain.user.domain.User;
import com.demogroup.demoweb.dom.dto.MangoDTO;
import com.demogroup.demoweb.domain.mango.service.DiseaseService;
import com.demogroup.demoweb.domain.user.service.UserService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/disease")
@RequiredArgsConstructor
public class MangoApiController {
    private final DiseaseService diseaseService;
    private final UserService userService;

    //망고 질병 검색을 진행하는 컨트롤러
    //리턴값 : top 3 결과와 망고 결과 정보를 리턴한다.
    @PostMapping(value = "/diagnosis", consumes =  MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DiseaseResponseDto> mangoDiagnosis(//@AuthUser User user,
                                                             @RequestPart(value = "mangoImage",required = false) MultipartFile mangoImage,
                                                             @RequestPart(value = "location",required = false) String location) throws Exception{
        if (location==null){
            location="";
        }
        //사용자 찾기
        //String username = user.getUsername();
        User findUser = userService.findByUsername("yujin00");

        //이미지를 s3에 저장하고, 망고 검사를 진행하는 페이지
        String s3Url = diseaseService.saveToS3(mangoImage);
        List<String> resultList = diseaseService.diagnosis_mango(s3Url);

        //망고 결과 저장하기
        boolean is_disease=true;
        String diseaseName="건강";
        Disease disease=null;


        if(resultList.get(0).equals("Healthy")){
            is_disease=false;
            disease=diseaseService.findDisease(resultList.get(0));
        }
        else {
            diseaseName=resultList.get(0);
            disease=diseaseService.findDisease(diseaseName);
            diseaseName=disease.getName();
        }
        MangoDTO dto =new MangoDTO(findUser,is_disease,diseaseName, s3Url, location);
        Mango mango = diseaseService.saveMango(dto,findUser);


        return ResponseEntity.ok().body(DiseaseResponseDto.of(mango,resultList,disease));
    }

    //망고 리스트 가져오기
    @GetMapping("/my-mango-list")
    public ResponseEntity<MangoListResponseDto> myMangoList(@AuthUser User user){
        //사용자 찾기
        String username = user.getUsername();

        //해당 사용자의 망고 리스트 반환
        List<Mango> mangoList=diseaseService.mangoList(username);


        return ResponseEntity.ok().body(MangoListResponseDto.of(mangoList));
    }

    @GetMapping("/{mid}")
    public ResponseEntity<MangoResponseDto> getMangoResultById(@PathVariable("mid") Long mid){
        Mango mango = diseaseService.findByMid(mid);
        Disease disease = diseaseService.findByDiseaseName(mango.getDisease());

        return ResponseEntity.ok().body(MangoResponseDto.of(mango,disease));
    }

    //프런트엔드에서 location을 보내면 location에 맞는 리스트만 뽑아 보내주는 컨트롤러
    @GetMapping("/lists/byLocation")
    public ResponseEntity<MangoListResponseDto> listByLocation(@AuthUser User user,
            @RequestParam("location") String location){
        String username=user.getUsername();
        List<Mango> mangoList = diseaseService.mangoListByLocation(location,username);

        return ResponseEntity.ok().body(MangoListResponseDto.of(mangoList));
    }

    //망고 리스트에서 해당 망고 객체를 삭제한다.
    @DeleteMapping("/lists/delete/{mid}")
    public ResponseEntity listDelete(@PathVariable("mid") Long mid){

        try{
            diseaseService.deleteMango(mid);
            return ResponseEntity.ok().body("successful delete mango");
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID에 해당하는 검사 결과가 존재하지 않습니다.");
        }
    }


}
