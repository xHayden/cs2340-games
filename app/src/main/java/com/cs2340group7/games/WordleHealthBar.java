package com.cs2340group7.games;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class WordleHealthBar {
    private LinearLayout ui;
    private ImageView heart1;
    private ImageView heart2;
    private ImageView heart3;


    public WordleHealthBar(LinearLayout ui) {
        this.ui = ui;
        heart1 = (ImageView) ui.getChildAt(0);
        heart2 = (ImageView) ui.getChildAt(1);
        heart3 = (ImageView) ui.getChildAt(2);
    }

    //    public void update(int value) {
//        this.ui.setProgress(value);
//    }
    public void updateHealth(int health) {
        if (health <= 0 || health > 6) {
            throw new IllegalArgumentException("Cannot have this many health");
        }
        switch (health) {
            case 1:
                heart3.setImageResource(R.drawable.halfheart);
                break;
            case 2:
                heart3.setImageResource(R.drawable.emptyheart);
                break;
            case 3:
                heart2.setImageResource(R.drawable.halfheart);
                break;
            case 4:
                heart2.setImageResource(R.drawable.emptyheart);
                break;
            case 5:
                heart1.setImageResource(R.drawable.halfheart);
                break;
            case 6:
                heart1.setImageResource(R.drawable.emptyheart);
        }
    }
}

