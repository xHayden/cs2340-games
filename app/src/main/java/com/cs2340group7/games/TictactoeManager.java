package com.cs2340group7.games;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

public class TictactoeManager {
    static char[][] board;
    static boolean playingGame;
    static int humanScore = 0;
    static int aiScore = 0;
    static int drawCount = 0;
    static boolean isFirstMove = true;
    static TictactoeBoard ui;

    public TictactoeManager(TextView gameTime) {
        playingGame = true;
        createBoard();
        createTimer(gameTime);
    }

    static void createTimer(TextView gameTime) {
        new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                gameTime.setText("Time Left: " + millisUntilFinished / 1000);
                // add in the method to call so that the PC makes its move and users move is skipped
            }
            public void onFinish() {
                gameTime.setText("Your turn was skipped");
                // add in the method to call so that the PC makes its move and users move is skipped


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
        updateWinnerAndScore(position);
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
            Log.d("Tic", "The winner is: x");
            humanScore++;
            playingGame = false;
        } else if (checkWinner(position, 'y') == 'd') {
            // PLAYER PLAYS MOVE THAT CAUSES DRAWING MOVE
            updateUI(position, 'x');
            Log.d("Tic", "It is a draw.");
            drawCount++;
            playingGame = false;
        } else {
            if (playingGame) {
                //AI next move
                position = findWinningMove('y');
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
                    Log.d("Tic", "The winner is: y");
                    aiScore++;
                    playingGame = false;
                } else {
                    if (checkWinner(position, 'y') == 'd') {
                        // AI PLAYED DRAW
                        updateUI(position, 'y');
                        Log.d("Tic", "It is a draw.");
                        drawCount++;
                        playingGame = false;
                    }
                }
            }
        }
    }

    public static void setBoardUI(TictactoeBoard tictactoeBoard) {
        ui = tictactoeBoard;
    }
}