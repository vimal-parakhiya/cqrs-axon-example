package com.github.vp.example.axon.domain.event;

import org.axonframework.serialization.Revision;

/**
 * Created by vimalpar on 01/07/17.
 */
@Revision("1")
public class CargoDeliveredEvent {

    private String trackingId;

    private CargoDeliveredEvent() {
    }

    public CargoDeliveredEvent(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getTrackingId() {
        return trackingId;
    }
}
