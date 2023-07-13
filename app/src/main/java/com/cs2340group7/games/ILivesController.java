package com.cs2340group7.games;

public interface ILivesController {
    void betLives(int lives);
    int won(); // signal how lives should be updated, returns int of lives
    int getLives();
}
