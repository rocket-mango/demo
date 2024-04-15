package com.demogroup.demoweb.security;

import com.demogroup.demoweb.domain.CustomOAuth2User;
import com.demogroup.demoweb.domain.CustomUserDetails;
import com.demogroup.demoweb.utils.JWTUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomOAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JWTUtils jwtUtils;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("successful oauth login");

        CustomOAuth2User customOAuth2User= (CustomOAuth2User) authentication.getPrincipal();
        String token = jwtUtils.createToken(customOAuth2User, 120 * 60 * 1000L);
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Authorization","Bearer "+token);

        //test 출력
        System.out.println(token);

        System.out.println("이 글자가 보이면 oauth2 response jwt 성공");


    }
}
