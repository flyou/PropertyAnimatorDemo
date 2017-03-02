package com.flyou.propertyanimator.interpolator;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.flyou.propertyanimator.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class InterpolatorPathActivity extends AppCompatActivity {

    @InjectView(R.id.go)
    Button go;
    @InjectView(R.id.image)
    ImageView image;
    float X;
    float Y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator_path);
        ButterKnife.inject(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        X = image.getX();
        Y = image.getY();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.go)
    public void onClick() {
        Path path = new Path();
        path.moveTo(X,Y);
        path.quadTo(800, Y, 800, 800);
        ObjectAnimator objectAnimator1 = new ObjectAnimator().ofFloat(image, "x", "y", path);
        objectAnimator1.setDuration(3000);
        objectAnimator1.setInterpolator(new PathInterpolator(0.9f,0.1f,0.5f,0.9f));

        objectAnimator1.start();
    }
}
