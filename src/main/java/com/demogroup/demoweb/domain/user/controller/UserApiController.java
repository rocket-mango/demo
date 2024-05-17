package com.demogroup.demoweb.domain.user.controller;

import com.demogroup.demoweb.domain.user.dto.UserDto;
import com.demogroup.demoweb.global.auth.annotation.AuthUser;
import com.demogroup.demoweb.global.auth.domain.CustomUserDetails;
import com.demogroup.demoweb.domain.user.domain.User;
import com.demogroup.demoweb.dom.dto.UserDTO;
import com.demogroup.demoweb.dom.dto.UserModifyDTO;
import com.demogroup.demoweb.dom.dto.UserResponseDTO;
import com.demogroup.demoweb.domain.user.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Operation;
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
    public ResponseEntity<UserDto> join(@RequestBody @Valid UserDTO dto){
        User user = userService.join(dto);
        UserDto responseDto = UserDto.of(user);
        return ResponseEntity.ok().body(responseDto);
    }

    //회원정보 불러오기
    @GetMapping("/information")
    @Operation(summary = "사용자 정보", description = "사용자의 정보를 제공합니다.")
    public ResponseEntity<UserDto> getUserInformation(@AuthUser User user){
        //사용자 찾기
        User user1 = userService.findById(user.getUid());
        UserDto responseDto = UserDto.of(user1);

        return ResponseEntity.ok().body(responseDto);
    }

    //사용자 정보 수정
    @PatchMapping("/modify")
    @Operation(summary = "사용자 정보 수정", description = "회원 정보를 수정한 내용을 제공합니다.")
    public ResponseEntity<UserDto> modify(@AuthUser User user,
            @RequestBody UserModifyDTO dto){
        //사용자 찾기
        Long id = userService.modify(dto, user.getUsername());
        User findUser = userService.findById(id);
        UserDto responseDto = UserDto.of(findUser);

        return ResponseEntity.ok().body(responseDto);
    }

    //사용자 회원탈퇴
    @DeleteMapping("/resignation")
    @Operation(summary = "사용자 탈퇴", description = "회원 탈퇴를 합니다.")
    public String resignation(@AuthUser User user){
        //사용자 찾기
        userService.userResignation(user.getUsername());
        return "사용자가 정상적으로 탈퇴되었습니다.";
    }

    public abstract class passwordIgnore {
        @JsonIgnore
        private String password;
    }

}
