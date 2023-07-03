package com.cs2340group7.games;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

public class WordleKeyboard implements IKeyboard {
    private final Button[] keys;

    public WordleKeyboard(Context context, LinearLayout keyboardContainer) {
        WordleController wordleController = WordleController.getInstance();

        // Define the keys and their labels
        String[][] keyLabels = {
                {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"},
                {"A", "S", "D", "F", "G", "H", "J", "K", "L"},
                {"Z", "X", "C", "V", "B", "N", "M"},
                {"", "ENTER", "DELETE"}
        };

        int labelsCounter = 0;
        for (int i = 0; i < keyLabels.length; i++) {
            labelsCounter += keyLabels[i].length;
        }
        keys = new Button[labelsCounter];
        int currKey = 0;

        // Create and add the keys to the keyboard layout
        for (int i = 0; i < keyLabels.length; i++) {
            LinearLayout rowLayout = new LinearLayout(context);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            rowLayout.setGravity(Gravity.CENTER);

            for (int j = 0; j < keyLabels[i].length; j++) {
                keys[currKey] = new Button(context);
                keys[currKey].setText(keyLabels[i][j]);

                // Determine the layout parameters based on the text of the button
                LinearLayout.LayoutParams params;
                if (keyLabels[i][j].length() > 1) {  // If the button text is more than 1 character long
                    params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.weight = 2; // Increase the weight for longer text
                } else {
                    params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.weight = 1; // Smaller weight for single character
                }

                params.setMargins(0, 2, 0, 2);
                keys[currKey].setLayoutParams(params);
                rowLayout.addView(keys[currKey]);
                currKey++;
            }


            keyboardContainer.addView(rowLayout);
        }

        // Add click listeners to the keys
        setKeyListeners();

    }

    private void setKeyListeners() {
        for (int i = 0; i < keys.length; i++) {
            final int index = i;
            keys[i].setOnClickListener(v -> {
                String key = keys[index].getText().toString();
                WordleController.getInstance().onKeyPress(key);
            });
        }
    }

    public void hideKeyboard() {
        for (Button key : keys) {
            key.setVisibility(View.GONE);
        }
    }

    public void showKeyboard() {
        for (Button key : keys) {
            key.setVisibility(View.VISIBLE);
        }
    }

    private final Map<Integer, Integer> colorMapping = new HashMap<Integer, Integer>() {{
        put(0, Color.GRAY);    // default
        put(1, Color.YELLOW);  // wrong position
        put(2, Color.GREEN);   // correct
        put(3, Color.RED);     // wrong
    }};

    /*public void updateKeyColors() {
        keys[0].setTextColor(colorMapping.get(0));
    }*/

}
