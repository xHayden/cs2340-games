package com.cs2340group7.games;

public interface IGameSignalObserver {
    void update(IGameSignalObservable o, GameSignals signal);
}
