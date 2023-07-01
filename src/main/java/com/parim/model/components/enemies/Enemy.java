package com.parim.model.components.enemies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.parim.model.components.mario.MarioObject;
import com.parim.model.components.TileObject;
import com.parim.model.interfaces.HasGravity;
import com.parim.model.interfaces.Movable;

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
public abstract class Enemy extends TileObject implements Movable, HasGravity {
    @JsonIgnore
    public static double SPEED_RIGHT = MarioObject.SPEED_RIGHT/2, SPEED_UP = MarioObject.SPEED_UP/2;
    @JsonIgnore
    protected boolean gravity = false;

    public Enemy(){
        xVelocity = -SPEED_RIGHT;
    }
    public Enemy(double x, double y) {
        super(x, y);
        xVelocity = -SPEED_RIGHT;
    }

    @Override
    public void move(){
        if (gravity) yVelocity = -SPEED_UP;
        if (yVelocity == 0) x += xVelocity;
        y += yVelocity;
    }

    @Override
    public void updateVelocityMoveRight() {
        xVelocity = SPEED_RIGHT;
    }

    @Override
    public void updateVelocityMoveLeft() {
        xVelocity = -SPEED_RIGHT;
    }

    @Override
    public void updateVelocityMoveUp() {
        yVelocity = SPEED_UP;
    }

    @Override
    public void updateVelocityMoveDown() {
        yVelocity = -SPEED_UP;
    }
    @Override
    public void activateGravity() {
        gravity = true;
    }

    @Override
    public void deactivateGravity() {
        gravity = false;
    }
}
