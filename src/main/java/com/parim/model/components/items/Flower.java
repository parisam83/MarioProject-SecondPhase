package com.parim.model.components.items;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Flower extends Item {
    public static int SCORE = 20, COIN = 0;

    public Flower(){}
    public Flower(double x, double y){
        super(x, y);
    }
}