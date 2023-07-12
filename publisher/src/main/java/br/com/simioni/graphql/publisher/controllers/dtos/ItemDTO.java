package br.com.simioni.graphql.publisher.controllers.dtos;

import lombok.Data;

@Data
public class ItemDTO {
    private String name;
    private String image;
    private Integer qty;
    private Float cost;
    private String currency;
}
