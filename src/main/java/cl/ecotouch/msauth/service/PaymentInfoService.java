package cl.ecotouch.msauth.service;

import cl.ecotouch.msauth.dto.PaymentInfoDto;

public interface PaymentInfoService {
    void savePaymentInfo(PaymentInfoDto paymentInfoDto);

    void updatePaymentInfo(PaymentInfoDto paymentInfoDto);
}
