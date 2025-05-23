package com.reactorintroduction.sec10;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec10.assignment.BookOrder;
import com.reactorintroduction.sec10.assignment.RevenueReport;

import reactor.core.publisher.Flux;

public class Lec02BufferAssignment {
    public static void main(String[] args) {
        var allowedCategories = Set.of(
                "Science fiction",
                "Fantasy",
                "Suspense/Thriller");

        orderStream()
                .filter(o -> allowedCategories.contains(o.genre()))
                .buffer(Duration.ofSeconds(5))
                .map(Lec02BufferAssignment::generateReport)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static Flux<BookOrder> orderStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> BookOrder.create());
    }

    private static RevenueReport generateReport(List<BookOrder> orders) {
        var revenue = orders.stream()
                .collect(Collectors.groupingBy(
                        BookOrder::genre, Collectors.summingInt(BookOrder::price)));

        return new RevenueReport(LocalTime.now(), revenue);
    }
}
