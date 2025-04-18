package com.reactorintroduction.sec09;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec09.applications.OrderService;
import com.reactorintroduction.sec09.applications.UserService;

public class Lec10MonoFlatMapMany {
    public static void main(String[] args) {
        UserService.getUserId("Jake")
                .flatMapMany(OrderService::getUserOrder)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }
}
