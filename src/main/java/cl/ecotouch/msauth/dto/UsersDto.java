package cl.ecotouch.msauth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsersDto {
    String username;
    String password;
    Integer profile;
    String email;
    String name;
    String rut;
    String mobile;
}
