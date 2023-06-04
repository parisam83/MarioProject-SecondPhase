package com.parim.model.components;


import com.parim.model.SectionObject;

public class PipeObject extends TileObject {
    private SectionObject section;
    private boolean activated;
    public PipeObject(int x, int y, String type) {
        super(x, y, type);
    }
}
