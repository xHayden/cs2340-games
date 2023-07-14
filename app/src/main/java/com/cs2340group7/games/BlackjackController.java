package com.cs2340group7.games;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class BlackjackController extends Observable implements IBlackjackController, IPlayersObserver {
    private static IBlackjackController instance;
    private int playerScore;
    private int dealerScore;
    private List<IPlayer> players; // both human and dealer
    private Blackjack blackjackFragment;
    private BlackjackController() {
        playerScore = 0;
        dealerScore = 0;
        addObserver(new BlackjackPlayer()); // this could be done programmatically
        addObserver(new BlackjackDealer());

    }

    public static IBlackjackController getInstance() {
        if (instance == null) {
            instance = new BlackjackController();
        }
        return instance;
    }

    public void update(ScoreUpdate su) {
        if (su.getPlayerType() == PlayerType.HUMAN) { // not ideal but I can't think of a better way
            playerScore = su.getScore();
            updatePlayerScore(playerScore);
            if (su.getStanding()) {
                // final move for dealer, maybe emit to dealer idk
            }
        } else if (su.getPlayerType() == PlayerType.DEALER) {
            dealerScore = su.getScore();
            updateDealerScore(dealerScore);
            if (su.getStanding()) {
                // final move for player
            }
        }
    }

    public void updatePlayerScore(int score) {
        if (blackjackFragment != null) {
            blackjackFragment.updatePlayerScore(score);
        }
    }

    public void updateDealerScore(int score) {
        if (blackjackFragment != null) {
            blackjackFragment.updateDealerScore(score);
        }
    }

    @Override
    public void notifyObservers() {
        for (IPlayer observer : players) {
        }
    }

    public void addObserver(Observer o) {
        super.addObserver(o);
        if (o instanceof IPlayer) {
            this.players.add((IPlayer) o);
        }
    }


    // player.makeMove(new StandStrategy());
    //  player.makeMove(new HitStrategy(new BlackjackCard(6)));
    // ScoreboardController.getInstance().setScore(2);
    // ScoreboardController.getInstance().getScore();
    // LivesController.getInstance().betLives(2);
    // LivesController.getInstance().won();
}
