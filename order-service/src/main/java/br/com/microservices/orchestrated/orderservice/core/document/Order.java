package br.com.microservices.orchestrated.orderservice.core.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String id;

    private List<OrderProduct> products;

    private LocalDateTime createAt;

    private String transactionalId;

    private double totalAmmount;

    private int totalItens;
}