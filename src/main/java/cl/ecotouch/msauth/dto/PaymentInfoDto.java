package cl.ecotouch.msauth.dto;

import lombok.Data;

@Data
public class PaymentInfoDto {
    String number;
    String date;
    String cvv;
    String holderName;
    int amount;
    String username;
}
