package com.github.vp.example.axon.domain.command;

import com.github.vp.example.axon.domain.vo.Itinerary;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.io.Serializable;

/**
 * Created by vimalpar on 01/07/17.
 */
public class RegisterCargoCommand implements Serializable{
    @TargetAggregateIdentifier
    private String trackingId;
    private Itinerary itinerary;

    private RegisterCargoCommand() {
    }

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
