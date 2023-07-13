package com.cs2340group7.games;

public interface ILivesController {
    boolean betLives(int lives); // true if successful
    int won(); // signal how lives should be updated, returns int of lives
    boolean lost(); // returns if u can continue
    int getLives();
}
