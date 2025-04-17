package com.reactorintroduction.sec09;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec09.applications.PaymentService;
import com.reactorintroduction.sec09.applications.UserService;

import reactor.core.publisher.Mono;

public class Lec09MonoFlatMap {
    public static void main(String[] args) {
        UserService.getUserId("Sam")
        .flatMap(PaymentService::getUserBalance)
        .subscribe(Util.subscriber());
    }
}
