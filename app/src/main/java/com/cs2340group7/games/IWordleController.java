package com.cs2340group7.games;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public interface IWordleController {
    ITiles getTiles();
    IHealthBar getHealthBar();
    void setHealthBar(LinearLayout healthBarUI, IHealthBar healthBar);
    void setTiles(LinearLayout tilesUI, ITiles tiles);
    void setScoreboard(TextView scoreboardUI, IScoreboard scoreboard);
    void setKeyboard(IKeyboard keyboard);
    void setScoreboardNoUI(IScoreboard scoreboard);
    void setTilesNoUI(ITiles tiles);
    void setHealthBarNoUI(IHealthBar healthBar);
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
