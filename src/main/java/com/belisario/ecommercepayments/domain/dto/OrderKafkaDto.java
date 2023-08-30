package com.belisario.ecommercepayments.domain.dto;

import com.belisario.ecommercepayments.domain.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderKafkaDto {

    private String orderId;
    private BigDecimal totalValue;

    public static Payment toPayment(OrderKafkaDto dto) {
        Payment payment = new Payment();
        payment.setOrderId(dto.getOrderId());
        payment.setTotalValue(dto.getTotalValue());
        return payment;
    }
}