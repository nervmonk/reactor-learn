package com.reactorintroduction.tests;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class Lec09TimeoutTest {
    private Flux<Integer> getItems() {
        return Flux.range(1, 5)
                .delayElements(Duration.ofMillis(200));
    }

    @Test
    void timeoutTest() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify(Duration.ofMillis(1100));
    }
}
