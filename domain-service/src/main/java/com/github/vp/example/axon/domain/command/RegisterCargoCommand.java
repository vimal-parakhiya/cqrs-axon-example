package com.github.vp.example.axon.domain.command;

import com.github.vp.example.axon.domain.vo.Itinerary;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by vimalpar on 01/07/17.
 */
public class RegisterCargoCommand {
    @TargetAggregateIdentifier
    private String trackingId;
    private Itinerary itinerary;

    public RegisterCargoCommand(String trackingId, Itinerary itinerary) {
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
