package com.github.vp.example.axon.domain.event;

/**
 * Created by vimalpar on 01/07/17.
 */
public class CargoDeliveredEvent {

    private String trackingId;

    public CargoDeliveredEvent(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getTrackingId() {
        return trackingId;
    }
}
