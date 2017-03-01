package com.cartracking.main.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMqConfig extends Config {

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(env.getProperty("rabbitmq.host"));
        connectionFactory.setUsername(env.getProperty("rabbitmq.user"));
        connectionFactory.setPassword(env.getProperty("rabbitmq.pass"));

        return connectionFactory;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrentConsumers(Integer.parseInt(env.getProperty("rabbitmq.consumer")));
        factory.setMaxConcurrentConsumers(Integer.parseInt(env.getProperty("rabbitmq.consumer.max")));
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);

        return factory;
    }
}
