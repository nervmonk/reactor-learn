package com.reactorintroduction.sec03;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;

public class Lec05FluxRange {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .subscribe(Util.subscriber());

        // generate 10 random first names
        Flux.range(1, 10)
                .map(i -> Util.faker().name().firstName())
                .subscribe(Util.subscriber());

    }
}
