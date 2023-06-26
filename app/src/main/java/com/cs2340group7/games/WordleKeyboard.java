package com.cs2340group7.games;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class WordleKeyboard {
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

        keys = new Button[keyLabels.length * keyLabels[0].length];

        // Create and add the keys to the keyboard layout
        for (int i = 0; i < keyLabels.length; i++) {
            LinearLayout rowLayout = new LinearLayout(context);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            rowLayout.setGravity(Gravity.CENTER);

            for (int j = 0; j < keyLabels[i].length; j++) {
                keys[i * keyLabels[i].length + j] = new Button(context);
                keys[i * keyLabels[i].length + j].setText(keyLabels[i][j]);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(10, 10, 10, 10);

                keys[i * keyLabels[i].length + j].setLayoutParams(params);
                rowLayout.addView(keys[i * keyLabels[i].length + j]);
            }

            keyboardContainer.addView(rowLayout);
        }

        // Add click listeners to the keys
        setKeyListeners();
    }

    private void setKeyListeners() {
        for (int i = 0; i < keys.length; i++) {
            final int index = i;
            keys[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String key = keys[index].getText().toString();
                    WordleController.getInstance().onKeyPress(key);
                }
            });
        }
    }
}
