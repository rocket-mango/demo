package com.demogroup.demoweb.domain.dto;

import com.demogroup.demoweb.domain.User;
import com.demogroup.demoweb.utils.annotation.PasswordValid;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
