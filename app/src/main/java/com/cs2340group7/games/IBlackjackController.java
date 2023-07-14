package com.cs2340group7.games;

import android.view.View;

public interface IBlackjackController extends IPlayersObserver {
    void instantiateView(View view);
    void updateDealerScoreUI(int score);
    void updatePlayerScoreUI(int score);
}
