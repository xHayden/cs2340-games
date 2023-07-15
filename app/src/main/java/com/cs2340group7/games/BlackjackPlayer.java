package com.cs2340group7.games;

import android.widget.Toast;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

public class BlackjackPlayer extends Observable implements IPlayer, Observer, IGameSignalObserver, IPlayersObserver, IPlayersObservable {
    private int score;
    private HashSet<IPlayersObserver> observers;
    private boolean standing;
    private int hitTimes;
    private IBlackjackCard recentCard;

    public BlackjackPlayer() {
        this.score = 0;
        this.standing = false;
        this.observers = new HashSet<>();
        this.hitTimes = 0;
    }

    public void playMove(IMoveStrategy strategy) {
        strategy.move(this);
    }

    public void setHitTimes(int hitTimes) {
        this.hitTimes = hitTimes;
    }

    public void hit(IBlackjackCard card) {
        if (hitTimes >= 5) {
            Toast.makeText(BlackjackController.getInstance().getBlackjackContext(), "You cannot hit more than 5 times!", Toast.LENGTH_LONG).show();
            return;
        }
        if (!standing) {
            score += card.getValue();
            recentCard = card;
            setChanged();
            notifyObservers();
        } else {
            throw new IllegalStateException("Cannot hit after standing");
        }
        hitTimes++;
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
            observer.update(new ScoreUpdate(PlayerType.HUMAN, score, standing, checkBust(), recentCard));
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
    public boolean checkBust() {
        return this.score > 21;
    }

    @Override
    public void update(Observable o, Object arg) {
        // general observer update method
    }

    @Override
    public void update(IGameSignalObservable o, GameSignals signal) {
        // game states go through here :) out of money from lives controller
    }

    public void registerObserver(IPlayersObserver observer) {
        this.observers.add(observer);
    }

    public void deregisterObserver(IPlayersObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void reset() {
        hitTimes = 0;
        score = 0;
        hitTimes = 0;
        standing = false;
    }
}
