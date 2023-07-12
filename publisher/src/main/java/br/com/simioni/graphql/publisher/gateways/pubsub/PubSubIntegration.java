package br.com.simioni.graphql.publisher.gateways.pubsub;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "pubSubOutputChannel")
public interface PubSubIntegration {

    void sendToPubSub(final String text);

}
