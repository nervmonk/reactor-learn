package com.reactorintroduction.sec06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;

public class Lec01ColdPublisher {
    private static final Logger logger = LoggerFactory.getLogger(Lec01ColdPublisher.class);

    public static void main(String[] args) {
        var flux = Flux.create(fluxSink -> {
            logger.info("Invoked");
            for (int i = 0; i < 3; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
        });

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));
    }
}
