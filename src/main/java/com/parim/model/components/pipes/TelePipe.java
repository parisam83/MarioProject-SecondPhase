package com.parim.model.components.pipes;

import com.parim.model.SectionObject;

public class TelePipe extends Pipe {
    protected SectionObject section;

    public TelePipe() {
    }

    public TelePipe(double x, double y, SectionObject section) {
        super(x, y);
        this.section = section;
    }

    // Getters and setters

    public SectionObject getSection() {
        return section;
    }

    public void setSection(SectionObject section) {
        this.section = section;
    }
}
