package com.cs2340group7.games;

import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class WordleTiles {
    private LinearLayout ui;
    private Stack<Character> tiles;
    public WordleTiles(LinearLayout ui) {
        this.ui = ui;
        tiles = new Stack<Character>();
    }

    public void setClickEvents() {
        for (int i = 0; i < ui.getChildCount(); i++) {
            TextView textBox = (TextView) ui.getChildAt(i);
            textBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public void update(String key) {
        if (tiles.size() >= getAllChildren().size()) {
            return;
        }

        int row = (tiles.size() / 5);

        switch (key){
            case "ENTER":
                if (tiles.size() % 5 == 0) {
                    break;
                } //breaks if row is completed
            case "DELETE":
                if (!(tiles.size() == row * 5)) {
                    tiles.pop();
                } // does not pop if the row is already complete
                Log.d("deleteT",key.toString());
                break;
            default:
                if (!(tiles.size() > (row + 1) * 5)) {
                    tiles.push(key.charAt(0));
                } //have logic and then move to next row
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
            Log.d("update",textBox.toString());
            Log.d("notworking",tilesArray[i].toString());
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
}
