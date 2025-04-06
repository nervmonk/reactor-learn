package com.reactorintroduction.sec05;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Mono;

public class Lec07DefaultIfEmpty {
    public static void main(String[] args) {
        Mono.just("Hello")
                .defaultIfEmpty("fallback")
                .subscribe(Util.subscriber());
    }
}
