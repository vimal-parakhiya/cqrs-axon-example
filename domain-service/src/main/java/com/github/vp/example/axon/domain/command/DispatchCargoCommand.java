package com.github.vp.example.axon.domain.command;

import com.github.vp.example.axon.domain.vo.Itinerary;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by vimalpar on 01/07/17.
 */
public class DispatchCargoCommand {
    @TargetAggregateIdentifier
    private String trackingId;

    public DispatchCargoCommand(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getTrackingId() {
        return trackingId;
    }

}
