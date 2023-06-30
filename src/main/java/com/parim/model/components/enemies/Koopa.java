package com.parim.model.components.enemies;

public class Koopa extends Enemy {
    public Koopa(){
        SCORE = 2;
    }

    public Koopa(double x, double y){
        super(x, y);
        SCORE = 2;
    }
}
