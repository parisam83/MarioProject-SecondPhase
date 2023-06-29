package com.parim.model.components.items;

public class Coin extends Item {
    public Coin(){
        SCORE = 10;
        COIN = 1;
    }
    public Coin(double x, double y){
        super(x, y);
        SCORE = 10;
        COIN = 1;
    }
}
