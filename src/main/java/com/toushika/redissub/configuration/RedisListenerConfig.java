package com.toushika.redissub.configuration;

import com.toushika.redissub.service.RedisConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisListenerConfig {

    @Value("${redis.general.topic}")
    private String topic;

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    //Creating Connection with Redis
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {

        var config = new RedisStandaloneConfiguration(redisHost, redisPort);

        return new LettuceConnectionFactory(config);
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(RedisConsumer consumer) {
        return new MessageListenerAdapter(consumer);
    }

    @Bean
    public RedisMessageListenerContainer listenerContainer(MessageListenerAdapter listenerAdapter,
                                                           RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new ChannelTopic(topic));
        return container;
    }
}