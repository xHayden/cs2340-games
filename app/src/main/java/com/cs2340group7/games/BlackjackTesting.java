package com.cs2340group7.games;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BlackjackTesting {

    private BlackjackDealer dealer;
    private BlackjackPlayer player;

    private BlackjackBetting betting;

    @Before
    public void setUp() {
        dealer = new BlackjackDealer();
        player = new BlackjackPlayer();
        betting = BlackjackBetting.getInstance();
    }

    @Test
    public void dealerPlayMove() {
        dealer.playMove(new HitStrategy(new BlackjackCard(Suit.HEARTS, Rank.TWO, 2)));
        assertEquals(2, dealer.getScore());
        assertFalse(dealer.checkBust());

        dealer.playMove(new HitStrategy(new BlackjackCard(Suit.HEARTS, Rank.JACK, 10)));
        assertEquals(12, dealer.getScore());
        assertFalse(dealer.checkBust());

        dealer.playMove(new HitStrategy(new BlackjackCard(Suit.HEARTS, Rank.KING, 10)));
        assertEquals(22, dealer.getScore());
        assertTrue(dealer.checkBust());
    }



}
