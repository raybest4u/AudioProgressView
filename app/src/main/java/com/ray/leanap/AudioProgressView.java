package com.ray.leanap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.TintTypedArray;

import static java.lang.Math.PI;

public class AudioProgressView extends View {
    private static  int LUMP_COUNT = 180;
    private static final int LUMP_WIDTH = 4;
    private static final int LUMP_SPACE = 1;
    private static  float LUMP_MAX_HEIGHT = 50;//TODO: HEIGHT
    private static final int LUMP_SIZE = LUMP_WIDTH + LUMP_SPACE;

    private static  int LUMP_COLOR =Color.parseColor("#a8a8a8");
    private static  int LUMP_COLOR_PROGRESS = Color.parseColor("#c0ffbd21");
    private Paint lumpPaint;
    private float process = 66;
    private final List<Float> position = new ArrayList<>();
    public AudioProgressView(Context context) {
        super(context);
        init();
    }

    public AudioProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AudioProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    @SuppressLint("RestrictedApi")
    public AudioProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (attrs != null) {
             TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs, R.styleable.AudioProgressView, defStyleAttr, 0);
            int n = a.getIndexCount();
            for (int i = 0; i < n; i++) {
                int attr = a.getIndex(i);
                switch (attr) {
                    case R.styleable.AudioProgressView_backColor:
                        LUMP_COLOR = a.getColor(attr, LUMP_COLOR);
                        break;
                    case R.styleable.AudioProgressView_progressColor:
                        LUMP_COLOR_PROGRESS = a.getColor(attr, LUMP_COLOR_PROGRESS);
                        break;
                    case R.styleable.AudioProgressView_progress:
                        process = a.getFloat(attr, process);
                        break;
                    case R.styleable.AudioProgressView_progressBarHeight:
                        LUMP_MAX_HEIGHT = a.getDimension(attr, LUMP_MAX_HEIGHT);
                        break;

                }
            }
            a.recycle();
        }
        init();
    }

    public float getProcess() {
        return process;
    }

    public void setProcess(float process) {
        this.process = process;
        postInvalidate();
    }

    private void init() {
        lumpPaint = new Paint();
        lumpPaint.setAntiAlias(true);
        lumpPaint.setColor(LUMP_COLOR);
        lumpPaint.setStyle(Paint.Style.FILL);
        float y;
        position.clear();
        for (int i = 0; i < LUMP_COUNT; i++) {
            y = getY(i);
            y += internalNextInt();
            position.add(y);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LUMP_COUNT = getMeasuredWidth()/LUMP_SIZE;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float y;
        for (int i = 0,j = position.size(); i < j; i++) {
            drawLump(canvas, i,position.get(i));
        }
    }
    private float getY(int i){
        return  getSin(i,10,8)/2
                +getCos(i,20,3)/2;
    }
    private float getSin(int i,int x,int a){
        return  (float) (a* (1-Math.sin(PI * i/x)));
    }
    private float getCos(int i,int x,int a){
        return  (float) (a* (1-Math.cos(PI * i/x)));
    }

    Random random = new Random(10);

    float internalNextInt() {
        float origin = LUMP_MAX_HEIGHT/40,
                bound =LUMP_MAX_HEIGHT/6;
        int n = (int)(bound - origin);
        return random.nextInt(n) + origin;
    }

    /**
     * 绘制矩形条
     */
    private void drawLump(Canvas canvas, int i, float y) {
//        int minus = reversal ? -1 : 1;
//        float top = internalNextInt(LUMP_MAX_HEIGHT/8,LUMP_MAX_HEIGHT/2);//(LUMP_MAX_HEIGHT - (LUMP_MIN_HEIGHT + bottom * SCALE) * minus);
        if(((i*1f/LUMP_COUNT)*100f)<process){
            lumpPaint.setColor(LUMP_COLOR_PROGRESS);
        }else{
            lumpPaint.setColor(LUMP_COLOR);
        }
        canvas.drawRect(LUMP_SIZE * i,
                LUMP_MAX_HEIGHT/2f-y,
                LUMP_SIZE * i + LUMP_WIDTH,
                LUMP_MAX_HEIGHT/2f+ y, lumpPaint);
    }
}
