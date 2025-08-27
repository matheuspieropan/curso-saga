package br.com.microservices.orchestrated.orchestratorservice.core.consumer;

import br.com.microservices.orchestrated.orchestratorservice.core.dto.Event;
import br.com.microservices.orchestrated.orchestratorservice.core.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SagaOrchestratorConsumer {

    private final JsonUtil jsonUtil;

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${spring.kafka.topic.start-saga}")
    public void consumeStartSagaEvent(String payload) {
        log.info("Recebendo notificações do tópico start-saga");
        Event event = jsonUtil.toEvent(payload);
        System.out.println(event);
    }

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${spring.kafka.topic.orchestrator}")
    public void consumeOrchestratorEvent(String payload) {
        log.info("Recebendo notificações do tópico orchestrator");
        Event event = jsonUtil.toEvent(payload);
        System.out.println(event);
    }

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${spring.kafka.topic.finish-sucess}")
    public void consumeFinishSucessoEvent(String payload) {
        log.info("Recebendo notificações do tópico finish-sucess");
        Event event = jsonUtil.toEvent(payload);
        System.out.println(event);
    }

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${spring.kafka.topic.finish-fail}")
    public void consumeFinishFailEvent(String payload) {
        log.info("Recebendo notificações do tópico finish-fail");
        Event event = jsonUtil.toEvent(payload);
        System.out.println(event);
    }
}