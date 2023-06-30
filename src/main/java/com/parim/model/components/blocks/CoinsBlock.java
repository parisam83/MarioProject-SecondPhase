package com.parim.model.components.blocks;

import com.parim.model.components.TileObject;
import com.parim.model.interfaces.Convertible;

import java.util.ArrayList;

public class CoinsBlock extends Block implements Convertible {
    public static int LIMIT_OF_HITS = 5;

    public CoinsBlock() {
        SCORE = 1;
        COIN = 1;
    }

    public CoinsBlock(double x, double y) {
        super(x, y);
        SCORE = 1;
        COIN = 1;
    }

    @Override
    public boolean limitOfHitExceeded() {
        return numberOfHits == LIMIT_OF_HITS;
    }

    @Override
    public ArrayList<TileObject> convert() {
        return new ArrayList<TileObject>(){{
            add(new EmptyBlock(x, y));
        }};
    }
}
