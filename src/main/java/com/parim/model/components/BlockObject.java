package com.parim.model.components;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BlockObject extends TileObject {
    String item;
    public BlockObject(){}
    public BlockObject(double x, double y, String type) {
        super(x, y, type);
    }
}
