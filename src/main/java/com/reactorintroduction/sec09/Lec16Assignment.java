package com.reactorintroduction.sec09;

import java.util.List;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec09.applications.Order;
import com.reactorintroduction.sec09.applications.OrderService;
import com.reactorintroduction.sec09.applications.PaymentService;
import com.reactorintroduction.sec09.applications.User;
import com.reactorintroduction.sec09.applications.UserService;

import reactor.core.publisher.Mono;

public class Lec16Assignment {
    record UserInformation(Integer userId, String username, Integer balance, List<Order> orders) {
    }

    public static void main(String[] args) {
        UserService.getAllUsers()
                .flatMap(Lec16Assignment::getUserInformation)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }

    private static Mono<UserInformation> getUserInformation(User user) {
        return Mono.zip(PaymentService.getUserBalance(user.id()), OrderService.getUserOrder(user.id()).collectList())
                .map(t -> new UserInformation(user.id(), user.name(), t.getT1(), t.getT2()));
    }
}
