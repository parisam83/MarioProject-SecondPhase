package com.parim.model;

import java.util.ArrayList;

public class LevelObject {
    private ArrayList<SectionObject> sections;

    public LevelObject(){}
    public LevelObject(ArrayList<SectionObject> sections){
        this.sections = sections;
    }

    // Getters and Setters
    public ArrayList<SectionObject> getSections() {
        return sections;
    }

    public void setSections(ArrayList<SectionObject> sections) {
        this.sections = sections;
    }
}
