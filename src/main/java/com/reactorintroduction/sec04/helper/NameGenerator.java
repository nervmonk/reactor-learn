package com.reactorintroduction.sec04.helper;

import java.util.function.Consumer;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.FluxSink;

public class NameGenerator implements Consumer<FluxSink<String>> {

    private FluxSink<String> sink;

    @Override
    public void accept(FluxSink<String> t) {
        this.sink = t;
    }

    public void generate() {
        this.sink.next(Util.faker().name().firstName());
    }

}
