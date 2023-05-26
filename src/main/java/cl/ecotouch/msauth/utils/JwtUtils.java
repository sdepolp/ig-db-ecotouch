package cl.ecotouch.msauth.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

import java.util.Base64;


public class JwtUtils {
    public static Claims decodeJwtToken(String token, String secretKey) {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(Base64.getEncoder().encode(secretKey.getBytes()))
                .build();

        Jwt jwt = jwtParser.parse(token);
        return (Claims) jwt.getBody();
    }
}
