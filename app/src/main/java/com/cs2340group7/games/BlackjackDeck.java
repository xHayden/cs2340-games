package com.cs2340group7.games;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackjackDeck {
    private final List<BlackjackCard> deck = new ArrayList<>();
    private Context context;

    public BlackjackDeck(Context context) {
        this.context = context;

        for (BlackjackCard.Suit suit : BlackjackCard.Suit.values()) {
            for (BlackjackCard.Rank rank : BlackjackCard.Rank.values()) {
                int imageResource = getCardImageResource(rank, suit);
                deck.add(new BlackjackCard(suit, rank, imageResource));
            }
        }

        Collections.shuffle(deck);
    }

    private int getCardImageResource(BlackjackCard.Rank rank, BlackjackCard.Suit suit) {
        String resourceName = "card_" + suit.toString().toLowerCase() + "_" + rank.toString().toLowerCase();
        return context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
    }

    public BlackjackCard dealCard() {
        if(deck.isEmpty()) {
            return null;
        }
        // Removes a card from the deck and returns it
        return deck.remove(deck.size() - 1);
    }
}
