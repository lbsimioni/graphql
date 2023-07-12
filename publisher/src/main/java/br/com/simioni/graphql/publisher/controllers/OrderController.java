package br.com.simioni.graphql.publisher.controllers;

import br.com.simioni.graphql.publisher.controllers.dtos.OrderDTO;
import br.com.simioni.graphql.publisher.converters.OrderConverter;
import br.com.simioni.graphql.publisher.models.Order;
import br.com.simioni.graphql.publisher.services.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {

    @Autowired
    private final PublisherService publisherService;

    @MutationMapping
    public Order publishPlacedOrderMessage(@Argument final OrderDTO order) {
        return publisherService.publishOrderMessage(OrderConverter.convert(order));
    }

    @QueryMapping
    public List<Order> placedOrders() {
        return publisherService.findAll();
    }

}
