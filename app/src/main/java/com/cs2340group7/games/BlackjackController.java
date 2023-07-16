package com.cs2340group7.games;


import android.content.Context;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class BlackjackController extends Observable implements IBlackjackController, IPlayersObserver {
    private static IBlackjackController instance;
    private Button playAgainButton;
    private TextView winnerText;
    private LinearLayout playAgain;
    private int playerScore;
    private int dealerScore;
    private List<IPlayer> players; // both human and dealer
    private LinearLayout playerHandLayout;
    private LinearLayout dealerHandLayout;
    private TextView playerScoreTextView;
    private TextView dealerScoreTextView;
    private ImageButton hitButton;
    private ImageButton standButton;
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
    private IBlackjackDeck deck;

    public IBlackjackDeck getDeck() {
        return deck;
    }
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
        BlackjackBetting.getInstance().setScore(50);

        playerHandLayout = view.findViewById(R.id.player_cards);
        playerScoreTextView = view.findViewById(R.id.player_number);
        dealerHandLayout = view.findViewById(R.id.aI_cards);
        dealerScoreTextView = view.findViewById(R.id.ai_number);
        playAgainButton = view.findViewById(R.id.playAgainButton);
        playAgain = view.findViewById(R.id.playAgain);
        winnerText = view.findViewById(R.id.winnerText);
        hitButton = view.findViewById(R.id.hit);
        standButton = view.findViewById(R.id.stand);
        dealButton = view.findViewById(R.id.deal);
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
                deck.reset();
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
                playerScoreTextView.setVisibility(View.VISIBLE);
                dealerScoreTextView.setVisibility(View.VISIBLE);
                coin1.setVisibility(View.GONE);
                coin2.setVisibility(View.GONE);
                coin3.setVisibility(View.GONE);
            }
        });
        coin1 = view.findViewById(R.id.coin1);
        coin2 = view.findViewById(R.id.coin2);
        coin3 = view.findViewById(R.id.coin3);
        scoreTextView = view.findViewById(R.id.score);
        updateScoreText(betting.getScore());
        playAgainButton.setOnClickListener(v -> {
            playAgain.setVisibility(View.GONE);
            resetGame();
            playerCardHandLayout.resetCards(playerHandLayout);
            dealerCardHandLayout.resetCards(dealerHandLayout);
        });

        coin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (betting.getScore() >= 5) {
                    betting.setBetAmount(betting.getBetAmount() + 5);
                    betting.decreaseScore(5); // these should be done in the same function in BlackjackBetting for SRP
                } else {
                    Toast.makeText(getBlackjackContext(), "You do not have enough lives to make this bet!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        coin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (betting.getScore() >= 10) {
                    betting.setBetAmount(betting.getBetAmount() + 10);
                    betting.decreaseScore(10);
                } else {
                    Toast.makeText(getBlackjackContext(), "You do not have enough lives to make this bet!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        coin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (betting.getScore() >= 20) {
                    betting.setBetAmount(betting.getBetAmount() + 20);
                    betting.decreaseScore(20);
                } else {
                    Toast.makeText(getBlackjackContext(), "You do not have enough lives to make this bet!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        playerCardHandLayout = new CardHandLayout(playerHandLayout);
        dealerCardHandLayout = new CardHandLayout(dealerHandLayout);
        deck = BlackjackDeck.getInstance();
        deck.reset();
    }

    public void updateScoreText(int score) {
        scoreTextView.setText(score + " lives");
    }

    @Override
    public void reset() {
        resetGame();
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
            updateDealerScoreUI(-1);
            if (su.getScore() == 21) {
                showHiddenCard();
                displayDealerWin();
            }
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
        prepareReset("Player");
    }
    private void displayDealerWin() {
        prepareReset("Dealer");
    }
    private void displayDraw() {
        prepareReset("Draw");
    }
    private void prepareReset(String winner) {
        hitButton.setVisibility(View.GONE);
        standButton.setVisibility(View.GONE);
        playAgain.setVisibility(View.VISIBLE);

        switch(winner) {
            case "Player":
                betting.updateScore(true);
                winnerText.setText(String.format("You won! Here's your %d lives!", betting.getBetAmount() * 2));
                updateDealerScoreUI(dealerScore);

                break;
            case "Dealer":
                winnerText.setText(String.format("Dealer won. You lost %d lives.", betting.getBetAmount()));
                updateDealerScoreUI(dealerScore);

                break;
            default:
                betting.updateScore(false);
                winnerText.setText(String.format("Draw, you keep your bet of %d lives.", betting.getBetAmount()));
                updateDealerScoreUI(dealerScore);

        }

        updateScoreText(betting.getScore());
    }
    private void resetGame() {
        dealButton.setVisibility(View.VISIBLE);
        blackjackPlayer.reset();
        blackjackDealer.reset();
        deck.reset();
        betting.clearBetAmount();
        playerScoreTextView.setVisibility(View.INVISIBLE);
        dealerScoreTextView.setVisibility(View.INVISIBLE);
        coin1.setVisibility(View.VISIBLE);
        coin2.setVisibility(View.VISIBLE);
        coin3.setVisibility(View.VISIBLE);
    }
    public void updatePlayerScoreUI(int score) {
        playerScoreTextView.setText("Player Score: " + score);
    }

    public void updateDealerScoreUI(int score) {
        dealerScoreTextView.setText("Dealer Score: " + ((score == -1) ? "?" : score));
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
