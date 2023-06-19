package com.cs2340group7.games;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TictactoeManager {
    static char[][] board;
    static boolean playingGame;
    static int playerScore = 0;
    private static TextView playerScoreUI;
    static int aiScore = 0;
    private static TextView aiScoreUI;
    static int drawCount = 0;
    static boolean isFirstMove = true;
    static TictactoeBoard ui;
    static CountDownTimer timer;
    static TextView text;
    private static Button playAgainButton;

    public TictactoeManager(TextView gameTime, TextView textView, TextView playerScore, TextView aiScore, Button playAgain) {
        playingGame = true;
        createBoard();
        createTimer(gameTime);
        text = textView;
        playerScoreUI = playerScore;
        aiScoreUI = aiScore;
        playAgainButton = playAgain;
    }

    public static void startNoUI() {
        playingGame = true;
        createBoard();
    }

    public static void restart() {
        restartNoUI();
        timer.start();
        ui.resetUI();
        ui.invalidate();
    }

    public static void restartNoUI() {
        playingGame = true;
        createBoard();
    }

    static void createTimer(TextView gameTime) {
        timer = new CountDownTimer(10500, 1000) {
            public void onTick(long millisUntilFinished) {
                gameTime.setText("Time Left: " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                text.setText("Your turn was skipped");
                aiMove();
                ui.invalidate();
                this.start();
            }
        }.start();
    }

    static void createBoard() {
        board = new char[3][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[j][i] = ' ';
            }
        }
    }

    static void printBoard() {
        System.out.printf(
                "|%s|%s|%s|\n|%s|%s|%s|\n|%s|%s|%s|\n"
                , board[0][0], board[1][0], board[2][0], board[0][1], board[1][1], board[2][1], board[0][2], board[1][2], board[2][2]);
    }

    static void putAtSpot(int position, char player) {
        int x = position % board[0].length;
        int y = position / board[0].length;
        board[x][y] = player;
    }

    static boolean isPositionFree(int position) {
        int x = position % 3;
        int y = position / 3;
        return board[x][y] == ' ';
    }

    static int findWinningMove(char player) {
        for (int i = 0; i < 9; i++) {
            if (isPositionFree(i)) {
                putAtSpot(i, player);
                if (checkWinner(i, player) == player) {
                    putAtSpot(i, ' '); // undo move
                    return i;
                }
                putAtSpot(i, ' '); // undo move
            }
        }
        return -1;
    }

    static char checkWinner(int position, char player) {
        int x = position % board[0].length;
        int y = position / board[0].length;

        // check column
        for(int i = 0; i < 3; i++) {
            if(board[i][y] != player) {
                break;
            }
            if(i == 2) {
                return player;
            }
        }

        // check row
        for(int i = 0; i < 3; i++) {
            if(board[x][i] != player) {
                break;
            }
            if(i == 2) {
                return player;
            }
        }

        // check diagonal
        if(x == y) {
            for(int i = 0; i < 3; i++) {
                if(board[i][i] != player) {
                    break;
                }
                if(i == 2) {
                    return player;
                }
            }
        }

        // check anti-diagonal
        if(x + y == 2) {
            for(int i = 0; i < 3; i++) {
                if(board[i][2-i] != player) {
                    break;
                }
                if(i == 2) {
                    return player;
                }
            }
        }

        // check draw
        int counter = 0;
        for (int i = 0; i < 9; i++) {
            if (isPositionFree(i)) {
                counter++;
            }
        }

        if (counter == 0) {
            return 'd';
        }

        return ' ';
    }

    public static void playerMove(int position) {
        text.setText("");
        updateWinnerAndScore(position);
        timer.cancel();
        timer.start();
    }

    private static void updateUI(int position, char player) {
        if (player == ' ') {
            ui.updateCell(position, 0);
            return;
        }
        Log.d("putAtSpot", String.valueOf(position) + ": " + player);
        putAtSpot(position, player);
        ui.updateCell(position, (player == 'x') ? 2 : 1);
    }

    private static void updateWinnerAndScore(int position) {
        updateUI(position, 'x');
        if (checkWinner(position, 'x') != ' ' && checkWinner(position, 'x') != 'd') {
            // PLAYER PLAYS WINNING MOVE
            updateUI(position, 'x');
            text.setText("Player wins!");
            timer.cancel();
            Log.d("Tic", "The winner is: x");
            playerScore++;
            playerScoreUI.setText("Player: " + playerScore);
            playAgainButton.setVisibility(View.VISIBLE);
            playingGame = false;
        } else if (checkWinner(position, 'y') == 'd') {
            // PLAYER PLAYS MOVE THAT CAUSES DRAWING MOVE
            updateUI(position, 'x');
            text.setText("Draw!");
            timer.cancel();
            Log.d("Tic", "It is a draw.");
            drawCount++;
            playAgainButton.setVisibility(View.VISIBLE);
            playingGame = false;
        } else {
            if (playingGame) {
                aiMove();
            }
        }
    }

    private static void aiMove() {
        //AI next move
        int position = findWinningMove('y');
        if (position == -1) {
            position = findWinningMove('x');
            if (position == -1) {
                if (isPositionFree(4)) {
                    position = 4;
                } else if (isPositionFree(0) && isFirstMove) {
                    position = 0;
                } else if (isPositionFree(2) && isFirstMove) {
                    position = 2;
                } else if (isPositionFree(6) && isFirstMove) {
                    position = 6;
                } else if (isPositionFree(8) && isFirstMove) {
                    position = 8;
                } else {
                    do {
                        position = (int) (Math.random() * 9);
                    } while (!isPositionFree(position));
                }
            }
        }

        updateUI(position, 'y');
        isFirstMove = false;
        if (checkWinner(position, 'y') != ' ' && checkWinner(position, 'y') != 'd') {
            // AI PLAYED WINNING MOVE
            updateUI(position, 'y');
            text.setText("The AI Wins!");
            timer.cancel();
            Log.d("Tic", "The winner is: y");
            aiScore++;
            aiScoreUI.setText("AI: " + aiScore);
            playAgainButton.setVisibility(View.VISIBLE);
            playingGame = false;
        } else {
            if (checkWinner(position, 'y') == 'd') {
                // AI PLAYED DRAW
                updateUI(position, 'y');
                text.setText("Draw!");
                timer.cancel();
                Log.d("Tic", "It is a draw.");
                drawCount++;
                playAgainButton.setVisibility(View.VISIBLE);
                playingGame = false;
            }
        }
    }

    public static void setBoardUI(TictactoeBoard tictactoeBoard) {
        ui = tictactoeBoard;
    }
}