package com.demogroup.demoweb.controller;

import com.demogroup.demoweb.domain.CustomUserDetails;
import com.demogroup.demoweb.domain.User;
import com.demogroup.demoweb.domain.dto.UserDTO;
import com.demogroup.demoweb.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static String convertEntityToJson(User user){
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.addMixIn(User.class, passwordIgnore.class);
        try{
            return objectMapper.writeValueAsString(user);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
    }

    //사용자 회원가입
    @PostMapping("/joinProc")
    public ResponseEntity join(@RequestBody @Valid UserDTO dto){
        User user = userService.join(dto);
        String response = convertEntityToJson(user);
        return ResponseEntity.ok().body(response);
    }

    //회원정보 불러오기
    @GetMapping("/information")
    public ResponseEntity getUserInformation(){
        System.out.println("UserApiController.getUserInformation");
        //사용자 찾기
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(principal.getUsername());
        String response = convertEntityToJson(user);
        return ResponseEntity.ok().body(response);
    }

    //사용자 정보 수정
    @PatchMapping("/modify")
    public ResponseEntity modify(@RequestBody @Valid UserDTO dto){
        //사용자 찾기
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User obj = userService.modify(dto, principal.getUsername());
        String response = convertEntityToJson(obj);

        return ResponseEntity.ok().body(response);
    }

    //사용자 회원탈퇴
    @DeleteMapping("/resignation")
    public ResponseEntity resignation(){
        //사용자 찾기
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User entity = userService.findByUsername(principal.getUsername());
        UserDTO dto = UserDTO.toDTO(entity, "");

        userService.userResignation(principal.getUsername());
        return ResponseEntity.ok().body("사용자가 정상적으로 탈퇴되었습니다.");
    }

    abstract class passwordIgnore {
        @JsonIgnore
        private String password;
    }

}
