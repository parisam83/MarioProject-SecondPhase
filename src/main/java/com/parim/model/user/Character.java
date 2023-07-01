package com.parim.model.user;

public enum Character {
    Mario(0),
    Frog(20), // jump
    MigMig(37), // fast
    Woman(123), // coin
    RobinHood(61) // shot
    ;

    private final int price;

    Character(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}