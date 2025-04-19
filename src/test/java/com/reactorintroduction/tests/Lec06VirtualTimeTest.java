package com.reactorintroduction.tests;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class Lec06VirtualTimeTest {
    private Flux<Integer> getItems() {
        return Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(10));
    }

    // @Test we can't run test like this
    void demo() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify();
    }

    @Test
    void virtualTimeTest1() {
        StepVerifier.withVirtualTime(this::getItems)
                .thenAwait(Duration.ofSeconds(51))
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify();
    }

    @Test
    void virtualTimeTest2() {
        StepVerifier.withVirtualTime(this::getItems)
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(9))
                .thenAwait(Duration.ofSeconds(1))
                .expectNext(1)
                .thenAwait(Duration.ofSeconds(40))
                .expectNext(2, 3, 4, 5)
                .expectComplete()
                .verify();
    }

}
