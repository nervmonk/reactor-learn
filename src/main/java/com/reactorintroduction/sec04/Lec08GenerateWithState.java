package com.reactorintroduction.sec04;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;

public class Lec08GenerateWithState {
    public static void main(String[] args) {
        Flux.generate(() -> 0, (counter, sink) -> {
            var country = Util.faker().backToTheFuture().character();
            sink.next(country);
            counter++;
            if (counter == 10 || country.equalsIgnoreCase("indonesia")) {
                sink.complete();
            }
            return counter;
        })
                .subscribe(Util.subscriber());
    }
}
