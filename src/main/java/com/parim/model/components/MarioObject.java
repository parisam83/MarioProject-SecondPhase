package com.parim.model.components;

import com.parim.model.interfaces.Movable;

public class MarioObject extends TileObject implements Movable {
    public MarioObject(double x, double y) {
        super(x, y);
        speed = 0.1;
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
}
