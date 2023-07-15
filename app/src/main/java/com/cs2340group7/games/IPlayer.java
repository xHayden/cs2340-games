package com.cs2340group7.games;

public interface IPlayer {
    void playMove(IMoveStrategy strategy);
    int getScore();
    void hit(IBlackjackCard card);
    void stand();
    void update(ScoreUpdate scoreUpdate);
    boolean checkBust();
    void reset();
}