package com.parim.model.components.pipes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.parim.model.components.TileObject;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({ //TODO
        @JsonSubTypes.Type(value = SimplePipe.class, name = "SIMPLE"),
        @JsonSubTypes.Type(value = TeleSimplePipe.class, name = "TELE_SIMPLE"),
        @JsonSubTypes.Type(value = PiranhaTrapPipe.class, name = "PIRANHA_TRAP"),
        @JsonSubTypes.Type(value = TelePiranhaPipe.class, name = "TELE_PIRANHA")
})
public class Pipe extends TileObject {
    @JsonIgnore
    public static double WIDTH_CONSTANT = 2, HEIGHT_CONSTANT = 2;
    public Pipe(){
        // width = height = 2;
    }

    public Pipe(double x, double y) {
        super(x, y);
        // width = height = 2;
    }
}
