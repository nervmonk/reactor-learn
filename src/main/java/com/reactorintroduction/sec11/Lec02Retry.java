package com.reactorintroduction.sec11;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

public class Lec02Retry {
    private static final Logger log = LoggerFactory.getLogger(Lec02Retry.class);

    public static void main(String[] args) {
        demo2();

        Util.sleepSeconds(5);
    }

    private static void demo1() {
        getCountryName()
                .retry(2)
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        getCountryName()
                .retryWhen(
                        Retry.fixedDelay(2, Duration.ofSeconds(1))
                                .filter(exc -> RuntimeException.class.equals(exc.getClass())))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getCountryName() {
        var atomicInteger = new AtomicInteger(0);
        return Mono.fromSupplier(() -> {
            if (atomicInteger.incrementAndGet() < 3) {
                throw new RuntimeException("oops");
            }
            return Util.faker().country().name();
        })
                .doOnError(err -> log.info("ERROR: {}", err.getMessage()))
                .doOnSubscribe(s -> log.info("subscribing"));
    }
}
