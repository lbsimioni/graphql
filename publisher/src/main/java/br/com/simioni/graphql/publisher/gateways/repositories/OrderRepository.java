package br.com.simioni.graphql.publisher.gateways.repositories;

import br.com.simioni.graphql.publisher.gateways.repositories.documents.OrderDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<OrderDocument, String> {
}
