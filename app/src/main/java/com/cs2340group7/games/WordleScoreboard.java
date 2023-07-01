package com.cs2340group7.games;

import android.annotation.SuppressLint;
import android.widget.TextView;

public class WordleScoreboard {
    private TextView ui;
    int score;
    public WordleScoreboard(TextView ui) {
        this.ui = ui;
    }
    @SuppressLint("DefaultLocale")
    public void update(int score) {
        ui.setText(String.format("Score: %d", score));
    }
    public void increase() { score++; update(score); }
}
