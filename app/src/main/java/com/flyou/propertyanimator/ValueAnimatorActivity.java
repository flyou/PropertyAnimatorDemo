package com.flyou.propertyanimator;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ValueAnimatorActivity extends AppCompatActivity {

    @InjectView(R.id.value_animator)
    Button valueAnimator;
    @InjectView(R.id.imageView)
    ImageView imageView;
    @InjectView(R.id.text)
    TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator);
        ButterKnife.inject(this);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        text.setText("imageView X:" + imageView.getY());
    }

    @OnClick(R.id.value_animator)
    public void onClick() {
        ValueAnimator valueAnimator = new ValueAnimator().ofFloat(0, 500);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                imageView.setTranslationY(animatedValue);
                text.setText("imageView X:" + imageView.getY());
            }
        });
        valueAnimator.start();


    }
}
