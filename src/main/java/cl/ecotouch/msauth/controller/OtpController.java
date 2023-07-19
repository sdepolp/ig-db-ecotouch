package cl.ecotouch.msauth.controller;

import cl.ecotouch.msauth.dto.OtpRequestDto;
import cl.ecotouch.msauth.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/v1/otp")
@RequiredArgsConstructor
public class OtpController {

    private final OtpService otpService;

    @CrossOrigin("*")
    @PostMapping("/generate")
    public void generateOTP(@RequestParam String email) throws MessagingException {
        String otp = otpService.generateOTP();
        otpService.saveOTP(email, otp);
        otpService.sendEmail(email, otp);
    }
    @CrossOrigin("*")
    @PostMapping("/validate")
    public ResponseEntity<?> validateOtp(@RequestBody OtpRequestDto otpRequestDto){
        return ResponseEntity.ok(otpService.validateOtp(otpRequestDto));
    }
}