package com.cs2340group7.games;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

public class TictactoeBoard extends View {

    // Constants for game states
    private static final int EMPTY = 0;
    private static final int X = 1;
    private static final int O = 2;

    private final Paint paint = new Paint();
    private int cellSize;
    private final Drawable xImage;
    private final Drawable oImage;
    private int[][] gameState;

    public TictactoeBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Resources resources = context.getResources();
        xImage = ResourcesCompat.getDrawable(resources, R.drawable.x_sprite, null);
        oImage = ResourcesCompat.getDrawable(resources, R.drawable.o_sprite, null);
        TictactoeManager.setBoardUI(this);

        // Initialize the game state array
        resetUI();
    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);

        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellSize = dimension / 3;

        setMeasuredDimension(dimension, dimension);
    }

    @Override
    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            int row = (int) (y / cellSize);
            int col = (int) (x / cellSize);
            if (gameState[row][col] == EMPTY) {
                TictactoeManager.playerMove((row * 3) + col);
            }
            invalidate();
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void updateCell(int position, int state) {
        int row = (int) position / 3;
        int col = position % 3;
        gameState[row][col] = state;
    }

    public void resetUI() {
        gameState = new int[][]{
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY}
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawGameBoard(canvas);
        for (int row = 0; row < gameState.length; row++) {
            for (int col = 0; col < gameState[row].length; col++) {
                if (gameState[row][col] == X) {
                    drawImage(canvas, xImage, row, col);
                } else if (gameState[row][col] == O) {
                    drawImage(canvas, oImage, row, col);
                }
            }
        }
    }

    private void drawImage(Canvas canvas, Drawable image, int row, int col) {
        int centerX = col * cellSize + cellSize / 2;
        int centerY = row * cellSize + cellSize / 2;

        int imageWidth = (int) (cellSize * 0.65);
        int imageHeight = (int) (cellSize * 0.65);
        int left = centerX - imageWidth / 2;
        int top = centerY - imageHeight / 2;
        int right = left + imageWidth;
        int bottom = top + imageHeight;

        image.setBounds(left, top, right, bottom);
        image.draw(canvas);
    }

    private void drawGameBoard(Canvas canvas) {
        Drawable boardDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.sorry, null);
        assert boardDrawable != null;
        boardDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        boardDrawable.draw(canvas);
    }

    public int[][] getGameState() {
        return gameState;
    }
}
