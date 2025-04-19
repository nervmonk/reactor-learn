package com.reactorintroduction.sec13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lec01Context {
    private static final Logger log = LoggerFactory.getLogger(Lec01Context.class);

    public static void main(String[] args) {
        getWelcomeMessage()
                .contextWrite(Context.of("a", "b"))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
            if (ctx.hasKey("user")) {
                return Mono.just("Welcome %s".formatted(ctx.get("user").toString()));
            }
            return Mono.error(new RuntimeException("Unauthenticated"));
        });
    }
}
