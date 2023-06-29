package com.parim.model.components.items;

import com.parim.model.components.MarioObject;
import com.parim.model.interfaces.HasTimeBeforeMove;

public class Star extends Item implements HasTimeBeforeMove {
    public static int TIME = 3;
    public static double SPEED_RIGHT = MarioObject.SPEED_RIGHT/2;
    private int timePassed = 0;

    public Star(){}
    public Star(double x, double y){
        super(x, y);
    }

    @Override
    public void updateTimePassed() {
        timePassed++;
    }

    @Override
    public boolean canMove() {
        return timePassed == TIME;
    }
}
