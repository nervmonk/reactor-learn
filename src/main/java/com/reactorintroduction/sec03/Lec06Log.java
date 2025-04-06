package com.reactorintroduction.sec03;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;

public class Lec06Log {
    public static void main(String[] args) {
        Flux.range(1, 5)
                .log("my-log")
                .subscribe(Util.subscriber());
    }
}
