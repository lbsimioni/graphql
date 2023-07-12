package br.com.simioni.graphql.listener.converters;

import br.com.simioni.graphql.listener.gateways.repositories.documents.ItemDocument;
import br.com.simioni.graphql.listener.gateways.repositories.documents.OrderDocument;
import br.com.simioni.graphql.listener.models.Item;
import br.com.simioni.graphql.listener.models.Order;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderConverter {
    public static OrderDocument convert(final Order orderDTO) {
        return OrderDocument.builder()
                .order(orderDTO.getOrder())
                .total(orderDTO.getTotal())
                .createdAt(orderDTO.getCreatedAt())
                .origin(orderDTO.getOrigin())
                .items(orderDTO.getItems().stream().map(OrderConverter::convert).collect(Collectors.toList()))
                .build();
    }

    private static ItemDocument convert(final Item itemDTO) {
        return ItemDocument.builder()
                .cost(itemDTO.getCost())
                .qty(itemDTO.getQty())
                .name(itemDTO.getName())
                .image(itemDTO.getImage())
                .currency(itemDTO.getCurrency())
                .build();
    }
}
