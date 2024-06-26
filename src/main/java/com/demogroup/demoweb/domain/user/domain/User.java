package com.demogroup.demoweb.domain.user.domain;

import com.demogroup.demoweb.dom.dto.UserDTO;
//import jakarta.persistence.*;
import com.demogroup.demoweb.dom.dto.UserModifyDTO;
import com.demogroup.demoweb.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String nickname;

    //로그인 시 사용할 id입니다
    @Column(unique = true, length = 50)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    private String role= Role.ROLE_USER.name();

    public static User toEntity(UserDTO dto, String encodePw){
        return User.builder()
                .name(dto.getName())
                .nickname(dto.getNickname())
                .username(dto.getUsername())
                .password(encodePw)
                .email(dto.getEmail())
                .role(Role.ROLE_USER.name())
                .build();

    }

    public void update(UserModifyDTO dto){
        this.username=dto.getUsername();
        this.name=dto.getName();
        this.email=dto.getEmail();
        this.nickname=dto.getNickname();
    }
}
