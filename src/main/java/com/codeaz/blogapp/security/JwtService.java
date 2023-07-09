package com.codeaz.blogapp.security;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;
    public String generateToken(Long id) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expiration * 1000);
        return JWT.create()
                .withIssuedAt(now)
                .withExpiresAt(expirationDate)
                .withSubject(id.toString())
                .sign(algorithm);
    }
    public Long getUserIdFromToken(String token) {
       var decodedToken = JWT.decode(token);
       return Long.parseLong(decodedToken.getSubject());
    }
}
