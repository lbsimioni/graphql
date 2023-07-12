package br.com.simioni.graphql.listener.gateways.repositories.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "orders")
public class OrderDocument {

    @Id
    private String order;
    private String origin;
    private Float total;
    private String createdAt;
    private List<ItemDocument> items;

}
