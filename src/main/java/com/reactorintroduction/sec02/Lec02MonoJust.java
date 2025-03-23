package com.reactorintroduction.sec02;

import com.reactorintroduction.sec01.subscriber.SubscriberImpl;

import reactor.core.publisher.Mono;

public class Lec02MonoJust {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Ryan");
        var subscriber = new SubscriberImpl();
        mono.subscribe(subscriber);

        subscriber.getSubscription().request(10);
        subscriber.getSubscription().request(10);
        subscriber.getSubscription().cancel();
    }
}
