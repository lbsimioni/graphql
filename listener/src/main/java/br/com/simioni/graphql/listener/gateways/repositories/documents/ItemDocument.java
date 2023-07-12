package br.com.simioni.graphql.listener.gateways.repositories.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDocument {
    private String name;
    private String image;
    private Integer qty;
    private Float cost;
    private String currency;
}
