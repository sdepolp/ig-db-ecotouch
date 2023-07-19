package cl.ecotouch.msauth.dto;

import lombok.Data;

@Data
public class OtpRequestDto {
    String email;
    String profile;
    String password;
    String otp;
}
