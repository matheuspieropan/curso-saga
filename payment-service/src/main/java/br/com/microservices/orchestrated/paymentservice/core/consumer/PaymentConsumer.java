package br.com.microservices.orchestrated.paymentservice.core.consumer;

import br.com.microservices.orchestrated.paymentservice.core.dto.Event;
import br.com.microservices.orchestrated.paymentservice.core.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class PaymentConsumer {

    private final JsonUtil jsonUtil;

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${spring.kafka.topic.payment-success}")
    public void consumeSucessEvent(String payload) {
        log.info("Recebendo notificação do tópico payment-successs");
        Event event = jsonUtil.toEvent(payload);
        System.out.println(event);
    }

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${spring.kafka.topic.payment-fail}")
    public void consumeFailEvent(String payload) {
        log.info("Recebendo rollback do tópico payment-fail");
        Event event = jsonUtil.toEvent(payload);
        System.out.println(event);
    }
}