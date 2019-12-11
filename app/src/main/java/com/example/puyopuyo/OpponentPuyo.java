package com.example.puyopuyo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import java.util.Arrays;

import static android.graphics.Bitmap.createScaledBitmap;
import static com.example.puyopuyo.FieldSize.*;

public class OpponentPuyo extends GameObject {
    private GameSurface gameSurface;
    private Bitmap[][] bitmaps = new Bitmap[2][6];
    public Puyo[][][] puyoMap = new Puyo[4][MAX_ROW][MAX_COLUMN]; // 필드 뿌요맵

    public OpponentPuyo(GameSurface gameSurface, Bitmap image) {
        super(image, 2, 6, 0, 0);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                bitmaps[i][j] = this.createSubImageAt(i, j);
                bitmaps[i][j] = createScaledBitmap(bitmaps[i][j], (int) (width * SCREEN_RATIO), (int) (height * SCREEN_RATIO), true);
            }
        }

        this.gameSurface = gameSurface;
    }

    @Override
    public void draw(Canvas canvas)  {
        int draw_x, draw_y;
        for (int p = 0; p < Network.max_player; p++) {
            for (int i = 0; i < MAX_ACTIVE_ROW; i++) {
                for (int j = 0; j < MAX_COLUMN; j++) {
                    if (p != Network.player) {
                        //Log.d("TAG", ""+puyo[p][i][j]);
                        if (puyoMap[p][i][j] != null) {
                            draw_x = 24 + 9 + j * COLUMN;
                            draw_y = (502 - (i * ROW));
                            canvas.drawBitmap(bitmaps[0][puyoMap[p][i][j].getColor()], (FIELD_INTERVAL * p + draw_x) * SCREEN_RATIO, (draw_y - height) * SCREEN_RATIO, null);
                        }
                    }
                }
            }
        }

        lastDrawNanoTime = System.nanoTime();
    }
}
