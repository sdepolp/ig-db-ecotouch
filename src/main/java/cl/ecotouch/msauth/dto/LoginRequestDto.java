package cl.ecotouch.msauth.dto;

import lombok.*;

@Data
@Builder
public class LoginRequestDto {
    String username;
    String password;
}
