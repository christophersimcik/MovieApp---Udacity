package com.example.csimcik.movieapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by csimcik on 5/5/2016.
 */
public class ProgressUpdate extends View {
    private Paint slicePaint;
    Resources neutral = getResources();
    int color = neutral.getColor(R.color.neut);
    Resources hi = getResources();
    int color2 = hi.getColor(R.color.green_hi);
    Resources lo = getResources();
    int color3 = lo.getColor(R.color.red_lo);
    private int [] sliceClrs = {color,color2,color3};
    private RectF rectf;
    private float dataPoints;
    private int clrSlct;
    public ProgressUpdate(Context context, AttributeSet attrs)  {
        super(context, attrs);
        slicePaint = new Paint();
        slicePaint.setAntiAlias(true);
        slicePaint.setDither(true);
        slicePaint.setStyle(Paint.Style.FILL);
    }
    private float scale() {

        float scaledData = (dataPoints / 10) * 360;

        return scaledData;
    }
    @Override
    protected void onDraw(Canvas canvas) {
            int startTop = 0+5;
            int startLeft = 0+5;
            int endBottom = getHeight()-5;
            int endRight = endBottom;
            rectf = new RectF(startLeft , startTop , endRight , endBottom );
        float scaledData = scale();
            float sliceStartPoint = 0;
                slicePaint.setColor(sliceClrs[clrSlct]);
                canvas.drawArc(rectf, sliceStartPoint, scaledData, true, slicePaint);

            }


    public void setDataPoints(float datapoints) {
        this.dataPoints = datapoints;
        invalidate();
    }
    public void setClrSlct(int clrSlct) {
        this.clrSlct = clrSlct;
        invalidate();
    }
}
