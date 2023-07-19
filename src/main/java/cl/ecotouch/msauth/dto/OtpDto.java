package cl.ecotouch.msauth.dto;
import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
public class OtpDto {
    int id;
    String email;
    String otp;
    Date expirationDate;
}
