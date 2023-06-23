package com.cs2340group7.games;

import android.widget.ProgressBar;

public class WordleHealthBar {
    private ProgressBar ui;
    public WordleHealthBar(ProgressBar ui) {
        this.ui = ui;
    }
    public void update(int value) {
        this.ui.setProgress(value);
    }
}
