package com.cs2340group7.games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackjackDeck implements IBlackjackDeck {
    private final List<IBlackjackCard> deck = new ArrayList<>();
    public BlackjackDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                int imageResource = getCardImageResource(rank, suit);
                deck.add(new BlackjackCard(suit, rank, imageResource));
            }
        }
        Collections.shuffle(deck);
    }

    private int getCardImageResource(Rank rank, Suit suit) {
        String resourceName = "card_" + suit.toString().toLowerCase() + "_" + rank.toString().toLowerCase();
        int resource =  BlackjackController.getInstance().getBlackjackContext().getResources()
                .getIdentifier(resourceName, "drawable", BlackjackController.getInstance().getBlackjackContext().getPackageName());
        return resource;
    }

    public IBlackjackCard dealCard() {
        if(deck.isEmpty()) {
            return null;
        }
        // Removes a card from the deck and returns it
        return deck.remove(deck.size() - 1);
    }
}
