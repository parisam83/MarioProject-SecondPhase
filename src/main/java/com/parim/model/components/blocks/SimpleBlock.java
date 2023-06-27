package com.parim.model.components.blocks;

public class SimpleBlock extends Block {
    public static int LIMIT_OF_HITS = 1, SCORE = 1, COIN = 0;

    public SimpleBlock() {
    }
    public SimpleBlock(double x, double y){
        super(x, y);
    }

    @Override
    public boolean limitOfHitExceeded() {
        return numberOfHits == LIMIT_OF_HITS;
    }
}
