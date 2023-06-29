package com.parim.model.components.blocks;

import com.parim.model.components.TileObject;
import com.parim.model.components.items.Coin;
import com.parim.model.interfaces.Convertible;

import java.util.ArrayList;

public class CoinBlock extends Block implements Convertible {
    public static int LIMIT_OF_HITS = 1;

    public CoinBlock() {
        SCORE = 1;
    }

    public CoinBlock(double x, double y) {
        super(x, y);
        SCORE = 1;
    }

    @Override
    public boolean limitOfHitExceeded() {
        return numberOfHits == LIMIT_OF_HITS;
    }

    @Override
    public ArrayList<TileObject> convert() {
        return new ArrayList<TileObject>(){{
            add(new SimpleBlock(x, y));
            add(new Coin(x, y - SIZE));
        }};
    }
}
