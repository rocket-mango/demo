package com.demogroup.demoweb.dom.dto;

import com.demogroup.demoweb.global.utils.annotation.PasswordValid;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserDTO {
    private String name;

    private String nickname;

    private String username;

    @PasswordValid(message = "비밀번호는 영문자(대소문자 가능),숫자,특수문자를 포함한 8~16자 이여야 합니다.")
    private String password;

    private String email;

}
