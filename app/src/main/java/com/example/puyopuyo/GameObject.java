package com.example.puyopuyo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import static android.content.ContentValues.TAG;

public abstract class GameObject { // 게임 오브젝트

    protected Bitmap image; // 이미지

    // 이미지 가로 세로 개수
    protected final int rowCount;
    protected final int colCount;

    // 총 이미지 크기
    protected final int WIDTH;
    protected final int HEIGHT;

    // 개별 이미지 크기
    protected final int width;
    protected final int height;

    // 오브젝트 위치
    protected int x;
    protected int y;

    // 이미지 스케일
    protected float xscale, yscale;

    protected long lastDrawNanoTime = -1;
    protected int deltaTime; // 이전 프레임에서부터 걸린 시간 (ms)

    public GameObject() {
        this.rowCount = 0;
        this.colCount = 0;

        this.x = 0;
        this.y = 0;

        this.WIDTH = 0;
        this.HEIGHT =  0;

        this.width = 0;
        this.height = 0;

        this.xscale = 1f;
        this.yscale = 1f;
    }

    public GameObject(Bitmap image, int rowCount, int colCount, int x, int y) {
        this.image = image;
        this.rowCount = rowCount;
        this.colCount = colCount;

        this.x = x;
        this.y = y;

        this.WIDTH = image.getWidth();
        this.HEIGHT =  image.getHeight();

        this.width = this.WIDTH / colCount;
        this.height = this.HEIGHT / rowCount;

        this.xscale = 1f;
        this.yscale = 1f;
    }

    public GameObject(Bitmap image, int rowCount, int colCount, int x, int y, float xscale, float yscale) {
        this.image = image;
        this.rowCount = rowCount; // 애니메이션용 자르기 기준
        this.colCount = colCount;

        this.x = x;
        this.y = y;

        this.WIDTH = image.getWidth(); // 전체 크기
        this.HEIGHT =  image.getHeight();

        this.width = this.WIDTH / colCount; // 한 이미지 크기
        this.height = this.HEIGHT / rowCount;

        this.xscale = xscale;
        this.yscale = yscale;
    }


    protected Bitmap createSubImageAt(int row, int col) {
        Bitmap subImage = Bitmap.createBitmap(image, col*width, row*height, width, height);
        return subImage;
    }



    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.image, x, y, null);
        lastDrawNanoTime = System.nanoTime();
    }

    public void update() { // += n * deltaTime : 1초에 n*1000 씩 증가
        long now = System.nanoTime();

        if(lastDrawNanoTime == -1) {
            lastDrawNanoTime = now;
        }
        deltaTime = (int) ((now - lastDrawNanoTime) / 1000000 );
    }

    public int getX()  {
        return this.x;
    }

    public int getY()  {
        return this.y;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}