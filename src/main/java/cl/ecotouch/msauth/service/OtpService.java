package cl.ecotouch.msauth.service;

import cl.ecotouch.msauth.dto.OtpRequestDto;
import cl.ecotouch.msauth.dto.UsersDto;

import javax.mail.MessagingException;

public interface OtpService {

    void saveOTP(String email, String otp);

    void sendEmail(String recipientEmail, String otp) throws MessagingException;

    String generateOTP();

    UsersDto validateOtp(OtpRequestDto otpRequestDto);

}
