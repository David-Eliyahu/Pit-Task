package com.david.interactivechart.charting.services;

/**
 * Class with static methods for calculating
 */
public class Utils {

    /**
     * Check if a given point is contains another given point inside it
     * @param x1 first point x position
     * @param y1 first point y position
     * @param x2 second point x position
     * @param y2 second point y position
     * @param radius the radius of the point
     * @param range increasing the radius range to identify press optimally

     * @return true if point is pressed otherwise false
     */
    public static boolean isPointInCircle (float x1, float y1, float x2, float y2, float radius, float range)
    {
            double x = Math.pow(Math.abs(x1 - x2),2);
            double y = Math.pow(Math.abs(y1 - y2),2);

            return Math.sqrt(x+y) < radius * range;
    }

    public static float calcPointPositionOnScreen(float originPosition , float motionPosition)
    {
        return -1*(originPosition - motionPosition);
    }
    
}
