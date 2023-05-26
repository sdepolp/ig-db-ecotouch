package cl.ecotouch.msauth.service.impl;

import cl.ecotouch.msauth.dto.LoginRequestDto;
import cl.ecotouch.msauth.dto.LoginResponseDto;
import cl.ecotouch.msauth.dto.UsersDto;
import cl.ecotouch.msauth.mapper.UserMapper;
import cl.ecotouch.msauth.service.UserService;
import cl.ecotouch.msauth.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public List<UsersDto> getUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public LoginResponseDto validateUser(LoginRequestDto loginRequestDto) {
        UsersDto usersDto = userMapper.getUserById(loginRequestDto);
        return LoginResponseDto.builder().token(generateToken(usersDto)).build();
    }

    @Override
    public UsersDto getUserById(String token) {
        Claims claims = JwtUtils.decodeJwtToken(token.substring(7),jwtSecret);
        String userName = claims.getSubject();
        UsersDto usersDto = userMapper.getUserByUsername(userName);
        usersDto.setPassword("***********");
        return usersDto;
    }

    private String generateToken(UsersDto loggedUser){
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(jwtSecret),
                SignatureAlgorithm.HS256.getJcaName());
        Instant now = Instant.now();
        String tokenResponse = Jwts.builder()
                .setSubject(loggedUser.getUsername())
                .setId(UUID.randomUUID().toString())
                .claim("profileId",loggedUser.getProfile())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(5l, ChronoUnit.MINUTES)))
                .compact();
        return tokenResponse;
    }

}
