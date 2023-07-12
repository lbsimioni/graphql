package br.com.simioni.graphql.listener.configs;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class PubSubConfig {

    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter(@Qualifier("pubSubInputChannel") final MessageChannel inputChannel,
                                                             final PubSubTemplate pubSubTemplate) {
        final var adapter = new PubSubInboundChannelAdapter(pubSubTemplate, "order-topic-sub");
        adapter.setOutputChannel(inputChannel);
        adapter.setAckMode(AckMode.MANUAL);

        return adapter;
    }

    @Bean
    public MessageChannel pubSubInputChannel() {
        return new DirectChannel();
    }

}
