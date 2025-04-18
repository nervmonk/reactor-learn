package com.reactorintroduction.sec09;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;

public class Lec14CollectList {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .collectList()
                .subscribe(Util.subscriber());
    }
}
