package com.cs2340group7.games;

import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class WordleController {
    // When using WordleController, get it with
    // WorldeController wordleController = WordleController.getInstance();
    static WordleController instance;
    private WordleScoreboard scoreboard;
    private WordleTiles tiles;
    private WordleKeyboard keyboard;
    private WordleHealthBar healthBar;
    private ProgressBar healthBarUI;
    private RecyclerView tilesUI;
    private TextView scoreboardUI;

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

    public void setTiles(RecyclerView tilesUI) {
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
    }
}
