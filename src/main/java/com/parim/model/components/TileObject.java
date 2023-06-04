package com.parim.model.components;

public abstract class TileObject {
    protected static int SIZE = 60;
    protected int x, y;
    protected int xVelocity, yVelocity;
    protected String type;

    public TileObject(int x, int y, String type){
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void move(){
        x += xVelocity;
        y += yVelocity;
    }

    // Getters and Setters

    public static int getSIZE() {
        return SIZE;
    }

    public static void setSIZE(int SIZE) {
        TileObject.SIZE = SIZE;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getYVelocity() {
        return yVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
