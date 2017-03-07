package com.david.interactivechart.charting.services;

import com.david.interactivechart.charting.entities.Point;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;


/**
 * It unites all the services that point need
 */
public class PointsService {

    private static PointsService instance;
    private List<Point> mPointList;
    private Point selectedPoint;
    public final static float POINT_RADIUS = 15f;
    public final static float RANGE = 4;


    /**
     * private constructor implements singleton design pattern
     */
    private PointsService()
    {
        mPointList = new LinkedList<>();
        selectedPoint = null;
    }
    
    public static PointsService getInstance()
    {
        return instance = instance == null ? new PointsService() : instance; 
    }


    /**
     *  base on horizontal and vertical positions set the selected value if found
     * @param x horizontal screen coordinate
     * @param y vertical screen coordinate
     * @return true if pressed position hitting a point otherwise false
     */
    public boolean handleSetSelected (float x, float y )
    {
        for (Point point: mPointList) {
            if (Utils.isPointInCircle(x, y,point.getDx(), point.getDy(), POINT_RADIUS, RANGE )) {
                setSelectedPoint(point);
                return true;
            }
        }
        return false;
    }

    /**
     * Recalculates the values of the points in order to deal with the rotation of the screen
     * @param width screen width
     * @param height screen height
     */
    public void screenRotated(float width, float height){
        for (Point point: mPointList) {
            point.setDx(point.getCx() + width);
            point.setDy(point.getCy() + height);
        }
    }


    public void addPoint(Point point) {
        mPointList.add(point);
    }


    public Point getSelectedPoint() {
        return selectedPoint;
    }

    private void setSelectedPoint(Point selectedPoint) {
        this.selectedPoint = selectedPoint;
    }

    /**
    * Get the list iterator by index
    * @param index position in the list to start iterate
     * @return return the list iterator by the given index
     */
    public ListIterator<Point> getPointIterator(int index)
    {
        return mPointList.listIterator(index);
    }

    /**
     * Initialization list by five random values
     * @param width screen width
     * @param height screen height
     */
    public void initList(float width, float height) {
        if (mPointList.size() == 0) {
            Random random = new Random();
            for (int i = 1; i <= 5; i++){
                int x =   random.nextInt(600) - 300;
                int y =  random.nextInt(800) - 400;
                addPoint(new Point(x, y, x+ width , y + height, PointsService.POINT_RADIUS ));
            }
            sort();
        }
    }

    public void sort() {
        Collections.sort(mPointList);
    }
}
