package com.cs2340group7.games;

public class HiddenHitStrategy implements IMoveStrategy {
    private IBlackjackCard card;

    public HiddenHitStrategy(IBlackjackCard card) {
        this.card = card;
    }

    public void move(IPlayer player) {
        int resource =  BlackjackController.getInstance().getBlackjackContext().getResources()
                .getIdentifier("hidden_card", "drawable", BlackjackController.getInstance().getBlackjackContext().getPackageName());
        card.setImageResource(resource);
        player.hit(card);
    }
}
