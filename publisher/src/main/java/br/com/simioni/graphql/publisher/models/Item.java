package br.com.simioni.graphql.publisher.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private String name;
    private String image;
    private Integer qty;
    private Float cost;
    private String currency;
}
