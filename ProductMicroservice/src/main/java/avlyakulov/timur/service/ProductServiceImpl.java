package avlyakulov.timur.service;

import avlyakulov.timur.event.ProductCreatedEvent;
import avlyakulov.timur.exception.AppException;
import avlyakulov.timur.service.dto.CreateProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    @Override
    public String createProduct(CreateProductDto createProductDto) {
        String productId = UUID.randomUUID().toString();

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(
                productId, createProductDto.getTitle(),
                createProductDto.getPrice(), createProductDto.getQuantity());

        SendResult<String, ProductCreatedEvent> result = sendEvent(productId, productCreatedEvent);

        log.info("Topic: {}", result.getRecordMetadata().topic());
        log.info("Partition: {}", result.getRecordMetadata().partition());
        log.info("Offset: {}", result.getRecordMetadata().offset());

        log.info("Return: {}", productId);

        return productId;
    }

    private SendResult<String, ProductCreatedEvent> sendEvent(String productId, ProductCreatedEvent productCreatedEvent) {
        try {
            return kafkaTemplate
                    .send("product-created-events-topic", productId, productCreatedEvent).get();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AppException("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}