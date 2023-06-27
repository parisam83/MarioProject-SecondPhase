package com.parim.model.components.blocks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.parim.model.components.TileObject;
import com.parim.model.components.items.*;
import com.parim.model.interfaces.Convertible;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionBlock extends Block implements Convertible {
    public static int LIMIT_OF_HITS = 1, SCORE = 1, COIN = 0;
    private ItemType item;

    public QuestionBlock() {}

    public QuestionBlock(double x, double y){
        super(x, y);
    }
    @Override
    public boolean limitOfHitExceeded() {
        return numberOfHits == LIMIT_OF_HITS;
    }

    @Override
    public ArrayList<TileObject> convert() {
        if (item == null) item = ItemType.randomItem();

        return new ArrayList<TileObject>(){{
            add(instanceOfItem());
        }};
    }

    private TileObject instanceOfItem(){
        if (item == ItemType.COIN) return new Coin(x, y - SIZE);
        else if (item == ItemType.STAR) return new Star(x, y - SIZE);
        else if (item == ItemType.MUSHROOM) return new Mushroom(x, y - SIZE);
        else return new Flower(x, y - SIZE);
    }
}
