package cl.ecotouch.msauth.service.impl;

import cl.ecotouch.msauth.dto.LoginRequestDto;
import cl.ecotouch.msauth.dto.LoginResponseDto;
import cl.ecotouch.msauth.dto.UsersDto;
import cl.ecotouch.msauth.mapper.UserMapper;
import cl.ecotouch.msauth.service.UserService;
import cl.ecotouch.msauth.utils.JwtUtils;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
        UsersDto maskedUserInfo = usersDto;
        maskedUserInfo.setPassword("********");
        return LoginResponseDto.builder().token(generateToken(usersDto))
                .userData(maskedUserInfo)
                .build();
    }

    @Override
    public UsersDto getUserById(String token) {
        boolean isTokenValid  = JwtUtils.decodeJwtToken(token.substring(7),jwtSecret);
        String userName = "sdepol";
        UsersDto usersDto = userMapper.getUserByUsername(userName);
        usersDto.setPassword("***********");
        return usersDto;
    }

    private String generateToken(UsersDto loggedUser) {
        try {
            JWTClaimsSet.Builder claimsSetBuilder = new JWTClaimsSet.Builder();
            claimsSetBuilder.subject(loggedUser.getUsername());
            claimsSetBuilder.claim("profileId", loggedUser.getProfile());
            claimsSetBuilder.issueTime(new Date());
            claimsSetBuilder.expirationTime(new Date(System.currentTimeMillis() + 5 * 60 * 1000)); // 5 minutes

            JWTClaimsSet claimsSet = claimsSetBuilder.build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(new MACSigner(jwtSecret));

            return signedJWT.serialize();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }

    @Override
    public void insertUsuario(UsersDto usuario) {
        userMapper.insertUser(usuario);
    }

    @Override
    public void updateUsuario(UsersDto usuario) {
        userMapper.updateUser(usuario);
    }

    @Override
    public void deleteUsuario(String username) {
        userMapper.deleteUser(username);
    }

    @Override
    public Optional<UsersDto> findUserByEmail(List<UsersDto> users, String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }
}
