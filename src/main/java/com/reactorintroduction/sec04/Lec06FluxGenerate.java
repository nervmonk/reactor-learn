package com.reactorintroduction.sec04;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;

public class Lec06FluxGenerate {
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> {
            synchronousSink.next(1);
            // synchronousSink.next(2);
            // synchronousSink.complete();
        }).subscribe(Util.subscriber());
    }
}
