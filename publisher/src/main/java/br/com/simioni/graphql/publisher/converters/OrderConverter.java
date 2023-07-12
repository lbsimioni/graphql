package br.com.simioni.graphql.publisher.converters;

import br.com.simioni.graphql.publisher.controllers.dtos.ItemDTO;
import br.com.simioni.graphql.publisher.controllers.dtos.OrderDTO;
import br.com.simioni.graphql.publisher.gateways.repositories.documents.ItemDocument;
import br.com.simioni.graphql.publisher.gateways.repositories.documents.OrderDocument;
import br.com.simioni.graphql.publisher.models.Item;
import br.com.simioni.graphql.publisher.models.Order;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderConverter {

    public static Order convert(final OrderDTO orderDTO) {
        return Order.builder()
                .order(orderDTO.getOrder())
                .origin(orderDTO.getOrigin())
                .items(orderDTO.getItems().stream().map(OrderConverter::convert).collect(Collectors.toList()))
                .build();
    }

    private static Item convert(final ItemDTO itemDTO) {
        return Item.builder()
                .cost(itemDTO.getCost())
                .qty(itemDTO.getQty())
                .name(itemDTO.getName())
                .image(itemDTO.getImage())
                .currency(itemDTO.getCurrency())
                .build();
    }

    public static Order convert(final OrderDocument orderDocument) {
        return Order.builder()
                .order(orderDocument.getOrder())
                .origin(orderDocument.getOrigin())
                .total(orderDocument.getTotal())
                .createdAt(orderDocument.getCreatedAt())
                .items(orderDocument.getItems().stream().map(OrderConverter::convert).collect(Collectors.toList()))
                .build();
    }

    private static Item convert(final ItemDocument itemDocument) {
        return Item.builder()
                .cost(itemDocument.getCost())
                .qty(itemDocument.getQty())
                .name(itemDocument.getName())
                .image(itemDocument.getImage())
                .currency(itemDocument.getCurrency())
                .build();
    }

}
