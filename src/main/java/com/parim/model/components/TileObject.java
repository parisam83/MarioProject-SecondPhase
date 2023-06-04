package com.parim.model.components;

public abstract class TileObject {
    protected static double SIZE;
    protected double x, y;
    protected double xVelocity, yVelocity;
    protected String type;
    protected double speed;

    public TileObject(){}
    public TileObject(double x, double y, String type){
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void move(){
        x += xVelocity;
        y += yVelocity;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
