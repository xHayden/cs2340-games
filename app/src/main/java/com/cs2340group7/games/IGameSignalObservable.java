package com.cs2340group7.games;

public interface IGameSignalObservable {
    void notifyObservers(GameSignals signal);
    void deregisterObserver(IGameSignalObserver o);
    void registerObserver(IGameSignalObserver o);
}
