package com.geekbrains.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nickname;
    private boolean online;

    public String getNickname() {
        return nickname;
    }

    public ClientHandler(Server server, Socket socket) throws IOException {
        this.online = false;
        this.server = server;
        this.socket = socket;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {
            try {
                while (true) { // цикл аутентификации
                    String msg = in.readUTF();
                    System.out.print("Сообщение от клиента: " + msg + "\n");
                    if (msg.startsWith("/auth ")) { // /auth login1 pass1
                        String[] tokens = msg.split(" ", 3);
                        String nickFromAuthManager = server.getAuthManager().getNicknameByLoginAndPassword(tokens[1], tokens[2]);
                        if (nickFromAuthManager != null) {
                            if (server.isNickBusy(nickFromAuthManager)) {
                                sendMsg("Данный пользователь уже в чате");
                                continue;
                            }
                            nickname = nickFromAuthManager;
                            server.broadcastMsg(nickname + " в сети");
                            server.subscribe(this);
                            sendMsg("/authok " + nickname);
                            this.online = true;
                            break;
                        } else {
                            sendMsg("Указан неверный логин/пароль");
                        }
                    }
                }
                while (true) { // цикл общения с сервером (обмен текстовыми сообщениями и командами)
                    String msg = in.readUTF();
                    System.out.print("Сообщение от клиента: " + msg + "\n");
                    if (msg.startsWith("/")) {
                        if (msg.equals("/end")) {
                            sendMsg("/end_confirm");
                            break;
                        }
                    }
                    if (msg.startsWith("/w ")) {
                        String[] msgParts = msg.split(" ", 3);
                        if (!msgParts[1].equals(nickname)) {
                            if (server.isUserOnline(msgParts[1])) {
                                server.wispMsg(nickname, msgParts[1], nickname + " шепнул " + msgParts[1] + ": " + msgParts[2]);
                            }
                            else {
                                sendMsg("Пользователь " + msgParts[1] + " не в сети");
                            }
                        } else {
                            sendMsg("Невозможно отправить сообщение самому себе");
                        }
                    } else {
                        server.broadcastMsg(nickname + ": " + msg);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }).start();
    }

    public boolean isOnline() {
        return online;
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        server.broadcastMsg(nickname + " покинул чат");
        this.online = false;
        server.unsubscribe(this);
        nickname = null;
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
