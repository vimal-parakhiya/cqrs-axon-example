package com.github.vp.example.axon;


import com.github.vp.example.axon.domain.command.DeliverCargoCommand;
import com.github.vp.example.axon.domain.command.DispatchCargoCommand;
import com.github.vp.example.axon.domain.command.RegisterCargoCommand;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.distributed.DistributedCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.github.vp.example.axon.Random.itinerary;
import static com.github.vp.example.axon.Random.trackingId;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by vimalpar on 01/07/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CommandGateway commandGateway;

    @Test
    public void shouldLoadContext() {
        assertThat(applicationContext.getBean(EventBus.class)).isNotNull();
        assertThat(applicationContext.getBean(CommandBus.class)).isNotNull();
        assertThat(applicationContext.getBean(DistributedCommandBus.class)).isNotNull();
    }

    @Test
    public void shouldCreateAndDeliverCargo() throws InterruptedException {
        String trackingId = trackingId();
        commandGateway.sendAndWait(new RegisterCargoCommand(trackingId, itinerary()));
        commandGateway.sendAndWait(new DispatchCargoCommand(trackingId));
        commandGateway.sendAndWait(new DeliverCargoCommand(trackingId));
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldNotDeliverCargoIfNotDispatched() throws InterruptedException {
        String trackingId = trackingId();
        commandGateway.sendAndWait(new RegisterCargoCommand(trackingId, itinerary()));
        commandGateway.sendAndWait(new DeliverCargoCommand(trackingId));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotDeliverSameCargoMultipleTimes() throws InterruptedException {
        String trackingId = trackingId();
        commandGateway.sendAndWait(new RegisterCargoCommand(trackingId, itinerary()));
        commandGateway.sendAndWait(new DispatchCargoCommand(trackingId));
        commandGateway.sendAndWait(new DeliverCargoCommand(trackingId));
        commandGateway.sendAndWait(new DeliverCargoCommand(trackingId));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotDispatchAlreadyDeliveredCargo() throws InterruptedException {
        String trackingId = trackingId();
        commandGateway.sendAndWait(new RegisterCargoCommand(trackingId, itinerary()));
        commandGateway.sendAndWait(new DeliverCargoCommand(trackingId));
        commandGateway.sendAndWait(new DeliverCargoCommand(trackingId));
        commandGateway.sendAndWait(new DispatchCargoCommand(trackingId));
    }
}