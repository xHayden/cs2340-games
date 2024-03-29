package com.cs2340group7.games;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackjackDeck implements IBlackjackDeck {
    private final List<IBlackjackCard> deck = new ArrayList<>();
    private static BlackjackDeck instance;

    BlackjackDeck() {
        this.reset();
    }

    public static BlackjackDeck getInstance() {
        if (instance == null) {
            instance = new BlackjackDeck();
        }
        return instance;
    }

    @Override
    public void reset() {
        deck.clear();
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
        int resource = BlackjackController.getInstance().getBlackjackContext().getResources()
                .getIdentifier(resourceName, "drawable", BlackjackController.getInstance().getBlackjackContext().getPackageName());
        return resource;
    }

    public IBlackjackCard dealCard() {
        if(deck.isEmpty()) {
            return null;
        }
        // Removes a card from the deck and returns it
        IBlackjackCard card = deck.remove(deck.size() - 1);
        if (card instanceof BlackjackCard) {
            ImageView cardView = ((BlackjackCard) card).getUI();
            if (cardView != null) {
                Animation animation = AnimationUtils.loadAnimation(BlackjackController.getInstance().getBlackjackContext(), R.anim.deal_card);
                cardView.startAnimation(animation);
            }
        }
        return card;
    }

}

