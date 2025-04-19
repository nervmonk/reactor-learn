package com.reactorintroduction.tests;

import java.util.function.UnaryOperator;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

class Lec09PublisherTest {
    private UnaryOperator<Flux<String>> processor() {
        return flux -> flux
                .filter(s -> s.length() > 1)
                .map(String::toUpperCase)
                .map(s -> s + ":" + s.length());
    }

    @Test
    void publisherTest1() {
        var publisher = TestPublisher.<String>create();
        var flux = publisher.flux();

        StepVerifier.create(flux.transform(processor()))
                .then(() -> publisher.emit("hi", "hello"))
                .expectNext("HI:2")
                .expectNext("HELLO:5")
                .expectComplete()
                .verify();
    }

    @Test
    void publisherTest2() {
        var publisher = TestPublisher.<String>create();
        var flux = publisher.flux();

        StepVerifier.create(flux.transform(processor()))
                .then(() -> publisher.emit("a", "b", "c"))
                .expectComplete()
                .verify();
    }
}
