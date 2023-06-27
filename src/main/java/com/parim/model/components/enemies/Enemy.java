package com.parim.model.components.enemies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.parim.model.components.TileObject;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Bowser.class, name = "BOWSER"),
        @JsonSubTypes.Type(value = Goomba.class, name = "GOOMBA"),
        @JsonSubTypes.Type(value = Koopa.class, name = "KOOPA"),
        @JsonSubTypes.Type(value = Spiny.class, name = "SPINY"),
})
public class Enemy extends TileObject {
    @JsonIgnore
    protected boolean alive = true;
    public Enemy(){}
    public Enemy(double x, double y) {
        super(x, y);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
