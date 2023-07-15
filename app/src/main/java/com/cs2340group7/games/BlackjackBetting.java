package com.cs2340group7.games;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class BlackjackBetting {
    private ImageButton coin1;
    private ImageButton coin2;
    private ImageButton coin3;
    private TextView scoreTextView;
    private int score;

    public void instantiateView(View view) {
        coin1 = view.findViewById(R.id.coin1);
        coin2 = view.findViewById(R.id.coin2);
        coin3 = view.findViewById(R.id.coin3);
        scoreTextView = view.findViewById(R.id.score);
        score = 50;
        updateScoreText();


        coin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseScore(5);
            }
        });

        coin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseScore(10);
            }
        });

        coin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseScore(20);
            }
        });
    }

        private void decreaseScore(int lowerScore) {
            score -= lowerScore;

            if(score < 0) {
                score = 0;
            }
            updateScoreText();
    }
        private void updateScoreText() {
            scoreTextView.setText("Score: " + score);
        }
    }

