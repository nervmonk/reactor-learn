package com.reactorintroduction.sec02;

import com.reactorintroduction.common.Util;

import reactor.core.publisher.Mono;

public class Lec04MonoEmptyError {
    public static void main(String[] args) {
        getUsername(7).subscribe(s -> System.out.println(s));
    }

    private static Mono<String> getUsername(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("invalid input"));
        };
    }
}
