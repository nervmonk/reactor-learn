package com.reactorintroduction.sec07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec05PublishOn {
    private static final Logger log = LoggerFactory.getLogger(Lec05PublishOn.class);

    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(fluxSink -> {
            for (int i = 0; i < 5; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
        })
                .subscribeOn(Schedulers.boundedElastic());

        flux.subscribeOn(Schedulers.parallel())
                .map(i -> i + "a")
                .subscribe(Util.subscriber());

        Util.sleepSeconds(20);
    }
}
