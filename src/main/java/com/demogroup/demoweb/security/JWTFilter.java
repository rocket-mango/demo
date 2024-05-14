package com.demogroup.demoweb.security;

import com.demogroup.demoweb.domain.CustomUserDetails;
import com.demogroup.demoweb.domain.User;
import com.demogroup.demoweb.domain.dto.UserDTO;
import com.demogroup.demoweb.exception.AppException;
import com.demogroup.demoweb.exception.ErrorCode;
import com.demogroup.demoweb.exception.JWTAuthenticationException;
import com.demogroup.demoweb.repository.UserRepository;
import com.demogroup.demoweb.utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;
    private final JWTUtils jwtUtils;
    private final RedisTemplate<String ,String> redisTemplate;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    //로그아웃된 JWT 토큰을 구별하는 곳입니다.
    //"Bearer "그대로 저장

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //헤더에서 토큰 가져오기
        String token0 = request.getHeader("Authorization");
        String token="";

        try{
            //토큰 유무 확인
            if(token0==null || !token0.startsWith("Bearer ")){
                System.out.println("토큰이 없습니다. 로그인을 진행합니다");
                throw new JWTAuthenticationException("토큰이 없습니다. 로그인을 진행합니다");
            }

            //순수 토큰 획득
            token = token0.split(" ")[1];

            //시간 만료 여부 검증
            if(jwtUtils.isExpired(token)){
                System.out.println("토큰 기간이 만료되었습니다. 로그인을 진행합니다");
                throw new JWTAuthenticationException("토큰 기간이 만료되었습니다. 로그인을 진행합니다");
            }

            //redis blacklist에 있는지 확인
            String username_ = jwtUtils.getUsername(token);
            Boolean isListed = redisTemplate.hasKey(token);
            if(isListed){
                System.out.println("로그아웃되었습니다. 다시 로그인을 진행합니다");
                String jwt = redisTemplate.opsForValue().get(token);
                throw new JWTAuthenticationException("로그아웃되었습니다. 다시 로그인을 진행합니다");
            }

            String name = jwtUtils.getName(token);
            String username = jwtUtils.getUsername(token);
            String nickname = jwtUtils.getNickname(token);
            String email = jwtUtils.getEmail(token);
            String role = jwtUtils.getRole(token);

            //임시 비밀번호, repository에서 가져와야한다.
            User byUsername = userRepository.findByUsername(username)
                    .orElseThrow(()->new AppException(ErrorCode.USERNAME_NOT_FOUND,"가입되지 않은 아이디입니다. 로그인을 진행합니다."));
            String password = byUsername.getPassword();

            //User 엔티티 생성
            UserDTO dto=new UserDTO(name,nickname,username,password,email);
            User user=User.toEntity(dto,password);

            CustomUserDetails customUserDetails = new CustomUserDetails(user);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);

        }catch (JWTAuthenticationException e){
            log.error("JWT 토큰이 존재하지 않거나 정상적이지 않습니다.");
        }

        System.out.println("그대로 진행됨");
        //******스프링 시큐리티 context에 등록하기******


        filterChain.doFilter(request,response);

    }

}
