package com.parim.model.components.enemies;

public class Goomba extends Enemy {
    public Goomba(){
        SCORE = 1;
    }

    public Goomba(double x, double y){
        super(x, y);
        SCORE = 1;
    }
}
