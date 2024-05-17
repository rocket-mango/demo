package com.demogroup.demoweb.global.auth.service;

import com.demogroup.demoweb.global.auth.domain.CustomUserDetails;
import com.demogroup.demoweb.domain.user.domain.User;
import com.demogroup.demoweb.global.exception.AppException;
import com.demogroup.demoweb.global.exception.ErrorCode;
import com.demogroup.demoweb.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User findUser = userRepository.findByUsername(username)
                .orElseThrow(()->new AppException(ErrorCode.USERNAME_NOT_FOUND,"가입되지 않은 아이디입니다. 로그인을 진행합니다."));

        if(findUser!=null){
            return new CustomUserDetails(findUser);
        }
        return null;
    }
}
