package com.reactorintroduction.sec03;

import java.time.Duration;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;

public class Lec09FluxInterval {
    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(500))
        .map(i -> Util.faker().name().firstName())
        .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }
}
