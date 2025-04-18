package com.reactorintroduction.sec10;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

public class Lec05GroupedFlux {
    private static final Logger log = LoggerFactory.getLogger(Lec05GroupedFlux.class);

    public static void main(String[] args) {
        Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(1))
                .map(i -> i * 2)
                .startWith(1)
                .groupBy(i -> i % 2)
                .flatMap(Lec05GroupedFlux::processEvents)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static Mono<Void> processEvents(GroupedFlux<Integer, Integer> groupedFlux) {
        log.info("received flux for {}", groupedFlux.key());
        return groupedFlux.doOnNext(i -> log.info("key: {}, item: {}", groupedFlux.key(), i))
                .doOnComplete(() -> log.info("{} completed", groupedFlux.key()))
                .then();
    }
}
