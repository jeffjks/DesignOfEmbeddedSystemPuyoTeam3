package com.example.puyopuyo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import static android.content.ContentValues.TAG;
import static android.graphics.Bitmap.createScaledBitmap;
import static com.example.puyopuyo.FieldSize.*;

public class Puyo extends GameObject { // 필드 뿌요

    private final int HEIGHT_UNIT = 32; // 1 row 당 32 height
    private GameSurface gameSurface;

    private int rowIndex; // For Image
    private int colIndex; // For Image

    private int row, column;
    private float fieldHeight;
    private int player, color;
    private boolean isFalling, isRemoving;
    public int removeTimer;
    private boolean visibility = true;

    public Puyo(GameSurface gameSurface, Bitmap image, int player, int color, int row, int col) {
        super(image, 2, 6, 24 + 9 + col * COLUMN, 502 - row * ROW); // row, col 에 맞는 위치로 설정
        setColor(color);
        setRowColumn(row, col);
        fieldHeight = row*HEIGHT_UNIT;

        this.player = player;
        this.gameSurface = gameSurface;
    }

    @Override
    public void draw(Canvas canvas)  {
        this.x = 24 + 9 + column * COLUMN + player*FIELD_INTERVAL;
        this.y = (int) (502 - (fieldHeight * ROW) / HEIGHT_UNIT);

        if (visibility) {
            Bitmap bitmap = this.createSubImageAt(rowIndex, colIndex);
            bitmap = createScaledBitmap(bitmap, (int) (width * SCREEN_RATIO), (int) (height * SCREEN_RATIO), true);
            canvas.drawBitmap(bitmap, x * SCREEN_RATIO, (y - height) * SCREEN_RATIO, null);
        }

        lastDrawNanoTime = System.nanoTime();
    }

    @Override
    public void update() {
        super.update();
        fall();
        if (isRemoving) {
            removeTimer += deltaTime;
            if (300 < removeTimer && removeTimer <= 400) {
                visibility = false;
            } else if (removeTimer <= 400) {
                visibility = true;
            } else if (removeTimer <= 500) {
                visibility = false;
            } else if (removeTimer <= 600) {
                visibility = true;
            } else if (removeTimer <= 700) {
                visibility = false;
            } else if (removeTimer <= 800) {
                visibility = true;
            } else {
                if (rowIndex == 0) {
                    rowIndex = 1;
                }
            }
        }
    }

    private void setColor(int color) {
        this.colIndex = color;
        this.color = color;
    }

    private void fall() { // 뿌요 낙하 구현 (현재 높이가 row 에 맞는 높이보다 높으면 낙하)
        if (fieldHeight > row*HEIGHT_UNIT) {
            fieldHeight -= (deltaTime * 0.001f) * 768f;
            isFalling = true;
        }
        if (fieldHeight < row*HEIGHT_UNIT) {
            fieldHeight = row*HEIGHT_UNIT;
            isFalling = false;
        }
    }

    public void remove() {
        isRemoving = true;
    }

    public int getColor() {
        return color;
    }

    public void setRowColumn(int row, int col) {
        this.row = row;
        this.column = col;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean getIsFalling() {
        return isFalling;
    }
}
