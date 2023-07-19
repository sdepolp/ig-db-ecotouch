package cl.ecotouch.msauth.mapper;

import cl.ecotouch.msauth.dto.OtpDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OtpMapper {

    @Insert("INSERT INTO otp_table (email, otp, expiration_date) " +
            "VALUES (#{email}, #{otp}, #{expirationDate})")
    void insertOTP(OtpDto otp);

    @Select("SELECT id, email, otp, expiration_date " +
            "FROM otp_table " +
            "WHERE email = #{email} order by expiration_date desc")
    OtpDto findOTPByEmail(@Param("email") String email);
}
