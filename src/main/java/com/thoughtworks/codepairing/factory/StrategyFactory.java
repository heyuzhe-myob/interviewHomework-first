package com.thoughtworks.codepairing.factory;

import com.thoughtworks.codepairing.impl.BuyTwoGetOne;
import com.thoughtworks.codepairing.impl.DisFifteenPercent;
import com.thoughtworks.codepairing.impl.DisTenPercent;
import com.thoughtworks.codepairing.impl.DisTwentyPercent;
import com.thoughtworks.codepairing.impl.NoDiscount;
import com.thoughtworks.codepairing.repository.DiscountStrategy;

public class StrategyFactory {

    public StrategyFactory() {
    }

    public static DiscountStrategy getStrategy(String productCode) {
        if (productCode.startsWith("DIS_10")) {
            return new DisTenPercent();
        }
        if (productCode.startsWith("DIS_15")) {
            return new DisFifteenPercent();
        }
        if (productCode.startsWith("DIS_20")) {
            return new DisTwentyPercent();
        }
        if (productCode.startsWith("BULK_BUY_2_GET_1")){
            return new BuyTwoGetOne();
        }
        return new NoDiscount();
    }
}
