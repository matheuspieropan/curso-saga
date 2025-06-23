package br.com.microservices.orchestrated.orchestratorservice.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.BASE_ORCHESTRATOR;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.FINISH_FAIL;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.FINISH_SUCCESS;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.INVENTORY_FAIL;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.INVENTORY_SUCCESS;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.NOTIFY_ENDING;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.PAYMENT_FAIL;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.PAYMENT_SUCCESS;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.PRODUCT_VALIDATION_FAIL;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.PRODUCT_VALIDATION_SUCCESS;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.START_SAGA;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String BOOTSTRAP_SERVERS;

    @Value("${spring.kafka.consumer.group-id}")
    private String GROUP_ID;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String AUTO_OFFSET_RESET;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumer());
    }

    @Bean
    public ConsumerFactory<String, String> producerFactory() {
        return new DefaultKafkaConsumerFactory<>(producer());
    }

    private Map<String, Object> consumer() {
        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, AUTO_OFFSET_RESET);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return props;
    }

    private Map<String, Object> producer() {
        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return props;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producer) {
        return new KafkaTemplate<>(producer);
    }

    @Bean
    public NewTopic startSagaTopic() {
        return createTopic(START_SAGA.getTopic());
    }

    @Bean
    public NewTopic orchestratorTopic() {
        return createTopic(BASE_ORCHESTRATOR.getTopic());
    }

    @Bean
    public NewTopic finishSuccessTopic() {
        return createTopic(FINISH_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic finishFailTopic() {
        return createTopic(FINISH_FAIL.getTopic());
    }

    @Bean
    public NewTopic productValidationSuccessTopic() {
        return createTopic(PRODUCT_VALIDATION_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic productValidationFailTopic() {
        return createTopic(PRODUCT_VALIDATION_FAIL.getTopic());
    }

    @Bean
    public NewTopic paymentSuccessTopic() {
        return createTopic(PAYMENT_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic paymentFailTopic() {
        return createTopic(PAYMENT_FAIL.getTopic());
    }

    @Bean
    public NewTopic inventorySuccessTopic() {
        return createTopic(INVENTORY_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic inventoryFailTopic() {
        return createTopic(INVENTORY_FAIL.getTopic());
    }

    @Bean
    public NewTopic notifyEndingTopic() {
        return createTopic(NOTIFY_ENDING.getTopic());
    }

    private NewTopic createTopic(String topicName) {
        return TopicBuilder.name(topicName)
                .partitions(1)
                .replicas(1)
                .build();
    }
}