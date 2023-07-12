package br.com.simioni.graphql.listener.gateways.pubsub;

import br.com.simioni.graphql.listener.models.Order;
import br.com.simioni.graphql.listener.services.OrderService;
import br.com.simioni.graphql.listener.utils.JsonUtil;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class PubSubListenerIntegration {

    @Autowired
    private final OrderService orderService;

    @Bean
    @ServiceActivator(inputChannel = "pubSubInputChannel")
    public MessageHandler messageReceiver() {
        return message -> {
            final var jsonMessage = new String((byte[]) message.getPayload());
            log.info("Listening new message! Payload: {}", jsonMessage);
            final var originalMessage = message.getHeaders()
                    .get(GcpPubSubHeaders.ORIGINAL_MESSAGE, BasicAcknowledgeablePubsubMessage.class);

            orderService.processOrderMessage(JsonUtil.fromJson(jsonMessage, Order.class));

            originalMessage.ack();
        };
    }

}
