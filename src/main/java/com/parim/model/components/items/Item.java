package com.parim.model.components.items;

import com.parim.model.components.mario.MarioObject;
import com.parim.model.components.TileObject;
import com.parim.model.interfaces.HasGravity;
import com.parim.model.interfaces.Movable;

public abstract class Item extends TileObject implements Movable, HasGravity {
    public static int time = 0;
    public boolean changesMarioState = true;
    protected boolean gravity = false;
    protected static double SPEED_RIGHT = MarioObject.SPEED_RIGHT/2, SPEED_UP = MarioObject.SPEED_UP/2;


    public Item(){}

    public Item(double x, double y){
        super(x, y);
    }

    @Override
    public void move(){
        if (gravity) yVelocity = -SPEED_UP;
        if (yVelocity == 0) x += xVelocity;
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

    @Override
    public void activateGravity() {
        gravity = true;
    }

    @Override
    public void deactivateGravity() {
        gravity = false;
    }
}
