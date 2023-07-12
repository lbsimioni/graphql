package br.com.simioni.graphql.listener.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class Order {
    private String order;
    private String origin;
    private String createdAt;
    private List<Item> items;

    public Float getTotal() {
        return (float) this.items.stream().mapToDouble(item -> item.getCost() * item.getQty()).sum();
    }
}
