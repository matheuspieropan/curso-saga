package br.com.microservices.orchestrated.orchestratorservice.core.dto;

import br.com.microservices.orchestrated.orchestratorservice.core.enums.EventSourceEnum;
import br.com.microservices.orchestrated.orchestratorservice.core.enums.SagaStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    private String id;

    private String transactionalId;

    private String orderId;

    private Order payload;

    private EventSourceEnum source;

    private SagaStatusEnum status;

    private List<History> eventHistory;

    private LocalDateTime createdAt;
}