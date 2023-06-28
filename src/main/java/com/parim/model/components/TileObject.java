package com.parim.model.components;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class TileObject {
    @JsonIgnore
    protected static double SIZE;
    protected double x, y, width = 1, height = 1;
    protected double xVelocity, yVelocity;

    public TileObject(){}
    public TileObject(double x, double y){
        this.x = x;
        this.y = y;
    }

    // Getters and Setters
    public static double getSIZE() {
        return SIZE;
    }

    public static void setSIZE(double SIZE) {
        TileObject.SIZE = SIZE;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getXVelocity() {
        return xVelocity;
    }

    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getYVelocity() {
        return yVelocity;
    }

    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public String getType(){
        return this.getClass().getSimpleName();
    }
}
