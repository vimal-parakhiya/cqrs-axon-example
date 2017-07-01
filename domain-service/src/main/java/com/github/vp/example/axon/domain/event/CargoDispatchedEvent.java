package com.github.vp.example.axon.domain.event;

import org.axonframework.serialization.Revision;

/**
 * Created by vimalpar on 01/07/17.
 */
@Revision("1")
public class CargoDispatchedEvent {

    private String trackingId;

    private CargoDispatchedEvent() {
    }

    public CargoDispatchedEvent(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getTrackingId() {
        return trackingId;
    }
}
