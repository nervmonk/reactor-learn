package com.reactorintroduction.sec09;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec09.applications.OrderService;
import com.reactorintroduction.sec09.applications.User;
import com.reactorintroduction.sec09.applications.UserService;

public class Lec11FluxFlatMap {
    public static void main(String[] args) {
        UserService.getAllUsers()
                .map(User::id)
                .flatMap(OrderService::getUserOrder)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }
}
