package com.david.interactivechart.charting.entities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Custom view class that draws the coordinate system onto a separate hardware layer
 */
public class AxisView extends View{

    private Paint mPaint;


    public AxisView(Context context, AttributeSet attrs)
    {
        super(context,attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.CYAN);
        mPaint.setStrokeWidth(5f);


    }

    @Override
    public void onDraw(Canvas canvas)
    {

        int width = this.getWidth();
        int height = this.getHeight();

        //Draw the vertical axis
        canvas.drawLine(width /2, 0, width /2, height *2, mPaint);
        //Draw the horizontal axis
        canvas.drawLine(0, height /2, width, height /2, mPaint);
    }
}
