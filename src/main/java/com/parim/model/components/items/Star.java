package com.parim.model.components.items;

import com.parim.model.components.MarioObject;
import com.parim.model.interfaces.HasTimeBeforeMove;

public class Star extends Item implements HasTimeBeforeMove {
    public static int TIME = 3;
    public static double SPEED_RIGHT = MarioObject.SPEED_RIGHT/2;
    private int ticksPassed = 0, timeStarCameToFloor = -1;
    private boolean starHasIntersectDownWithFloor = false, starHasIntersectDownWithBlock = true;
    private double tmp;

    public Star(){
        SCORE = 40;
    }
    public Star(double x, double y){
        super(x, y);
        SCORE = 40;
    }

    @Override
    public void move() {
        if (starHasIntersectDownWithBlock) {
            // System.out.println("star is on question block");
            updateVelocityMoveRight();
        }
        if (!starHasIntersectDownWithBlock && !starHasIntersectDownWithFloor && timeStarCameToFloor == -1){
            // System.out.println("star is on هوا");
            tmp = xVelocity;
            xVelocity = 0;
            updateVelocityMoveDown();
            // System.out.println(yVelocity + " " + y);
        }
        if (yVelocity < 0 && y > 0 && y < 0.5) {
            y = 0;
            timeStarCameToFloor = 1;
            starHasIntersectDownWithFloor = true;
            // System.out.println("star is comming down");
            if (tmp == 0) updateVelocityMoveRight();
            else xVelocity = tmp;
            yVelocity = 0;
        }
/*        if (timeStarCameToFloor == ticksPassed){
            yVelocity = 0;
            updateVelocityMoveRight();
        }*/
        if (ticksPassed % 120 == 0 && starHasIntersectDownWithFloor){
            xVelocity = 0;
            updateVelocityMoveUp();
        }
        if (timeStarCameToFloor != -1 && y >= 2.5) {
            xVelocity = 0;
            updateVelocityMoveDown();
        }
        super.move();
    }

    public void updateTimeStarCameToFloor(){
        timeStarCameToFloor = 0;
    }

    @Override
    public void updateTicksPassed() {
        ticksPassed++;
    }

    @Override
    public void enableMoveIfPossible() {
        if (ticksPassed == TIME*60)
            xVelocity = SPEED_RIGHT;
    }

    public int getTimeStarCameToFloor() {
        return timeStarCameToFloor;
    }

    public void setTimeStarCameToFloor(int timeStarCameToFloor) {
        this.timeStarCameToFloor = timeStarCameToFloor;
    }

    public boolean isStarHasIntersectDownWithFloor() {
        return starHasIntersectDownWithFloor;
    }

    public void setStarHasIntersectDownWithFloor(boolean starHasIntersectDownWithFloor) {
        this.starHasIntersectDownWithFloor = starHasIntersectDownWithFloor;
    }

    public boolean isStarHasIntersectDownWithBlock() {
        return starHasIntersectDownWithBlock;
    }

    public void setStarHasIntersectDownWithBlock(boolean starHasIntersectDownWithBlock) {
        this.starHasIntersectDownWithBlock = starHasIntersectDownWithBlock;
    }
}
