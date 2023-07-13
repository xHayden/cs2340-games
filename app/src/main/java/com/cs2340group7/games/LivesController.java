package com.cs2340group7.games;

public class LivesController implements ILivesController {
    private static int lives;
    private static int bet;
    private static ILivesController instance;
    private LivesController(int initialLives) {
        lives = initialLives;
        bet = 0;
    }

    public static ILivesController getInstance() {
        if (instance == null) {
            instance = new LivesController(5);
        }
        return instance;
    }

    @Override
    public void betLives(int lives) {
        bet = lives;
    }

    @Override
    public int won() {
        lives = lives + (bet * 2);
        bet = 0;
        return lives;
    }

    @Override
    public int getLives() {
        return lives;
    }
}
