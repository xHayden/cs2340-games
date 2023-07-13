package com.cs2340group7.games;

public class ScoreboardController implements IScoreboardController {
    public static IScoreboardController instance;
    private int score;
    private ScoreboardController() {
        this.score = 0;
    }

    public static IScoreboardController getInstance() {
        if (instance == null) {
            instance = new ScoreboardController();
        }
        return instance;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
