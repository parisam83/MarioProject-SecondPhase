package com.parim.model.components.blocks;

public class EmptyBlock extends Block{
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
