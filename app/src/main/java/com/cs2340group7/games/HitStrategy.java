package com.cs2340group7.games;

public class HitStrategy implements IMoveStrategy {
    private ICard card;

    public HitStrategy(ICard card) {
        this.card = card;
    }

    public void move(IPlayer player) {
        player.hit(card);
    }
}
