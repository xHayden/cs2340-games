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

    @Test
    public void playerPlayMove() {
        player.playMove(new HitStrategy(new BlackjackCard(Suit.HEARTS, Rank.TWO, 2)));
        assertEquals(2, player.getScore());
        assertFalse(player.checkBust());

        player.playMove(new HitStrategy(new BlackjackCard(Suit.HEARTS, Rank.JACK, 10)));
        assertEquals(12, player.getScore());
        assertFalse(player.checkBust());

        player.playMove(new HitStrategy(new BlackjackCard(Suit.HEARTS, Rank.KING, 10)));
        assertEquals(22, player.getScore());
        assertTrue(player.checkBust());
    }
    @Test(expected = IllegalStateException.class)
    public void playerCannotHitAfterStanding() {
        player.playMove(new StandStrategy());
        player.playMove(new HitStrategy(new BlackjackCard(Suit.HEARTS, Rank.TWO, 2)));
    }
    @Test
    public void playerReset() {
        player.playMove(new HitStrategy(new BlackjackCard(Suit.HEARTS, Rank.KING, 10)));
        player.reset();
        assertEquals(0, player.getScore());
        assertFalse(player.checkBust());
    }
    @Test
    public void dealerReset() {
        dealer.playMove(new HitStrategy(new BlackjackCard(Suit.HEARTS, Rank.KING, 10)));
        dealer.reset();
        assertEquals(0, dealer.getScore());
        assertFalse(dealer.checkBust());
    }



}
