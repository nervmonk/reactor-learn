package com.reactorintroduction.sec04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec01.subscriber.SubscriberImpl;

import reactor.core.publisher.Flux;

public class Lec04FluxCreateDownstreamDemand {
    private static final Logger log = LoggerFactory.getLogger(Lec04FluxCreateDownstreamDemand.class);

    public static void main(String[] args) {
        produceOnDemand();
    }

    private static void produceEarly() {
        var subscriber = new SubscriberImpl();
        Flux.<String>create(sink -> {
            for (int i = 0; i < 10; i++) {
                var name = Util.faker().name().firstName();
                log.info("generated: {}", name);
                sink.next(name);
            }
            sink.complete();
        }).subscribe(subscriber);

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().cancel();
    }

    private static void produceOnDemand() {
        var subscriber = new SubscriberImpl();
        Flux.<String>create(sink -> {
            sink.onRequest(request -> {
                for (int i = 0; i < request && !sink.isCancelled(); i++) {
                    var name = Util.faker().name().firstName();
                    log.info("generated: {}", name);
                    sink.next(name);
                }
            });
        }).subscribe(subscriber);

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().cancel();
    }
}
