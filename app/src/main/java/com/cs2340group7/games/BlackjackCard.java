package com.cs2340group7.games;

import android.view.View;
import android.widget.ImageView;

public class BlackjackCard implements ICard, UIComponent {
    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }

    public enum Rank {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
        JACK(10), QUEEN(10), KING(10), ACE(11);

        private final int value;

        Rank(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private final Suit suit;
    private final Rank rank;
    private final int imageResource;
    private ImageView ui;

    public BlackjackCard(Suit suit, Rank rank, int imageResource) {
        this.suit = suit;
        this.rank = rank;
        this.imageResource = imageResource;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public int getValue() {
        return rank.getValue();
    }

    @Override
    public void setUI(View view) {
        if(view instanceof ImageView) {
            this.ui = (ImageView) view;
            this.update();
        }
    }

    @Override
    public ImageView getUI() {
        return this.ui;
    }

    public void update() {
        if(this.ui != null) {
            this.ui.setImageResource(imageResource);
        }
    }
}
