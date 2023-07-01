package com.cs2340group7.games;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Random;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Stack;

    public class WordleController {
    // When using WordleController, get it with
    // WorldeController wordleController = WordleController.getInstance();
    static WordleController instance;

        private WordleScoreboard scoreboard;
    private WordleTiles tiles;
    private WordleKeyboard keyboard;
    private WordleHealthBar healthBar;
    private ProgressBar healthBarUI;
    private LinearLayout tilesUI;
    private TextView scoreboardUI;
    Random rand = new Random();
    String key = WordleWordBank.wordleBank[rand.nextInt(WordleWordBank.wordleBank.length)];


        static int wonInOne;
    static int wonInTwo;
    static int wonInThree;
    static int wonInFour;
    static int wonInFive;
    static int wonInSix;
    static int fails;
    static int attempts;

    private WordleController() {
    }

    public static WordleController getInstance() {
        if (instance == null) {
            instance = new WordleController();
        }
        return instance;
    }

    public void setHealthBar(ProgressBar healthBarUI) {
        this.healthBarUI = healthBarUI;
        this.healthBar = new WordleHealthBar(healthBarUI);
    }

    public void setTiles(LinearLayout tilesUI) {
        this.tilesUI = tilesUI;
        this.tiles = new WordleTiles(tilesUI);
    }

    public void setScoreboard(TextView scoreboardUI) {
        this.scoreboardUI = scoreboardUI;
        this.scoreboard = new WordleScoreboard(scoreboardUI);
    }

    public void setKeyboard(WordleKeyboard keyboard) {
        this.keyboard = keyboard;
    }

    public void onKeyPress(String key) {
        tiles.update(key);
    }

    public int[] checkWord(Character[] answer) {
        char[] arrayKey = key.toCharArray();
        int[] color = new int[]{0, 0, 0, 0, 0};
        boolean[] keyUsed = new boolean[5];
        boolean[] answerUsed = new boolean[5];

        //Check Greens
        for (int i = 0; i < 5; i++) {
            if (answer[i] == arrayKey[i]) {
                color[i] = 2;
                keyUsed[i] = true;
                answerUsed[i] = true;
            }
        }

        //Check Yellows (for those unused)
        for (int i = 0; i < 5; i++) {
            if (!answerUsed[i]) {
                for (int j = 0; j < 5; j++) {
                    if (!keyUsed[j] && answer[i] == arrayKey[j]) {
                        color[i] = 1;
                        keyUsed[j] = true;
                        break;
                    }
                }
            }
        }

        return color;
    }
}
