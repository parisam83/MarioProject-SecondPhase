package com.parim.model.components.pipes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.parim.model.SectionObject;
import com.parim.model.components.TileObject;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimplePipe.class, name = "SIMPLE"),
        @JsonSubTypes.Type(value = TeleSimplePipe.class, name = "TELE_SIMPLE"),
        @JsonSubTypes.Type(value = PiranhaTrapPipe.class, name = "PIRANHA_TRAP"),
        @JsonSubTypes.Type(value = TelePiranhaPipe.class, name = "TELE_PIRANHA")
})
public abstract class Pipe extends TileObject {
    private SectionObject section;
    private boolean activated = false;

    @JsonIgnore
    public static double WIDTH_CONSTANT = 2, HEIGHT_CONSTANT = 2;
    public Pipe(){
        // width = height = 2;
    }

    public Pipe(double x, double y) {
        super(x, y);
        // width = height = 2;
    }

    public SectionObject getSection() {
        return section;
    }

    public void setSection(SectionObject section) {
        this.section = section;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
