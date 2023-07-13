package com.cs2340group7.games;

public class StandStrategy implements IMoveStrategy {
    public StandStrategy() {
    }
    public void move(IPlayer player) {
        player.stand();
    }
}
