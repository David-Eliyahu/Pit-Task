package com.david.interactivechart.charting.entities;


import android.support.annotation.NonNull;

/**
 * Maintains the state for a point.
 */
public class Point implements Comparable<Point> {

    private float cx;
    private float cy;
    private float dx;
    private float dy;
    private float radius;

    public Point(float cx, float cy,float dx, float dy, float radius){

        this.cx = cx;
        this.cy = cy;

        this.dx = dx;
        this.dy = dy;
        this.radius = radius;
    }



    public float getRadius() {
        return radius;
    }

    public float getCy() {
        return cy;
    }

    public void setCy(float cy) {
        this.cy = cy;
    }

    public float getCx() {
        return cx;
    }

    public void setCx(float cx) {
        this.cx = cx;
    }

    @Override
    public int compareTo(@NonNull Point point) {
        return (int)(this.getCx() - point.getCx());
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }
}
