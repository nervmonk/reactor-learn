package com.reactorintroduction.sec02;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Mono;

public class Lec06MonoFromCallable {
    private static final Logger log = LoggerFactory.getLogger(Lec06MonoFromCallable.class);

    public static void main(String[] args) {
        var list = List.of(1, 2, 3);
        Mono.fromCallable(() -> sum(list)).subscribe(Util.subscriber());
    }

    private static int sum(List<Integer> list) {
        log.info("finding sum of {}", list);
        return list.stream().mapToInt(a -> a).sum();
    }

}
