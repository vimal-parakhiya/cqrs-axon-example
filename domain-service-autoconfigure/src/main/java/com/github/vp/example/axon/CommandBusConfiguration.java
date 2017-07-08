package com.github.vp.example.axon;

import org.axonframework.commandhandling.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.disruptor.DisruptorCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.monitoring.NoOpMessageMonitor;
import org.axonframework.spring.config.AnnotationDriven;
import org.axonframework.spring.config.CommandHandlerSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by vimalpar on 08/07/17.
 */
@Configuration
@AnnotationDriven
public class CommandBusConfiguration {
    @Primary
    @Bean("localSegment")
    public DisruptorCommandBus disruptorCommandBus(EventStore eventStore) {
        return new DisruptorCommandBus(eventStore);
    }

    @Bean
    public CommandGateway defaultCommandGateway(CommandBus commandBus) {
        return new DefaultCommandGateway(commandBus);
    }

    @Bean
    public CommandHandlerSubscriber commandHandlerSubscriber(CommandBus commandBus) {
        CommandHandlerSubscriber commandHandlerSubscriber = new CommandHandlerSubscriber();
        commandHandlerSubscriber.setCommandBus(commandBus);
        return commandHandlerSubscriber;
    }
}
