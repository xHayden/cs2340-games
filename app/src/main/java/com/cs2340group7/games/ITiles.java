package com.cs2340group7.games;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;

public interface ITiles {
    void update(String key);
    ArrayList<TextView> getAllChildren();
    void changeRowTiles(int rowNum, Integer[] tiles);
    void resetGame();
    void resetTilesNoUI();
    Stack<Character> getTiles();
    int getRowsCompleted();
}
