package com.example.springboard.global.auth;
import com.example.springboard.global.error.ErrorCode;
import com.example.springboard.global.error.exception.ApiException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

@Component
public class TokenProvider {
    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    public static final long ACCESSTOKEN_TIME = 1000 * 60 * 30; // 30분
    public static final long REFRESHTOKEN_TIME = 1000 * 60 * 60 * 24 * 1; // 1일
    public static final String ACCESS_PREFIX_STRING = "Bearer";
    public static final String ACCESS_HEADER_STRING = "Authorization";
    public static final String REFRESH_HEADER_STRING = "RefreshToken";

    private final String secret;
    private static SecretKey secretKey;

    @Autowired
    public TokenProvider(@Value("${token.secret_key}") String secret) {
        this.secret = secret;
    }

    @PostConstruct
    protected void init(){
        String keyBase64Encoded = Base64.getEncoder().encodeToString(secret.getBytes());
        secretKey = Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());
    }


    public Token createToken(int uid, String nickname, int accessLevel){
        String accessToken = createAccessToken(uid, nickname, accessLevel);
        String refreshToken = createRefreshToken(uid);
        return new Token(accessToken, refreshToken);
    }


    public String createAccessToken(int uid, String nickname, int accessLevel){
        Map<String, Object> claims = new HashMap<>();

        claims.put("uid", uid);
        claims.put("nickname", nickname);
        claims.put("accessLevel", accessLevel);

        Date expiration = new Date(System.currentTimeMillis() + ACCESSTOKEN_TIME);

        return Jwts.builder()
                .subject(Long.toString(uid))
                .claims(claims)
                .expiration(expiration)
                .signWith(getSecretKey())
                .compact();
    }

    /**
     * 리프레시 토큰 생성
     * 외부에서 호출 불가 -> 리프레시 토큰이 만료될 시에는 다시 로그인
     * @return 리프레시 토큰
     */
    private String createRefreshToken(int uid){
        Date expiration = new Date(System.currentTimeMillis() + REFRESHTOKEN_TIME);
        return Jwts.builder()
                .subject(Long.toString(uid))
                .claim("uid", uid)
                .expiration(expiration)
                .signWith(getSecretKey())
                .compact();
    }

    public Jws<Claims> getClaims(String token){
        Jws<Claims> claims;
        try {
            claims = Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token);
        } catch (ExpiredJwtException ex){
            return null;
        }
        return claims;
    }

    public boolean checkAccessToken(String token){
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException ex){
            throw new ApiException(ErrorCode.TOKEN_EXPIRED_ERROR);
        }
    }


    public static SecretKey getSecretKey() {
        return secretKey;
    }
}
