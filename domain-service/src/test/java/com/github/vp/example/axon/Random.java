package com.github.vp.example.axon;

import com.github.vp.example.axon.domain.vo.Address;
import com.github.vp.example.axon.domain.vo.Itinerary;

import java.util.UUID;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

/**
 * Created by vimalpar on 01/07/17.
 */
public class Random {
    public static Address address() {
        return new Address(random(5), random(5), random(5), random(3), random(3), randomNumeric(6));
    }

    public static Itinerary itinerary() {
        return new Itinerary(address(), address());
    }

    public static String trackingId() {
        return UUID.randomUUID().toString();
    }
}
