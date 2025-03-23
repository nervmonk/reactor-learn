package com.reactorintroduction.sec03;

import java.util.List;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Flux;

public class Lec04FluxFromStream {

    public static void main(String[] args) {
        var list = List.of(1, 2, 3, 4);

        var flux = Flux.fromStream(list::stream);
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));
    }
}
