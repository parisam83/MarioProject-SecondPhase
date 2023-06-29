package com.parim.model.components.items;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Flower extends Item {
    public Flower(){
        SCORE = 20;
    }
    public Flower(double x, double y){
        super(x, y);
        SCORE = 20;
    }
}