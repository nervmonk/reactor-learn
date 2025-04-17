package com.reactorintroduction.sec09.applications;

import java.util.Map;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserService {
    private static final Map<String, Integer> userTable = Map.of(
            "Sam", 1,
            "Mike", 2,
            "Jake", 3);

    public static Flux<User> getAllUsers() {
        return Flux.fromIterable(userTable.entrySet())
                .map(entry -> new User(entry.getValue(), entry.getKey()));
    }

    public static Mono<Integer> getUserId(String username){
        return Mono.fromSupplier(() -> userTable.get(username));
    }
}
