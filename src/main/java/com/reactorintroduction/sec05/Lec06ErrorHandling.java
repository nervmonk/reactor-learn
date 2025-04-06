package com.reactorintroduction.sec05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06ErrorHandling {
    private static final Logger log = LoggerFactory.getLogger(Lec06ErrorHandling.class);

    public static void main(String[] args) {
        Mono.error(new ArithmeticException("oops"))
                .onErrorResume(ArithmeticException.class, ex -> fallback1())
                .onErrorResume(err -> fallback2())
                // .onErrorReturn(-1)
                // .onErrorReturn(ArithmeticException.class, 2025)
                .subscribe(Util.subscriber());
    }

    private static void onErrorreturn() {
        Mono.just(5)
                .map(i -> i == 5 ? 5 / 0 : i)
                .onErrorReturn(IllegalArgumentException.class, 2020)
                .onErrorReturn(ArithmeticException.class, 2025)
                .onErrorReturn(-1)
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallback1() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(10, 100));
    }

    private static Mono<Integer> fallback2() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 200));
    }
}
