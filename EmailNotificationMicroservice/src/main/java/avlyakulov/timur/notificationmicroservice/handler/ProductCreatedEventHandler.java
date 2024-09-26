package avlyakulov.timur.notificationmicroservice.handler;

import avlyakulov.timur.event.ProductCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@KafkaListener(topics = "product-created-events-topic")
public class ProductCreatedEventHandler {

    @KafkaHandler //теперь он будет мапить на этот хендлер по типу входных аргументов
    public void handle(ProductCreatedEvent productCreatedEvent) {
        log.info("Received event: {}", productCreatedEvent.getTitle());
    }
}