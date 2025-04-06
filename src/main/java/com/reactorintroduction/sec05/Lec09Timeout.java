package com.reactorintroduction.sec05;

import java.time.Duration;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Mono;

public class Lec09Timeout {
    public static void main(String[] args) {
        getProductName()
                .timeout(Duration.ofSeconds(1), fallback())
                .onErrorReturn("fallback")
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }

    private static Mono<String> getProductName() {
        return Mono.fromSupplier(() -> "service- " + Util.faker().commerce().productName())
                .delayElement(Duration.ofMillis(1500));
    }

    private static Mono<String> fallback() {
        return Mono.fromSupplier(() -> "fallback- " + Util.faker().commerce().productName())
                .delayElement(Duration.ofMillis(1800));
    }
}
