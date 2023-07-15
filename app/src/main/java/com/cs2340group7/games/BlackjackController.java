package com.cs2340group7.games;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.fragment.NavHostFragment;

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
    private ImageButton coin1;
    private ImageButton coin2;
    private ImageButton coin3;
    private TextView scoreTextView;
    private Context blackjackContext;
    private CardHandLayout playerCardHandLayout;
    private CardHandLayout dealerCardHandLayout;
    private BlackjackPlayer blackjackPlayer;
    private BlackjackDealer blackjackDealer;
    private IBlackjackCard hiddenCard;
    private BlackjackBetting betting;

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
        betting = BlackjackBetting.getInstance();
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
            }
        });
        standButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blackjackPlayer.playMove(new StandStrategy());
                standButton.setVisibility(View.GONE);
                hitButton.setVisibility(View.GONE);
                blackjackDealer.playAIMove();
            }
        });
        dealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("super", String.format("%s", betting.getBetAmount()));
                // start game
                deck = new BlackjackDeck();
                playerScore = 0;
                dealerScore = 0;
                blackjackPlayer.playMove(new HitStrategy(deck.dealCard()));
                blackjackPlayer.playMove(new HitStrategy(deck.dealCard()));
                hiddenCard = deck.dealCard();
                blackjackDealer.playMove(new HiddenHitStrategy(new BlackjackCard(hiddenCard.getSuit(), hiddenCard.getRank(), hiddenCard.getImageResource())));
                blackjackDealer.playMove(new HitStrategy(deck.dealCard()));
                hitButton.setVisibility(View.VISIBLE);
                standButton.setVisibility(View.VISIBLE);
                dealButton.setVisibility(View.GONE);
                Log.d("be", String.format("%s", betting.getBetAmount()));
                //betting.setBetAmount(betting);
                Log.d("aft", String.format("%s", betting.getBetAmount()));
            }
        });

        coin1 = view.findViewById(R.id.coin1);
        coin2 = view.findViewById(R.id.coin2);
        coin3 = view.findViewById(R.id.coin3);
        scoreTextView = view.findViewById(R.id.score);
        updateScoreText(betting.getScore());

        coin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("before", String.format("%s", betting.getBetAmount()));
                betting.setBetAmount(betting.getBetAmount() + 5);
                Log.d("after", String.format("%s", betting.getBetAmount()));
                betting.decreaseScore(5);
                Log.d("afterds", String.format("%s", betting.getBetAmount()));
            }
        });

        coin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betting.setBetAmount(betting.getBetAmount() + 10);
                betting.decreaseScore(10);
            }
        });

        coin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betting.setBetAmount(betting.getBetAmount() + 20);
                betting.decreaseScore(20);
            }
        });

        playerCardHandLayout = new CardHandLayout(playerHandLayout);
        dealerCardHandLayout = new CardHandLayout(dealerHandLayout);
        deck = new BlackjackDeck();
    }

    public void updateScoreText(int score) {
        scoreTextView.setText("Score: " + score);
    }

    public void update(ScoreUpdate su) {
        if (su.getPlayerType() == PlayerType.HUMAN) { // not ideal but I can't think of a better way
            playerScore = su.getScore();
            Log.d("Player score update: ", String.valueOf(playerScore));
            updatePlayerScoreUI(playerScore);
            if (!su.getStanding()) {
                playerCardHandLayout.addCard(su.getRecentCard().getImageResource());
            }
            if (su.getBusted()) {
                showHiddenCard();
                displayDealerWin();
            }
            if (su.getScore() == 21) {
                showHiddenCard();
                displayPlayerWin();
            }
        } else if (su.getPlayerType() == PlayerType.DEALER) {
            dealerScore = su.getScore();
            Log.d("Dealer score update: ", String.valueOf(dealerScore));
            updateDealerScoreUI(dealerScore);
            if (!su.getStanding()) {
                dealerCardHandLayout.addCard(su.getRecentCard().getImageResource());
            } else {
                showHiddenCard();
                if (su.getBusted()) {
                    displayPlayerWin();
                } else {
                    checkWinnerBasedOnScore();
                }
            }
        }
    }

    private void showHiddenCard() {
        if (dealerHandLayout.getChildCount() > 0) {
            View card = dealerHandLayout.getChildAt(0);
            if (card instanceof ImageView) {
                ImageView imageView = (ImageView) card;
                imageView.setImageResource(hiddenCard.getImageResource());
            }
        }
    }
    private void checkWinnerBasedOnScore() {
        if (playerScore > dealerScore) {
            displayPlayerWin();
        } else if (dealerScore > playerScore) {
            displayDealerWin();
        } else {
            displayDraw();
        }
    }
    private void displayPlayerWin() {
        prepareReset("player");
    }
    private void displayDealerWin() {
        prepareReset("dealer");
    }
    private void displayDraw() {
        prepareReset("draw");
    }
    private void prepareReset(String winner) {
        hitButton.setVisibility(View.GONE);
        standButton.setVisibility(View.GONE);
        AlertDialog.Builder builder = new AlertDialog.Builder(getBlackjackContext());

        switch(winner) {
            case "player":
                betting.updateScore(true);
                builder.setTitle(String.format("You won, you gained: %d coins", betting.getBetAmount() * 2));
                break;
            case "dealer":
                builder.setTitle(String.format("Dealer won, you lost: %d coins", betting.getBetAmount()));
                break;
            default:
                betting.updateScore(false);
                builder.setTitle(String.format("Draw, you keep your bet of: %d coins", betting.getBetAmount()));
        }

        builder.setMessage("Play again?");

        builder.setPositiveButton("Yes", (dialog, id) -> {

            resetGame();
            playerCardHandLayout.resetCards(playerHandLayout);
            dealerCardHandLayout.resetCards(dealerHandLayout);
        });

        builder.setNegativeButton("No", (dialog, id) -> {
            //back to main menu
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void resetGame() {
        dealButton.setVisibility(View.VISIBLE);
        blackjackPlayer.reset();
        blackjackDealer.reset();
        deck.reset();
    }
    public void updatePlayerScoreUI(int score) {

    }

    public void updateDealerScoreUI(int score) {
        // access ui element to update score
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
