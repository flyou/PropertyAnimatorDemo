package com.flyou.propertyanimator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.flyou.propertyanimator.customProperty.CustomPropertyActivity;

public class ObjectAnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private android.widget.Button Alpha;
    private android.widget.Button rotationX;
    private android.widget.Button rotationY;
    private android.widget.Button scaleX;
    private android.widget.Button scaleY;
    private android.widget.Button translateX;
    private android.widget.Button translateY;
    private android.widget.Button set;
    private android.widget.Button set_after;
    private android.widget.Button custom_property;
    private ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        this.image = (ImageView) findViewById(R.id.image);
        this.translateX = (Button) findViewById(R.id.translateX);
        this.translateY = (Button) findViewById(R.id.translateY);
        this.scaleX = (Button) findViewById(R.id.scaleX);
        this.scaleY = (Button) findViewById(R.id.scaleY);
        this.rotationX = (Button) findViewById(R.id.rotationX);
        this.rotationY = (Button) findViewById(R.id.rotationY);
        this.Alpha = (Button) findViewById(R.id.Alpha);
        this.set = (Button) findViewById(R.id.SET);
        this.set_after = (Button) findViewById(R.id.SET_AFTER);
        this.custom_property = (Button) findViewById(R.id.custom_property);

        image.setOnClickListener(this);
        Alpha.setOnClickListener(this);
        translateX.setOnClickListener(this);
        translateY.setOnClickListener(this);
        scaleX.setOnClickListener(this);
        scaleY.setOnClickListener(this);
        set.setOnClickListener(this);
        rotationX.setOnClickListener(this);
        rotationY.setOnClickListener(this);
        set_after.setOnClickListener(this);
        custom_property.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        ObjectAnimator objectAnimator;
        switch (view.getId()) {

            case R.id.scaleX:
                objectAnimator = new ObjectAnimator().ofFloat(image, "scaleX", 1f, 0f, 1f);
                objectAnimator.setDuration(2000);
                objectAnimator.start();
                break;
            case R.id.scaleY:
                objectAnimator = new ObjectAnimator().ofFloat(image, "scaleY", 1f, 0f, 1f);
                objectAnimator.setDuration(2000);
                objectAnimator.start();
                break;
            case R.id.rotationX:
                objectAnimator = new ObjectAnimator().ofFloat(image, "rotationX", 0, 360);
                objectAnimator.setDuration(2000);
                objectAnimator.start();
                break;
            case R.id.rotationY:
                objectAnimator = new ObjectAnimator().ofFloat(image, "rotationY", 0, 360);
                objectAnimator.setDuration(2000);
                objectAnimator.start();
                break;
            case R.id.Alpha:
                objectAnimator = new ObjectAnimator().ofFloat(image, "alpha", 1f, 0f, 1f);
                objectAnimator.setDuration(2000);
                objectAnimator.start();
                break;
            case R.id.translateX:
                objectAnimator = new ObjectAnimator().ofFloat(image, "translationX", 0, 300, 0);
                objectAnimator.setDuration(2000);
                objectAnimator.start();
                break;
            case R.id.translateY:
                objectAnimator = new ObjectAnimator().ofFloat(image, "translationY", 0, 300, 0);
                objectAnimator.setDuration(2000);
                objectAnimator.start();
                break;
            case R.id.SET:
                ObjectAnimator alpha = new ObjectAnimator().ofFloat(image, "alpha", 1f, 0, 1);
                ObjectAnimator translationX = new ObjectAnimator().ofFloat(image, "translationX", 0, 300, 0);
                ObjectAnimator rotationX = new ObjectAnimator().ofFloat(image, "rotationX", 0, 360);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(3000);
                animatorSet.playTogether(alpha, translationX, rotationX);
                animatorSet.start();
                break;
            case R.id.SET_AFTER:
                ObjectAnimator alpha1 = new ObjectAnimator().ofFloat(image, "alpha", 1f, 0.2f);
                ObjectAnimator alpha2 = new ObjectAnimator().ofFloat(image, "alpha", 0.2f, 1f);
                ObjectAnimator translationX1 = new ObjectAnimator().ofFloat(image, "translationX", 0, 300);
                ObjectAnimator translationX2 = new ObjectAnimator().ofFloat(image, "translationX", 300, 0);
                ObjectAnimator rotationX1 = new ObjectAnimator().ofFloat(image, "rotationX", 0, 360, 0);
                AnimatorSet animatorSet1 = new AnimatorSet();
                animatorSet1.setDuration(2000);
                animatorSet1.play(alpha1).before(translationX1);
                animatorSet1.play(rotationX1).after(alpha1);
                animatorSet1.play(rotationX1).before(translationX2);
                animatorSet1.play(translationX2).with(alpha2);
                animatorSet1.start();
                break;
            case R.id.custom_property:
                Intent intent=new Intent(ObjectAnimatorActivity.this, CustomPropertyActivity.class);
                startActivity(intent);


                break;

    }
}
}
