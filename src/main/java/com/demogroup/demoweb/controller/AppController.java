package com.demogroup.demoweb.controller;

import com.demogroup.demoweb.domain.mango.domain.Disease;
import com.demogroup.demoweb.domain.user.domain.User;
import com.demogroup.demoweb.dom.dto.MangoDTO;
import com.demogroup.demoweb.domain.home.service.HomeService;
import com.demogroup.demoweb.domain.mango.service.DiseaseService;
import com.demogroup.demoweb.domain.user.service.UserService;
import com.demogroup.demoweb.MakeJsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AppController {
    private final HomeService appService;

    private final UserService userService;
    private final DiseaseService diseaseService;

    @GetMapping("/test")
    @ResponseBody
    public ResponseEntity test(){
        List<String> lst=new ArrayList<>();
        lst.add("질병1");
        lst.add("질병2");
        lst.add("질병3");

        User user = userService.findByUsername("임시2");
        Disease dieBack = diseaseService.findDisease("Cutting Weevil");

        MangoDTO dto=new MangoDTO(1000l, user, true, "질병1","alsdkjflkjl","지역구1");

        String resultJson = MakeJsonUtil.makeDiagnosisResultJson(lst, dto, dieBack);
        return ResponseEntity.ok().body(resultJson);
    }

    @GetMapping("/test2")
    public ResponseEntity test2(){
        System.out.println("AppController.test2");
        return ResponseEntity.ok().body("이 글자가 보이면 test2 성공");
    }

}
