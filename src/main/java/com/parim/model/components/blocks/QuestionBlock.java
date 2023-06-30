package com.parim.model.components.blocks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.parim.model.components.TileObject;
import com.parim.model.components.items.*;
import com.parim.model.interfaces.Convertible;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionBlock extends Block implements Convertible {
    public static int LIMIT_OF_HITS = 1;
    private ItemType item;

    public QuestionBlock() {
        SCORE = 1;
    }

    public QuestionBlock(double x, double y){
        super(x, y);
        SCORE = 1;
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
            add(new EmptyBlock(x, y));
        }};
    }

    private TileObject instanceOfItem(){
        if (item == ItemType.COIN) return new Coin(x, y + 1);
        else if (item == ItemType.STAR) return new Star(x, y + 1);
        else if (item == ItemType.MUSHROOM) return new Mushroom(x, y + 1);
        else return new Flower(x, y + 1);
    }
}
