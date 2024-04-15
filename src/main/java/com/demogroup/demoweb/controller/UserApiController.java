package com.demogroup.demoweb.controller;

import com.demogroup.demoweb.domain.CustomUserDetails;
import com.demogroup.demoweb.domain.User;
import com.demogroup.demoweb.domain.dto.UserDTO;
import com.demogroup.demoweb.domain.dto.UserModifyDTO;
import com.demogroup.demoweb.domain.dto.UserResponseDTO;
import com.demogroup.demoweb.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "사용자 API", description = "Swagger 사용자 api")
public class UserApiController {
    private final UserService userService;

    //사용자 회원가입
    @PostMapping("/joinProc")
    @Operation(summary = "회원가입", description = "회원 가입한 정보를 제공합니다.")
    public UserResponseDTO join(@RequestBody @Valid UserDTO dto){
        User user = userService.join(dto);
        UserResponseDTO response = UserResponseDTO.from(user);
        return response;
    }

    //회원정보 불러오기
    @GetMapping("/information")
    @Operation(summary = "사용자 정보", description = "사용자의 정보를 제공합니다.")
    public UserResponseDTO getUserInformation(){
        System.out.println("UserApiController.getUserInformation");
        //사용자 찾기
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(principal.getUsername());
        UserResponseDTO response = UserResponseDTO.from(user);

        return response;
    }

    //사용자 정보 수정
    @PatchMapping("/modify")
    @Operation(summary = "사용자 정보 수정", description = "회원 정보를 수정한 내용을 제공합니다.")
    public UserResponseDTO modify(@RequestBody UserModifyDTO dto){
        //사용자 찾기
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = userService.modify(dto, principal.getUsername());
        User user = userService.findById(id);
        UserResponseDTO response = UserResponseDTO.from(user);

        return response;
    }

    //사용자 회원탈퇴
    @DeleteMapping("/resignation")
    @Operation(summary = "사용자 탈퇴", description = "회원 탈퇴를 합니다.")
    public String resignation(){
        //사용자 찾기
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        userService.userResignation(principal.getUsername());
        return "사용자가 정상적으로 탈퇴되었습니다.";
    }

    abstract class passwordIgnore {
        @JsonIgnore
        private String password;
    }

}
