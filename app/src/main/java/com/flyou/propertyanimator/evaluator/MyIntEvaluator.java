package com.flyou.propertyanimator.evaluator;

import android.animation.TypeEvaluator;

/**
 * Created by flyou on 2017/2/28.
 * VersionCode: 1
 * Desc:
 */

public class MyIntEvaluator implements TypeEvaluator<Integer> {
    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        int startInt = startValue;
        return (int)(startInt + fraction * (endValue - startInt))*5;
    }
}
