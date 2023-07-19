package cl.ecotouch.msauth.controller;

import cl.ecotouch.msauth.dto.PaymentInfoDto;
import cl.ecotouch.msauth.service.PaymentInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment-info")
@RequiredArgsConstructor
public class PaymentInfoController {
    private final PaymentInfoService paymentInfoService;

    @CrossOrigin("*")
    @PostMapping("/save")
    public ResponseEntity<String> savePaymentInfo(@RequestBody PaymentInfoDto paymentInfoDto) {
        paymentInfoService.savePaymentInfo(paymentInfoDto);
        return ResponseEntity.ok("Payment information saved successfully");
    }

    @PutMapping("/update/{username}/{number}")
    public ResponseEntity<String> updatePaymentInfo(@PathVariable("username") String username,
                                                    @RequestBody PaymentInfoDto paymentInfoDto) {
        paymentInfoDto.setUsername(username);
        paymentInfoService.updatePaymentInfo(paymentInfoDto);
        return ResponseEntity.ok("Payment information updated successfully");
    }
}