package cl.ecotouch.msauth.service.impl;

import cl.ecotouch.msauth.dto.PaymentInfoDto;
import cl.ecotouch.msauth.mapper.PaymentInfoMapper;
import cl.ecotouch.msauth.service.PaymentInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentInfoServiceImpl implements PaymentInfoService {

    private final PaymentInfoMapper paymentInfoMapper;

    @Override
    public void savePaymentInfo(PaymentInfoDto paymentInfoDto) {
        paymentInfoMapper.insertPaymentInfo(paymentInfoDto);
    }

    @Override
    public void updatePaymentInfo(PaymentInfoDto paymentInfoDto) {
        paymentInfoMapper.updatePaymentInfo(paymentInfoDto);
    }
}
