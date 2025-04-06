package com.reactorintroduction.sec04;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;

public class Lec07FluxGenerateUntil {
    public static void main(String[] args) {
        assignment();
    }

    private static void demo1() {
        Flux.generate(synchronousSink -> {
            var country = Util.faker().country().name();
            synchronousSink.next(country);
            if (country.equalsIgnoreCase("canada")) {
                synchronousSink.complete();
            }
        }).subscribe(Util.subscriber());
    }

    private static void demo2() {
        Flux.<String>generate(synchronousSink -> {
            var country = Util.faker().country().name();
            synchronousSink.next(country);
        })
                .takeUntil(c -> c.equalsIgnoreCase("italy"))
                .subscribe(Util.subscriber());
    }

    private static void assignment() {
        Flux.<String>generate(synchronousSink -> {
            var country = Util.faker().country().name();
            synchronousSink.next(country);
        }).handle((item, sink) -> {
            if (item.equalsIgnoreCase("canada")) {
                sink.next(item);
                sink.complete();
            } else {
                sink.next(item);
            }
        })
                .subscribe(Util.subscriber());
    }

    private static void example() {
        Flux.range(1, 10)
                .filter(i -> i != 7)
                .handle((item, sink) -> {
                    switch (item) {
                        case 1 -> sink.next(-2);
                        case 4 -> {
                        }
                        case 7 -> sink.error(new RuntimeException("oops"));
                        default -> sink.next(item);
                    }
                })
                .cast(Integer.class)
                .subscribe(Util.subscriber());
    }
}
