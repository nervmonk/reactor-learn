package com.reactorintroduction.sec09;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;

public class Lec04ConcatError {
    private static Logger log = LoggerFactory.getLogger(Lec04ConcatError.class);

    public static void main(String[] args) {

        demo2();
        Util.sleepSeconds(3);

    }

    private static void demo1() {
        producer1()
                .concatWith(producer3())
                .concatWith(producer2())
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        Flux.concatDelayError(producer1(), producer3(), producer2())
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3)
                .doOnSubscribe(s -> log.info("subscribing to producer1 {}", s))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2() {
        return Flux.just(51, 52, 53)
                .doOnSubscribe(s -> log.info("subscribing to producer2{}", s))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer3() {
        return Flux.error(new RuntimeException("oops"));
    }
}
