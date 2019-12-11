package com.example.puyopuyo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import static android.content.ContentValues.TAG;


class Network {
    public static int player = -1;
    public static int max_player = -1;
    public static int current_player;
    public static boolean server = true;
    public static String hostAddress;
}

public class MainActivity extends Activity {
    Button btn_oneRoom;
    Button btn_twoRoom;
    Button btn_threeRoom;
    Button btn_fourRoom;
    Button btn_enterRoom;
    TextView tv_addr;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("7segment");
    }

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

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        btn_oneRoom = (Button)findViewById(R.id.makeOneRoomBtn);
        btn_twoRoom = (Button)findViewById(R.id.makeTwoRoomBtn);
        btn_threeRoom = (Button)findViewById(R.id.makeThreeRoomBtn);
        btn_fourRoom = (Button)findViewById(R.id.makeFourRoomBtn);
        btn_enterRoom = (Button)findViewById(R.id.enterRoomBtn);
        tv_addr = (TextView)findViewById(R.id.addrText);

        btn_oneRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //server 만들기
                Network.server = true;
                Network.max_player = 1;
                Network.player = 0;
                Network.current_player = 1;
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        btn_twoRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //server 만들기
                Network.server = true;
                Network.max_player = 2;
                Network.player = 0;
                Network.current_player = 1;
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        btn_threeRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //server 만들기
                Network.server = true;
                Network.max_player = 3;
                Network.player = 0;
                Network.current_player = 1;
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        btn_fourRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //server 만들기
                Network.server = true;
                Network.max_player = 4;
                Network.player = 0;
                Network.current_player = 1;
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        btn_enterRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //client 생성하여 접속하기
                Network.server = false;
                Network.player = -1;
                Network.hostAddress = tv_addr.getText().toString();

                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
}
