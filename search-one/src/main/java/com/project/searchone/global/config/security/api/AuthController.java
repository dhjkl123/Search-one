package com.project.searchone.global.config.security.api;

import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.searchone.global.config.security.application.TokenProvider;
import com.project.searchone.global.config.security.application.UserServiceImpl;
import com.project.searchone.global.config.security.dto.LoginReqDto;
import com.project.searchone.global.config.security.dto.LoginResDto;
import com.project.searchone.global.config.security.dto.myUser;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserServiceImpl userServiceImpl;

    @PostMapping("")
    public ResponseEntity<?> authorize(@RequestBody LoginReqDto login) throws ExecutionException, InterruptedException {

        myUser loginDto = userServiceImpl.getUserByUserName(login.getId());

        if(!loginDto.getPassword().equals(login.getPw()))
            return new ResponseEntity<>(null, HttpStatus.FOUND);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        // authenticate 메소드가 실행이 될 때 CustomUserDetailsService class의 loadUserByUsername 메소드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 해당 객체를 SecurityContextHolder에 저장하고
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // authentication 객체를 createToken 메소드를 통해서 JWT Token을 생성
        String jwt = tokenProvider.createToken(authentication);

        LoginResDto result = LoginResDto.builder()
                            .id(login.getId())
                            .token(jwt)
                            .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
