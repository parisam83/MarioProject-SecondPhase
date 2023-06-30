package com.parim.model.components.enemies;

public class Spiny extends Enemy {
    private int numberOfHits = 0, LIMIT_OF_HITS = 2;
    public Spiny(){
        SCORE = 3;
    }

    public Spiny(double x, double y){
        super(x, y);
        SCORE = 3;
    }

    public void addNumberOfHits(){
        numberOfHits++;
    }

    public boolean limitOfHitExceeded(){
        return numberOfHits == LIMIT_OF_HITS;
    }

}
