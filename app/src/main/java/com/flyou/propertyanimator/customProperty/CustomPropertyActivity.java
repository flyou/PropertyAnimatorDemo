package com.flyou.propertyanimator.customProperty;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.flyou.propertyanimator.R;


import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CustomPropertyActivity extends AppCompatActivity {

    @InjectView(R.id.play)
    Button play;


    private int buttonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curtomprierty);
        ButterKnife.inject(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        buttonWidth=play.getWidth();
    }

    @OnClick(R.id.play)
    public void onClick() {
        ViewWrapper viewWrapper = new ViewWrapper(play);
        ObjectAnimator objectAnimator = new ObjectAnimator().ofInt(viewWrapper, "width",buttonWidth,800);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
    }
    private static class ViewWrapper {
        private View mTarget;

        public ViewWrapper(View target) {
            mTarget = target;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }
    }
}
