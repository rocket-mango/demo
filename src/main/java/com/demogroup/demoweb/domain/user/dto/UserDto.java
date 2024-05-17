package com.demogroup.demoweb.domain.user.dto;

import com.demogroup.demoweb.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
@Builder
@Getter
public class UserDto {
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private long uid;
    private String name;
    private String nickname;
    private String username;
    private String email;
    private String role;

    public static UserDto of(User e){
        return UserDto.builder()
                .uid(e.getUid())
                .name(e.getName())
                .nickname(e.getNickname())
                .username(e.getUsername())
                .email(e.getEmail())
                .role(e.getRole())
                .createdDate(e.getCreatedDate())
                .modifiedDate(e.getModifiedDate())
                .build();
    }
}
