package com.reactorintroduction.sec09.helper;

import java.time.Duration;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;

public class AmericanAirlines {
    private static final String AIRLINE = "American Airlines";

    public static Flux<Flight> getFlights() {
        return Flux.range(1, Util.faker().random().nextInt(5, 10))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(200, 1200)))
                .map(i -> new Flight(AIRLINE, Util.faker().random().nextInt(300, 1200)))
                .transform(Util.fluxLogger(AIRLINE));
    }
}
