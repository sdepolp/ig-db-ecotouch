package cl.ecotouch.msauth.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public
class LoginRequestDto {
    public String username;
    public String password;
}
