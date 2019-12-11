package com.example.puyopuyo;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import static android.graphics.Bitmap.createScaledBitmap;
import static com.example.puyopuyo.FieldSize.SCREEN_RATIO;

public class FieldUI extends GameObject { // UI

    private GameSurface gameSurface;

    public FieldUI(GameSurface gameSurface, Bitmap image, int x, int y) {
        super(image, 1, 1, x, y);

        this.gameSurface = gameSurface;
    }

    @Override
    public void draw(Canvas canvas)  {
        Bitmap bitmap = this.image;
        bitmap = createScaledBitmap(bitmap, (int) (width*SCREEN_RATIO), (int) (height*SCREEN_RATIO), true);
        canvas.drawBitmap(bitmap, x*SCREEN_RATIO, (y - height/2)*SCREEN_RATIO, null);
    }

    @Override
    public void update() {
        return;
    }
}
