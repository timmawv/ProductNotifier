package avlyakulov.timur.notificationmicroservice.handler;

import avlyakulov.timur.event.ProductCreatedEvent;
import avlyakulov.timur.notificationmicroservice.exception.NonRetryableException;
import avlyakulov.timur.notificationmicroservice.exception.RetryableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
@KafkaListener(topics = "product-created-events-topic")
public class ProductCreatedEventHandler {

    private final RestTemplate restTemplate;

    @KafkaHandler //теперь он будет мапить на этот хендлер по типу входных аргументов
    public void handle(ProductCreatedEvent productCreatedEvent) {
        log.info("Received event: {}", productCreatedEvent.getTitle());

        String url = "http://localhost:8090/response/200";
//        try {
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
//
//            if (response.getStatusCode().value() == HttpStatus.OK.value())
//                log.info("Received response: {}", response.getBody());
//
//        } catch (ResourceAccessException e) {
//            log.error(e.getMessage());
//            throw new RetryableException(e);
//        } catch (HttpServerErrorException e) {
//            log.error(e.getMessage());
//            throw new NonRetryableException(e);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            throw new NonRetryableException(e);
//        }
    }
}