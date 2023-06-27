package com.parim.model.components.blocks;

public class EmptyBlock extends Block{
    public static int SCORE = 0, COIN = 0;

    public EmptyBlock() {
    }
    public EmptyBlock(double x, double y){
        super(x, y);
    }

    @Override
    public boolean limitOfHitExceeded() {
        return false;
    }
}
