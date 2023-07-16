package com.cs2340group7.games;

import android.content.Context;
import android.view.View;

public interface IBlackjackController extends IPlayersObserver {
    void instantiateView(View view);
    void updateDealerScoreUI(int score);
    void updatePlayerScoreUI(int score);
    void setBlackjackContext(Context context);
    Context getBlackjackContext();;
    IBlackjackDeck getDeck();
    void updateScoreText(int score);
    void reset();
}
