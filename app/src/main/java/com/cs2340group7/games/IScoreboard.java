package com.cs2340group7.games;

public interface IScoreboard {
    void update(int score);
    void increase();
    void setScore(int score);
    int getScore();
}
