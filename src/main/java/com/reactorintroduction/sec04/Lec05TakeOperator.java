package com.reactorintroduction.sec04;

import java.util.stream.IntStream;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;

public class Lec05TakeOperator {
    public static void main(String[] args) {
        takeUntil();
    }

    private static void take() {
        Flux.range(1, 10)
                .log("take")
                .take(3)
                .log("sub")
                .subscribe(Util.subscriber());
    }

    private static void takeWhile() {
        Flux.range(1, 10)
                .log("take")
                .takeWhile(i -> i < 5) // stop when condition is not met
                .log("sub")
                .subscribe(Util.subscriber());
    }

    private static void takeUntil() {
        Flux.range(1, 10)
                .log("take")
                .takeUntil(i -> i < 3) // stop when condition is met
                .log("sub")
                .subscribe(Util.subscriber());
    }
}
