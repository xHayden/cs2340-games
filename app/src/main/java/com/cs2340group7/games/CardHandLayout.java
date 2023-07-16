package com.cs2340group7.games;


import android.widget.ImageView;
import android.widget.LinearLayout;

public class CardHandLayout implements ICardHandLayout {
    private LinearLayout layout;

    public CardHandLayout(LinearLayout previousLayout) {
        this.layout = previousLayout;
        resetCards(previousLayout);
    }

    private LinearLayout resetLayout(LinearLayout previousLayout) {
        previousLayout.removeAllViews();
//        LinearLayout layout = new LinearLayout(BlackjackController.getInstance().getBlackjackContext());
//        layout.setOrientation(LinearLayout.HORIZONTAL);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                350,  // width
//                90  // height
//        );
//        layoutParams.setMargins(
//                30,  // start
//                10,  // top
//                50,  // end
//                0  // bottom
//        );
//        layout.setLayoutParams(layoutParams);
        return layout;
    }

    public void addCard(int resource) {
        if (resource == 0) {
            return;
        }
        ImageView card = new ImageView(BlackjackController.getInstance().getBlackjackContext());
        card.setImageResource(resource);
        LinearLayout.LayoutParams cardLayoutParams = new LinearLayout.LayoutParams(
                dpToPx(70),  // width
                dpToPx(90)  // height
        );
        card.setLayoutParams(cardLayoutParams);
        layout.addView(card);
    }

    public void resetCards(LinearLayout previousLayout) {
        resetLayout(previousLayout);
    }

    private int dpToPx(int dp) {
        return (int) (dp * BlackjackController.getInstance().getBlackjackContext().getResources().getDisplayMetrics().density);
    }
}
