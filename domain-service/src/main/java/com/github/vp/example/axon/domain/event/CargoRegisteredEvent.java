package com.github.vp.example.axon.domain.event;

import com.github.vp.example.axon.domain.vo.Itinerary;

/**
 * Created by vimalpar on 01/07/17.
 */
public class CargoRegisteredEvent {
    private String trackingId;
    private Itinerary itinerary;

    private CargoRegisteredEvent() {
    }

    public CargoRegisteredEvent(String trackingId, Itinerary itinerary) {
        this.trackingId = trackingId;
        this.itinerary = itinerary;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }
}
