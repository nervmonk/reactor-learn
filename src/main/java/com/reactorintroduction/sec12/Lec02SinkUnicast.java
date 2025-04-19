package com.reactorintroduction.sec12;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Sinks;

public class Lec02SinkUnicast {

    public static void main(String[] args) {
        demo2();
    }

    private static void demo1() {
        // handle through which we would push items
        // onBackPressureBuffer - unbonded queue
        var sink = Sinks.many().unicast().onBackpressureBuffer();

        // handle through which subscribers will receive items
        var flux = sink.asFlux();

        sink.tryEmitNext("Hi");
        sink.tryEmitNext("How are you?");
        sink.tryEmitNext("Quite well, thank you very much");

        flux.subscribe(Util.subscriber("Ryan"));
    }

    private static void demo2() {
        // handle through which we would push items
        // onBackPressureBuffer - unbonded queue
        var sink = Sinks.many().unicast().onBackpressureBuffer();

        // handle through which subscribers will receive items
        var flux = sink.asFlux();

        sink.tryEmitNext("Hi");
        sink.tryEmitNext("How are you?");
        sink.tryEmitNext("Quite well, thank you very much");

        flux.subscribe(Util.subscriber("Ryan"));
        flux.subscribe(Util.subscriber("Rambe"));
    }
}
