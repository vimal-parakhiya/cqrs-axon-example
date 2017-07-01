package com.github.vp.example.axon.domain.vo;

import java.io.Serializable;

/**
 * Created by vimalpar on 01/07/17.
 */
public class Itinerary implements Serializable{
    private Address source;
    private Address destination;

    public Itinerary(Address source, Address destination) {
        this.source = source;
        this.destination = destination;
    }

    public Address getSource() {
        return source;
    }

    public Address getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return String.format("Itinerary: %s --> %s", source, destination);
    }
}
