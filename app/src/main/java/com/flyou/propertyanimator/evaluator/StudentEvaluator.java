package com.flyou.propertyanimator.evaluator;

import android.animation.TypeEvaluator;

/**
 * Created by flyou on 2017/3/1.
 * VersionCode: 1
 * Desc:
 */

public class StudentEvaluator implements TypeEvaluator<Student> {

    @Override
    public Student evaluate(float fraction, Student startValue, Student endValue) {
        int startAge = startValue.getAge();
        int startHeight = startValue.getHeight();
        int endAge = endValue.getAge();
        int endHeight = endValue.getHeight();

        int currentAge = (int) (startAge + fraction * (endAge - startAge));

        int currentHeight = (int) (startHeight + fraction * (endHeight - startHeight));

        return new Student(currentAge, currentHeight);
    }
}
