package cl.ecotouch.msauth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    String token;
    String errorMsg;
    UsersDto userData;
}
