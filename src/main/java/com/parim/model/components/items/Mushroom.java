package com.parim.model.components.items;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.parim.model.components.MarioObject;
import com.parim.model.interfaces.HasTimeBeforeMove;
import com.parim.model.interfaces.Movable;

public class Mushroom extends Item implements HasTimeBeforeMove {
    public static int TIME = 3;
    public static double SPEED_RIGHT = MarioObject.SPEED_RIGHT/2;
    private int timePassed = 0;

    public Mushroom(){
        SCORE = 30;
    }
    public Mushroom(double x, double y){
        super(x, y);
        SCORE = 30;
    }

    @Override
    public void updateTimePassed(){
        timePassed++;
    }

    @Override
    public boolean canMove() {
        return timePassed == TIME;
    }
}
