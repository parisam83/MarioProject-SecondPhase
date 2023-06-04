package com.parim.model.components;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.parim.model.SectionObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PipeObject extends TileObject {
    private SectionObject section;
    private boolean activated;
    public PipeObject(){}
    public PipeObject(double x, double y, String type) {
        super(x, y, type);
    }
}
