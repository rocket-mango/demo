package com.demogroup.demoweb.global.exception;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.naming.AuthenticationException;

@Getter
public class JWTAuthenticationException extends AuthenticationException {
    public JWTAuthenticationException(String message){
        super(message);
    }

}
