package com.cs2340group7.games;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

public class BlackjackDealer extends Observable implements IPlayer, Observer, IPlayersObservable {
    private int score;
    private HashSet<IPlayersObserver> observers;
    private boolean standing;
    private IBlackjackCard recentCard;

    public BlackjackDealer() {
        this.score = 0;
        this.standing = false;
        observers = new HashSet<>();
    }

    public void playAIMove() {
        // decide what strategy to use
        IMoveStrategy aiMove;
        if (score >= 17) {
            aiMove = new StandStrategy();
            playMove(aiMove);
        } else {
            IBlackjackCard card = BlackjackController.getInstance().getDeck().dealCard();
            aiMove = new HitStrategy(card);
            score += card.getValue();
            playMove(aiMove);
        }
    }

    public void playMove(IMoveStrategy strategy) {
        strategy.move(this);
    }

    public void hit(IBlackjackCard card) {
        score += card.getValue();
        recentCard = card;
        setChanged();
        notifyObservers();
    }

    public void stand() {
        // notify to observer that you're standing
        this.standing = true;
        setChanged();
        notifyObservers();
    }

    @Override
    public void update(ScoreUpdate scoreUpdate) {

    }

    public int getScore() {
        return this.score;
    }

    @Override
    public void notifyObservers() {
        for (IPlayersObserver observer : observers) {
            observer.update(new ScoreUpdate(PlayerType.DEALER, score, standing, checkBust(), recentCard));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
    }

    @Override
    public void registerObserver(IPlayersObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void deregisterObserver(IPlayersObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public boolean checkBust() {
        return this.score > 21;
    }

    @Override
    public void reset() {
        score = 0;
        standing = false;
    }

}
