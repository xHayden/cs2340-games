package com.cs2340group7.games;

import java.util.ArrayList;
import java.util.Observable;

public class LivesController extends Observable implements ILivesController, IGameSignalObservable {
    private static int lives;
    private static int bet;
    private ArrayList<IGameSignalObserver> observers;
    private static ILivesController instance;
    private LivesController(int initialLives) {
        lives = initialLives;
        bet = 0;
    }

    public static ILivesController getInstance() {
        if (instance == null) {
            instance = new LivesController(5);
        }
        return instance;
    }

    @Override
    public boolean betLives(int lives) {
        if (lives <= bet) {
            // cannot bet all your lives
            return false;
        } else {
            bet = lives;
            return true;
        }
    }

    @Override
    public int won() {
        lives = lives + (bet * 2);
        bet = 0;
        return lives;
    }

    @Override
    public boolean lost() { // returns if you can continue
        lives = lives - bet;
        bet = 0;
        if (lives <= 0) {
            notifyObservers(GameSignals.OUT_OF_MONEY);
            return false;
        }
        return true;
    }


    @Override
    public int getLives() {
        return lives;
    }

    public void notifyObservers(GameSignals signal) {
        for (IGameSignalObserver observer : observers) {
            observer.update(this, signal);
        }
    }

    public void registerObserver(IGameSignalObserver o) {
        this.observers.add(o);
    }

    public void deregisterObserver(IGameSignalObserver o) {
        this.observers.remove(o);
    }
}
