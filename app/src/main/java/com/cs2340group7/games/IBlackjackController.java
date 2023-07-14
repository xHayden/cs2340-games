package com.cs2340group7.games;

public interface IBlackjackController extends IPlayersObserver {
    void updatePlayerScore(int score);
    void updateDealerScore(int score);

}
