package br.com.microservices.orchestrated.orderservice.core.service;

import br.com.microservices.orchestrated.orderservice.core.document.Event;
import br.com.microservices.orchestrated.orderservice.core.document.Order;
import br.com.microservices.orchestrated.orderservice.core.dto.OrderRequest;
import br.com.microservices.orchestrated.orderservice.core.producer.SagaProducer;
import br.com.microservices.orchestrated.orderservice.core.repository.OrderRepository;
import br.com.microservices.orchestrated.orderservice.core.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    private final EventService eventService;

    private final JsonUtil jsonUtil;

    private final SagaProducer producer;

    public Order createOrder(OrderRequest orderRequest) {
        var order = Order.
                builder().
                products(orderRequest.getProducts()).
                createAt(LocalDateTime.now()).
                transactionalId(String.format("%s_%s", Instant.now().toEpochMilli(), UUID.randomUUID())).
                build();

        var payload = jsonUtil.toJson(createPayload(order));
        producer.sendEvent(payload);
        return repository.save(order);
    }

    private Event createPayload(Order order) {
        var event = Event.
                builder().
                orderId(order.getId()).
                transactionalId(order.getTransactionalId()).
                payload(order).
                build();

        return eventService.save(event);
    }
}