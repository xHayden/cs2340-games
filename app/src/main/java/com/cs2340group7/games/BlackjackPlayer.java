package com.cs2340group7.games;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class BlackjackPlayer extends Observable implements IPlayer, Observer, IGameSignalObserver, IPlayersObserver {
    private int score;
    private List<IPlayersObserver> observers;
    private boolean standing;

    public BlackjackPlayer() {
        this.score = 0;
        this.standing = false;
    }

    public void playMove(IMoveStrategy strategy) {
        strategy.move(this);
    }

    public void hit(ICard card) {
        score += card.getValue();
        setChanged();
        notifyObservers();
    }

    public void stand() {
        // notify to observer that you're standing
        this.standing = true;
        setChanged();
        notifyObservers();
    }

    public int getScore() {
        return this.score;
    }

    @Override
    public void notifyObservers() {
        for (IPlayersObserver observer : observers) {
            observer.update(new ScoreUpdate(PlayerType.HUMAN, score, standing));
        }
    }

    public void update(Observable o, GameSignals signal) {
        if (signal == GameSignals.OUT_OF_MONEY) {

        }
    }

    public void update(ScoreUpdate scoreUpdate) {
        // score update from players
    }

    @Override
    public void update(Observable o, Object arg) {
        // general observer update method
    }

    @Override
    public void update(IGameSignalObservable o, GameSignals signal) {
        // game states go through here :) out of money from lives controller
    }
}
