package cl.ecotouch.msauth.mapper;

import cl.ecotouch.msauth.dto.LoginRequestDto;
import cl.ecotouch.msauth.dto.UsersDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS")
    List<UsersDto> getAllUsers();

    @Select("SELECT * FROM USERS WHERE USERNAME = #{username} AND PASSWORD = #{password}")
    @Results(value = {
            @Result(property = "username", column = "USERNAME"),
            @Result(property = "password", column = "PASSWORD"),
            @Result(property = "profile", column = "PROFILE"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "name", column = "NAME")
    })
    UsersDto getUserById(LoginRequestDto loginRequestDto);

    @Select("SELECT TOP 1 * FROM USERS WHERE USERNAME = #{username} ")
    @Results(value = {
            @Result(property = "username", column = "USERNAME"),
            @Result(property = "password", column = "PASSWORD"),
            @Result(property = "profile", column = "PROFILE"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "name", column = "NAME")
    })
    UsersDto getUserByUsername(String username);
}
