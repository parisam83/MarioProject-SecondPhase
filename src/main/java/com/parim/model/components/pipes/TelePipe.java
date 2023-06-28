package com.parim.model.components.pipes;

import com.parim.model.SectionObject;

public class TelePipe extends Pipe {
    protected SectionObject section;

    public TelePipe() {
        height = 3;
    }

    public TelePipe(double x, double y, SectionObject section) {
        super(x, y);
        this.section = section;
        height = 3;
    }

    // Getters and setters

    public SectionObject getSection() {
        return section;
    }

    public void setSection(SectionObject section) {
        this.section = section;
    }
}
