package com.reactorintroduction.common;

import java.time.Duration;
import java.util.function.UnaryOperator;

import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Util {
    private static final Faker faker = Faker.instance();

    private static final Logger log = LoggerFactory.getLogger(Util.class);

    public static <T> Subscriber<T> subscriber() {
        return new DefaultSubscriber<>("");
    }

    public static <T> Subscriber<T> subscriber(String name) {
        return new DefaultSubscriber<>(name);
    }

    public static void main(String[] args) {
        var mono = Mono.just(1);

        mono.subscribe(subscriber("sub1"));
        mono.subscribe(subscriber("sub2"));
    }

    public static Faker faker() {
        return faker;
    }

    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleep(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> UnaryOperator<Flux<T>> fluxLogger(String name) {
        return flux -> flux
                .doOnSubscribe(s -> log.info("subscribing to {}", name))
                .doOnCancel(() -> log.info("cancelling {}", name))
                .doOnComplete(() -> log.info("{} completed", name));
    }
}
