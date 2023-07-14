package com.cs2340group7.games;

import android.view.View;
import android.widget.ImageView;

public class BlackjackCard implements ICard, UIComponent, IBlackjackCard {
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
        if (view instanceof ImageView) {
            this.ui = (ImageView) view;
            this.update();
        }
    }

    @Override
    public ImageView getUI() {
        return this.ui;
    }

    public void update() {
        if (this.ui != null) {
            this.ui.setImageResource(imageResource);
        }
    }
}
