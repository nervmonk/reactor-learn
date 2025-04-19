package com.reactorintroduction.tests;

import java.util.Objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class Lec05AssertNextTest {
    record Book(int id, String author, String title) {

    }

    private Flux<Book> getBooks() {
        return Flux.range(1, 3)
                .map(i -> new Book(i, Util.faker().book().author(), Util.faker().book().title()));
    }

    @Test
    void assertNextTest() {
        StepVerifier.create(getBooks())
                .assertNext(b -> Assertions.assertEquals(1, b.id()))
                .thenConsumeWhile(b -> Objects.nonNull(b.title()))
                .expectComplete()
                .verify();
    }

    @Test
    void collectAllAndTest() {
        StepVerifier.create(getBooks().collectList())
                .assertNext(list -> Assertions.assertEquals(3, list.size()))
                .expectComplete()
                .verify();
    }
}
