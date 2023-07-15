package com.cs2340group7.games;

public interface IBlackjackCard extends ICard {
    Rank getRank();
    Suit getSuit();
    int getImageResource();
    void setImageResource(int res);
}
