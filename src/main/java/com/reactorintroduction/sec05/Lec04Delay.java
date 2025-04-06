package com.reactorintroduction.sec05;

import java.time.Duration;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;

public class Lec04Delay {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(12);
    }
}
