package com.reactorintroduction.sec10;

import java.time.Duration;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;

public class Lec01Buffer {
    public static void main(String[] args) {
        demo4();

        Util.sleepSeconds(6);
    }

    private static void demo1() {
        eventStream()
                .buffer() // int-max value or the source has to complete
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        eventStream()
                .buffer(3) // every 3 items
                .subscribe(Util.subscriber());
    }

    private static void demo3() {
        eventStream()
                .buffer(Duration.ofMillis(100)) // every 3 items
                .subscribe(Util.subscriber());
    }

    private static void demo4() {
        eventStream()
                .bufferTimeout(3, Duration.ofSeconds(1)) // every 3 items
                .subscribe(Util.subscriber());
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(200))
                .take(10)
                .concatWith(Flux.never())
                .map(i -> "event-" + (i + 1));
    }
}
