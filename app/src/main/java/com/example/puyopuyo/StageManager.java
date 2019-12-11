package com.example.puyopuyo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.ViewDebug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import static com.example.puyopuyo.Stage.*;
import static com.example.puyopuyo.FieldSize.*;
import static android.content.ContentValues.TAG;

public class StageManager extends GameObject {

    static {
        System.loadLibrary("led");
    }

    public native int ledWrite(int combo);

    private final int NEXT_NUMBER = 2;
    private GameSurface gameSurface;

    private List<ColorPair> nextPuyoPairsFactory = new ArrayList<>(); // 색패 생성 및 섞기
    private Queue<ColorPair> nextPuyoPairsQueue = new LinkedList<>(); // 색패 대기 큐
    private PuyoNext[][] puyoNexts = new PuyoNext[4][NEXT_NUMBER]; // 넥스트 뿌요 보여주기
    private ColorPair[] nextPuyoPairs = new ColorPair[NEXT_NUMBER]; // 넥스트 뿌요 색깔
    private List<Integer> garbagePuyoPosition = new ArrayList<>(); // 색패 생성 및 섞기
    private GarbageTray[] garbageTray = new GarbageTray[4]; // 예고 뿌요

    private int currentStage, nextStage; // Control Stage -> [Fall Stage -> Chain Stage] -> Garbage Stage
    private boolean checkNextStage = true;
    private boolean garbageFallTiming, garbageFalling;
    private int chainCnt;

    public StageManager(GameSurface gameSurface) {
        super();

        this.gameSurface = gameSurface;
        addShuffledColorPairs(true);
        initNextPuyo();

        for (int i = 0; i < MAX_COLUMN; i++) {
            garbagePuyoPosition.add(i);
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        Bitmap bitmap_puyo = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.spr_puyo, options);
        Bitmap bitmap_warning = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.spr_warning, options);
        for (int p = 0; p < 4; p++) {
            puyoNexts[p][0] = new PuyoNext(gameSurface, bitmap_puyo, 157 + FIELD_INTERVAL*p, 58, nextPuyoPairs[0].getColor(0), nextPuyoPairs[0].getColor(1), 1f, 1f);
            puyoNexts[p][1] = new PuyoNext(gameSurface, bitmap_puyo, 190 + FIELD_INTERVAL*p, 82, nextPuyoPairs[1].getColor(0), nextPuyoPairs[1].getColor(1), 0.8f, 0.8f);
            garbageTray[p] = new GarbageTray(bitmap_warning, 32 + FIELD_INTERVAL*p, 150);
        }
    }

    @Override
    public void draw(Canvas canvas)  {
        for (int p = 0; p < Network.max_player; p++) {
            for (int i = 0; i < NEXT_NUMBER; i++) {
                puyoNexts[p][i].draw(canvas);
                garbageTray[p].draw(canvas);
            }
        }
        lastDrawNanoTime = System.nanoTime();
    }

    @Override
    public void update() {
        super.update();

        if (currentStage == CONTROL_STAGE) {
            checkDefeat(); // 패배 체크
            if (gameSurface.gameState != 1) { // 플레이 중일때만 색패 생성
                return;
            }
            else if (checkNextStage) {
                if (gameSurface.attacked) {
                    gameSurface.networkManager.finishAttack();
                    gameSurface.attacked = false;
                }
                checkNextStage = false;
                bringNextPuyo();
                nextStage = CHAIN_STAGE;
                chainCnt = 0;
                ledWrite(chainCnt);
            }
            else {
                return;
            }
        }
        if (currentStage == FALL_STAGE) {
            if (!garbageFalling) {
                if (checkNextStage) {
                    gameSurface.networkManager.fallStage();
                    checkNextStage = false;
                    gameSurface.fallPuyo(Network.player);
                    gameSurface.destroyOutPuyo();
                } else {
                    checkAllPuyoIsFalling();
                }
            }
        }
        if (currentStage == CHAIN_STAGE) { // 체인 존재시 다시 FALL_STAGE 로, 체인 없을시 GARBAGE_STAGE 로
            if (checkNextStage) {
                checkNextStage = false;
                if (gameSurface.chainPuyo()) {
                    chainCnt++;
                    ledWrite(chainCnt);
                } else {
                    currentStage = GARBAGE_STAGE;
                }
            }
        }
        if (currentStage == GARBAGE_STAGE) {
            if (garbageFallTiming) {
                checkNextStage = true;
                garbageFalling = true;
                gameSurface.networkManager.fallGarbage(); // 떨어질 방해뿌요 개수 요청
            }
            nextStage = CONTROL_STAGE;
            currentStage = FALL_STAGE;
        }
    }

    public void fallGarbagePuyo(int garbage_amount) {
        if (garbage_amount <= 0) {
            garbageFalling = false;
            garbageFallTiming = false;
            return;
        }
        int i;
        int line = garbage_amount / MAX_COLUMN; // line 줄
        int remainder = garbage_amount % MAX_COLUMN; // 나머지

        for (i = MAX_ACTIVE_ROW; i < MAX_ACTIVE_ROW + line; i++) {
            for (int j = 0; j < MAX_COLUMN; j++) {
                gameSurface.createPuyo(Network.player, Color.COLOR_GARBAGE, i, j);
            }
        }
        Collections.shuffle(garbagePuyoPosition);
        for (int j = 0; j < remainder; j++) {
            gameSurface.createPuyo(Network.player, Color.COLOR_GARBAGE, i, garbagePuyoPosition.get(j));
        }
        garbageFalling = false;
    }

    private void checkAllPuyoIsFalling() {
        for (int i = 0; i < gameSurface.opponentPuyo.puyoMap[Network.player].length * gameSurface.opponentPuyo.puyoMap[Network.player][0].length; i++) {
            int row = i / gameSurface.opponentPuyo.puyoMap[Network.player][0].length; // 행
            int column = i % gameSurface.opponentPuyo.puyoMap[Network.player][0].length; // 열
            if (gameSurface.opponentPuyo.puyoMap[Network.player][row][column] != null) {
                if (gameSurface.opponentPuyo.puyoMap[Network.player][row][column].getIsFalling()) {
                    return;
                }
            }
        }
        checkNextStage = true;
        currentStage = nextStage;
    }

    private void addShuffledColorPairs(boolean start) { // 64개의 색패가 한 사이클
        ColorPair colorPair;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int repeat = 0; repeat < 4; repeat++) {
                    nextPuyoPairsFactory.add(new ColorPair(i + 1, j + 1));
                }
            }
        }
        if (nextPuyoPairs[0] == null) {
            Collections.shuffle(nextPuyoPairsFactory, new Random(gameSurface.shuffleSeed));
            gameSurface.shuffleSeed++;
            if (start) {
                while (checkAllDifferentColor()) {
                    Collections.shuffle(nextPuyoPairsFactory, new Random(gameSurface.shuffleSeed));
                    gameSurface.shuffleSeed++;
                }
            }
        }
        else {
            Collections.shuffle(nextPuyoPairsFactory, new Random(gameSurface.shuffleSeed));
            gameSurface.shuffleSeed++;
        }

        for (int i = 0; i < nextPuyoPairsFactory.size(); i++) {
            colorPair = nextPuyoPairsFactory.get(i);
            nextPuyoPairsQueue.add(colorPair);
            nextPuyoPairsFactory.remove(i);
        }
    }

    private void checkDefeat() {
        if (gameSurface.gameState == 1) {
            if (gameSurface.opponentPuyo.puyoMap[Network.player][11][2] != null) {
                gameSurface.gameState = 0;
                gameSurface.networkManager.defeat();
            }
        }
    }

    private boolean checkAllDifferentColor() {
        int[] color = new int[4];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                color[i*2+j] = nextPuyoPairsFactory.get(i).getColor(j);
                for (int k = 0; k < i*2+j; k++) {
                    if (color[k] == color[i*2+j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void initNextPuyo() {
        while (nextPuyoPairs[0] == null) {
            nextPuyoPairs[0] = nextPuyoPairs[1];
            nextPuyoPairs[1] = nextPuyoPairsQueue.poll();
        }
    }

    private void bringNextPuyo() {
        gameSurface.createPuyoActivePair(nextPuyoPairs[0].getColor(0), nextPuyoPairs[0].getColor(1));
        nextPuyoPairs[0] = nextPuyoPairs[1];
        nextPuyoPairs[1] = nextPuyoPairsQueue.poll();
        if (nextPuyoPairsQueue.isEmpty()) { // 큐가 비었으면 추가로 생성
            addShuffledColorPairs(false);
        }
        //if (!Network.server) {
            //gameSurface.clientThread.sendNextPuyoMessage(nextPuyoPairs[1].getColor(0), nextPuyoPairs[1].getColor(1));
            gameSurface.networkManager.nextPuyo(nextPuyoPairs[1].getColor(0), nextPuyoPairs[1].getColor(1));
            //gameSurface.networkManager.nextPuyo(puyoNexts[Network.player][1].getColor(0), puyoNexts[Network.player][1].getColor(1));
        //}
        updateNextPuyo(Network.player);
    }

    public void updateNextPuyo(int player) {
        for (int i = 0; i < NEXT_NUMBER; i++) {
            puyoNexts[player][i].setColor(nextPuyoPairs[i].getColor(0), nextPuyoPairs[i].getColor(1));
        }
    }

    public void addNextPuyo(int player, int color1, int color2) {
        puyoNexts[player][0].setColor(puyoNexts[player][1].getColor(0), puyoNexts[player][1].getColor(1));
        puyoNexts[player][1].setColor(color1, color2);
    }

    public void endControlStage() {
        currentStage = FALL_STAGE;
        checkNextStage = true;
    }

    public void endChainStage() {
        currentStage = FALL_STAGE;
        checkNextStage = true;
    }

    public int getChainCnt() {
        return chainCnt;
    }

    public void addWarningPuyo(int playerTo, int warningPuyo) {
        garbageTray[playerTo].addWarningPuyo(warningPuyo);
        /*
        if (playerBy != Network.player) {
            hashMap.put(chainNumber * 10 + playerBy, false);
        }*/
    }

    public void finishAttack() {
        garbageFallTiming = true;
    }
}

class ColorPair {
    final int main;
    final int sub;

    public ColorPair(int main, int sub) {
        this.main = main;
        this.sub = sub;
    }

    public int getColor(int num) {
        if (num == 0) {
            return main;
        }
        else {
            return sub;
        }
    }
}