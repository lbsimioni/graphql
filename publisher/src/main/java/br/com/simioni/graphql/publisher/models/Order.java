package br.com.simioni.graphql.publisher.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Order {
    private String order;
    private String origin;
    private Float total;
    private String createdAt;
    private List<Item> items;
}
