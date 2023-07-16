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
    @Test
    public void testPlayerHit() {
        IBlackjackCard card = new BlackjackCard(Suit.CLUBS, Rank.FIVE, 0);
        player.hit(card);
        assertEquals(5, player.getScore());
    }

    @Test
    public void testPlayerBust() {
        IBlackjackCard card1 = new BlackjackCard(Suit.CLUBS, Rank.TEN, 0);
        IBlackjackCard card2 = new BlackjackCard(Suit.HEARTS, Rank.TEN, 0);
        IBlackjackCard card3 = new BlackjackCard(Suit.DIAMONDS, Rank.FIVE, 0);
        player.hit(card1);
        player.hit(card2);
        player.hit(card3);
        assertTrue(player.checkBust());
    }

    @Test
    public void testBlackjackBettingClearBetAmount() {
        betting.setBetAmount(10);
        betting.clearBetAmount();
        assertEquals(0, betting.getBetAmount());
    }



    @Test
    public void testDealerStand() {
        IBlackjackCard card1 = new BlackjackCard(Suit.CLUBS, Rank.SEVEN, 0);
        IBlackjackCard card2 = new BlackjackCard(Suit.HEARTS, Rank.SEVEN, 0);
        dealer.hit(card1);
        dealer.hit(card2);
        dealer.stand();
        assertEquals(14, dealer.getScore());
    }


    @Test
    public void testDealerHitUntil17() {
        IBlackjackCard card1 = new BlackjackCard(Suit.CLUBS, Rank.EIGHT, 0);
        IBlackjackCard card2 = new BlackjackCard(Suit.HEARTS, Rank.SEVEN, 0);
        IBlackjackCard card3 = new BlackjackCard(Suit.DIAMONDS, Rank.TWO, 0);
        dealer.hit(card1);
        dealer.hit(card2);
        dealer.hit(card3);
        // Assuming the dealer hits until their score is 17 or more
        assertEquals(17, dealer.getScore());
    }

    @Test
    public void testPlayerStand() {
        IBlackjackCard card1 = new BlackjackCard(Suit.CLUBS, Rank.EIGHT, 0);
        IBlackjackCard card2 = new BlackjackCard(Suit.HEARTS, Rank.THREE, 0);
        player.hit(card1);
        player.hit(card2);
        player.stand();
        assertEquals(11, player.getScore());
    }



}
