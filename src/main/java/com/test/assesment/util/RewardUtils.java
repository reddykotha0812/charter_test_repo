package com.test.assesment.util;

import com.test.assesment.entity.CustomerTransaction;

public class RewardUtils {
    public static Integer calculateRewardPoint(Long amount) {
        int rewardPoint = 0 ;
        if (amount > 100) {
            rewardPoint = (int) ((((amount - 100) * 2) + 50));
        } else if (amount > 50 && amount < 100) {
            rewardPoint = (int) (amount - 50);
        }
        return rewardPoint;
    }
}
