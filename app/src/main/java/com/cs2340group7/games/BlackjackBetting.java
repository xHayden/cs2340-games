package com.cs2340group7.games;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class BlackjackBetting {
    private ImageButton coin1;
    private ImageButton coin2;
    private ImageButton coin3;
    private TextView scoreTextView;
    private int score;
    private int betAmount;
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

    public BlackjackBetting getInstance() {
        return this;
    }

    public void instantiateView(View view) {
        coin1 = view.findViewById(R.id.coin1);
        coin2 = view.findViewById(R.id.coin2);
        coin3 = view.findViewById(R.id.coin3);
        scoreTextView = view.findViewById(R.id.score);
        updateScoreText();


        coin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("before", String.format("%s", betAmount));
                betAmount += 5;
                Log.d("after", String.format("%s", betAmount));
                decreaseScore(5);
                Log.d("afterds", String.format("%s", betAmount));
            }
        });

        coin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                betAmount += 10;
                decreaseScore(10);
            }
        });

        coin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                betAmount += 20;
                decreaseScore(20);

            }
        });
    }

        public void decreaseScore(int lowerScore) {
            score -= lowerScore;

            if(score < 0) {
                score = 0;
            }
            updateScoreText();
    }

    public void updateScore(Boolean win) {
        Log.d("asd", String.format("%s",win));
        if (win) {
            Log.d("betAmount", String.format("%s", betAmount));
            Log.d("score", String.format("%s", score));
            score += betAmount*2;
        } else {
            score += betAmount;
        }
    }
        public void updateScoreText() {
            scoreTextView.setText("Score: " + score);
        }
    }

