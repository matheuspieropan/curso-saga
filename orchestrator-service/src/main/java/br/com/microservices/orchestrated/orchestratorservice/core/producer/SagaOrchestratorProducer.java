package br.com.microservices.orchestrated.orchestratorservice.core.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SagaOrchestratorProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendEvent(String payload, String topico) {
        try {
            log.info("Enviando event para o tópico {}", topico);
            kafkaTemplate.send(topico, payload);
        } catch (Exception e) {
            log.error("Erro ao publicar no tópico {}", topico);
        }
    }
}