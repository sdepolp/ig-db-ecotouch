package cl.ecotouch.msauth.mapper;

import cl.ecotouch.msauth.dto.LoginRequestDto;
import cl.ecotouch.msauth.dto.UsersDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS")
    List<UsersDto> getAllUsers();

    @Select("SELECT USERNAME,PASSWORD,PROFILE,EMAIL,NAME,RUT,MOBILE FROM USERS WHERE USERNAME = #{username} AND PASSWORD = #{password}")
    @Results(value = {
            @Result(property = "username", column = "USERNAME"),
            @Result(property = "password", column = "PASSWORD"),
            @Result(property = "profile", column = "PROFILE"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "rut", column = "RUT"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "mobile", column = "MOBILE")
    })
    UsersDto  getUserById(LoginRequestDto loginRequestDto);

    @Select("SELECT TOP 1 * FROM USERS WHERE USERNAME = #{username} ")
    @Results(value = {
            @Result(property = "username", column = "USERNAME"),
            @Result(property = "password", column = "PASSWORD"),
            @Result(property = "profile", column = "PROFILE"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "mobile", column = "MOBILE")
    })
    UsersDto getUserByUsername(String username);

    @Insert("INSERT INTO USERS(username, password, profile, email, name, rut, mobile) " +
            "VALUES (#{username}, #{password}, #{profile}, #{email}, #{name}, #{rut}, #{mobile})")
    void insertUser(UsersDto usuario);

    @Update("UPDATE USERS SET password = #{password}, profile = #{profile}, " +
            "email = #{email}, name = #{name}, rut = #{rut}, mobile = #{mobile} WHERE username = #{username}")
    void updateUser(UsersDto usuario);

    @Delete("DELETE FROM USERS WHERE username = #{username}")
    void deleteUser(String username);
}
