package com.cs2340group7.games;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

public class WordleScoreboard implements UIComponent {
    private TextView ui;
    private TextView playAgainScore;
    int score;

    public WordleScoreboard(TextView ui, TextView playAgainScore) {
        this();
        this.ui = ui;
        this.playAgainScore = playAgainScore;
    }

    public WordleScoreboard() {
        score = 0;
    }

    @SuppressLint("DefaultLocale")
    public void update(int score) {
        ui.setText(String.format("Score: %d", score));
        playAgainScore.setText(String.format("Score: %d", score));
    }

    public void increase() {
        score++;
        update(score);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }


    @Override
    public View getUI() {
        return this.ui;
    }

    @Override
    public void setUI(View ui) {
        this.ui = (TextView) ui;
    }
}
