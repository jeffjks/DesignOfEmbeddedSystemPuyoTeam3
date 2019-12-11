package com.example.puyopuyo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import java.io.IOException;

import static android.content.ContentValues.TAG;

// 사이트 참조: https://o7planning.org/en/10521/android-2d-game-tutorial-for-beginners

class Color {
    static final int COLOR_RED = 1;
    static final int COLOR_GREEN = 2;
    static final int COLOR_BLUE = 3;
    static final int COLOR_YELLOW = 4;
    static final int COLOR_GARBAGE = 5;
}

class Stage {
    static final int CONTROL_STAGE = 0;
    static final int FALL_STAGE = 1;
    static final int CHAIN_STAGE = 2;
    static final int GARBAGE_STAGE = 3;
}

class FieldSize {
    static final int COLUMN = 32;
    static final int ROW = 29;
    static final int MAX_ROW = 18;
    static final int MAX_COLUMN = 6;
    static final int MAX_ACTIVE_ROW = 13;
    static final int FIELD_INTERVAL = 234;
    static float SCREEN_RATIO;
}

public class GameActivity extends Activity {

    GameSurface gameSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // 가로 모드
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // 화면 꺼짐 방지
        super.onCreate(savedInstanceState);

        // Full Screen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Hide Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Log.d(TAG, ">>> size.x : " + size.x + ", size.y : " + size.y);

        FieldSize.SCREEN_RATIO = (float) size.y / 600f;

        //setContentView(R.layout.activity_main);
        gameSurface = new GameSurface(this, size.x, size.y);
        this.setContentView(gameSurface);

        Log.d(TAG, "SCREEN_RATIO: "+FieldSize.SCREEN_RATIO);
    }

    @Override
    public void onBackPressed() {
        gameSurface.gameThread.interrupt();
        finish();
    }




    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
}
