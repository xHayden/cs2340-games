package com.cs2340group7.games;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class BlackjackDealer extends Observable implements IPlayer, Observer {
    private int score;
    private List<IPlayersObserver> observers;
    private boolean standing;

    public BlackjackDealer() {
        this.score = 0;
        this.standing = false;
    }

    public void playAIMove() {
        // decide what strategy to use
        IMoveStrategy aiMove = new StandStrategy(); // or hit strategy, whatever the AI thinks is best
        playMove(aiMove);
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

    @Override
    public void update(ScoreUpdate scoreUpdate) {

    }

    public int getScore() {
        return this.score;
    }

    @Override
    public void notifyObservers() {
        for (IPlayersObserver observer : observers) {
            observer.update(new ScoreUpdate(PlayerType.DEALER, score, standing));
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
