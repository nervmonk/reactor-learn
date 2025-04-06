package com.reactorintroduction.sec04;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;

public class Lec01FluxCreate {

    public static void main(String[] args) {
        Flux.create(sink -> {
            // for (int i = 0; i < 10; i++) {
            // sink.next(Util.faker().country().name());
            // }
            String country = "";
            while (!country.equalsIgnoreCase("italy")) {
                country = Util.faker().country().name();
                sink.next(country);
            }
            sink.complete();
        }).subscribe(Util.subscriber());
    }
}
