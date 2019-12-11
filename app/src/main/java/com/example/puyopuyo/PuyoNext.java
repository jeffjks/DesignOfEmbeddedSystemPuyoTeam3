package com.example.puyopuyo;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import static android.graphics.Bitmap.createScaledBitmap;
import static com.example.puyopuyo.FieldSize.*;

public class PuyoNext extends GameObject {

    private GameSurface gameSurface;
    private int rowIndexMain; // For Image
    private int colIndexMain;
    private int rowIndexSub;
    private int colIndexSub;

    private int colorMain, colorSub;

    public PuyoNext(GameSurface gameSurface, Bitmap image, int x, int y, int colorMain, int colorSub, float x_scale, float y_scale) {
        super(image, 2, 6, x, y, x_scale, y_scale); // row, col 에 맞는 위치로 설정
        setColor(colorMain, colorSub);

        this.gameSurface = gameSurface;
    }

    @Override
    public void draw(Canvas canvas)  {
        Bitmap bitmapMain = this.createSubImageAt(rowIndexMain, colIndexMain);
        bitmapMain = createScaledBitmap(bitmapMain, (int) (width*SCREEN_RATIO*xscale), (int) (height*SCREEN_RATIO*yscale), true);
        canvas.drawBitmap(bitmapMain, x*SCREEN_RATIO, y*SCREEN_RATIO, null);

        Bitmap bitmapSub = this.createSubImageAt(rowIndexSub, colIndexSub);
        bitmapSub = createScaledBitmap(bitmapSub, (int) (width*SCREEN_RATIO*xscale), (int) (height*SCREEN_RATIO*yscale), true);
        canvas.drawBitmap(bitmapSub, x*SCREEN_RATIO, (y - ROW*yscale)*SCREEN_RATIO, null);

        lastDrawNanoTime = System.nanoTime();
    }

    public void setColor(int colorMain, int colorSub) {
        this.colIndexMain = colorMain;
        this.colIndexSub = colorSub;
        this.colorMain = colorMain;
        this.colorSub = colorSub;
    }

    public int getColor(int num) {
        if (num == 0) {
            return colorMain;
        }
        else if (num == 1) {
            return colorSub;
        }
        return -1;
    }
}
