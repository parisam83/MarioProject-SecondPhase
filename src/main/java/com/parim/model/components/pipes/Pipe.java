package com.parim.model.components.pipes;

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
    public Pipe(){}
    public Pipe(double x, double y) {
        super(x, y);
    }
}
