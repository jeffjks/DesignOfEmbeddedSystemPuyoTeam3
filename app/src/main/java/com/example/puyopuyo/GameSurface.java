package com.example.puyopuyo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static android.content.ContentValues.TAG;
import static com.example.puyopuyo.FieldSize.*;


public class GameSurface extends SurfaceView implements SurfaceHolder.Callback { // 게임 화면

    static {
        System.loadLibrary("pushBtn");
        System.loadLibrary("7segment");
        System.loadLibrary("dotmatrix");
        System.loadLibrary("lcd");
    }

    public native int pushBtnRead();
    public native int SSegmentWrite(int score);
    public native int makeExplosion();
    public native void lcdWrite(String text, int pos);

    public GameThread gameThread;
    public static final int MAX_STREAMS = 100;

    public int gameState = -1; // -1:시작전, 0:패배, 1:플레이중, 2:승리

    public StageManager stageManager;
    private SoundManager soundManager;
    private PuyoChainingAnimation puyoChainingAnimation;
    private PuyoActivePair puyoActivePair;
    private final List<GameObject> managerObjectList = new ArrayList<GameObject>(); // 매니저 오브젝트들
    private final List<GameObject> fieldUIBackgroundList = new ArrayList<GameObject>(); // 필드 배경들
    private final List<GameObject> fieldUIList = new ArrayList<GameObject>(); // 기타 UI들 (넥스트, 필드 등)
    public final List<Score> fieldUIScoreList = new ArrayList<Score>(); // 스코어 UI
    private final List<GameObject> puyoList = new ArrayList<GameObject>(); // 필드에 있는 뿌요들
    public BitmapFactory.Options options = new BitmapFactory.Options();
    public OpponentPuyo opponentPuyo;
    public NetworkManager networkManager;
    public boolean attacked = false;
    public long shuffleSeed;

    private static final int NO_PUSH = -1, PUSH_LEFT = 0, PUSH_DOWN = 1, PUSH_RIGHT = 2;
    private static final int PUSH_ROTATE_LEFT = 3, PUSH_ROTATE_RIGHT = 4;

    private int touch_down = -1;
    private int garbageScore, garbagePuyo;
    private int defeatedPlayer;

    private int size_x, size_y;
    private final String showPlayerText = "1   2   3   4   ";
    private String showPlayerState = "O   O   O   O   ";

    public GameSurface(Context context, int x, int y)  {
        super(context);
        options.inScaled = false;

        if (Network.server) {
            //shuffleSeed = System.currentTimeMillis();
        }

        this.setFocusable(true);

        this.size_x = x;
        this.size_y = y;
        this.getHolder().addCallback(this);
    }

    public void startGame(long seed) {
        Log.d("START", "startGame");
        lcdWrite(showPlayerText, 0);
        lcdWrite(showPlayerState, 16);
        //shuffleSeed = seed;
        gameState = 1;
        //stageManager = new StageManager(this);
        //managerObjectList.add(stageManager);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (event.getY() < 300*SCREEN_RATIO) {
                if (event.getX() > 640 * SCREEN_RATIO) {
                    puyoActivePair.move(1);
                    return true;
                } else if (event.getX() > 320 * SCREEN_RATIO) {
                    touch_down = 1;
                    return true;
                } else {
                    puyoActivePair.move(-1);
                    return true;
                }
            }
            else {
                if (event.getX() > 480 * SCREEN_RATIO) {
                    puyoActivePair.rotate(1);
                    return true;
                } else {
                    puyoActivePair.rotate(-1);
                    return true;
                }
            }
        }
        else if (event.getAction() == MotionEvent.ACTION_UP) {
            touch_down = -1;
            return true;
        }
        return false;
    }

    public void defeatPlayer(int player) {
        defeatedPlayer++;
        // TODO. 패배한 player LCD
        Log.d("TAG", ""+defeatedPlayer + ","+Network.max_player);
        if (defeatedPlayer >= Network.max_player - 1) {
            if (gameState != 0) {
                gameState = 2;
            }
        }

        String text = "X";
        switch(player){
            case 0:
                lcdWrite(text, 16);
                break;
            case 1:
                lcdWrite(text, 20);
                break;
            case 2:
                lcdWrite(text, 24);
                break;
            case 3:
                lcdWrite(text, 28);
        }
    }

    public void update() {
        //Log.d("TAG", networkManager.warningPuyo[0]+","+networkManager.warningPuyo[1]+","+networkManager.warningPuyo[2]+","+networkManager.warningPuyo[3]);
        for(GameObject gameObject: managerObjectList) {
            gameObject.update();
        }
        if (stageManager != null)
            stageManager.update();
        for(GameObject gameObject: fieldUIBackgroundList) {
            gameObject.update();
        }
        for(GameObject puyo: puyoList) {
            puyo.update();
        }
        if (puyoActivePair != null) {
            puyoActivePair.update();
            puyoActivePair.moveDown(touch_down);
        }
        for(GameObject gameObject: fieldUIList) {
            gameObject.update();
        }
        for (Score score: fieldUIScoreList) {
            score.update();
        }

        if (gameState == 1) {
            int cmd = pushBtnRead();
            if (cmd != NO_PUSH) {
                switch (cmd) {
                    case PUSH_LEFT:
                        puyoActivePair.move(-1);
                        puyoActivePair.moveDown(0);
                        break;
                    case PUSH_DOWN:
                        puyoActivePair.moveDown(1);
                        break;
                    case PUSH_RIGHT:
                        puyoActivePair.move(1);
                        puyoActivePair.moveDown(0);
                        break;
                    case PUSH_ROTATE_LEFT:
                        puyoActivePair.rotate(-1);
                        puyoActivePair.moveDown(0);
                        break;
                    case PUSH_ROTATE_RIGHT:
                        puyoActivePair.rotate(1);
                        puyoActivePair.moveDown(0);
                        break;
                }
            } else if (touch_down != 1) {
                puyoActivePair.moveDown(0);
            }
        }
        SSegmentWrite(fieldUIScoreList.get(Network.player).getScore());
    }

    @Override
    public void draw(Canvas canvas)  {
        super.draw(canvas);
        for(GameObject gameObject: fieldUIBackgroundList)  {
            gameObject.draw(canvas);
        }
        for(GameObject puyo: puyoList) {
            puyo.draw(canvas);
        }
        if (puyoActivePair != null) {
            puyoActivePair.draw(canvas);
        }
        for(GameObject gameObject: fieldUIList)  {
            gameObject.draw(canvas);
        }
        for (Score score: fieldUIScoreList) {
            score.draw(canvas);
        }
        for(GameObject gameObject: managerObjectList) {
            gameObject.draw(canvas);
        }
        if (stageManager != null)
            stageManager.draw(canvas);
    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        soundManager = new SoundManager(this);
        managerObjectList.add(soundManager);
        puyoChainingAnimation = new PuyoChainingAnimation(this);
        managerObjectList.add(puyoChainingAnimation);
        stageManager = new StageManager(this);
        managerObjectList.add(stageManager);
        opponentPuyo = new OpponentPuyo(this, BitmapFactory.decodeResource(getResources(), R.drawable.spr_puyo, options));
        managerObjectList.add(opponentPuyo);

        for (int i = 0; i < 4; i++) {
            createFieldUI(R.drawable.ui_field_background, fieldUIBackgroundList, 24 + FIELD_INTERVAL*i, 300);
            createFieldUI(R.drawable.ui_field_frame, fieldUIList, 24 + FIELD_INTERVAL*i, 300);
            createFieldUI(R.drawable.ui_field_next, fieldUIList, 24 + FIELD_INTERVAL*i + 128, 70);
            createScoreUI(R.drawable.ui_field_score, fieldUIScoreList, i, 64 + FIELD_INTERVAL*i, 510);
        }

        options.inScaled = false;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.spr_puyo, options);
        puyoActivePair = new PuyoActivePair(this, soundManager, bitmap);

        this.gameThread = new GameThread(this, holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();

        networkManager = new NetworkManager(this);
    }

    private void createFieldUI(int id, List<GameObject> list, int x, int y) { // UI 오브젝트 생성
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id, options);
        list.add(new FieldUI(this, bitmap, x, y));
    }

    private void createScoreUI(int id, List<Score> list, int player, int x, int y) { // Score UI 오브젝트 생성
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id, options);
        list.add(new Score(this, player, bitmap, x, y));
    }

    public void createPuyoActivePair(int colorMain, int colorSub) {
        if (colorMain < 1 || 4 < colorMain) { // 1:red, 2:green, 3:blue, 4:yellow
            return;
        }
        if (colorSub < 1 || 4 < colorSub) { // 1:red, 2:green, 3:blue, 4:yellow
            return;
        }
        puyoActivePair.setEnabled(colorMain, colorSub);
    }

    public void createPuyo(int player, int color, int row, int col) {
        try {
            if (0 < color && color <= 5) { // 1:red, 2:green, 3:blue, 4:yellow, 5:garbage
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.spr_puyo, options);
                opponentPuyo.puyoMap[player][row][col] = new Puyo(this, bitmap, player, color, row, col);
                //puyoArray[row][col] = new Puyo(this, bitmap, row, col, color);

                puyoList.add(opponentPuyo.puyoMap[player][row][col]);

                if (!(row >= MAX_ACTIVE_ROW && color != 5)) {
                    if (player == Network.player) {
                        networkManager.addPuyo(color, row, col);
                    }
                }
            }
        }
        catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public void destroyPuyo(int player, int row, int col) {
        puyoList.remove(opponentPuyo.puyoMap[player][row][col]);
        opponentPuyo.puyoMap[player][row][col] = null;

        if (row < MAX_ACTIVE_ROW) {
            if (player == Network.player) {
                networkManager.destroyPuyo(row, col);
            }
        }
    }

    public void fallPuyo(int player) {
        for (int c = 0; c < MAX_COLUMN; c++) {
            int empty = 0;
            for (int r = 0; r < MAX_ROW; r++) {
                if (opponentPuyo.puyoMap[player][r][c] == null) {
                    empty++;
                }
                else if (empty > 0) {
                    int new_r = r-empty;
                    opponentPuyo.puyoMap[player][new_r][c] = opponentPuyo.puyoMap[player][r][c];
                    opponentPuyo.puyoMap[player][new_r][c].setRowColumn(new_r, c);
                    opponentPuyo.puyoMap[player][r][c] = null;
                }
            }
        }
    }

    public void destroyOutPuyo() {
        for (int c = 0; c < MAX_COLUMN; c++) {
            for (int r = MAX_ACTIVE_ROW; r < MAX_ROW; r++) {
                if (opponentPuyo.puyoMap[Network.player][r][c] != null) {
                    destroyPuyo(Network.player, r, c);
                }
            }
        }
    }

    public boolean chainPuyo() {
        boolean result = false;
        boolean visited[][] = new boolean[MAX_ROW][MAX_COLUMN];

        // 점수 계산
        int score_puyo_number = 0; // 지운 개수
        int[] score_connection_number = {0, 0, 0, 0}; // 색상별 연결 개수
        int color_number = 0; // 색상 수

        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> tmp = new LinkedList<>();
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int c = 0; c < MAX_COLUMN; c++) {
            for (int r = 0; r < MAX_ACTIVE_ROW - 1; r++) {
                if (opponentPuyo.puyoMap[Network.player][r][c] == null)
                    break;
                else if (opponentPuyo.puyoMap[Network.player][r][c].getColor() == Color.COLOR_GARBAGE)
                    continue;
                else if (visited[r][c])
                    continue;

                visited[r][c] = true;
                int[] arr = {r, c};
                queue.offer(arr);
                tmp.offer(arr);
                int puyoColor = opponentPuyo.puyoMap[Network.player][r][c].getColor();

                while(!queue.isEmpty()) {
                    int[] rc = queue.poll();

                    for (int i = 0; i < 4; i++) {
                        int dr = rc[0] + dy[i];
                        int dc = rc[1] + dx[i];

                        if (dr >= 0 && dr < MAX_ACTIVE_ROW && dc >= 0 && dc < MAX_COLUMN) {
                            if (opponentPuyo.puyoMap[Network.player][dr][dc] != null) {
                                if (!visited[dr][dc]) {
                                    if (puyoColor == opponentPuyo.puyoMap[Network.player][dr][dc].getColor()) {
                                        visited[dr][dc] = true;
                                        int[] arrD = new int[2];
                                        arrD[0] = dr;
                                        arrD[1] = dc;
                                        queue.offer(arrD);
                                        tmp.offer(arrD);
                                    }
                                }
                            }
                        }
                    }
                }

                if (tmp.size() >= 4) {
                    int[] e = tmp.element();
                    makeExplosion();
                    score_puyo_number += tmp.size();
                    score_connection_number[opponentPuyo.puyoMap[Network.player][e[0]][e[1]].getColor() - 1] += tmp.size();

                    while(!tmp.isEmpty()) {
                        int[] t = tmp.poll();

                        for (int i = 0; i < 4; i++) { // 주변 방해뿌요 제거
                            int dr = t[0] + dy[i];
                            int dc = t[1] + dx[i];

                            if (dr >= 0 && dr < MAX_ACTIVE_ROW && dc >= 0 && dc < MAX_COLUMN) {
                                if (opponentPuyo.puyoMap[Network.player][dr][dc] != null) {
                                    if (!visited[dr][dc]) {
                                        if (opponentPuyo.puyoMap[Network.player][dr][dc].getColor() == Color.COLOR_GARBAGE) {
                                            visited[dr][dc] = true;
                                            puyoChainingAnimation.addPuyo(opponentPuyo.puyoMap[Network.player][dr][dc]);
                                            opponentPuyo.puyoMap[Network.player][dr][dc].remove();
                                        }
                                    }
                                }
                            }
                        }
                        puyoChainingAnimation.addPuyo(opponentPuyo.puyoMap[Network.player][t[0]][t[1]]);
                        opponentPuyo.puyoMap[Network.player][t[0]][t[1]].remove();
                    }
                    result = true;
                }
                else {
                    while(!tmp.isEmpty()) {
                        tmp.poll();
                    }
                }
            }
        }

        for(Integer integer: score_connection_number) {
            if (integer > 0) {
                color_number++;
            }
        }

        if (result) {
            int chain_count = stageManager.getChainCnt();
            fieldUIScoreList.get(Network.player).addScore(score_puyo_number, chain_count, score_connection_number, color_number);
            puyoChainingAnimation.startChainingAnimation();
        }
        return result; // 체인이 존재하면 true 리턴. 없으면 false 리턴.
    }

    public void chainAttack() {
        if (garbagePuyo > 0) {
            attacked = true;
            networkManager.setGarbage(Network.player, garbagePuyo);
            //stageManager.addWarningPuyo(-garbagePuyo); // Defense
            garbagePuyo = 0;
        }
    }

    public int getColumnHeight(int col) {
        int row = 0;
        int columnHeight = 0;
        while(opponentPuyo.puyoMap[Network.player][row][col] != null) {
            row++;
            columnHeight++;
        }
        return columnHeight;
    }

    public boolean isPuyoExists(int row, int col) { // 뿌요가 존재하거나 벽이면 true 리턴
        try {
            if (opponentPuyo.puyoMap[Network.player][row][col] == null) {
                return false;
            } else {
                return true;
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry= true;
        while(retry) {
            try {
                this.gameThread.setRunning(false);

                // Parent thread must wait until the end of GameThread.
                this.gameThread.join();
            } catch (InterruptedException e)  {
                e.printStackTrace();
            }
            retry = true;
        }
    }

    public void calculateGarbagePuyo(int add) {
        garbageScore += add;
        int garbagePuyo = garbageScore / 70;
        garbageScore -= garbagePuyo*70;
        this.garbagePuyo += garbagePuyo;
    }
}