package com.reactorintroduction.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec02EmptyErrorTest {

    Mono<String> getUsername(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("invalid input"));
        };
    }

    @Test
    void userTest() {
        StepVerifier.create(getUsername(1))
                .expectNext("sam")
                .expectComplete()
                .verify();
    }

    @Test
    void emptyTest() {
        StepVerifier.create(getUsername(2))
                .expectComplete()
                .verify();
    }

    @Test
    void errorTest1() {
        StepVerifier.create(getUsername(3))
                .expectError()
                .verify();
    }

    @Test
    void errorTest2() {
        StepVerifier.create(getUsername(3))
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    void errorTest3() {
        StepVerifier.create(getUsername(3))
                .expectErrorMessage("invalid input")
                .verify();
    }

    @Test
    void errorTest4() {
        StepVerifier.create(getUsername(3))
                .consumeErrorWith(ex -> {
                    Assertions.assertEquals(RuntimeException.class, ex.getClass());
                    Assertions.assertEquals("invalid input", ex.getMessage());
                })
                .verify();
    }
}
