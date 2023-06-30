package com.parim.model.components.blocks;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SimpleBlock extends Block {
    @JsonIgnore
    public static int LIMIT_OF_HITS = 1;

    public SimpleBlock() {
        SCORE = 1;
    }
    public SimpleBlock(double x, double y){
        super(x, y);
        SCORE = 1;
    }

    @Override
    public boolean limitOfHitExceeded() {
        return numberOfHits == LIMIT_OF_HITS;
    }
}
