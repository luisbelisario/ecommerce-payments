package com.belisario.ecommercepayments.service;

import com.belisario.ecommercepayments.domain.dto.OrderKafkaDto;
import com.belisario.ecommercepayments.domain.model.Payment;
import com.belisario.ecommercepayments.repository.PaymentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @Autowired
    PaymentRepository paymentRepository;

    @KafkaListener(topics = "NEW_ORDER", groupId = "ecommerce-new-order")
    public void processPayment(ConsumerRecord<String, String> record) throws JsonProcessingException {
        System.out.println("Pedido recebido! Processando pagamento...");
        ObjectMapper objectMapper = new ObjectMapper();
        OrderKafkaDto kafkaDto = objectMapper.readValue(record.value(), OrderKafkaDto.class);
        Payment payment = OrderKafkaDto.toPayment(kafkaDto);
        paymentRepository.save(payment);

        System.out.println("Pagamento processado!");
    }
}
