package com.cs2340group7.games;

public class HitStrategy implements IMoveStrategy {
    private IBlackjackCard card;

    public HitStrategy(IBlackjackCard card) {
        this.card = card;
    }

    public void move(IPlayer player) {
        player.hit(card);
    }
}
