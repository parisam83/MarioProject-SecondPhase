package com.parim.model.components.items;

import java.util.concurrent.ThreadLocalRandom;

public enum ItemType {
    COIN(2),
    STAR(5),
    MUSHROOM(3),
    FLOWER(1);

    private final int weight;
    ItemType(int weight){
        this.weight = weight;
    }

    public static ItemType randomItem(){
        int randomNumber = ThreadLocalRandom.current().nextInt(1, (COIN.weight + STAR.weight + MUSHROOM.weight + FLOWER.weight) + 1);
        if (randomNumber <= COIN.weight) return COIN;
        if (randomNumber <= COIN.weight + STAR.weight) return STAR;
        if (randomNumber <= COIN.weight + STAR.weight + MUSHROOM.weight) return MUSHROOM;
        return FLOWER;
    }
}
