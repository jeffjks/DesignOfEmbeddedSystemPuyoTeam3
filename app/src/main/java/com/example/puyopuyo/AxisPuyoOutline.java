package com.example.puyopuyo;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import static android.graphics.Bitmap.createScaledBitmap;
import static com.example.puyopuyo.FieldSize.SCREEN_RATIO;

public class AxisPuyoOutline extends GameObject { // 그냥 Main 뿌요 하얀 외곽선 빤짝빤짝거리는 용
    private final int BLINK_TIME = 240;
    private GameSurface gameSurface;

    private int rowIndex;
    private int colIndex;

    private int blinkTimer;

    private int color;

    public AxisPuyoOutline(GameSurface gameSurface, Bitmap image, int x, int y) {
        super(image, 1, 6, x, y);

        this.gameSurface = gameSurface;
    }

    @Override
    public void draw(Canvas canvas)  {
        if (blinkTimer > BLINK_TIME) {
            Bitmap bitmap = this.createSubImageAt(rowIndex, colIndex);
            bitmap = createScaledBitmap(bitmap, (int) (width*SCREEN_RATIO), (int) (height*SCREEN_RATIO), true);
            canvas.drawBitmap(bitmap, (x - 4)*SCREEN_RATIO, (y - HEIGHT + 4)*SCREEN_RATIO, null);
        }

        lastDrawNanoTime = System.nanoTime();
    }

    @Override
    public void update() {
        super.update();
        blinkTimer += deltaTime;
        if (blinkTimer > BLINK_TIME*2) {
            blinkTimer -= BLINK_TIME*2;
        }
    }

    public void setColor(int color) {
        this.colIndex = color;
        this.color = color;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
