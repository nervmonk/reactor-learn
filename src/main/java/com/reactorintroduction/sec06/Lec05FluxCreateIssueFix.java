package com.reactorintroduction.sec06;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec04.helper.NameGenerator;

import reactor.core.publisher.Flux;

public class Lec05FluxCreateIssueFix {
    public static void main(String[] args) {
        var generator = new NameGenerator();
        var flux = Flux.create(generator).share();
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));

        for (int i = 0; i < 10; i++) {
            generator.generate();
        }
    }
}
