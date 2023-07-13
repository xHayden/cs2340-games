package com.cs2340group7.games;

public class BlackjackCard implements ICard {
    private final int value;

    public BlackjackCard(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
