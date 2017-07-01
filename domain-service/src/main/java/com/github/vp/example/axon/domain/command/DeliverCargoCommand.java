package com.github.vp.example.axon.domain.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by vimalpar on 01/07/17.
 */
public class DeliverCargoCommand {
    @TargetAggregateIdentifier
    private String trackingId;

    public DeliverCargoCommand(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getTrackingId() {
        return trackingId;
    }

}
