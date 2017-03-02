package com.flyou.propertyanimator.interpolator;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.flyou.propertyanimator.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class InterpolatorActivity extends AppCompatActivity {

    @InjectView(R.id.LinearInterpolator)
    Button LinearInterpolator;
    @InjectView(R.id.AccelerateDecelerateInterpolator)
    Button AccelerateDecelerateInterpolator;
    @InjectView(R.id.AccelerateInterpolator)
    Button AccelerateInterpolator;
    @InjectView(R.id.AnticipateInterpolator)
    Button AnticipateInterpolator;
    @InjectView(R.id.BounceInterpolator)
    Button BounceInterpolator;
    @InjectView(R.id.CycleInterpolator)
    Button CycleInterpolator;
    @InjectView(R.id.DecelerateInterpolator)
    Button DecelerateInterpolator;
    @InjectView(R.id.AnticipateOvershootInterpolator)
    Button AnticipateOvershootInterpolator;
    @InjectView(R.id.OvershootInterpolator)
    Button OvershootInterpolator;
    @InjectView(R.id.PathInterpolator)
    Button PathInterpolator;
    @InjectView(R.id.image)
    ImageView image;

    ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        objectAnimator = new ObjectAnimator().ofFloat(image, "translationY", 0, 1200);
        objectAnimator.setDuration(3000);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.LinearInterpolator, R.id.AccelerateDecelerateInterpolator,
            R.id.AccelerateInterpolator, R.id.AnticipateInterpolator, R.id.BounceInterpolator,
            R.id.CycleInterpolator, R.id.DecelerateInterpolator, R.id.AnticipateOvershootInterpolator,
            R.id.OvershootInterpolator, R.id.PathInterpolator})
    public void onClick(View view) {
        switch (view.getId()) {
            //匀速线性
            case R.id.LinearInterpolator:
                objectAnimator.setInterpolator(new LinearInterpolator());
                break;
            //先加速后减速
            case R.id.AccelerateDecelerateInterpolator:
                objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                break;
            //一直加速
            case R.id.AccelerateInterpolator:
                objectAnimator.setInterpolator(new AccelerateInterpolator());
                break;
            //反向移动然后正向加速
            case R.id.AnticipateInterpolator:
                objectAnimator.setInterpolator(new AnticipateInterpolator());
                break;
            //加速下落回弹
            case R.id.BounceInterpolator:
                objectAnimator.setInterpolator(new BounceInterpolator());
                break;
            //循环播放 参数指定循环次数
            case R.id.CycleInterpolator:
                objectAnimator.setInterpolator(new CycleInterpolator(2f));
                break;
            //减速效果
            case R.id.DecelerateInterpolator:
                objectAnimator.setInterpolator(new DecelerateInterpolator());
                break;
            //反向超过原来位置 然后正向加速超过规定位置 返回
            case R.id.AnticipateOvershootInterpolator:
                objectAnimator.setInterpolator(new AnticipateOvershootInterpolator());
                break;
            //向前甩一定值后再回到原来位置  可以传值指定加速度值
            case R.id.OvershootInterpolator:
                objectAnimator.setInterpolator(new OvershootInterpolator());
                break;
            case R.id.PathInterpolator:
                Intent intent=new Intent(InterpolatorActivity.this,InterpolatorPathActivity.class);
                startActivity(intent);
                break;

        }
        objectAnimator.start();
    }

}
