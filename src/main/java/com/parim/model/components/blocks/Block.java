package com.parim.model.components.blocks;

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
        @JsonSubTypes.Type(value = CoinBlock.class, name = "COIN"),
        @JsonSubTypes.Type(value = CoinsBlock.class, name = "COINS"),
        @JsonSubTypes.Type(value = EmptyBlock.class, name = "EMPTY"),
        @JsonSubTypes.Type(value = QuestionBlock.class, name = "QUESTION"),
        @JsonSubTypes.Type(value = SimpleBlock.class, name = "SIMPLE")
})
public abstract class Block extends TileObject {
    @JsonIgnore
    protected int numberOfHits = 0;
    public Block(){}
    public Block(double x, double y) {
        super(x, y);
    }

    public abstract boolean limitOfHitExceeded();

    // Getters and Setters
    public void addNumberOfHits(){
        numberOfHits++;
    }
    public int getNumberOfHits() {
        return numberOfHits;
    }

    public void setNumberOfHits(int numberOfHits) {
        this.numberOfHits = numberOfHits;
    }
}
