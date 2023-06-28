package com.parim.model.components.blocks;

public class Floor extends Block{
    public Floor(){}
    public Floor(double x, double y){
        super(x, y);
    }

    @Override
    public boolean limitOfHitExceeded() {
        return false;
    }
}
