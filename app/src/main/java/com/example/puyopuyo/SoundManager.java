package com.example.puyopuyo;

import android.graphics.Canvas;
import android.media.AudioAttributes;
import android.media.SoundPool;

import static com.example.puyopuyo.GameSurface.MAX_STREAMS;

public class SoundManager extends GameObject {
    private GameSurface gameSurface;

    private int soundMovePuyo, streamMovePuyo;
    private int soundRotatePuyo, streamRotatePuyo;
    private boolean soundPoolLoaded;
    private SoundPool soundPool;
    private boolean enabled;

    public SoundManager(GameSurface gameSurface) {
        super();

        this.gameSurface= gameSurface;
        enabled = false;

        initSoundPool();
    }

    private void initSoundPool() {
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        SoundPool.Builder builder= new SoundPool.Builder();
        builder.setAudioAttributes(audioAttributes).setMaxStreams(MAX_STREAMS);

        soundPool = builder.build();
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPoolLoaded = true;
            }
        });

        if (enabled) {
            //soundMovePuyo = soundPool.load(gameSurface.getContext(), R.raw.puyo_control_move,1);
            //soundRotatePuyo = soundPool.load(gameSurface.getContext(), R.raw.puyo_control_rotate,1);
        }
        else {
            soundPoolLoaded = false;
        }
    }

    @Override
    public void draw(Canvas canvas)  {
        lastDrawNanoTime = System.nanoTime();
    }

    @Override
    public void update() {
        super.update();
    }

    public void playMovePuyo()  {
        if (soundPoolLoaded) {
            soundPool.stop(streamMovePuyo);
            streamMovePuyo = soundPool.play(soundMovePuyo, 1f, 1f, 1, 0, 1f);
        }
    }

    public void playRotatePuyo()  {
        if (soundPoolLoaded) {
            soundPool.stop(streamRotatePuyo);
            streamRotatePuyo = soundPool.play(soundRotatePuyo, 1f, 1f, 1, 0, 1f);
        }
    }
}
