package com.reactorintroduction.sec10;

import java.time.Duration;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec03Window {

    public static void main(String[] args) {
        eventStream()
                .window(Duration.ofMillis(1800))
                .flatMap(Lec03Window::processEvents)
                .subscribe();

        Util.sleepSeconds(60);

    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> "event-" + (i + 1));
    }

    private static Mono<Void> processEvents(Flux<String> events) {
        return events.doOnNext(e -> System.out.print("*"))
                .doOnComplete(System.out::println)
                .then();
    }
}
