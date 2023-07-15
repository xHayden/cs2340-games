package com.cs2340group7.games;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class BlackjackController extends Observable implements IBlackjackController, IPlayersObserver {
    private static IBlackjackController instance;
    private int playerScore;
    private int dealerScore;
    private List<IPlayer> players; // both human and dealer
    private LinearLayout playerHandLayout;
    private LinearLayout dealerHandLayout;
    private TextView playerScoreTextView;
    private TextView dealerScoreTextView;
    private ImageView deckImageView;
    private ImageButton hitButton;
    private ImageButton standButton;
    // Game Status UI Component
    private TextView gameStatusTextView;
    // Game Control UI Component
    private ImageButton newGameButton;
    private ImageButton quitGameButton;
    private Context blackjackContext;
    private CardHandLayout playerCardHandLayout;
    private CardHandLayout dealerCardHandLayout;
    private BlackjackPlayer blackjackPlayer;
    private BlackjackDealer blackjackDealer;

    public IBlackjackDeck getDeck() {
        return deck;
    }

    private IBlackjackDeck deck;
    private ImageButton dealButton;

    private BlackjackController() {
        playerScore = 0;
        dealerScore = 0;
        this.players = new ArrayList<>();
        blackjackPlayer = new BlackjackPlayer();
        blackjackDealer = new BlackjackDealer();
        addObserver(blackjackPlayer);
        addObserver(blackjackDealer);
        blackjackPlayer.registerObserver(this);
        blackjackDealer.registerObserver(this);
    }

    public static IBlackjackController getInstance() {
        if (instance == null) {
            instance = new BlackjackController();
        }
        return instance;
    }

    public void instantiateView(View view) {
        playerHandLayout = view.findViewById(R.id.player_cards);
        playerScoreTextView = view.findViewById(R.id.player_number);
        dealerHandLayout = view.findViewById(R.id.aI_cards);
        dealerScoreTextView = view.findViewById(R.id.ai_number);
        // deckImageView = view.findViewById(R.id.deck_image_view);

        // Initialize the Action UI Components
        hitButton = view.findViewById(R.id.hit);
        standButton = view.findViewById(R.id.stand);
        dealButton = view.findViewById(R.id.deal);

        // Initialize the Game Status UI Component
//      gameStatusTextView = view.findViewById(R.id.);
//      newGameButton = view.findViewById(R.id.); need to put the new game button for blackjack
//      quitGameButton = view.findViewById(R.id.quit_game_button); i don't think we have this one but we can make one
        hitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IBlackjackCard card = deck.dealCard();
                blackjackPlayer.playMove(new HitStrategy(card));
                // display card
                playerCardHandLayout.addCard(card.getImageResource());
                blackjackDealer.playAIMove();
            }
        });
        standButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blackjackPlayer.playMove(new StandStrategy());
                blackjackDealer.playAIMove();
            }
        });
        dealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start game
                deck = new BlackjackDeck();
                playerScore = 0;
                dealerScore = 0;
                IBlackjackCard card = deck.dealCard();
                blackjackPlayer.playMove(new HitStrategy(card));
                playerCardHandLayout.addCard(card.getImageResource());
                card = deck.dealCard();
                blackjackPlayer.playMove(new HitStrategy(card));
                playerCardHandLayout.addCard(card.getImageResource());
                deck.dealCard();
                blackjackDealer.playMove(new HitStrategy(card));
                dealerCardHandLayout.addCard(card.getImageResource()); // should be hidden card sprite
                deck.dealCard();
                blackjackDealer.playMove(new HitStrategy(card));
                dealerCardHandLayout.addCard(card.getImageResource());
                hitButton.setVisibility(View.VISIBLE);
                standButton.setVisibility(View.VISIBLE);
                dealButton.setVisibility(View.GONE);
            }
        });
        // create coin event listener to place bet, signal LivesController.

//        newGameButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //                BlackjackController.getInstance().startNewGame(); have not made the method yet cause we don't know where it would be at
//            }
//        });
//        quitGameButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                BlackjackController.getInstance().quitGame(); have not made the method yet cause we don't know where it would be at
//            }
//        });
        playerCardHandLayout = new CardHandLayout(playerHandLayout);
        dealerCardHandLayout = new CardHandLayout(dealerHandLayout);
        deck = new BlackjackDeck();
    }

    public void update(ScoreUpdate su) {
        if (su.getPlayerType() == PlayerType.HUMAN) { // not ideal but I can't think of a better way
            playerScore = su.getScore();
            Log.d("Player score update: ", String.valueOf(playerScore));
            updatePlayerScoreUI(playerScore);
        } else if (su.getPlayerType() == PlayerType.DEALER) {
            dealerScore = su.getScore();
            Log.d("Dealer score update: ", String.valueOf(dealerScore));
            updateDealerScoreUI(dealerScore);
            // end game, count score
        }
    }

    public void updatePlayerScoreUI(int score) {

    }

    public void updateDealerScoreUI(int score) {

    }

    @Override
    public void notifyObservers() {
        for (IPlayer observer : players) {
        }
    }

    public void addObserver(Observer o) {
        super.addObserver(o);
        if (o instanceof IPlayer) {
            this.players.add((IPlayer) o);
        }
    }

    public Context getBlackjackContext() {
        if (blackjackContext == null) {
            throw new IllegalStateException("BlackjackContext has not been set yet!");
        }
        return blackjackContext;
    }

    public void setBlackjackContext(Context blackjackContext) {
        this.blackjackContext = blackjackContext;
    }


    // player.makeMove(new StandStrategy());
    //  player.makeMove(new HitStrategy(new BlackjackCard(6)));
    // ScoreboardController.getInstance().setScore(2);
    // ScoreboardController.getInstance().getScore();
    // LivesController.getInstance().betLives(2);
    // LivesController.getInstance().won();
}
