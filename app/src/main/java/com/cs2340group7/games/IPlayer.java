package com.cs2340group7.games;

public interface IPlayer {
    void playMove(IMoveStrategy strategy);
    int getScore();
    void hit(ICard card);
    void stand();
    void update(ScoreUpdate scoreUpdate);
    boolean checkBust();
    void drawInitialCards(ICard[] cards);
    void reset();
}