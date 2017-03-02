package com.flyou.propertyanimator;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.flyou.propertyanimator.evaluator.Student;
import com.flyou.propertyanimator.evaluator.StudentEvaluator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class EvaluatorActivity extends AppCompatActivity {

    @InjectView(R.id.info)
    TextView info;
    @InjectView(R.id.grow)
    Button grow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluator);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.grow)
    public void onClick() {
        ValueAnimator valueAnimator=new ValueAnimator().ofObject(new StudentEvaluator(),new Student(0,40),new Student(22,188));
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Student student = (Student) animation.getAnimatedValue();
                info.setText("学生年龄:"+student.getAge()+"岁，学生身高:"+student.getHeight()+"cm");
            }
        });
    valueAnimator.start();
    }
}
