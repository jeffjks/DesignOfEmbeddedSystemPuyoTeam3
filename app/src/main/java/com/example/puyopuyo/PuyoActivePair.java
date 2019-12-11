package com.example.puyopuyo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Build;
import android.util.Log;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

import static android.graphics.Bitmap.createScaledBitmap;
import static com.example.puyopuyo.FieldSize.*;

import static android.content.ContentValues.TAG;
import static com.example.puyopuyo.GameSurface.MAX_STREAMS;

public class PuyoActivePair extends GameObject { // 현재 색패 (Main, Sub 로 이루어져있음. Sub 뿌요가 Main 뿌요를 중심으로 회전)
    private final int HEIGHT_UNIT = 32; // 1 row 당 32 fieldHeight
    private final int DROP_UNIT = 16; // 16 fieldHeight 씩 display
    private final int IDLE_DROP_SPEED = 3; // 낙하 속도 (기본 1)
    private final int SOFT_DROP_SPEED = 30 - IDLE_DROP_SPEED; // 소프트 드랍 속도 (기본 30)
    private final int PLACE_TIME = 1500; // 바닥에 닿았을 때 최종 배치까지 걸리는 시간
    private final int INIT_ROW = 12, INIT_COL = 2;
    private final int[][] ROTATE_POSITION = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 회전에 따른 rowSub, columnSub 위치
    private GameSurface gameSurface;
    private AxisPuyoOutline axisPuyoOutline;
    private SoundManager soundManager;

    private int rowIndexMain, colIndexMain, rowIndexSub, colIndexSub; // For Image
    private boolean enabled = false;

    private int rowMain, columnMain, rowSub, columnSub; // 현재 색패의 row, column.
    private float fieldHeight; // 현재 높이
    private int placeTimer;
    private int colorMain, colorSub;

    private int rotation; // 0:up(default), 1:right, 2:down, 3:left
    private int softDrop;

    public PuyoActivePair(GameSurface gameSurface, SoundManager soundManager, Bitmap image) {
        super(image, 2, 6, 0, 0); // row, col 에 맞는 위치로 설정

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap bitmap = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.spr_puyo_outline, options);
        axisPuyoOutline = new AxisPuyoOutline(gameSurface, bitmap, x, y);

        this.soundManager = soundManager;
        this.gameSurface = gameSurface;
    }

    @Override
    public void draw(Canvas canvas)  {
        if (enabled) {
            this.x = 24 + 9 + columnMain * COLUMN + FIELD_INTERVAL*Network.player;
            this.y = 502 - ((((int) (fieldHeight / DROP_UNIT)) * DROP_UNIT) * ROW) / HEIGHT_UNIT;

            Bitmap bitmapMain = this.createSubImageAt(rowIndexMain, colIndexMain);
            bitmapMain = createScaledBitmap(bitmapMain, (int) (width * SCREEN_RATIO), (int) (height * SCREEN_RATIO), true);
            canvas.drawBitmap(bitmapMain, x * SCREEN_RATIO, (y - height) * SCREEN_RATIO, null);

            Bitmap bitmapSub = this.createSubImageAt(rowIndexSub, colIndexSub);
            bitmapSub = createScaledBitmap(bitmapSub, (int) (width * SCREEN_RATIO), (int) (height * SCREEN_RATIO), true);
            canvas.drawBitmap(bitmapSub, (x + ROTATE_POSITION[rotation][1] * COLUMN) * SCREEN_RATIO, (y - ROTATE_POSITION[rotation][0] * ROW - height) * SCREEN_RATIO, null);

            axisPuyoOutline.setPosition(x, y);
            axisPuyoOutline.draw(canvas);
        }

        lastDrawNanoTime = System.nanoTime();
    }

    @Override
    public void update() {
        super.update();

        if (enabled) {
            setSubRowColumn();

            int max_field_height = Math.max(gameSurface.getColumnHeight(columnMain), gameSurface.getColumnHeight(columnSub)) * HEIGHT_UNIT;
            if (rotation == 2) {
                max_field_height += HEIGHT_UNIT;
            }

            if (fieldHeight > max_field_height) {
                fieldHeight -= (deltaTime * 0.001f) * 16f * (IDLE_DROP_SPEED + softDrop);
            }
            if (fieldHeight <= max_field_height) {
                placeTimer += deltaTime + softDrop*30;
                fieldHeight = max_field_height;
            }
            rowMain = (int) fieldHeight / HEIGHT_UNIT;

            if (placeTimer > PLACE_TIME) {
                gameSurface.stageManager.endControlStage(); // 다음 Stage
                gameSurface.createPuyo(Network.player, colorMain, rowMain, columnMain);
                gameSurface.createPuyo(Network.player, colorSub, rowSub, columnSub);
                setDisabled();
            }
            axisPuyoOutline.update();
        }
    }

    public void move(int direction) {
        if (Math.abs(direction) != 1)
            return;
        if (!gameSurface.isPuyoExists(rowMain, columnMain + direction)) {
            if (!gameSurface.isPuyoExists(rowSub, columnSub + direction)) {
                rowMain += direction;
                columnMain += direction;
                setSubRowColumn();
                soundManager.playMovePuyo();
            }
        }
    }

    public void moveDown(int press) {
        if (press == 1) {
            softDrop = SOFT_DROP_SPEED;
            if (enabled) {
                gameSurface.fieldUIScoreList.get(Network.player).addScore(1);
            }
        }
        else {
            softDrop = 0;
        }
    }

    public void rotate(int rotate_direction) {
        if (Math.abs(rotate_direction) != 1)
            return;

        if (rotation%2 == 0) { // 색패 방향이 세로이고 양 옆에 뿌요가 존재하면
            if (gameSurface.isPuyoExists(rowMain, columnMain + 1) && gameSurface.isPuyoExists(rowMain, columnMain - 1)) {
                if (rotation == 0) {
                    fieldHeight += HEIGHT_UNIT;
                }
                else {
                    fieldHeight -= HEIGHT_UNIT;
                }
                rotation += rotate_direction; // 한 번 더 회전
                Log.d(TAG, "columnMain: "+columnMain);
            }
        }
        rotation += rotate_direction;

        if (rotation > 3) {
            rotation -= 4;
        }
        else if (rotation < 0) {
            rotation += 4;
        }

        switch (rotation) {
            case 0:
                break;
            case 1:
                if (gameSurface.isPuyoExists(rowMain, columnMain + 1)) {
                    columnMain--;
                }
                break;
            case 2:
                if (gameSurface.isPuyoExists(rowMain - 1, columnMain)) {
                    rowMain++;
                    fieldHeight += HEIGHT_UNIT;
                }
                break;
            case 3:
                if (gameSurface.isPuyoExists(rowMain, columnMain - 1)) {
                    columnMain++;
                }
                break;
        }
        setSubRowColumn();
        soundManager.playRotatePuyo();
    }

    private void setSubRowColumn() {
        rowSub = rowMain + ROTATE_POSITION[rotation][0];
        columnSub = columnMain + ROTATE_POSITION[rotation][1];
    }

    public void setEnabled(int colorMain, int colorSub) {
        enabled = true;
        placeTimer = 0;
        x = 24 + 9 + INIT_COL * COLUMN + FIELD_INTERVAL*Network.player;
        y = 502 - INIT_ROW * ROW;
        rotation = 0;
        initRowColumn(INIT_ROW, INIT_COL);
        fieldHeight = INIT_ROW*HEIGHT_UNIT;
        setColor(colorMain, colorSub);
        axisPuyoOutline.setColor(colorMain);
    }

    private void setDisabled() {
        enabled = false;
    }

    private void setColor(int colorMain, int colorSub) {
        this.colIndexMain = colorMain;
        this.colIndexSub = colorSub;
        this.colorMain = colorMain;
        this.colorSub = colorSub;
    }

    public int getColor(boolean main) {
        if (main) {
            return colorMain;
        }
        else {
            return colorSub;
        }
    }

    private void initRowColumn(int row, int col) {
        this.rowMain = row;
        this.columnMain = col;
        this.rowSub = row + ROTATE_POSITION[rotation][0];
        this.columnSub = col + ROTATE_POSITION[rotation][1];
    }
}
