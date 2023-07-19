package cl.ecotouch.msauth.mapper;

import cl.ecotouch.msauth.dto.PaymentInfoDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PaymentInfoMapper {
    @Insert("INSERT INTO paymentinfo (number, date, cvv, holdername, amount, username) " +
            "VALUES (#{number}, #{date}, #{cvv}, #{holderName}, #{amount}, #{username})")
    void insertPaymentInfo(PaymentInfoDto paymentInfoDto);

    @Update("UPDATE paymentinfo SET number = #{number}, date = #{date}, cvv = #{cvv}, holdername = #{holderName}, " +
            "amount = #{amount} WHERE username = #{username}")
    void updatePaymentInfo(PaymentInfoDto paymentInfoDto);
}
