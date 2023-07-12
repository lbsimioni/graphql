package br.com.simioni.graphql.publisher.services;

import br.com.simioni.graphql.publisher.converters.OrderConverter;
import br.com.simioni.graphql.publisher.gateways.pubsub.PubSubIntegration;
import br.com.simioni.graphql.publisher.gateways.repositories.OrderRepository;
import br.com.simioni.graphql.publisher.models.Order;
import br.com.simioni.graphql.publisher.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PublisherService {

    @Autowired
    private final PubSubIntegration pubSubIntegration;

    @Autowired
    private final OrderRepository orderRepository;

    public Order publishOrderMessage(final Order order) {
        final var json = JsonUtil.toJson(order);
        log.info("Trying publish order message in pub-sub: {}", json);

        try {
            this.pubSubIntegration.sendToPubSub(json);
            log.info("Message published with success");
            return order;
        } catch (final Exception e) {
            log.error("Error while publish message in pub-sub", e);
            throw new RuntimeException(e);
        }
    }

    public List<Order> findAll() {
        return this.orderRepository.findAll()
                .stream()
                .map(OrderConverter::convert)
                .collect(Collectors.toList());
    }

}
