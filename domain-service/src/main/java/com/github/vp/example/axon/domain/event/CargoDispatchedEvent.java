package com.github.vp.example.axon.domain.event;

/**
 * Created by vimalpar on 01/07/17.
 */
public class CargoDispatchedEvent {

    private String trackingId;

    public CargoDispatchedEvent(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getTrackingId() {
        return trackingId;
    }
}
