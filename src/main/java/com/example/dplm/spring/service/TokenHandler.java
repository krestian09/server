package com.example.dplm.spring.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Optional;

@Component
public class TokenHandler {
    private final SecretKey secretKey;

    public TokenHandler() {
        String jwtKey = "jwtkey12345512";
        byte[] decodeKey = Base64.getDecoder().decode(jwtKey);
        this.secretKey = new SecretKeySpec(decodeKey, 0, decodeKey.length, "AES");
    }

    public Optional<Long> extract(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            return Optional
                    .ofNullable(body.getId())
                    .map(Long::valueOf);
        }catch (RuntimeException e){
            return Optional.empty();
        }
    }

    public String generateAccessToken(@NonNull long id, @NonNull LocalDateTime expires) {
        return Jwts.builder()
                .setId(String.valueOf(id))
                .setExpiration(Date.from(expires.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}
