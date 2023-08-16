package com.project.searchone.component;

import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.conscrypt.OpenSSLMessageDigestJDK.SHA256;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.project.searchone.dto.myUser;
import com.project.searchone.service.UserService;

import ch.qos.logback.core.encoder.EncoderBase;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class TokenProvider implements InitializingBean{

    private String secret = "a2FyaW10b2thcmltdG9rYXJpbXRva2FyaW10b2thcmltdG9rYXJpbXRva2FyaW10b2thcmltdG9rYXJpbXRva2FyaW10b2thcmltdG9rYXJpbXRva2FyaW10b2thcmltdG9rYXJpbXRva2FyaW10b2thcmltdG9rYXJpbXRva2FyaW10b2thcmltdG9rYXJpbQ==";
    private final UserDetailsService userDetailsService;
    private Key key;
    private final long tokenValidityInMilliseconds = 60 * 60 * 1000L;
    private static final String AUTHORITIES_KEY = "auth";

    @Autowired
    private UserService userService;

    // public TokenProvider(
    //         @Value("${jwt.secret}") String secret,
    //         @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {

    //     this.secret = secret;
    //     this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
    // }

    
    public TokenProvider(
             UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        //init();
    }

    // // 객체 초기화, secretKey를 Base64로 인코딩한다.
    // @PostConstruct
    // protected void init() {
    //     secret = Base64.getEncoder().encodeToString(secret.getBytes());

    // }

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        System.out.println(keyBytes.toString());
        //byte[] keyBytes =  Base64.getEncoder().encodeToString(secret.getBytes()).getBytes();
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // 토큰의 expire 시간을 설정
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities) // 정보 저장
                .signWith(key, SignatureAlgorithm.HS512) // 사용할 암호화 알고리즘과 , signature 에 들어갈 secret값 세팅
                .setExpiration(validity) // set Expire Time 해당 옵션 안넣으면 expire안함
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        log.info(claims.getSubject());

        String name = claims.getSubject();

        myUser principal = null;
        try {
            principal = userService.getUserByUserName(name);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } //new myUser(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    Map<String, String> parseSubject(String subject){

        Map<String, String> m = new HashMap<>();

        int sepIdx = subject.indexOf(",");
        int passwordIdx = subject.indexOf("password");

        m.put("email", subject.substring("email".length() +2, sepIdx));
        m.put("password", subject.substring(passwordIdx + "password".length()+1, subject.length()-1));

        return m;

    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            
            log.info("잘못된 JWT 서명입니다.");

        } catch (ExpiredJwtException e) {
            
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    
}
