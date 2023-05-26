package cl.ecotouch.msauth.service;

import cl.ecotouch.msauth.dto.LoginRequestDto;
import cl.ecotouch.msauth.dto.LoginResponseDto;
import cl.ecotouch.msauth.dto.UsersDto;

import java.util.List;

public interface UserService {
    List<UsersDto> getUsers();

    LoginResponseDto validateUser(LoginRequestDto loginRequestDto);

    UsersDto getUserById(String token);
}
