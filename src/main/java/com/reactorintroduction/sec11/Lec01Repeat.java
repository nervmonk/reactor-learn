package com.reactorintroduction.sec11;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Mono;

public class Lec01Repeat {
    public static void main(String[] args) {
        demo4();

        Util.sleepSeconds(10);
    }

    private static void demo1() {
        getCountryName()
                .repeat(3)
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        getCountryName()
                .repeat()
                .takeUntil(c -> c.equalsIgnoreCase("poland"))
                .subscribe(Util.subscriber());
    }

    private static void demo3() {
        var atomicInteger = new AtomicInteger(0);
        getCountryName()
                .repeat(() -> atomicInteger.incrementAndGet() < 3)
                .subscribe(Util.subscriber());
    }

    private static void demo4() {
        getCountryName()
                .repeatWhen(flux -> flux.delayElements(Duration.ofSeconds(2)).take(3))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getCountryName() {
        return Mono.fromSupplier(() -> Util.faker().country().name());
    }
}
