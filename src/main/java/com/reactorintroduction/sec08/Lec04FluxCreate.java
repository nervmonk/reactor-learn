package com.reactorintroduction.sec08;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec04FluxCreate {
    private static final Logger log = LoggerFactory.getLogger(Lec04FluxCreate.class);

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");
        var producer = Flux.create(sink -> {
            for (int i = 0; i <= 500 && !sink.isCancelled(); i++) {
                log.info("generating {}", i);
                sink.next(i);
                Util.sleep(Duration.ofMillis(50));
            }
            sink.complete();
        })
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                .publishOn(Schedulers.boundedElastic())
                .map(Lec04FluxCreate::timeConsumingTask)
                .subscribe();

        Util.sleepSeconds(60);
    }

    private static int timeConsumingTask(int i) {
        log.info("received {}", i);
        Util.sleepSeconds(1);
        return i;
    }
}
