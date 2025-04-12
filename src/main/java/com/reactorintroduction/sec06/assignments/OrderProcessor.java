package com.reactorintroduction.sec06.assignments;

import reactor.core.publisher.Flux;

public interface OrderProcessor {
    void consume(Order order);

    Flux<String> stream();
}
