package com.cs2340group7.games;


import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class WordleTiles {
    private LinearLayout ui;
    private Stack<Character> tiles;
    private int rowsCompleted = 0;
    private HashMap<Integer, Integer> colorsMap;
    public WordleTiles(LinearLayout ui) {
        this.ui = ui;
        tiles = new Stack<>();
        colorsMap = new HashMap<Integer, Integer>() {{
            put(0, R.drawable.single_tile);
            put(1, R.drawable.wrong_pos_tile);
            put(2, R.drawable.correct_tile);
            put(3, R.drawable.wrong_tile);
        }};
    }

    public HashMap<Integer, Integer> getColorsMap() {
        return colorsMap;
    }

    public void update(String key) {
        if (tiles.size() > getAllChildren().size()) {
            return;
        }
        switch (key){
            case "ENTER":
                if (tiles.size() % 5 == 0 && rowsCompleted * 5 != tiles.size()) {
                    WordleController.getInstance().attempts++;
                    Character[] guess = new Character[5];
                    for (int i = 0; i < 5; i++) {
                        guess[i] = tiles.get((rowsCompleted * 5) + i);
                    }
                    int[] answers = WordleController.getInstance().checkWord(guess);
                    Integer[] answerColors = new Integer[5];
                    for (int i = 0; i < answers.length; i++) {
                        answerColors[i] = colorsMap.get(answers[i]);
                    }
                    changeRowTiles(rowsCompleted, answerColors);
                    rowsCompleted++;
                    if (Arrays.equals(answers, new int[]{2, 2, 2, 2, 2})) {
                        WordleController.getInstance().getKeyboard().hideKeyboard();
                        Toast.makeText(ui.getContext(), String.format("You won in %d %s!", rowsCompleted, rowsCompleted == 1 ? "attempt" : "attempts"), Toast.LENGTH_LONG).show();
                        WordleController.getInstance().increaseScore();
                        WordleController.getInstance().displayPlayAgain();
                        switch(rowsCompleted) {
                            case (6):
                                WordleController.getInstance().wonInSix++;
                                break;
                            case (5):
                                WordleController.getInstance().wonInFive++;
                                break;
                            case (4):
                                WordleController.getInstance().wonInFour++;
                                break;
                            case (3):
                                WordleController.getInstance().wonInThree++;
                                break;
                            case (2):
                                WordleController.getInstance().wonInTwo++;
                                break;
                            case (1):
                                WordleController.getInstance().wonInOne++;
                                break;
                        }
                    } else if (rowsCompleted == 6) {
                        WordleController.getInstance().getKeyboard().hideKeyboard();
                        WordleController.getInstance().fails++;
                        Toast.makeText(ui.getContext(), String.format("You lost, the word was %s.", WordleController.getInstance().getKey()), Toast.LENGTH_LONG).show();
                        WordleController.getInstance().displayPlayAgain();
                    }
                    Log.d("testsa", String.format("%d %d %d %d %d", answers[0], answers[1], answers[2], answers[3], answers[4]));
                    WordleController.getInstance().getHealthBar().updateHealth(rowsCompleted, answers);
                    //WordleController.getInstance().getKeyboard().updateKeyColors();
                }
            case "DELETE":
                if (tiles.size() > rowsCompleted * 5) {
                    tiles.pop();
                } // does not pop if the row is already complete
                break;
            default:
                if ((rowsCompleted + 1) * 5 > tiles.size()) {
                    tiles.push(key.charAt(0));
                }
                break;
        }

        Character[] tilesArray = tiles.toArray(new Character[tiles.size()]);
        ArrayList<TextView> textView = getAllChildren();
        if (tiles.size() > textView.size()) {
            return;
        }
        for(int i = 0; i < textView.size(); i++) {
            textView.get(i).setText("");
        }
        for (int i = 0; i < tiles.size(); i++) {
            TextView textBox = textView.get(i);
            textBox.setText(tilesArray[i].toString());
        }
    }
    private ArrayList<TextView> getAllChildren() {
        ArrayList<TextView> textViews = new ArrayList<>();
        for (int i = 0; i < ui.getChildCount(); i++) {
            LinearLayout row = (LinearLayout) ui.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++) {
               textViews.add((TextView) row.getChildAt(j));
            }
        }
        return textViews;
    }

    public void changeRowTiles(int rowNum, Integer[] tiles) {
        if (tiles.length != 5) {
            throw new IllegalArgumentException("Need to specify 5 colors");
        }
        LinearLayout row = (LinearLayout) ui.getChildAt(rowNum);
        for (int i = 0; i < row.getChildCount(); i++) {
            if (tiles[i] != null) {
                TextView tile = (TextView) row.getChildAt(i);
                tile.setBackgroundResource(tiles[i]);
            }
        }
    }

    public void resetGame() {
        ArrayList<TextView> tileUI = getAllChildren();
        for (int i = 0; i < tileUI.size(); i++) {
            tileUI.get(i).setText("");
            tileUI.get(i).setBackgroundResource(colorsMap.get(0));
        }
        tiles = new Stack<>();
        rowsCompleted = 0;
    }
}
