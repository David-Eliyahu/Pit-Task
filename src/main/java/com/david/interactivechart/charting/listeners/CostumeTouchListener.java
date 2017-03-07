package com.david.interactivechart.charting.listeners;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.david.interactivechart.charting.services.PointsService;
import com.david.interactivechart.charting.services.Utils;

/**
 * Extends {@link GestureDetector.SimpleOnGestureListener} to provide custom gesture
 * processing.
 */
public class CostumeTouchListener extends GestureDetector.SimpleOnGestureListener {

    private final View view;
    private int width;
    private int height;

    public CostumeTouchListener(View view)
    {
        this.view = view;
    }

    @Override
    public boolean onDown(MotionEvent e) {

        boolean result = PointsService.getInstance().handleSetSelected(e.getX(), e.getY());

        if(result) {
            width = view.getMeasuredWidth() / 2;
            height = view.getMeasuredHeight() / 2;
        }

        return result;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        //set the new coordinate only if the bounds of the view
        if(e2.getY() < view.getBottom() && e2.getY() > 0){

            PointsService pService = PointsService.getInstance();

            pService.getSelectedPoint().setCx(Utils.calcPointPositionOnScreen(width,e2.getX()));
            pService.getSelectedPoint().setCy(Utils.calcPointPositionOnScreen(height,e2.getY()));

            pService.getSelectedPoint().setDx(e2.getX());
            pService.getSelectedPoint().setDy(e2.getY());
        }

        return super.onScroll(e1, e2, distanceX, distanceY);
    }
}
