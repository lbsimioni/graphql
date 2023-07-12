package br.com.simioni.graphql.listener.gateways.repositories;

import br.com.simioni.graphql.listener.gateways.repositories.documents.OrderDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<OrderDocument, String> {
}
