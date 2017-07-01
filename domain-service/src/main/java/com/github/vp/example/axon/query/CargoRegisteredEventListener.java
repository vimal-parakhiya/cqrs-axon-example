package com.github.vp.example.axon.query;

import com.github.vp.example.axon.domain.event.CargoRegisteredEvent;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by vimalpar on 01/07/17.
 */
@Component
public class CargoRegisteredEventListener {
    private final static Logger logger = LoggerFactory.getLogger(CargoRegisteredEventListener.class);

    @EventHandler
    public void on(CargoRegisteredEvent event) {
        logger.info("Registered Cargo with Tracking Id: {}, Itinerary: {}", event.getTrackingId(), event.getItinerary());
    }
}
