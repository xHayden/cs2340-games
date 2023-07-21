package com.cs2340group7.games;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class WordleHealthBar implements UIComponent, IHealthBar {
    private static final int FULL_HEART = 6;
    private static final int HALF_HEART = 3;
    private static final int EMPTY_HEART = 0;

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

    public WordleHealthBar() {
    }

    public void updateHealth(int health, int[] color) {
        if (hasCustomColor(color)) {
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
                    break;
                default:
                    throw new IllegalArgumentException("Invalid health value: " + health);
            }
        }
    }

    private boolean hasCustomColor(int[] color) {
        for (int c : color) {
            if (c != 2) {
                return true;
            }
        }
        return false;
    }

    public void reset() {
        heart1.setImageResource(R.drawable.fullheart);
        heart2.setImageResource(R.drawable.fullheart);
        heart3.setImageResource(R.drawable.fullheart);
    }

    @Override
    public View getUI() {
        return this.ui;
    }

    @Override
    public void setUI(View ui) {
        this.ui = (LinearLayout) ui;
    }
}
