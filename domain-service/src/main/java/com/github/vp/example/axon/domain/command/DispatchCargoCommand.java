package com.github.vp.example.axon.domain.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.io.Serializable;

/**
 * Created by vimalpar on 01/07/17.
 */
public class DispatchCargoCommand implements Serializable{
    @TargetAggregateIdentifier
    private String trackingId;

    private DispatchCargoCommand() {
    }

    public DispatchCargoCommand(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getTrackingId() {
        return trackingId;
    }

}
