package com.cs2340group7.games;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class WordleHealthBar {
//    private ProgressBar ui;
//    public WordleHealthBar(ProgressBar ui) {
//        this.ui = ui;
//    }
//    public void update(int value) {
//        this.ui.setProgress(value);
//    }
    public void updateHealth(){
        ImageView image1 = (ImageView) findViewById(R.id.heart1);
        ImageView image2 = (ImageView) findViewById(R.id.heart2);
        ImageView image3 = (ImageView) findViewById(R.id.heart3);
        switch(num){
            case 1:
                image3.setImageResource(R.drawable.halfheart);
                break;
            case 2:
                image3.setImageResource(R.drawable.emptyheart);
                break;
            case 3:
                image2.setImageResource(R.drawable.halfheart);
                break;
            case 4:
                image2.setImageResource(R.drawable.emptyheart);
                break;
            case 5:
                image1.setImageResource(R.drawable.halfheart);
                break;
            case 6:
                image1.setImageResource(R.drawable.emptyheart);
        }
        }
    }
}
