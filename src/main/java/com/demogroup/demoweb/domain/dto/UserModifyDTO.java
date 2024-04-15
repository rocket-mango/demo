package com.demogroup.demoweb.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserModifyDTO {
    private String name;
    private String nickname;
    private String username;
    private String email;
}
