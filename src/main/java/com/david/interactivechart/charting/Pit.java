package com.david.interactivechart.charting;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.david.interactivechart.charting.entities.AxisView;
import com.david.interactivechart.charting.entities.Point;
import com.david.interactivechart.charting.listeners.CostumeTouchListener;
import com.david.interactivechart.charting.services.PointsService;
import java.util.ListIterator;


/**
 * Custom view that shows an interactive line chart.
 */
public class Pit extends ViewGroup{

    private AxisView mAxisView;
    private Paint mPointPaint;
    private Paint mLinePaint;
    private PointsService pointsService;
    private GestureDetector mDetector;

    /**
     * Class constructor taking only a context. Use this constructor to create
     * {@link Pit} objects from your own code.
     * @param context the context of the sender
     */
    public Pit(Context context) {
        super(context);
        init();
    }

    public Pit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    /**
     * Initialize the control. This code is in a separate method so that it can be
     * called from both constructors.
     */
    private void init() {


        mPointPaint = new Paint();
        mPointPaint.setStyle(Paint.Style.FILL);
        mPointPaint.setColor(Color.RED);

        mLinePaint = new Paint();
        mLinePaint.setColor(Color.BLACK);
        mLinePaint.setStrokeWidth(3f);

        mAxisView = new AxisView(getContext(), null);
        addView(mAxisView);

        mDetector = new GestureDetector(getContext(), new CostumeTouchListener(this));

        pointsService = PointsService.getInstance();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Let the GestureDetector interpret this event
        boolean result = mDetector.onTouchEvent(event);
            onDataChanged();
            return  result;
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        //Pit lays out its children in onSizeChanged()
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int minWidth = getPaddingLeft() + getPaddingRight();
        int minHeight = getPaddingTop() + getPaddingBottom();

        int width = resolveSizeAndState(minWidth, widthMeasureSpec, 0);
        int height = resolveSizeAndState(minHeight, heightMeasureSpec, 0);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldWidth, int oldHeight) {
        super.onSizeChanged(w, h, oldWidth, oldHeight);
        //
        // Set dimensions for view
        //
        // Account for padding
        float xPad = (float) (getPaddingLeft() + getPaddingRight());
        float yPad = (float) (getPaddingTop() + getPaddingBottom());

        float calculatedWidth = (float) w - xPad;
        float calculatedHeight = (float) h - yPad;

        int width = getMeasuredWidth()/2;
        int height = getMeasuredHeight()/2;


        // Figure out how big we can make the chart.
        RectF mAxisBounds = new RectF(0, 0, calculatedWidth, calculatedHeight);

        // Lay out the child view that draws the axis.
        mAxisView.layout(
                (int) mAxisBounds.left,
                (int) mAxisBounds.top,
                (int) mAxisBounds.right,
                (int) mAxisBounds.bottom);


        // Initialization the list with five random points
        pointsService.initList(width,height);

        // Changing the values of the Dx and Dy points handle screen rotated
        pointsService.screenRotated(width,height);

    }


    /**
     * Add new Point to the graph at the origin axis (0, 0) coordinate
     */
    public void addPoint() {
        Point point = new Point(0,0,getMeasuredWidth()/2,getMeasuredHeight()/2,PointsService.POINT_RADIUS);
        pointsService.addPoint(point);
        onDataChanged();
    }

    /**
     * Sort the list and invalidate the view
     */
    private void onDataChanged() {
        pointsService.sort();
        invalidate();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        ListIterator<Point> listIterator = pointsService.getPointIterator(0);

        // get the current width and height of the view
        float width = this.getMeasuredWidth()/2;
        float height = this.getMeasuredHeight()/2;


        while (listIterator.hasNext()) {
            Point previous = null;
            if (listIterator.nextIndex() > 0){
                previous = listIterator.previous();
                listIterator.next();
            }
            Point next = listIterator.next();

            // Draw each point from the list
            canvas.drawCircle(width + next.getCx(), height + next.getCy(),next.getRadius(),mPointPaint);

            if(previous != null){
                // Draw liner edge between the points base on the previous and current point
                canvas.drawLine( width + previous.getCx(),height + previous.getCy(), width + next.getCx(),height + next.getCy(), mLinePaint);
            }

        }

    }
}
