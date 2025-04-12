package com.reactorintroduction.sec06;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;

public class Lec04HotPublisherCache {
    private static final Logger log = LoggerFactory.getLogger(Lec04HotPublisherCache.class);

    public static void main(String[] args) {
        var stockFlux = stockStream().replay(1).autoConnect(0);

        Util.sleepSeconds(4);

        log.info("Sam joining");

        stockFlux.subscribe(Util.subscriber("Sam"));

        Util.sleepSeconds(3);

        log.info("Mike joining");

        stockFlux.subscribe(Util.subscriber("Mike"));

        Util.sleepSeconds(15);
    }

    private static Flux<Integer> stockStream() {
        return Flux.generate(sink -> sink.next(Util.faker().random().nextInt(10, 100)))
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price -> log.info("Emitting price: {}", price))
                .cast(Integer.class);
    }
}
