package com.reactorintroduction.sec09;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;

public class Lec01StartsWith {
    private static final Logger log = LoggerFactory.getLogger(Lec01StartsWith.class);

    public static void main(String[] args) {

        demo4();
        Util.sleepSeconds(3);

    }

    private static void demo1() {
        producer1()
                .startWith(-1, 0)
                .take(2)
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        producer1()
                .startWith(-2, -1, 0)
                .subscribe(Util.subscriber());
    }

    private static void demo3() {
        producer1()
                .startWith(producer2())
                .subscribe(Util.subscriber());
    }

    private static void demo4() {
        producer1()
                .startWith(0)
                .startWith(producer2())
                .startWith(49, 50)
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
}
