package com.cs2340group7.games;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public interface IWordleController {
    ITiles getTiles();
    IHealthBar getHealthBar();
    void setHealthBar(LinearLayout healthBar);
    void setTiles(LinearLayout tiles);
    void setScoreboard(TextView scoreboardUI, TextView playAgainScore);
    void setKeyboard(IKeyboard keyboard);
    void setScoreboardNoUI();
    void setTilesNoUI();
    void setHealthBarNoUI();
    IKeyboard getKeyboard();
    void onKeyPress(String key);
    void setPlayAgainButton(Button playAgainButton);
    void setPlayAgain(LinearLayout playAgain);
    int[] checkWord(Character[] answer);
    String getKey();
    String newKey();
    void setKey(String key);
    void displayPlayAgain();
    void increaseScore();
}
