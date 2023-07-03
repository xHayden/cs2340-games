package com.cs2340group7.games;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class WordleController implements IWordleController {
    // When using WordleController, get it with
    // WorldeController wordleController = WordleController.getInstance();
    static WordleController instance;
    IScoreboard scoreboard;
    private ITiles tiles;
    private IKeyboard keyboard;
    private IHealthBar healthBar;
    private LinearLayout healthBarUI;
    private LinearLayout tilesUI;
    private TextView scoreboardUI;
    private Button playAgainButton;
    private LinearLayout playAgain;
    Random rand = new Random();
    String key = WordleWordBank.wordleBank[rand.nextInt(WordleWordBank.wordleBank.length)];
    int wonInOne;
    int wonInTwo;
    int wonInThree;
    int wonInFour;
    int wonInFive;
    int wonInSix;
    int fails;
    int attempts;

    private WordleController() {
    }

    public ITiles getTiles() {
        return tiles;
    }

    public static WordleController getInstance() {
        if (instance == null) {
            instance = new WordleController();
        }
        return instance;
    }

    public IHealthBar getHealthBar() {
        return healthBar;
    }

    public void setHealthBar(LinearLayout healthBarUI, IHealthBar healthBar) {
        this.healthBarUI = healthBarUI;
        this.healthBar = healthBar;
    }

    public void setTiles(LinearLayout tilesUI, ITiles wordleTiles) {
        this.tilesUI = tilesUI;
        this.tiles = wordleTiles;
    }

    public void setScoreboard(TextView scoreboardUI, IScoreboard wordleScoreboard) {
        this.scoreboardUI = scoreboardUI;
        this.scoreboard = wordleScoreboard;
    }

    public void setKeyboard(IKeyboard keyboard) {
        this.keyboard = keyboard;
    }

    public void setScoreboardNoUI(IScoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public void setTilesNoUI(ITiles tiles) {
        this.tiles = tiles;
    }

    public void setHealthBarNoUI(IHealthBar wordleHealthBar) {
        this.healthBar = wordleHealthBar;
    }

    public IKeyboard getKeyboard() {
        return keyboard;
    }

    public void onKeyPress(String key) {
        tiles.update(key);
    }

    public void setPlayAgainButton(Button playAgainButton) {
        this.playAgainButton = playAgainButton;
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiles.resetGame();
                key = newKey();
                playAgain.setVisibility(View.GONE);
                keyboard.showKeyboard();
                WordleController.getInstance().getHealthBar().reset();
            }
        });
    }

    public void setPlayAgain(LinearLayout playAgain) {
        this.playAgain = playAgain;
    }

    public int[] checkWord(Character[] answer) {
        if (answer == null) {
            throw new NullPointerException("Answer array cannot be null");
        }

        if (answer.length == 0) {
            return new int[]{}; // returns empty array if input array is empty
        }
        char[] arrayKey = key.toCharArray();
        int[] color = new int[]{3, 3, 3, 3, 3};
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

    public String getKey() {
        if (key == null) {
            key = newKey();
        }
        return key;
    }

    public String newKey() {
        return WordleWordBank.wordleBank[rand.nextInt(WordleWordBank.wordleBank.length)];
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void displayPlayAgain() {
        playAgain.setVisibility(View.VISIBLE);
    }

    public void increaseScore() {
        scoreboard.increase();
    }
}
