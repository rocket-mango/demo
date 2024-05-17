package com.demogroup.demoweb.domain.user.service;

import com.demogroup.demoweb.domain.user.domain.User;
import com.demogroup.demoweb.dom.dto.UserDTO;
import com.demogroup.demoweb.dom.dto.UserModifyDTO;
import com.demogroup.demoweb.global.exception.AppException;
import com.demogroup.demoweb.global.exception.ErrorCode;
import com.demogroup.demoweb.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    //사용자 회원가입
    @Transactional
    public User join(UserDTO dto) {
        System.out.println("UserService.join");

        boolean isExisted = userRepository.existsByUsername(dto.getUsername());
        User response;
        if(!isExisted){
            String encodedPw = encoder.encode(dto.getPassword());
            User user = User.toEntity(dto, encodedPw);
            response= userRepository.save(user);

        }else{
            throw new AppException(ErrorCode.USERNAME_DUPLICATED, "이미 가입된 아이디가 존재합니다.");
        }
        return response;
    }

    @Transactional(readOnly = true)
    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, "가입되지 않은 아이디입니다. 로그인을 진행합니다."));
    }

    @Transactional
    public User findByUsername(String username){
        //가입된 사용자 확인
        User user = userRepository.findByUsername(username)
                //Optional의 orElseThrow 함수는 람다식으로 작성해줘야 한다.
                .orElseThrow(()->new AppException(ErrorCode.USERNAME_NOT_FOUND,"가입되지 않은 아이디입니다. 로그인을 진행합니다."));

        //가입된 사용자의 dto를 보내기
        return user;
    }

    //회원정보를 수정하는 메서드이다.
    //update(or delete 쿼리도 마찬가지) 쿼리를 사용하기 때문에 @Transactional 을 사용해야 한다.
    @Transactional
    public Long modify(UserModifyDTO dto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, "가입되지 않은 아이디입니다. 로그인을 진행합니다."));
        user.update(dto);
        return user.getUid();
    }

    @Transactional
    public void userResignation(String username) {
        userRepository.deleteByUsername(username);
    }
}
