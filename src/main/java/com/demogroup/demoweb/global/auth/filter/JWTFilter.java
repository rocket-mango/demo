package com.demogroup.demoweb.global.auth.filter;

import com.demogroup.demoweb.global.auth.domain.CustomUserDetails;
import com.demogroup.demoweb.domain.user.domain.User;
import com.demogroup.demoweb.global.exception.AppException;
import com.demogroup.demoweb.global.exception.ErrorCode;
import com.demogroup.demoweb.global.exception.JWTAuthenticationException;
import com.demogroup.demoweb.domain.user.repository.UserRepository;
import com.demogroup.demoweb.global.auth.jwt.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

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

            //User 엔티티 생성
            User user1 = userRepository.findByUsername(username)
                    .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, "가입되지 않은 아이디입니다. 로그인을 진행합니다."));

            CustomUserDetails customUserDetails = new CustomUserDetails(user1);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);

        }catch (JWTAuthenticationException e){
            log.error("JWT 토큰이 존재하지 않거나 정상적이지 않습니다.");
        }
        filterChain.doFilter(request,response);

    }

}
