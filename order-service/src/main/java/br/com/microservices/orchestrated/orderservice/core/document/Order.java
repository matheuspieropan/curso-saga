package br.com.microservices.orchestrated.orderservice.core.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("order")
public class Order {

    @Id
    private String id;

    private List<OrderProduct> products;

    private LocalDateTime createAt;

    private String transactionalId;

    private double totalAmmount;

    private int totalItens;
}