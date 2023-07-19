package cl.ecotouch.msauth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrackingResponseDto {
    String username;
    String method;
    String route;
    String input;
    String output;
    String timestamp;
}