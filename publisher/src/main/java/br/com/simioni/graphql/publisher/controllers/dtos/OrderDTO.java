package br.com.simioni.graphql.publisher.controllers.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private String order;
    private String origin;
    private Float total;
    private String createdAt;
    private List<ItemDTO> items;
}
