package br.com.microservices.orchestrated.orderservice.core.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SagaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.start-saga}")
    private String startSaga;

    public void sendEvent(String payload) {
        try {
            log.info("Enviando event para o tópico {}", startSaga);
            kafkaTemplate.send(startSaga, payload);
        } catch (Exception e) {
            log.error("Erro ao publicar no tópico {}", startSaga);
        }
    }
}