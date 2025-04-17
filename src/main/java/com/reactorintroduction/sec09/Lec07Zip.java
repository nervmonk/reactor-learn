package com.reactorintroduction.sec09;

import java.time.Duration;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;

/*
 * Zip
 * - Will subscribe to all producers at the same time
 * - All or nothing
 * All producers will have to emit an item
 */
public class Lec07Zip {

    record Car(String body, String engine, String tires){}

    public static void main(String[] args) {
        Flux.zip(getBody(), getEngine(), getTires())
        .map(t -> new Car(t.getT1(), t.getT2(), t.getT3()))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }

    private static Flux<String> getBody() {
        return Flux.range(1, 5)
                .map(i -> "body-" + i)
                .delayElements(Duration.ofMillis(100));
    }

    private static Flux<String> getEngine() {
        return Flux.range(1, 3)
                .map(i -> "engine-" + i)
                .delayElements(Duration.ofMillis(100));
    }

    private static Flux<String> getTires() {
        return Flux.range(1, 10)
                .map(i -> "tires-" + i)
                .delayElements(Duration.ofMillis(75));
    }
}
