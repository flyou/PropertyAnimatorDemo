package com.flyou.propertyanimator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.flyou.propertyanimator.interpolator.InterpolatorActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @InjectView(R.id.basic)
    Button basic;
    @InjectView(R.id.valueAnimator)
    Button valueAnimator;
    @InjectView(R.id.interpolator)
    Button interpolator;
    @InjectView(R.id.evaluator)
    Button evaluator;
    @InjectView(R.id.animate)
    Button animate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proprety);
        ButterKnife.inject(this);


    }

    @OnClick({R.id.basic, R.id.valueAnimator, R.id.interpolator, R.id.evaluator, R.id.animate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.basic:
                Intent intent = new Intent(MainActivity.this, ObjectAnimatorActivity.class);
                startActivity(intent);
                break;
            case R.id.value_animator:

                Intent intent1 = new Intent(MainActivity.this, ValueAnimatorActivity.class);
                startActivity(intent1);
                break;
            case R.id.interpolator:
                Intent intent2 = new Intent(MainActivity.this, InterpolatorActivity.class);
                startActivity(intent2);
                break;
            case R.id.evaluator:
                Intent intent3 = new Intent(MainActivity.this, EvaluatorActivity.class);
                startActivity(intent3);
                break;

            case R.id.animate:
                Intent intent4= new Intent(MainActivity.this, AnimActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
