package com.cs2340group7.games;

public class ScoreUpdate {
    private PlayerType playerType;
    private int score;
    private boolean standing;
    private boolean busted;
    public ScoreUpdate(PlayerType playerType, int score, boolean standing, boolean busted) {
        this.playerType = playerType;
        this.score = score;
        this.standing = standing;
        this.busted = busted;
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
    public boolean getBusted() {
        return this.busted;
    }
}
