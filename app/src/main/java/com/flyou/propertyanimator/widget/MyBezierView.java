package com.flyou.propertyanimator.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.flyou.propertyanimator.utils.CanvasAidUtils;
import com.flyou.propertyanimator.utils.DensityUtils;

/**
 * Created by fzl on 2017/3/2.
 * VersionCode: 1
 * Desc:
 */

public class MyBezierView extends View {
    private static final String TAG = "BezierView";
    private Paint mPaint;
    private Path mPath;
    private Point startPoint;
    private Point endPoint;
    //坐标点Paint
    private Paint mTextPaint;
    // 控制点
    private Point assistPoint;

    public MyBezierView(Context context) {
        this(context, null);
    }

    public MyBezierView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyBezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mTextPaint = new Paint();
        mPaint = new Paint();
        mPath = new Path();
        startPoint = new Point(200, 200);
        endPoint = new Point(800, 800);
        assistPoint = new Point(800, 200);
        // 抗锯齿
        mPaint.setAntiAlias(true);
        // 防抖动
        mPaint.setDither(true);
        //坐标
        mTextPaint.setColor(Color.RED);
        mTextPaint.setTextSize(20);
        mTextPaint.setStrokeWidth(10);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        CanvasAidUtils.setLineColor(Color.BLACK);
        CanvasAidUtils.setLineWidth(10);
        CanvasAidUtils.set2DAxisLength(DensityUtils.dip2px(getContext(),360), DensityUtils.dip2px(getContext(),360));
        CanvasAidUtils.draw2DCoordinateSpace(canvas);



        canvas.drawText("X:" + assistPoint.x + "Y:" + assistPoint.y, assistPoint.x-60 , assistPoint.y-20, mTextPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);

        //画贝塞尔控制连线
        mPath.moveTo(200,200);
        mPath.lineTo(assistPoint.x,assistPoint.y);
        mPath.lineTo(800,800);
        canvas.drawPath(mPath, mPaint);

        // 重置路径
        mPath.reset();

        mPath.moveTo(startPoint.x, startPoint.y);

        mPath.quadTo(assistPoint.x, assistPoint.y, endPoint.x, endPoint.y);

        canvas.drawPath(mPath, mPaint);
        // 画辅助点
        canvas.drawPoint(assistPoint.x, assistPoint.y, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                assistPoint.x = (int) event.getX();
                assistPoint.y = (int) event.getY();
                invalidate();
                break;
        }
        return true;
    }
}
