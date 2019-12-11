package com.example.puyopuyo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import static android.graphics.Bitmap.createScaledBitmap;
import static com.example.puyopuyo.FieldSize.SCREEN_RATIO;

public class GarbageTray extends GameObject {
    private Bitmap[] bitmaps = new Bitmap[6];
    private int warningPuyo;

    public GarbageTray(Bitmap image, int x, int y) {
        super(image, 1, 6, 0, 0);
        this.x = x;
        this.y = y;

        for (int i = 0; i < 6; i++) {
            bitmaps[i] = this.createSubImageAt(0, i);
            bitmaps[i] = createScaledBitmap(bitmaps[i], (int) (width * SCREEN_RATIO), (int) (height * SCREEN_RATIO), true);
        }
    }

    // 1, 6, 30, 180, 360, 720
    @Override
    public void draw(Canvas canvas) {
        int temp = warningPuyo;
        int[] unit = {0, 0, 0, 0, 0, 0};
        unit[5] = temp / 720; temp -= unit[5]*720;
        unit[4] = temp / 360; temp -= unit[4]*360;
        unit[3] = temp / 180; temp -= unit[3]*180;
        unit[2] = temp / 30; temp -= unit[2]*30;
        unit[1] = temp / 6; temp -= unit[1]*6;
        unit[0] = temp;

        int slot = unit.length - 1;
        for (int i = 0; i < 6; i++) {
            while (unit[slot] <= 0) {
                slot--;
                if (slot < 0) {
                    break;
                }
            }
            if (slot < 0) {
                break;
            }
            canvas.drawBitmap(bitmaps[slot], (x + i * 32) * SCREEN_RATIO, (y - height) * SCREEN_RATIO, null);
            unit[slot]--;
        }

        lastDrawNanoTime = System.nanoTime();
    }

    public void addWarningPuyo(int warningPuyo) {
        this.warningPuyo += warningPuyo;

        if (this.warningPuyo < 0) {
            this.warningPuyo = 0;
        }
        else if (this.warningPuyo > 720*6) {
            this.warningPuyo = 720*6;
        }
    }

    public int getWarningPuyo() {
        return warningPuyo;
    }
}
