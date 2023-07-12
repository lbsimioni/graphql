package br.com.simioni.graphql.listener.services;

import br.com.simioni.graphql.listener.converters.OrderConverter;
import br.com.simioni.graphql.listener.gateways.repositories.OrderRepository;
import br.com.simioni.graphql.listener.models.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    /**
     * Method responsible to get order base data, process (calculate total cost and generate creationDate)
     * and save in database (mongodb)
     * @param order Order to be process
     */
    public void processOrderMessage(final Order order) {
        try {
            this.orderRepository.save(OrderConverter.convert(order.toBuilder().createdAt(LocalDateTime.now().toString()).build()));
            log.info("Order saved with success!");
        } catch (final Exception e) {
            log.error("Error while persisting order data", e);
        }

    }

}
