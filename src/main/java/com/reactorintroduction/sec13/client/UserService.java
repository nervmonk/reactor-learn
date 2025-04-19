package com.reactorintroduction.sec13.client;

import java.util.Map;
import java.util.function.Function;

import reactor.util.context.Context;

public class UserService {
    private static final Map<String, String> USER_CATEGORY = Map.of(
            "Ryan", "prime",
            "Vins", "standard");

    static Function<Context, Context> userCategoryContext() {
        return ctx -> ctx.getOrEmpty("user")
                .filter(USER_CATEGORY::containsKey)
                .map(USER_CATEGORY::get)
                .map(category -> ctx.put("category", category))
                .orElse(Context.empty());
    }
}
