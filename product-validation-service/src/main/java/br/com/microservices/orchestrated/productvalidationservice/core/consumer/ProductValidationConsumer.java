package br.com.microservices.orchestrated.productvalidationservice.core.consumer;

import br.com.microservices.orchestrated.productvalidationservice.core.dto.Event;
import br.com.microservices.orchestrated.productvalidationservice.core.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ProductValidationConsumer {

    private final JsonUtil jsonUtil;

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${spring.kafka.topic.product-validation-success}")
    public void consumeSucessEvent(String payload) {
        log.info("Recebendo notificação do tópico product-validation-success");
        Event event = jsonUtil.toEvent(payload);
        System.out.println(event);
    }

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${spring.kafka.topic.product-validation-fail}")
    public void consumeFailEvent(String payload) {
        log.info("Recebendo rollback do tópico product-validation-fail");
        Event event = jsonUtil.toEvent(payload);
        System.out.println(event);
    }
}