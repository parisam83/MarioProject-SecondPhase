package com.parim.model.components.items;

import com.parim.model.components.TileObject;

public abstract class Item extends TileObject {
    public static int LIMIT_OF_HITS = 1;
    public boolean changesMarioState = true;

    public Item(){}

    public Item(double x, double y){
        super(x, y);
    }
}
