package com.github.vp.example.axon.config;

import com.github.vp.example.axon.domain.Cargo;
import org.axonframework.commandhandling.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.disruptor.DisruptorCommandBus;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vimalpar on 08/07/17.
 */
@Configuration
public class AxonConfiguration {
    @Bean
    public AggregateFactory<Cargo> cargoAggregateFactory() {
        return new GenericAggregateFactory<>(Cargo.class);
    }

    @Bean
    public Repository<Cargo> cargoRepository(DisruptorCommandBus commandBus, AggregateFactory<Cargo>  aggregateFactory) {
        return commandBus.createRepository(aggregateFactory);
    }

    @Bean
    public AggregateAnnotationCommandHandler<Cargo> aggregateAnnotationCommandHandler(Repository<Cargo> cargoRepository) {
        return new AggregateAnnotationCommandHandler<>(Cargo.class, cargoRepository);
    }
}
