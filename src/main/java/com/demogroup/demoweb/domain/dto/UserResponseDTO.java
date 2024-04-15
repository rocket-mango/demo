package com.demogroup.demoweb.domain.dto;

import com.demogroup.demoweb.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long uid;
    private String name;
    private String username;
    private String nickname;
    private String email;
    private Timestamp createdDate;
    private Timestamp modifiedDate;

    public static UserResponseDTO from(User user){
        return new UserResponseDTO(
                user.getUid(),
                user.getName(),
                user.getUsername(),
                user.getNickname(),
                user.getEmail(),
                user.getCreatedDate(),
                user.getModifiedDate()
        );
    }
}
