package com.geekbrains.chat.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nickname;
    private static final Logger logger = LogManager.getLogger(Server.class.getName());

    public String getNickname() {
        return nickname;
    }

    public ClientHandler(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());

        ExecutorService service = Executors.newFixedThreadPool(10);
        service.execute(() -> {
            try {
                while (true) { // цикл аутентификации
                    String msg = in.readUTF();
                    logger.info("Сообщение от клиента: " + msg + "\n");
                    if (msg.startsWith("/auth ")) { // /auth login1 pass1
                        String[] tokens = msg.split(" ", 3);
                        String nickFromAuthManager = server.getAuthManager().getNicknameByLoginAndPassword(tokens[1], tokens[2]);
                        if (nickFromAuthManager != null) {
                            if (server.isNickBusy(nickFromAuthManager)) {
                                sendMsg("Данный пользователь уже в чате");
                                continue;
                            }
                            nickname = nickFromAuthManager;
                            sendMsg("/authok " + nickname);
                            server.subscribe(ClientHandler.this);
                            break;
                        } else {
                            sendMsg("Указан неверный логин/пароль");
                        }
                    }
                }
                while (true) { // цикл общения с сервером (обмен текстовыми сообщениями и командами)
                    String msg = in.readUTF();
                    logger.info("Сообщение от клиента: " + msg + "\n");
                    if (msg.startsWith("/")) {
                        if (msg.startsWith("/w ")) {
                            String[] tokens = msg.split(" ", 3); // /w user2 hello, user2
                            server.sendPrivateMsg(ClientHandler.this, tokens[1], tokens[2]);
                            continue;
                        }
                        if (msg.startsWith("/change_nick ")) {
                            String[] tokens = msg.split(" ", 2);
                            server.broadcastMsg("Пользователь " + nickname + " сменил ник на " + tokens[1], false);
                            BasicAuthManager.setNewNickname(nickname, tokens[1]);
                            nickname = tokens[1];
                            server.broadcastClientsList();
                        }
                        if (msg.equals("/end")) {
                            sendMsg("/end_confirm");
                            break;
                        }
                    } else {
                        server.broadcastMsg(nickname + ": " + msg, true);
                    }
                }
            } catch (IOException | SQLException e) {
                logger.error(e);
                //e.printStackTrace();
            } finally {
                close();
            }
        });
        service.shutdown();

       // new Thread(() -> {

       // }).start();
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            logger.error(e);
            //e.printStackTrace();
        }
    }

    public void close() {
        server.unsubscribe(this);
        nickname = null;
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                logger.error(e);
                //e.printStackTrace();
            }
        }
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                logger.error(e);
                //e.printStackTrace();
            }
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                logger.error(e);
                //e.printStackTrace();
            }
        }
    }
}
