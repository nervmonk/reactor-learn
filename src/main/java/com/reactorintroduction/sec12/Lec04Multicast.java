package com.reactorintroduction.sec12;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Sinks;

public class Lec04Multicast {

    public static void main(String[] args) {
        demo2();
    }

    private static void demo1() {
        // handle through which we would push items
        // onBackPressureBuffer - bounded queue
        var sink = Sinks.many().multicast().onBackpressureBuffer();

        // handle through which subscribers will receive items
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("Ryan"));
        flux.subscribe(Util.subscriber("Vins"));

        sink.tryEmitNext("Hi");
        sink.tryEmitNext("How are you?");
        sink.tryEmitNext("Quite well, thank you very much");

        Util.sleepSeconds(2);

        flux.subscribe(Util.subscriber("Jake"));
        sink.tryEmitNext("Grab a beer?");
    }

    // warm up behavior
    private static void demo2() {
        // handle through which we would push items
        // onBackPressureBuffer - bounded queue
        var sink = Sinks.many().multicast().onBackpressureBuffer();

        // handle through which subscribers will receive items
        var flux = sink.asFlux();

        sink.tryEmitNext("Hi");
        sink.tryEmitNext("How are you?");
        sink.tryEmitNext("Quite well, thank you very much");

        Util.sleepSeconds(2);

        flux.subscribe(Util.subscriber("Ryan"));
        flux.subscribe(Util.subscriber("Vins"));
        flux.subscribe(Util.subscriber("Jake"));
        sink.tryEmitNext("Grab a beer?");
    }
}
