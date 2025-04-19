package com.reactorintroduction.sec12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Sinks;

public class Lec01SinkOne {
    private static final Logger log = LoggerFactory.getLogger(Lec01SinkOne.class);

    public static void main(String[] args) {
        demo3();
    }

    private static void demo1() {
        var sink = Sinks.one();
        var mono = sink.asMono();
        mono.subscribe(Util.subscriber());
        // sink.tryEmitValue("Hi");
        // sink.tryEmitEmpty();
        sink.tryEmitError(new RuntimeException("oops"));
    }

    private static void demo2() {
        var sink = Sinks.one();
        var mono = sink.asMono();
        mono.subscribe(Util.subscriber("Ryan"));
        mono.subscribe(Util.subscriber("Vins"));
        sink.tryEmitValue("Hi");
    }

    private static void demo3() {
        var sink = Sinks.one();
        var mono = sink.asMono();
        mono.subscribe(Util.subscriber("Ryan"));
        sink.emitValue("Hi", ((signalType, emitResult) -> {
            log.info("Hi");
            log.info(signalType.name());
            log.info(emitResult.name());
            return false;
        }));

        sink.emitValue("Hello", ((signalType, emitResult) -> {
            log.info("Hello");
            log.info(signalType.name());
            log.info(emitResult.name());
            return false;
        }));
    }
}
