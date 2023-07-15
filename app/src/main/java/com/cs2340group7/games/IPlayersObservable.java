package com.cs2340group7.games;

public interface IPlayersObservable {
    void registerObserver(IPlayersObserver observer);
    void deregisterObserver(IPlayersObserver observer);
}
