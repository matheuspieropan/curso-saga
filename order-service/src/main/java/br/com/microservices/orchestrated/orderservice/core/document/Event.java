package br.com.microservices.orchestrated.orderservice.core.document;

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

    private String source;

    private String status;

    private List<History> eventHistory;

    private LocalDateTime createdAt;
}