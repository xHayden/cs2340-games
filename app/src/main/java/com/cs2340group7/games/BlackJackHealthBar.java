package com.cs2340group7.games;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


import java.util.Arrays;

public class BlackJackHealthBar implements UIComponent, IHealthBar{

    private LinearLayout ui;
    private ImageView heart1;
    private ImageView heart2;
    private ImageView heart3;
    private boolean dealerWin;




    public BlackJackHealthBar(LinearLayout ui, boolean dealerWin) {
        this.ui = ui;
        heart1 = (ImageView) ui.getChildAt(0);
        heart2 = (ImageView) ui.getChildAt(1);
        heart3 = (ImageView) ui.getChildAt(2);
        this.dealerWin = dealerWin;
    }


    public void updateHealth(int health, int[] color) {
        if (dealerWin) {
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




