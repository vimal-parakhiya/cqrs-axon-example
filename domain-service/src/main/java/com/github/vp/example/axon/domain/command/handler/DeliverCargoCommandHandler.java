package com.github.vp.example.axon.domain.command.handler;

import com.github.vp.example.axon.domain.Cargo;
import com.github.vp.example.axon.domain.command.DeliverCargoCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vimalpar on 09/07/17.
 */
@Component
public class DeliverCargoCommandHandler {
    private Repository<Cargo> cargoRepository;

    @Autowired
    public DeliverCargoCommandHandler(Repository<Cargo> cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    @CommandHandler
    public void on(DeliverCargoCommand command) {
        Aggregate<Cargo> cargoAggregate = cargoRepository.load(command.getTrackingId());
        cargoAggregate.execute(Cargo::deliver);
    }
}
