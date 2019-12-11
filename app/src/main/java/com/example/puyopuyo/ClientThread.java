package com.example.puyopuyo;

import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Scanner;

import static com.example.puyopuyo.FieldSize.*;

public class ClientThread extends Thread {
    private GameSurface gameSurface;
    private static String address;
    private static int port;
    private static final int TIMEOUT = 5000;
    private PrintWriter out;

    private Socket mSocket;

    public ClientThread(String address, int port, GameSurface gameSurface) {
        this.address = address;
        this.port = port;
        this.gameSurface = gameSurface;
    }

    @Override
    public void run() {
        try {
            mSocket = new Socket(address, port);
            mSocket.setSoTimeout(TIMEOUT);

            final BufferedReader in = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
            out = new PrintWriter(mSocket.getOutputStream(), true);

            InputThread inputThread = new InputThread(gameSurface, mSocket, in);
            inputThread.start();

            sendConnectionMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendMessage(String msg) {
        Log.d("SENT", msg);
        out.write(msg);
        out.flush();
        //this.msg += msg;
    }

    private void sendConnectionMessage() {
        sendMessage("[CONNECTION]\n");
    }
}

 class InputThread extends Thread {
    private GameSurface gameSurface;
    private Socket socket;
    private BufferedReader in;
    private boolean mShouldStop = false;

    public InputThread(GameSurface gameSurface, Socket socket, BufferedReader in) {
        this.gameSurface = gameSurface;
        this.socket = socket;
        this.in = in;
    }

    @Override
    public void run() {
        String reply;
        try {
            while (!mShouldStop) {
                reply = in.readLine();
                //while ((reply = in.readLine()) != null) {
                //if (!reply.startsWith("[SCORE]") && reply.length() > 1) {
                    //Log.d("REPLY", "r: " + reply);
                //}

                if (reply.startsWith("[SCORE]")) {
                    int player = reply.charAt(7) - 48;
                    int score = Integer.parseInt(reply.substring(8));
                    if (player != Network.player) {
                        gameSurface.fieldUIScoreList.get(player).setScore(score);
                    }
                } else if (reply.startsWith("[CONNECTION]")) {
                    int player = reply.charAt(12) - 48;
                    if (player == 0) { // 풀방
                        closeConnection();
                    } else {
                        Network.player = player;
                        Network.max_player = reply.charAt(14) - 48;
                        //Network.current_player++;
                    }
                } else if (reply.startsWith("[ADD]")) {
                    String[] value = reply.substring(5).split(",");
                    int player = Integer.parseInt(value[0]);
                    int color = Integer.parseInt(value[1]);
                    int row = Integer.parseInt(value[2]);
                    int col = Integer.parseInt(value[3]);
                    if (player != Network.player) {
                        gameSurface.createPuyo(player, color, row, col);
                    }
                } else if (reply.startsWith("[DESTROY]")) {
                    String[] value = reply.substring(9).split(",");
                    int player = Integer.parseInt(value[0]);
                    int row = Integer.parseInt(value[1]);
                    int col = Integer.parseInt(value[2]);
                    if (player != Network.player) {
                        gameSurface.destroyPuyo(player, row, col);
                    }
                } else if (reply.startsWith("[NEXT]")) {
                    int player = reply.charAt(6) - 48;
                    int color1 = reply.charAt(7) - 48;
                    int color2 = reply.charAt(8) - 48;
                    if (player != Network.player) {
                        gameSurface.stageManager.addNextPuyo(player, color1, color2);
                    }
                } else if (reply.startsWith("[GARBAGE]")) {
                    Log.d("REPLY", "r: " + reply);
                    int playerTo = reply.charAt(9) - 48;
                    int garbage = Integer.parseInt(reply.substring(10));
                    gameSurface.stageManager.addWarningPuyo(playerTo, garbage);
                } else if (reply.startsWith("[FALL_GARBAGE]")) {
                    Log.d("REPLY", "r: " + reply);
                    int player = reply.charAt(14) - 48;
                    int garbage = Integer.parseInt(reply.substring(15));
                    gameSurface.stageManager.addWarningPuyo(player, -garbage);
                    if (player == Network.player) {
                        gameSurface.stageManager.fallGarbagePuyo(garbage);
                    }
                } else if (reply.startsWith("[FINISH_ATTACK]")) {
                    int player = reply.charAt(15) - 48;
                    if (player != Network.player) {
                        gameSurface.stageManager.finishAttack();
                    }
                } else if (reply.startsWith("[FALL_STAGE]")) {
                    int player = reply.charAt(12) - 48;
                    if (player != Network.player) {
                        gameSurface.fallPuyo(player);
                    }
                } else if (reply.startsWith("[START]")) {
                    long seed = Long.parseLong(reply.substring(7));
                    gameSurface.startGame(seed);
                } else if (reply.startsWith("[DEFEAT]")) {
                    Log.d("DEFEAT", "r: " + reply);
                    int player = reply.charAt(8) - 48;
                    if (player != Network.player) {
                        gameSurface.defeatPlayer(player);
                    }
                }
                //Thread.sleep(100);
                //}
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            mShouldStop = true;
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // ignore
                }
            }
            socket = null;
        }
    }

    public void closeConnection() throws IOException {
        mShouldStop = true;
        if (socket != null) {
            socket.close();
        }
        socket = null;
        Log.d("TAG", "CLOSE CONNECTION");
    }
}
