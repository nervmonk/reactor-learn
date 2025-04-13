package com.reactorintroduction.sec09;

import com.reactorintroduction.common.Util;
import com.reactorintroduction.sec09.helper.NameGenerator;

public class Lec02StartsWithUseCase {
    public static void main(String[] args) {
        var nameGenerator = new NameGenerator();
        nameGenerator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("Ryan"));

        nameGenerator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("Jake"));

        nameGenerator.generateNames()
                .take(3)
                .subscribe(Util.subscriber("Adam"));
    }
}
