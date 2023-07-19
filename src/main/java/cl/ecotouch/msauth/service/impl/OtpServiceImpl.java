package cl.ecotouch.msauth.service.impl;

import java.util.*;

import cl.ecotouch.msauth.dto.OtpRequestDto;
import cl.ecotouch.msauth.dto.UsersDto;
import cl.ecotouch.msauth.mapper.OtpMapper;
import cl.ecotouch.msauth.mapper.UserMapper;
import cl.ecotouch.msauth.service.OtpService;
import cl.ecotouch.msauth.dto.OtpDto;
import cl.ecotouch.msauth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final OtpMapper otpMapper;
    private final UserMapper userMapper;
    private final JavaMailSender javaMailSender;
    private final UserService userService;

    @Value("${spring.mail.username}")
    private String senderMail;

    @Override
    public String generateOTP() {
        int otpLength = 6;
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder otp = new StringBuilder(otpLength);
        for (int i = 0; i < otpLength; i++) {
            otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return otp.toString();
    }

    @Override
    public UsersDto validateOtp(OtpRequestDto otpRequestDto) {
        OtpDto otpFound = otpMapper.findOTPByEmail(otpRequestDto.getEmail());
        if (!Objects.equals(otpFound.getOtp(), otpRequestDto.getOtp())) {
            return null;
        }
        List<UsersDto> allUsers = userMapper.getAllUsers();
        //TODO IF EMAIL EXISTS
        Optional<UsersDto> foundUser = userService.findUserByEmail(allUsers, otpRequestDto.getEmail());
        UsersDto newUser = null;
        if (foundUser.isPresent()) {
            foundUser.get().setPassword(otpRequestDto.getPassword());
            userMapper.updateUser(foundUser.get());
            return foundUser.get();
        } else {
            newUser = UsersDto
                    .builder()
                    .username(otpRequestDto.getEmail())
                    .password(otpRequestDto.getPassword())
                    .profile(Integer.parseInt(otpRequestDto.getProfile()))
                    .email(otpRequestDto.getEmail())
                    .name("")
                    .rut("")
                    .build();
            userMapper.insertUser(newUser);
            return newUser;
        }
    }

    @Override
    public void saveOTP(String email, String otp) {
        Date expirationDate = new Date(System.currentTimeMillis() + (3 * 60 * 1000)); // 3 minutes
        OtpDto otpObj = OtpDto.builder()
                        .email(email)
                        .otp(otp)
                        .expirationDate(expirationDate)
                        .build();
        otpMapper.insertOTP(otpObj);
    }

    @Override
    public void sendEmail(String recipientEmail, String otp) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(senderMail);
        helper.setTo(recipientEmail);
        helper.setSubject("Ecotouch | Codigo OTP");
        helper.setText("Su código de OTP:<br/><h2>"+otp+"</h2>", true);
        javaMailSender.send(message);
    }
}
