package com.reactorintroduction.sec12;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Sinks;

public class Lec06MulticastDirectAllOrNothing {
    private static final Logger log = LoggerFactory.getLogger(Lec06MulticastDirectAllOrNothing.class);

    public static void main(String[] args) {
        demo1();

        Util.sleepSeconds(10);
    }

    private static void demo1() {

        log.info("Available processors {}", Runtime.getRuntime().availableProcessors());

        System.setProperty("reactor.bufferSize.small", "16");
        // handle through which we would push items
        // onBackPressureBuffer - bounded queue
        var sink = Sinks.many().multicast().directAllOrNothing();

        // handle through which subscribers will receive items
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("Ryan"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("Vins"));

        for (int i = 0; i <= 100; i++) {
            var result = sink.tryEmitNext(i);
            log.info("item: {}, result: {}", i, result);
        }
    }
}
