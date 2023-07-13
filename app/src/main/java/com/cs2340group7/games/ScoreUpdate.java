package com.cs2340group7.games;

public class ScoreUpdate {
    private PlayerType playerType;
    private int score;
    private boolean standing;
    public ScoreUpdate(PlayerType playerType, int score, boolean standing) {
        this.playerType = playerType;
        this.score = score;
        this.standing = standing;
    }
    public PlayerType getPlayerType() {
        return this.playerType;
    }
    public int getScore() {
        return this.score;
    }
    public boolean getStanding() {
        return this.standing;
    }
}
