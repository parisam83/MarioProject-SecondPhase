package com.parim.model.components;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.parim.model.interfaces.Movable;

public class MarioObject extends TileObject implements Movable {
    public static double SPEED_RIGHT = 0.1, SPEED_UP = 0.1;
    @JsonIgnore
    private double initialYBeforeJump;

    public MarioObject(double x, double y) {
        super(x, y);
        initialYBeforeJump = y;
    }

    @Override
    public String getType() {
        return "MINI"; // TODO
    }

    @Override
    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    @Override
    public void updateVelocityMoveRight() {
        xVelocity = SPEED_RIGHT;
    }

    @Override
    public void updateVelocityMoveLeft() {
        xVelocity = -SPEED_RIGHT;
    }

    @Override
    public void updateVelocityMoveUp() {
        yVelocity = SPEED_UP;
    }

    @Override
    public void updateVelocityMoveDown() {
        yVelocity = -SPEED_UP;
    }

    // Getters and Setters

    public double getInitialYBeforeJump() {
        return initialYBeforeJump;
    }

    public void setInitialYBeforeJump(double initialYBeforeJump) {
        this.initialYBeforeJump = initialYBeforeJump;
    }
}
