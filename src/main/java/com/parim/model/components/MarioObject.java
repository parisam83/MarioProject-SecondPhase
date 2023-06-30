package com.parim.model.components;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.parim.model.interfaces.Movable;

public class MarioObject extends TileObject implements Movable {
    public static double SPEED_RIGHT = 0.1, SPEED_UP = 0.1;
    @JsonIgnore
    private double initialYBeforeJump;
    @JsonIgnore
    private boolean marioHasIntersectDown = false;

    public MarioObject(double x, double y) {
        super(x, y);
        initialYBeforeJump = y;
    }

    @Override
    public String GetType() {
        return "MINI"; // TODO
    }

    @Override
    public void move() {
        if (!marioHasIntersectDown && initialYBeforeJump == y && y > 0) updateVelocityMoveDown();
        if (y >= initialYBeforeJump + 5) updateVelocityMoveDown();
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

    public void marioHadIntersectDown(){
        marioHasIntersectDown = true;
        yVelocity = 0;
        initialYBeforeJump = y;
    }

    // Getters and Setters

    public double getInitialYBeforeJump() {
        return initialYBeforeJump;
    }

    public void setInitialYBeforeJump(double initialYBeforeJump) {
        this.initialYBeforeJump = initialYBeforeJump;
    }

    public boolean isMarioHasIntersectDown() {
        return marioHasIntersectDown;
    }

    public void setMarioHasIntersectDown(boolean marioHasIntersectDown) {
        this.marioHasIntersectDown = marioHasIntersectDown;
    }
}
