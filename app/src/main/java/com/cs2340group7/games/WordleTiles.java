package com.cs2340group7.games;

import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class WordleTiles {
    private LinearLayout ui;
    private Stack<Character> tiles;
    private int rowsCompleted = 0;
    public WordleTiles(LinearLayout ui) {
        this.ui = ui;
        tiles = new Stack<>();
    }

    public void update(String key) {
        if (tiles.size() >= getAllChildren().size()) {
            return;
        }

        switch (key){
            case "ENTER":
                if (tiles.size() % 5 == 0 && rowsCompleted * 5 != tiles.size()) {
                    Character[] guess = new Character[5];
                    for (int i = 0; i < 5; i++) {
                        guess[i] = tiles.get((rowsCompleted * 5) + i);
                    }
                    int[] answers = WordleController.getInstance().checkWord(guess);
                    Integer[] answerColors = new Integer[5];
                    HashMap<Integer, Integer> colorsMap = new HashMap<Integer, Integer>() {{
                        put(0, R.drawable.single_tile);
                        put(1, R.drawable.wrong_pos_tile);
                        put(2, R.drawable.correct_tile);
                    }};
                    for (int i = 0; i < answers.length; i++) {
                        answerColors[i] = colorsMap.get(answers[i]);
                    }
                    changeRowTiles(rowsCompleted, answerColors);
                    rowsCompleted++;
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
        WordleController.getInstance().updateTiles(tilesArray);
        ArrayList<TextView> textView = getAllChildren();
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
}
