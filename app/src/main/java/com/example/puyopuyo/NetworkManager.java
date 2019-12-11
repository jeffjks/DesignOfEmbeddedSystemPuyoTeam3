package com.example.puyopuyo;

import android.util.Log;

import java.io.IOException;

public class NetworkManager {
    private GameSurface gameSurface;
    private ServerThread serverThread;
    private ClientThread clientThread;
    public int[] warningPuyo = new int[4];

    public NetworkManager(GameSurface gameSurface) {
        this.gameSurface = gameSurface;
        if (Network.server) {
            startServer();
        }
        else {
            startClient(Network.hostAddress, 8081);
        }
    }

    private void startServer() {
        if (Network.max_player == 1) { // 싱글 플레이
            gameSurface.startGame(gameSurface.shuffleSeed);
        }
        else { // 2인 이상
            if (serverThread == null) {
                serverThread = new ServerThread(8081, gameSurface);
                serverThread.start();
            }
        }
    }

    private void stopServer() {
        try {
            if (serverThread != null) {
                serverThread.closeServer();
            }
            serverThread = null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Log.d("TAG", "Server Stopped");
        }
    }

    private void startClient(String address, int port) {
        clientThread = new ClientThread(address, port, gameSurface);
        clientThread.start();
    }


    void setScore() {
        sendMessage("[SCORE]" + Network.player + gameSurface.fieldUIScoreList.get(Network.player).getScore() + "\n");
    }

    void addPuyo(int color, int row, int col) { // 뿌요 생성
        sendMessage("[ADD]" + Network.player + "," + color + "," + row + "," + col + "\n");
    }

    void destroyPuyo(int row, int col) { // 뿌요 제거
        sendMessage("[DESTROY]" + Network.player + "," + row + "," + col + "\n");
    }

    void finishAttack() {
        sendMessage("[FINISH_ATTACK]" + Network.player + "\n");
    }

    void fallStage() {
        sendMessage("[FALL_STAGE]" + Network.player + "\n");
    }

    void nextPuyo(int color1, int color2) {
        sendMessage("[NEXT]" + Network.player + color1 + color2 + "\n");
    }

    void defeat() {
        if (Network.max_player > 1) {
            sendMessage("[DEFEAT]" + Network.player + "\n");
        }
        if (Network.server) {
            gameSurface.defeatPlayer(Network.player);
        }
    }



    public void setGarbage(int playerBy, int add) { // playerBy를 playerTo로 변환 [GARBAGE](playerTo)(Garbage)
        if (add == 0) {
            return;
        }
        if (Network.server) {
            String msg = "";
            int attack = add - warningPuyo[playerBy];

            if (attack > 0) {
                for (int p = 0; p < 4; p++) { // playerBy: By, p: To
                    if (p != playerBy) { // Attack All
                        msg += "[GARBAGE]" + p + attack + "\n";
                        gameSurface.stageManager.addWarningPuyo(p, attack);
                        warningPuyo[p] += attack;
                    }
                    else {
                        msg += "[GARBAGE]" + p + (-warningPuyo[p]) + "\n";
                        gameSurface.stageManager.addWarningPuyo(p, -warningPuyo[p]);
                        warningPuyo[p] -= add;
                    }
                }
            }
            else { // Defense
                msg += "[GARBAGE]" + playerBy + (-add) + "\n";
                gameSurface.stageManager.addWarningPuyo(playerBy, -add);
                warningPuyo[playerBy] -= add;
            }

            if (warningPuyo[playerBy] < 0) {
                warningPuyo[playerBy] = 0;
            } else if (warningPuyo[playerBy] > 720 * 6) {
                warningPuyo[playerBy] = 720 * 6;
            }
            sendMessage(msg + "\n");
        }
        else {
            sendMessage("[ATTACK]" + playerBy + add + "\n"); // ATTACK 메시지는 클라이언트만 사용 (서버가 이를 바탕으로 GARBAGE 계산)
        }
    }

    void fallGarbage() { // 떨어질 방해뿌요 개수 요청 ([GET_GARBAGE])
        if (Network.server) {
            //fallGarbage(Network.player);
            int garbage_amount = Math.min(30, warningPuyo[Network.player]); // 최대 한 번에 30개씩만 방해뿌요 낙하
            warningPuyo[Network.player] -= garbage_amount;
            sendMessage("[GARBAGE]" + Network.player + (-garbage_amount) + "\n");
            gameSurface.stageManager.addWarningPuyo(Network.player, -garbage_amount);
            gameSurface.stageManager.fallGarbagePuyo(garbage_amount);
        }
        else {
            sendMessage("[GET_GARBAGE]" + Network.player + "\n");
        }
        //gameSurface.stageManager.fallGarbagePuyo(10); // For Test
    }

    void fallGarbage(int player) { // player 의 떨어질 방해 뿌요 계산 (서버만 사용)
        int garbage_amount = Math.min(30, warningPuyo[player]); // 최대 한 번에 30개씩만 방해뿌요 낙하
        warningPuyo[player] -= garbage_amount;
        sendMessage("[FALL_GARBAGE]" + player + garbage_amount + "\n"); // broadcast
        gameSurface.stageManager.addWarningPuyo(player, -garbage_amount);
    }


    private void sendMessage(String msg) {
        if (Network.max_player > 1) {
            if (Network.server) {
                serverThread.broadcast(msg);
            } else {
                clientThread.sendMessage(msg);
            }
        }
        //else if (!msg.startsWith("[SCORE]")) {
            //Log.d("SentMSG", msg);
        //}
    }
}
