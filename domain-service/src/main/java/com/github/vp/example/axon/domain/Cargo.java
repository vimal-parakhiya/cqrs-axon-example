package com.github.vp.example.axon.domain;

import com.github.vp.example.axon.domain.command.DeliverCargoCommand;
import com.github.vp.example.axon.domain.command.DispatchCargoCommand;
import com.github.vp.example.axon.domain.command.RegisterCargoCommand;
import com.github.vp.example.axon.domain.event.CargoDeliveredEvent;
import com.github.vp.example.axon.domain.event.CargoDispatchedEvent;
import com.github.vp.example.axon.domain.event.CargoRegisteredEvent;
import com.github.vp.example.axon.domain.vo.CargoStatus;
import com.github.vp.example.axon.domain.vo.Itinerary;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import static com.github.vp.example.axon.domain.vo.CargoStatus.*;
import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * Created by vimalpar on 01/07/17.
 */
@Aggregate
public class Cargo {
    @AggregateIdentifier
    private String trackingId;
    private Itinerary itinerary;
    private CargoStatus cargoStatus;

    private Cargo() {
    }

    @CommandHandler
    public Cargo(RegisterCargoCommand registerCargoCommand) {
        apply(new CargoRegisteredEvent(registerCargoCommand.getTrackingId(), registerCargoCommand.getItinerary()));
    }

    @CommandHandler
    public void dispatch(DispatchCargoCommand command) {
        Assert.isTrue(cargoStatus == REGISTERED, String.format("Unable to dispatch cargo %s due to invalid status %s", trackingId, cargoStatus) );
        apply(new CargoDispatchedEvent(command.getTrackingId()));
    }

    @CommandHandler
    public void deliver(DeliverCargoCommand command) {
        Assert.isTrue(cargoStatus == DISPATCHED, String.format("Unable to dispatch cargo %s due to invalid status %s", trackingId, cargoStatus));
        apply(new CargoDeliveredEvent(command.getTrackingId()));
    }

    @EventSourcingHandler
    public void on(CargoRegisteredEvent event) {
        trackingId = event.getTrackingId();
        itinerary = event.getItinerary();
        cargoStatus = REGISTERED;
    }

    @EventSourcingHandler
    public void on(CargoDispatchedEvent event) {
        cargoStatus = DISPATCHED;
    }

    @EventSourcingHandler
    public void on(CargoDeliveredEvent event) {
        cargoStatus = DELIVERED;
    }
}
