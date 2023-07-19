package cl.ecotouch.msauth.service;

import cl.ecotouch.msauth.dto.LoginRequestDto;
import cl.ecotouch.msauth.dto.LoginResponseDto;
import cl.ecotouch.msauth.dto.UsersDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UsersDto> getUsers();

    LoginResponseDto validateUser(LoginRequestDto loginRequestDto);

    UsersDto getUserById(String token);

    void insertUsuario(UsersDto usuario);

    void updateUsuario(UsersDto usuario);

    void deleteUsuario(String username);

    Optional<UsersDto> findUserByEmail(List<UsersDto> users, String email);
}
