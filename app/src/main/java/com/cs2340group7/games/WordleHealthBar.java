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

    //^^ update(rowsCompleted && boolean completed)
    // 6 images --> per each case // 3 full .. 0 empty
    // start -> image 6
    // switch(rowsCompleted)
    // (1) && notCompleted -> image 5 -> 2 full 1/2
    // . . .
}
