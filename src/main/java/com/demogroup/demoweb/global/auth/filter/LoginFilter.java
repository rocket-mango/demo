package com.demogroup.demoweb.global.auth.filter;

import com.demogroup.demoweb.global.auth.domain.CustomUserDetails;
import com.demogroup.demoweb.global.auth.jwt.JWTUtils;
import com.demogroup.demoweb.global.auth.handler.CustomAuthenticationFailureHandler;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
//@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;
    private final CustomAuthenticationFailureHandler authenticationFailureHandler;

    public LoginFilter(AuthenticationManager authenticationManager,CustomAuthenticationFailureHandler authenticationFailureHandler, JWTUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.authenticationFailureHandler = authenticationFailureHandler;

        //직접 UsernamePasswordAuthenticationFilter를 custom해주는 것이기 때문에
        //HttpSecurity에서 formLogin으로 설정해도 안되고
        //이렇게 setFilterProcessesUrl로 설정해줘야 된다.
        setFilterProcessesUrl("/api/user/login");
    }

    //request로부터 아이디와 비밀번호를 받아서
    //usernamepasswordauthenticationtoken에 넣어
    //authenticationmanager에 전달하여 인증을 진행시키는 메소드이다.
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {

            //요청 본문에서 json 데이터 추출
            BufferedReader reader = request.getReader();
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            String jsonBody = sb.toString();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonBody);


            String username = (String) jsonObject.get("username");
            String password = (String) jsonObject.get("password");

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password, null);

            return authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        }catch (AuthenticationException e) {
            unsuccessfulAuthentication(request, response, e);
            return null;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String role = ((CustomUserDetails) authentication.getPrincipal())
                .getAuthorities()
                .iterator().next()
                .getAuthority();

        //1초*60(분)*60(1시간)=>1시간 기한의 JWT이다.
        String token = jwtUtils.createToken(customUserDetails,  60 * 60 * 1000L);
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Authorization","Bearer "+token);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        try{
            authenticationFailureHandler.onAuthenticationFailure(request,response, failed);
        }catch (Exception e){
            return;
        }

    }
}
