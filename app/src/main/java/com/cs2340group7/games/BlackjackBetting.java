package com.cs2340group7.games;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class BlackjackBetting {
    private int score;
    private int betAmount;
    private static BlackjackBetting instance;
    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int bet) {
        betAmount = bet;
    }

    public void clearBetAmount() {
        betAmount = 0;
    }

    public BlackjackBetting() {
        score = 50;
        betAmount = 0;
    }

    public int getScore() {
        return score;
    }

    public static BlackjackBetting getInstance() {
        if (instance == null) {
            instance = new BlackjackBetting();
        }
        return instance;
    }
    public void decreaseScore(int lowerScore) {
        score -= lowerScore;
        if (score < 0) {
            score = 0;
        }
        BlackjackController.getInstance().updateScoreText(score);
    }

    public void updateScore(Boolean win) {
        Log.d("asd", String.format("%s", win));
        if (win) {
            Log.d("betAmount", String.format("%s", betAmount));
            Log.d("score", String.format("%s", score));
            score += betAmount * 2;
        } else {
            score += betAmount;
        }
    }
}

