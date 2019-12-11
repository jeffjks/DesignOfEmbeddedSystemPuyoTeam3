package com.example.puyopuyo;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class ServerThread extends Thread {
    private GameSurface gameSurface;
    private static int GIVEN_PORT;
    private ServerSocket serverSocket;
    private HashMap<Integer, Object> hashMap = new HashMap<Integer, Object>();

    public ServerThread(int port, GameSurface gameSurface) {
        GIVEN_PORT = port;
        this.gameSurface = gameSurface;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(GIVEN_PORT);
            //Broadcaster broadcaster = new Broadcaster(hashMap);
            //broadcaster.start();

            while (true) {
                if (Network.current_player < Network.max_player) {
                    Socket connection = serverSocket.accept();
                    Thread worker = new SimpleWorkerThread(this, connection, hashMap, gameSurface);
                    worker.start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeServer() throws IOException {
        if (serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
        }
        serverSocket = null;
    }

    public void broadcast(String msg) {
        synchronized (hashMap) {
            Collection<Object> collection = hashMap.values();
            Iterator<?> iterator = collection.iterator();
            while (iterator.hasNext()) {
                PrintWriter pw = (PrintWriter)iterator.next();
                pw.write(msg + "\n");
                pw.flush();
            }
        }
    }
}

class SimpleWorkerThread extends Thread {
    private Socket socket;
    private GameSurface gameSurface;
    private ServerThread serverThread;
    private BufferedReader in;
    private int player;
    final private HashMap<Integer, Object> hashMap;

    SimpleWorkerThread(ServerThread serverThread, Socket socket, HashMap<Integer, Object> hashMap, GameSurface gameSurface) {
        this.serverThread = serverThread;
        this.socket = socket;
        this.hashMap = hashMap;
        this.gameSurface = gameSurface;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            player = Network.current_player;
            String connection = in.readLine();
            Log.d("SERVER WORKER THREAD", "CONNECTION: " + connection + "::");

            if (player <= Network.max_player) {
                out.write("[CONNECTION]" + player + "," + Network.max_player + "\n");
                out.flush();
                synchronized (hashMap) {
                    hashMap.put(player, out);
                }
                Network.current_player++;

                if (Network.current_player == Network.max_player) {
                    broadcast("[START]"+gameSurface.shuffleSeed+"\n");
                    gameSurface.startGame(gameSurface.shuffleSeed);
                }
            } else {
                out.write("[CONNECTION]0," + Network.max_player + "\n");
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String msg;

            while (true) {
                while ((msg = in.readLine()) != null) {

                    if (!msg.startsWith("[SCORE]")) {
                        Log.d("SERVER WORKER THREAD", "client: " + msg + " sent data");
                    }

                    if (msg.startsWith("[SCORE]")) {
                        int player = msg.charAt(7) - 48;
                        int score = Integer.parseInt(msg.substring(8));
                        gameSurface.fieldUIScoreList.get(player).setScore(score);
                        broadcast(msg + "\n");
                        //broadcast("[SCORE]" + Network.player + gameSurface.fieldUIScoreList.get(Network.player).getScore() + "\n");
                    } else if (msg.startsWith("[ADD]")) {
                        String[] value = msg.substring(5).split(",");
                        int player = Integer.parseInt(value[0]);
                        int color = Integer.parseInt(value[1]);
                        int row = Integer.parseInt(value[2]);
                        int col = Integer.parseInt(value[3]);
                        gameSurface.createPuyo(player, color, row, col);
                        broadcast(msg + "\n");
                    } else if (msg.startsWith("[DESTROY]")) {
                        String[] value = msg.substring(9).split(",");
                        int player = Integer.parseInt(value[0]);
                        int row = Integer.parseInt(value[1]);
                        int col = Integer.parseInt(value[2]);
                        gameSurface.destroyPuyo(player, row, col);
                        broadcast(msg + "\n");
                    } else if (msg.startsWith("[NEXT]")) {
                        int player = msg.charAt(6) - 48;
                        int color1 = msg.charAt(7) - 48;
                        int color2 = msg.charAt(8) - 48;
                        gameSurface.stageManager.addNextPuyo(player, color1, color2);
                        broadcast(msg + "\n");
                    } else if (msg.startsWith("[ATTACK]")) {
                        int playerBy = msg.charAt(8) - 48;
                        int garbage = Integer.parseInt(msg.substring(9));
                        gameSurface.networkManager.setGarbage(playerBy, garbage);
                    } else if (msg.startsWith("[GET_GARBAGE]")) {
                        int player = msg.charAt(13) - 48;
                        gameSurface.networkManager.fallGarbage(player); // networkManager 에게 방해 뿌요 개수 요청
                    } else if (msg.startsWith("[FALL_STAGE]")) {
                        int player = msg.charAt(12) - 48;
                        gameSurface.fallPuyo(player);
                        broadcast(msg + "\n");
                    } else if (msg.startsWith("[FINISH_ATTACK]")) {
                        gameSurface.stageManager.finishAttack();
                        broadcast(msg + "\n");
                    } else if (msg.startsWith("[DEFEAT]")) {
                        int player = msg.charAt(8) - 48;
                        gameSurface.defeatPlayer(player);
                        broadcast(msg + "\n");
                    }
                }
                //Thread.sleep(100);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void broadcast(String msg) {
        if (!msg.startsWith("[SCORE]")) {
            Log.d("Broadcast", msg);
        }
        synchronized (hashMap) {
            Collection<Object> collection = hashMap.values();
            Iterator<?> iterator = collection.iterator();
            while (iterator.hasNext()) {
                PrintWriter pw = (PrintWriter)iterator.next();
                pw.write(msg);
                pw.flush();
            }
        }
    }
}